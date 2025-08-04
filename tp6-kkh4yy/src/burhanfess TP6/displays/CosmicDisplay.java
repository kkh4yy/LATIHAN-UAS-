package burhanfess.displays;

import burhanfess.exceptions.MenfessNotFoundException;
import burhanfess.menfess.Menfess;
import burhanfess.services.CosmicService;
import burhanfess.services.CosmicServiceImpl;
import burhanfess.users.Cosmic;
import burhanfess.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CosmicDisplay implements Display<Cosmic> {
    private final CosmicService svc;
    private final Cosmic cosmic;

    public CosmicDisplay(Cosmic cosmic) {
        this.cosmic = cosmic;
        this.svc = new CosmicServiceImpl(cosmic);
    }

    @Override
    public void showHeader() {
        System.out.println("Halo, Cosmic " + cosmic.getUsername() + "!");
        showCurrentDate();
        System.out.println("--------------------------------------------------------------------------------");
    }

    @Override
    public void showMenu() {
        System.out.println("Silakan pilih salah satu opsi berikut");
        System.out.println("1. Mengirim satu menfess");
        System.out.println("2. Melihat menfess publik");
        System.out.println("3. Melihat menfess yang kamu kirim");
        System.out.println("4. Melihat menfess yang kamu terima");
        System.out.println("5. Hapus Menfess saya");
        System.out.println("6. Leaderboard Menfess");
        System.out.println("7. Ubah password");
        System.out.println("8. Logout");
        System.out.print("Masukkan pilihan: ");
    }

    @Override
    public String runAndGetUsername() {
        while (true) {
            showHeader();
            showMenu();
            String in = scanner.nextLine();
            try {
                int choice = Integer.parseInt(in);
                switch (choice) {
                    case 1 -> {
                        System.out.println("Masukkan isi menfess yang ingin kamu kirim:");
                        String content = scanner.nextLine();
                        System.out.println("Silakan pilih tipe menfess yang ingin dikirim.");
                        System.out.println("1. Curhat");
                        System.out.println("2. Promosi");
                        System.out.println("3. Confession");
                        System.out.print("Masukkan tipe menfess: ");
                        String t = scanner.nextLine();
                        switch (t) {
                            case "1" -> svc.sendCurhatFess(content);
                            case "2" -> svc.sendPromosiFess(content);
                            case "3" -> {
                                System.out.print("Masukkan username yang ingin kamu kirimkan menfess: ");
                                String recv = scanner.nextLine();
                                svc.sendConfessFess(content, recv);
                            }
                            default -> throw new RuntimeException("Pilihan tidak valid. Silakan coba lagi.");
                        }
                        System.out.println("Menfess berhasil dikirim.");
                    }
                    case 2 -> {
                        List<Menfess> list = svc.getAllUnhiddenMenfesses();
                        System.out.println("Daftar menfess bersifat publik:");
                        list.forEach(m -> System.out.println(m));
                    }
                    case 3 -> {
                        List<Menfess> list = svc.getAllSentMenfesses();
                        System.out.println("Daftar menfess yang kamu kirim:");
                        list.forEach(m -> System.out.println(m));
                    }
                    case 4 -> {
                        List<Menfess> list = svc.getAllReceivedMenfesses();
                        System.out.println("Daftar menfess yang kamu terima:");
                        list.forEach(m -> System.out.println(m));
                    }
                    case 5 -> {
                        List<Menfess> list = svc.getAllSentMenfesses();
                        if (list.isEmpty()) {
                            System.out.println("Kamu belum mengirimkan menfess apapun.");
                            break;
                        }
                        System.out.println("Daftar menfess yang kamu kirim:");
                        list.forEach(m -> System.out.println(m));

                        System.out.print("Masukan ID menfess yang ingin dihapus : ");
                        try {
                            int idHapus = Integer.parseInt(scanner.nextLine());
                            boolean berhasil = svc.hapusMenfess(idHapus);
                            if (berhasil) {
                                System.out.println("Menfess berhasil dihapus.");
                            }
                        } catch (MenfessNotFoundException e) {
                            System.out.println("Gagal menghapus menfess: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("Input ID tidak valid. Masukkan angka.");
                        }
                    }
                    case 6 -> {
                        Map<User, Integer> leaderboard = svc.getLeaderboardMenfess();
                        if (leaderboard.isEmpty()) {
                            System.out.println("Belum ada fess yang dikirimkan.");
                        } else {
                            System.out.println("Leaderboard Pengirim Fess:");
                            List<Map.Entry<User, Integer>> entries = new ArrayList<>(leaderboard.entrySet());
                            entries.sort((e1, e2) -> {
                                int cmp = e2.getValue().compareTo(e1.getValue());
                                if (cmp == 0) {
                                    return e1.getKey().getUsername()
                                             .compareTo(e2.getKey().getUsername());
                                }
                                return cmp;
                            });
                            int rank = 0, jumlahFessLogin = 0, idx = 1;
                            for (var entry : entries) {
                                if (idx <= 5) {
                                    System.out.println(idx + ". " + entry.getKey().getUsername()
                                                       + " - " + entry.getValue() + " fess");
                                }
                                if (entry.getKey().getUsername().equals(cosmic.getUsername())) {
                                    rank = idx;
                                    jumlahFessLogin = entry.getValue();
                                }
                                idx++;
                            }
                            System.out.println("Rank anda: " + rank);
                            System.out.println("Jumlah menfess dikirim: " + jumlahFessLogin);
                        }
                    }
                    case 7 -> {
                        System.out.print("Masukkan password baru: ");
                        String np = scanner.nextLine();
                        svc.changePassword(np);
                        System.out.println("Password berhasil diubah.");
                    }
                    case 8 -> {
                        System.out.println("Kamu telah berhasil logout.");
                        System.out.println("--------------------------------------------------------------------------------");
                        showFooter();
                        return null;
                    }
                    default -> System.out.println("Input tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void showFooter() {
        System.out.println("BurhanFess - 2025");
        System.out.println("Created by Burhan");
    }

    @Override
    public Cosmic getCurrentUser() {
        return this.cosmic;
    }
}
