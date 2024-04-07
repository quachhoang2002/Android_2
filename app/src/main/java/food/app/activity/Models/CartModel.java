package food.app.activity.Models;

import java.util.Date;

public class CartModel {
    private int productId;
    private int quantity;
    private double price;
    private int userId;
    private String createdAt;

    public CartModel() {
    }

    public CartModel(int productId, int quantity, double price, int userId, String createdAt) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
