import java.util.List;
import java.util.Objects;

public interface UserManagement {
    boolean validatePassword(String enteredPassword);
    public void changeUsername(String newUsername);
    public void changePassword(String newPassword);
    public static User login(String username, String password) {
        String encodedPassword = HashText.encodeText(password);
        List <User> users = Hogwarts.getAllUsers();
        for (User user : users) {
            if (Objects.equals(user.username, username) && Objects.equals(user.getPassword(), encodedPassword)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isUsernameUnique(String username) {
        List <User> users = Hogwarts.getAllUsers();
        for (User user : users) {
            if (Objects.equals(user.username, username)) {
                return false;
            }
        }
        return true;
    }
}