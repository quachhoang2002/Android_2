package food.app.activity.Models;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("totalItem")
    private int totalItem;
    @SerializedName("totalPage")
    private int totalPage;
    private int limit;
    private int currentPage;

    public Meta() {
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
