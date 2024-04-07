package food.app.activity.Response;

public class FacebookInformation {
    private String email;
    private String name;

    private String token;

    public FacebookInformation(String email, String name,String token) {
        this.email = email;
        this.name = name;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
