package food.app.activity.Request;

public class OrderRequest {
    private String customerName;
    private String shippingAddress;
    private String customerPhone;
    private String email_receive;
    private int status;
    private double total_price;
    private User user;
    private long createdAt;

    // Default Constructor
    public OrderRequest() {
    }

    // Parameterized Constructor
    public OrderRequest(String customerName, String shippingAddress, String customerPhone,
                        String email_receive, int status, double total_price, User user, long createdAt) {
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.customerPhone = customerPhone;
        this.email_receive = email_receive;
        this.status = status;
        this.total_price = total_price;
        this.user = user;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getemail_receive() {
        return email_receive;
    }

    public void setEmail_receive(String email_receive) {
        this.email_receive = email_receive;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double gettotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    // Nested User class
    public static class User {
        private int id;

        // Default Constructor
        public User() {
        }

        // Parameterized Constructor
        public User(int id) {
            this.id = id;
        }

        // Getter and setter
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    // toString method
    @Override
    public String toString() {
        return "OrderRequest{" +
                "customerName='" + customerName + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", email_receive='" + email_receive + '\'' +
                ", status=" + status +
                ", total_price=" + total_price +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}

