package burhanfess.displays;

import burhanfess.exceptions.InvalidCredentialsException;
import burhanfess.menfess.Menfess;
import burhanfess.services.AdminService;
import burhanfess.services.AdminServiceImpl;
import burhanfess.users.Admin;
import burhanfess.users.comparators.UserIdComparator;
import burhanfess.users.comparators.UserUsernameComparator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdminDisplay implements Display<Admin> {
    private final AdminService svc;
    private final Admin admin;

    public AdminDisplay(Admin admin) {
        this.admin = admin;
        this.svc = new AdminServiceImpl();
    }

    @Override
    public void showHeader() {
        System.out.println("Halo, Admin " + admin.getUsername() + "!");
        showCurrentDate();
        System.out.println("--------------------------------------------------------------------------------");
    }

    @Override
    public void showMenu() {
        System.out.println("Silakan pilih salah satu opsi berikut");
        System.out.println("1. Lihat daftar pengguna");
        System.out.println("2. Tambah admin");
        System.out.println("3. Reset password pengguna");
        System.out.println("4. Sembunyikan menfess");
        System.out.println("5. Tampilkan menfess");
        System.out.println("6. Hapus akun cosmic");
        System.out.println("7. Buat logfile sistem");
        System.out.println("8. Statistik user");
        System.out.println("9. Logout");
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
                        System.out.println("Silakan pilih opsi pengurutan:");
                        System.out.println("1. Berdasarkan ID");
                        System.out.println("2. Berdasarkan username");
                        System.out.print("Masukkan pilihan: ");
                        String s = scanner.nextLine();
                        List<burhanfess.users.User> users;
                        if ("1".equals(s)) {
                            users = svc.getAllUsers(new UserIdComparator());
                            System.out.println("Daftar pengguna diurutkan berdasarkan ID:");
                        } else if ("2".equals(s)) {
                            users = svc.getAllUsers(new UserUsernameComparator());
                            System.out.println("Daftar pengguna diurutkan berdasarkan username:");
                        } else {
                            System.out.println("Input tidak valid. Silakan coba lagi.");
                            break;
                        }
                        users.forEach(u -> System.out.println(u));
                    }
                    case 2 -> {
                        System.out.println("Silakan masukkan username dan password untuk admin baru:");
                        System.out.print("Username: ");
                        String u = scanner.nextLine();
                        System.out.print("Password: ");
                        String p = scanner.nextLine();
                        svc.addAdmin(u, p);
                        System.out.println("Admin '" + u + "' berhasil ditambahkan");
                    }
                    case 3 -> {
                        System.out.print("Masukkan username pengguna yang passwordnya ingin direset: ");
                        String u2 = scanner.nextLine();
                        System.out.print("Masukkan password baru: ");
                        String np = scanner.nextLine();
                        svc.resetPassword(u2, np);
                        System.out.println("Password berhasil direset.");
                    }
                    case 4 -> {
                        List<Menfess> list = svc.getAllUnhiddenMenfesses();
                        System.out.println("Daftar menfess yang ditampilkan:");
                        list.forEach(m -> System.out.println(m));
                        System.out.print("Masukkan ID menfess yang ingin disembunyikan: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        svc.hideMenfess(id);
                        System.out.println("Menfess berhasil disembunyikan.");
                    }
                    case 5 -> {
                        List<Menfess> list = svc.getAllHiddenMenfesses();
                        System.out.println("Daftar menfess yang disembunyikan:");
                        list.forEach(m -> System.out.println(m));
                        System.out.print("Masukkan ID menfess yang ingin ditampilkan: ");
                        int id2 = Integer.parseInt(scanner.nextLine());
                        svc.unhideMenfess(id2);
                        System.out.println("Menfess berhasil ditampilkan.");
                    }
                    case 6 -> {
                        System.out.print("Masukkan username akun yang ingin dihapus: ");
                        String usernameHapus = scanner.nextLine();
                        try {
                            svc.deleteUser(usernameHapus);
                            System.out.println("Akun '" + usernameHapus + "' berhasil dihapus.");
                        } catch (InvalidCredentialsException e) {
                            System.out.println("Gagal menghapus akun: " + e.getMessage());
                        }
                    }
                    case 7 -> {
                        File logFile = new File("logFile.txt");
                        try (FileWriter writer = new FileWriter(logFile)) {
                            for (var entry : UnauthorizedDisplay.getFormatLogin().entrySet()) {
                                writer.write(entry.getValue() + ":" + entry.getKey() + "\n");
                            }
                            System.out.println("Data berhasil disimpan ke file: " + logFile.getName());
                        } catch (IOException e) {
                            System.out.println("Terjadi kesalahan saat menyimpan file: " + e.getMessage());
                        }
                    }
                    case 8 -> svc.showUserStatistics();
                    case 9 -> {
                        System.out.println("Kamu telah berhasil logout.");
                        System.out.println("--------------------------------------------------------------------------------");
                        showFooter();
                        return null;
                    }
                    default -> System.out.println("Input tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka yang sesuai.");
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
    public Admin getCurrentUser() {
        return this.admin;
    }
}