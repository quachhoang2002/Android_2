package food.app.activity.Models;

import java.util.Date;

public class CartModel {
    private int productId;
    private int quantity;
    private double price;
    private int user_id;


    public CartModel() {
    }

    public CartModel(int productId, int quantity, double price, int user_id) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.user_id = user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
