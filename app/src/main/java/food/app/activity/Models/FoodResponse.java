package food.app.activity.Models;

import java.util.List;

import food.app.activity.FoodItemModel;

public class FoodResponse {
    private String status;
    private String message;
    private List<FoodItemModel> data;
    private Meta meta;

    public FoodResponse() {
    }

    public FoodResponse(String status, String message, List<FoodItemModel> data, Meta meta) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.meta = meta;
    }

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

    public List<FoodItemModel> getData() {
        return data;
    }

    public void setData(List<FoodItemModel> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
