package food.app.activity.Request;

public class RegisterRequest {
    private String email;
    private String name;
    private String password;

    private String phone;
    private boolean remember;
    private boolean isThirdParty;

    // Default constructor
    public RegisterRequest() {
    }

    // Constructor with parameters
    public RegisterRequest(String email, String name,String phone ,String password, boolean remember, boolean isThirdParty) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.remember = remember;
        this.phone = phone;
        this.isThirdParty = isThirdParty;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRemember() {
        return remember;
    }

    public boolean isThirdParty() {
        return isThirdParty;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public void setThirdParty(boolean isThirdParty) {
        this.isThirdParty = isThirdParty;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                ", isThirdParty=" + isThirdParty +
                '}';
    }
}
