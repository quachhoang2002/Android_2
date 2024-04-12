package food.app.activity.Response;

public class LoginResponse {
    private String status;
    private String message;
    private Data data;

    // Getters and Setters for status and message
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // Nested Data class
    public static class Data {
        public int id;
        public String name;
        public String email;
        public String password;
        public String phone;
        public String createdat;
        public int status;
        public String token;

        // Getters and Setters for all fields in Data
        // ... (omitted for brevity)
    }
}

