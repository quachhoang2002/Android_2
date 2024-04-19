package food.app.activity.Response;

public class OrderResponse {
    private String status;
    private String message;
    private Data data;

    public static class Data {
        public int id;
        public String customerName;
        public String shippingAddress;
        public String customerPhone;
        public String emailReceive;
        public Double totalPrice;
        public Integer employeeID; // Use object types for fields that can be null

    }


    // Getter and setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and setter for message

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and setter for data

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

