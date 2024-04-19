package food.app.activity.Models;

    public class CartItemModel {
        private int id;
        private String imageResId;
        private String productName;
        private double price;
        private int quantity;

        public CartItemModel(int id,String imageResId, String productName, double price, int quantity) {
            this.id = id;
            this.imageResId = imageResId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public String getImageResId() {
            return imageResId;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImageResId(String imageResId) {
            this.imageResId = imageResId;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

