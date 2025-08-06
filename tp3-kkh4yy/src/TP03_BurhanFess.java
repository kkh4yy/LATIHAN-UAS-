import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class TP03_BurhanFess {
    static int[][] followMatrix;
    static Scanner scanner = new Scanner(System.in);
    static int jumlahUser;
    static User loggedInUser;
    public static void main(String[] args) {

        System.out.print("Berapa user yang ingin didaftarkan? ");
        jumlahUser = Integer.parseInt(scanner.nextLine());
        User[] users = new User[jumlahUser];
        followMatrix = new int[jumlahUser][jumlahUser];
        for (int i = 0; i < jumlahUser; i++) {
            System.out.print("Masukkan username user dengan ID " + i + ": ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password user dengan ID " + i + ": ");
            String password = scanner.nextLine();
            users[i] = new User(i, username, password);
        }

        System.out.println();
        System.out.println("Telah dibuat " + jumlahUser + " user dengan masing-masing ID dan username:");
        for (User user : users) {
            System.out.println(user.getId() + ". " + user.getUsername());
        }

        // Lanjutan TP03 
        System.out.println("silakan login untuk dapat menggunakan fitur kami");
        while (true) {
            System.out.print("username: ");
            String username = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();
            boolean tanda = false;
            
            for(int i = 0 ; i < users.length; i++ ){
                if (users[i].ValidateLogin(username, password)){
                    System.out.println(" Berhasil login! "); 
                    tanda = true;
                    loggedInUser = users[i];

                    break;
                } 
            }
            if (tanda == false){
                System.out.println("Maaf, username atau password yang anda masukkan salah. Tolong masukkan kembali dengan benar");
            } else {
                break;
        }
     }

        while(true) {
            System.out.println();
            System.out.println("Pilih salah satu menu kami!");
            System.out.println("1. Follow pengguna lain");
            System.out.println("2. Mengirim menfess");
            System.out.println("3. Melihat daftar followers dan following");
            System.out.println("4. Logout");
            System.out.print("Masukkan pilihanmu: ");
            int opsi = scanner.nextInt();
            scanner.nextLine();
    
             if ( opsi == 1){ 
                pilihan1(users);
            } else if (opsi == 2){
                pilihan2(users);
            } else if (opsi == 3){
                pilihan3(users);
            } else if (opsi == 4) {
                break;
            } 
        }

        // System.out.print("Berapa fess yang ingin dijadwalkan? (maks 5): ");
        // int jumlahFess = Integer.parseInt(scanner.nextLine());
        // if (jumlahFess > 5)
        //     jumlahFess = 5;

        // int[] delays = new int[jumlahFess];
        // for (int i = 0; i < jumlahFess; i++) {
        //     System.out.print("Masukkan delay fess #" + (i + 1) + " (detik): ");
        //     delays[i] = Integer.parseInt(scanner.nextLine());
        // }

        // Arrays.sort(delays);
        // LocalDateTime now = LocalDateTime.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
        // System.out.println("Mengurutkan dan mengirimkan fess...");
        // for (int delay : delays) {
        //     LocalDateTime waktuKirim = now.plusSeconds(delay);
        //     System.out.println("Fess akan dikirim pada: " + waktuKirim.format(formatter));
        // }

        // followMatrix = new int[jumlahUser][jumlahUser];
        // System.out.print("Berapa kali akan dilakukan follow user? ");
        // int jumlahFollow = Integer.parseInt(scanner.nextLine());

        // for (int i = 0; i < jumlahFollow; i++) {
        //     while (true) {
        //         System.out.print("Masukkan username " + (i + 1) + " yang mem-follow: ");
        //         String followerName = scanner.nextLine();
        //         System.out.print("Masukkan username " + (i + 1) + " yang di-follow: ");
        //         String followedName = scanner.nextLine();

        //         int followerId = cariId(users, followerName);
        //         int followedId = cariId(users, followedName);

        //         if (followerId == -1 || followedId == -1) {
        //             System.out.println("Ada username yang tidak ditemukan! Silakan masukkan ulang");
        //         } else if (followerId == followedId) {
        //             System.out.println("Tidak bisa mem-follow diri sendiri! Silakan masukkan ulang");
        //         } else if (followMatrix[followerId][followedId] == 1) {
        //             System.out.println(
        //                     "User " + followerName + " sudah mem-follow " + followedName + "! Silakan masukkan ulang");
        //         } else {
        //             followMatrix[followerId][followedId] = 1;
        //             System.out.println("User " + followerName + " berhasil mem-follow " + followedName + "!");
        //             break;
        //         }
        //     }
        // }

        // System.out.println("\nDaftar mutual burhanFess:");
        // for (User user : users) {
        //     System.out.println("== User: " + user.getUsername() + ", ID: " + user.getId() + " ==");
        //     System.out.println("Followers:");
        //     boolean adaFollower = false;
        //     for (int i = 0; i < jumlahUser; i++) {
        //         if (followMatrix[i][user.getId()] == 1) {
        //             System.out.println("- " + users[i].getUsername());
        //             adaFollower = true;
        //         }
        //     }
        //     if (!adaFollower)
        //         System.out.println("Pengguna belum di-follow siapapun");

        //     System.out.println("Following:");
        //     boolean adaFollowing = false;
        //     for (int j = 0; j < jumlahUser; j++) {
        //         if (followMatrix[user.getId()][j] == 1) {
        //             System.out.println("- " + users[j].getUsername());
        //             adaFollowing = true;
        //         }
        //     }
        //     if (!adaFollowing)
        //         System.out.println("Pengguna belum mem-follow siapapun");
        // }

        // // Subtask 6: Fitur Bebas - Edit Bio
        // System.out.println("\n== Fitur Subtask 6: Update Bio Pengguna ==");
        // for (User user : users) {
        //     System.out.print("Masukkan bio untuk " + user.getUsername() + ": ");
        //     String bio = scanner.nextLine();
        //     user.setBio(bio);
        // }

        // System.out.println("\n== Daftar Profil Pengguna ==");
        // for (User user : users) {
        //     System.out.println("Username: " + user.getUsername());
        //     System.out.println("Bio     : " + user.getBio());
        // }

        scanner.close();
    }

    public static int cariId(User[] users, String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user.getId();
            }
        }
        return -1;
    }
    public static void pilihan1(User[] users) {
        System.out.print("Berapa kali akan dilakukan follow user? ");
        int jumlahFollow = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < jumlahFollow; i++) {
            while (true) {
                System.out.print("Masukkan username " + (i + 1) + " yang mem-follow: ");
                String followerName = scanner.nextLine();
                System.out.print("Masukkan username " + (i + 1) + " yang di-follow: ");
                String followedName = scanner.nextLine();

                int followerId = cariId(users, followerName);
                int followedId = cariId(users, followedName);

                if (followerId == -1 || followedId == -1) {
                    System.out.println("Ada username yang tidak ditemukan! Silakan masukkan ulang");
                } else if (followerId == followedId) {
                    System.out.println("Tidak bisa mem-follow diri sendiri! Silakan masukkan ulang");
                } else if (followMatrix[followerId][followedId] == 1) {
                    System.out.println(
                            "User " + followerName + " sudah mem-follow " + followedName + "! Silakan masukkan ulang");
                } else {
                    followMatrix[followerId][followedId] = 1;
                    System.out.println("User " + followerName + " berhasil mem-follow " + followedName + "!");
                    break;
                }
            }
        }
    }
    
    public static void pilihan2(User[] users) {
        System.out.print("Berapa fess yang ingin dijadwalkan? (maks 5): ");
        int jumlahFess = Integer.parseInt(scanner.nextLine());
        if (jumlahFess > 5)
            jumlahFess = 5;

        int[] delays = new int[jumlahFess];
        for (int i = 0; i < jumlahFess; i++) {
            System.out.print("Masukkan delay fess #" + (i + 1) + " (detik): ");
            delays[i] = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan fess: ");
            String pesan = scanner.nextLine();

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime waktuKirim = now.plusSeconds(delays[i]);

            Menfess menfess = new Menfess(loggedInUser.getUsername(), pesan, waktuKirim);
            loggedInUser.addMenfess(menfess);
        }

        Arrays.sort(delays);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
        System.out.println("Mengurutkan dan mengirimkan fess...");
        for (Menfess menfess : loggedInUser.getMenfess()) {
            if(menfess==null) {
                break;
            }
            System.out.println("Fess akan dikirim pada: " + menfess.getTanggalKirim().format(formatter));
            System.out.println("Pesan Menfess: "+ menfess.getIsiMenfess());
        }
    }
    
    public static void pilihan3(User[] users) {
        System.out.println("\nDaftar mutual burhanFess:");
        for (User user : users) {
            System.out.println("== User: " + user.getUsername() + ", ID: " + user.getId() + " ==");
            System.out.println("Followers:");
            boolean adaFollower = false;
            for (int i = 0; i < jumlahUser; i++) {
                if (followMatrix[i][user.getId()] == 1) {
                    System.out.println("- " + users[i].getUsername());
                    adaFollower = true;
                }
            }
            if (!adaFollower)
                System.out.println("Pengguna belum di-follow siapapun");

            System.out.println("Following:");
            boolean adaFollowing = false;
            for (int j = 0; j < jumlahUser; j++) {
                if (followMatrix[user.getId()][j] == 1) {
                    System.out.println("- " + users[j].getUsername());
                    adaFollowing = true;
                }
            }
            if (!adaFollowing)
                System.out.println("Pengguna belum mem-follow siapapun");
        }
     }   
}
