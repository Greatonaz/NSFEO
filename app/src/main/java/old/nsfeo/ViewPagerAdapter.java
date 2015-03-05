package old.nsfeo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_PAGES = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f = null;

        switch(position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                throw new IndexOutOfBoundsException("No view to display in the fragment adapter.");
        }
        return null;
    }
    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}
