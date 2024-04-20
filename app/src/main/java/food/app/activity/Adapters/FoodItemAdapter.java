package food.app.activity.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import food.app.activity.Fragments.DrinkFragment;
import food.app.activity.Fragments.FoodsFragment;
import food.app.activity.Fragments.SnackFragment;

public class FoodItemAdapter extends FragmentPagerAdapter {

    int tabCounts;
    private String text;

    public void SetName(String name) {
        this.text = name;
    }

    public String GetName() {
        return this.text;
    }

    public FoodItemAdapter(@NonNull FragmentManager fm, int tabCounts) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCounts = tabCounts;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FoodsFragment fm = new FoodsFragment();
                return fm;
            case 1:
                return new DrinkFragment();
            case 2:
                return new SnackFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCounts;
    }

    public void reloadTab(int position) {
        System.out.println("reload tab");
        notifyDataSetChanged();
    }


}
