package burhanfess.services;

import java.util.List;
import java.util.Map;

import burhanfess.menfess.Menfess;
import burhanfess.users.User;

public interface CosmicService {
    void sendCurhatFess(String content);
    void sendPromosiFess(String content);
    void sendConfessFess(String content, String receiverUsername);
    List<Menfess> getAllUnhiddenMenfesses();
    List<Menfess> getAllSentMenfesses();
    List<Menfess> getAllReceivedMenfesses();
    boolean hapusMenfess(int idHapus);
    Map<User, Integer> getLeaderboardMenfess();
    void changePassword(String newPassword);
    void logout();
}
