package food.app.activity.Request;

public class FacebookLoginRequest {
    private String email;
    private String phone;

    private String name;
    private String providerName;

    private Boolean isThirdParty;

    public FacebookLoginRequest() {
        this.isThirdParty = true;
    }

    public FacebookLoginRequest(String email, String phone, String name, String providerName) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.providerName = providerName;
        this.isThirdParty = true;
    }

    //setters and getters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    //toString
    @Override
    public String toString() {
        return "FacebookLoginRequest{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", providerName='" + providerName + '\'' +
                ", isThirdParty=" + isThirdParty +
                '}';
    }

}
