package food.app.activity.Models;

import com.google.gson.stream.JsonReader;

import java.util.List;

public class CartResponse {
    private List<FoodItemModel> productItems;
    private List<CartItem> cartItems;

    public List<FoodItemModel> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<FoodItemModel> productItems) {
        this.productItems = productItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }
}
