package old.nsfeo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.UnderlinePageIndicator;


public class MainMenu extends ActionBarActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private UnderlinePageIndicator underlinePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call the super constructor
        super.onCreate(savedInstanceState);

        // Get the view from viewpager_main.xml
        setContentView(R.layout.activity_main_menu);

        // If the saved Instance state is null, create a new fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        // Locate the ViewPager in viewpager_main.xml
        //this.viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Pass results to ViewPagerAdapter Class
        this.pagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());

        //
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
            return rootView;
        }
    }
}
