package food.app.activity.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import food.app.activity.Fragments.DrinkFragment;
import food.app.activity.Fragments.FoodsFragment;
import food.app.activity.Fragments.SnackFragment;

public class FoodItemAdapter extends FragmentPagerAdapter {

    int tabCounts;

    public FoodItemAdapter(@NonNull FragmentManager fm, int tabCounts) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCounts = tabCounts;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println(position + "Hiện hồn dùm");
        switch (position) {
            case 0:
                return new FoodsFragment();
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
}
