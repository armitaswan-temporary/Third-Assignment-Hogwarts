import java.util.UUID;

public abstract class User implements UserManagement {

    protected UUID userID;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected int roleID;
    protected int houseID;
    private String password;

    public User (String firstName, String lastName, String username, String password) throws Exception {
        this.username = username;
        if (UserManagement.isUsernameUnique(username) == false) {
            throw new Exception("Username was not unique");
        }

        this.userID = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = HashText.encodeText(password);
    }
    @Override
    public boolean validatePassword(String enteredPassword) {
        return HashText.encodeText(enteredPassword).equals(password);
    }

    @Override
    public void changeUsername(String newUsername) {
        if (username.equals(newUsername) == false || UserManagement.isUsernameUnique(newUsername)) {
            username = newUsername;
        }
    }

    @Override
    public void changePassword(String newPassword) {
        password = HashText.encodeText(newPassword);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof User == false) {
            return false;
        }
        return ((User) obj).userID == userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getRoleName() {
        return switch (roleID) {
            case 2 -> "Teacher";
            case 3 -> "Student";
            default -> "Assistant";
        };
    }

    public UUID getUserID() {
        return  userID;
    }

    public String getUsername() {
        return  username;
    }

    public String getHouseName() {
        return switch (houseID) {
            case 2 -> "Hufflepuff";
            case 3 -> "Ravenclaw";
            case 4 -> "Slytherin";
            default -> "Gryffindor";
        };
    }
}