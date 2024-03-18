package food.app.activity.Models;

import java.util.List;

public class CategoryResponse {
    private String status;
    private String message;
    private List<CategoryModel> data;
    private Meta meta;

    public CategoryResponse() {
    }

    public CategoryResponse(String status, String message, List<CategoryModel> data, Meta meta) {
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

    public List<CategoryModel> getData() {
        return data;
    }

    public void setData(List<CategoryModel> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
