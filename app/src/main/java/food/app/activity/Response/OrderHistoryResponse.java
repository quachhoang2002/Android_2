package food.app.activity.Response;

import food.app.activity.Request.OrderRequest;

public class OrderHistoryResponse {
    private int id;
    private String customerName;
    private String shippingAddress;
    private String customerPhone;
    private String email_receive;
    private int status;
    private double total_price;
    private User user;
    private String createdAt;
    private String updateAt;

    public OrderHistoryResponse(String customerName, String shippingAddress, String customerPhone, String email_receive, int status, double total_price, User user, String createdAt, String updateAt) {
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.customerPhone = customerPhone;
        this.email_receive = email_receive;
        this.status = status;
        this.total_price = total_price;
        this.user = user;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public OrderHistoryResponse(int id, String customerName, String shippingAddress, String customerPhone, String email_receive, int status, double total_price, User user, String createdAt, String updateAt) {
        this.id = id;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.customerPhone = customerPhone;
        this.email_receive = email_receive;
        this.status = status;
        this.total_price = total_price;
        this.user = user;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail_receive() {
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

    public double getTotal_price() {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
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
}
