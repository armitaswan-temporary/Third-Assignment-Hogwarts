import java.util.UUID;

public class Account implements AccountManagement {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UUID accountID;
    private boolean signedUp;
    private String role;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = hash(password);
        accountID = UUID.randomUUID();
        signedUp = true;
        this.role = role;
    }

    private String hash(String password) {
        long pass = 0;
        for (int i = 0; i < password.length(); i++) {
            pass *= 100;
            pass += password.charAt(i) - '0';
        }
        long hashedPass = password.hashCode();
        return String.valueOf(hashedPass);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hash(password);
    }

    public UUID getAccountID() {
        return accountID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public String getRole() {
        return role;
    }

    public boolean getSigningUpStatus() {
        return signedUp;
    }

    public void signOut() {
        signedUp = false;
    }

    @Override
    public boolean validatePassword(String enteredPassword) {
        return hash(enteredPassword).equals(this.password);
    }

    @Override
    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = hash(newPassword);
    }
}
