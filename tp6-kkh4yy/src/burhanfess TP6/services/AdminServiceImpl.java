package burhanfess.services;

import java.util.List;
import java.util.Comparator;
import burhanfess.repositories.UserRepositoryImpl;
import burhanfess.repositories.MenfessRepositoryImpl;
import burhanfess.users.User;
import burhanfess.users.Admin;
import burhanfess.users.Cosmic;
import burhanfess.menfess.Menfess;
import burhanfess.exceptions.*;

public class AdminServiceImpl implements AdminService {
    private final UserRepositoryImpl userRepo = UserRepositoryImpl.getInstance();
    private final MenfessRepositoryImpl menfessRepo = MenfessRepositoryImpl.getInstance();

    @Override
    public List<User> getAllUsers(Comparator<User> comparator) {
        List<User> list = userRepo.getAllUsers();
        list.sort(comparator);
        return list;
    }

    @Override
    public void addAdmin(String username, String password) {
        if (userRepo.getUserByUsername(username) != null) {
            throw new UsernameAlreadyExistsException("User dengan username '" + username + "' sudah ada");
        }
        userRepo.addUser(new Admin(username, password));
    }

    @Override
    public void resetPassword(String username, String newPassword) {
        User u = userRepo.getUserByUsername(username);
        if (u == null) {
            throw new InvalidCredentialsException("User dengan username '" + username + "' tidak ditemukan");
        }
        if (u.getPassword().equals(newPassword)) {
            throw new PasswordUnchangedException("Password yang dimasukkan tidak boleh sama dengan password sebelumnya");
        }
        userRepo.changePassword(u, newPassword);
    }

    @Override
    public List<Menfess> getAllHiddenMenfesses() {
        return menfessRepo.getAllHiddenMenfesses();
    }

    @Override
    public List<Menfess> getAllUnhiddenMenfesses() {
        return menfessRepo.getAllUnhiddenMenfesses();
    }

    @Override
    public void hideMenfess(int menfessId) {
        Menfess m = menfessRepo.getAllUnhiddenMenfesses().stream()
            .filter(x -> x.getId() == menfessId)
            .findFirst()
            .orElseThrow(() -> new MenfessNotFoundException("Menfess dengan ID " + menfessId + " tidak ditemukan"));
        menfessRepo.hideMenfess(m);
    }

    @Override
    public void unhideMenfess(int menfessId) {
        Menfess m = menfessRepo.getAllHiddenMenfesses().stream()
            .filter(x -> x.getId() == menfessId)
            .findFirst()
            .orElseThrow(() -> new MenfessNotFoundException("Menfess dengan ID " + menfessId + " tidak ditemukan"));
        menfessRepo.unhideMenfess(m);
    }

    @Override
    public void logout() {
        // no-op
    }

    @Override
    public void deleteUser(String username) {
        if(userRepo.getUserByUsername(username)==null){
            throw new InvalidCredentialsException("Username "+ username +" tidak ditemukan");
        }

        int counter=0;
        User userDicari = userRepo.getUserByUsername(username);
        userRepo.removeItem(userDicari);
        System.out.println("Akun dengan username " + username + " berhasil dihapus.");
        for(Menfess menfess: menfessRepo.getAllMenfessesByUser(userDicari)){
            menfessRepo.removeItem(menfess);
            counter++;
        }
        System.out.println("Berhasil menghapus " + counter + " menfess yang dikirim oleh username " + username);
        counter=0;
        for(Menfess menfess: menfessRepo.getAllMenfessesForUser(userDicari)){
            menfessRepo.removeItem(menfess);
            counter++;
        }
        System.out.println("Berhasil menghapus " + counter + " menfess yang diterima oleh username " + username);
    }

    @Override
    public void showUserStatistics() {
        List<User> allUsers = userRepo.getAllUsers();

        System.out.println("--------------------------------------------------------------");
        System.out.println("STATISTIK USER COSMIC");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("| %-10s | %-6s | %-6s | %-7s | %-15s |\n",
                        "Username", "Confess", "Curhat", "Promosi", "Menfess Diterima");
        System.out.println("+------------+--------+--------+---------+-----------------+");

        int jumlahUser =0;
        for (User user : allUsers) {
            if (!(user instanceof Cosmic)) continue;
            List<Menfess> menfesses = menfessRepo.getAllMenfessesByUser(user);
            List<Menfess> menfesses1 = menfessRepo.getAllMenfessesForUser(user);

            long confessCount = menfesses.stream()
                    .filter(m -> m.getType().equalsIgnoreCase("confession"))
                    .count();
            long curhatCount = menfesses.stream()
                    .filter(m -> m.getType().equalsIgnoreCase("curhat"))
                    .count();
            long promosiCount = menfesses.stream()
                    .filter(m -> m.getType().equalsIgnoreCase("promosi"))
                    .count();
            long diterimaCount = menfesses1.stream()
                                        .filter(m -> !m.isHidden())
                                        .count();

            System.out.printf("| %-10s | %-6d | %-6d | %-7d | %-15d |\n",
                            user.getUsername(), confessCount, curhatCount, promosiCount, diterimaCount);
            jumlahUser++;
        }

        System.out.println("+------------+--------+--------+---------+-----------------+");
        System.out.println("Total pengguna terdaftar: " + jumlahUser);
    }
}