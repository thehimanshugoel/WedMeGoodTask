package himanshugoel.com.moviesexplorer.Views.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.View;

import himanshugoel.com.moviesexplorer.Interfaces.SearchListener;
import himanshugoel.com.moviesexplorer.R;
import himanshugoel.com.moviesexplorer.Views.Fragments.FavouriteTab;
import himanshugoel.com.moviesexplorer.Views.Fragments.SearchTab;

public class Dashboard extends BaseActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    SectionPagerAdapter sectionPagerAdapter;
    SearchView searchView;
    SearchListener searchListener;
    Fragment searchTab;
    Fragment favouriteTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
        initFragments();
        searchView.setQueryHint("Search For Directors");
        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchListener = (SearchListener) searchTab;
                searchListener.onSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    searchView.setVisibility(View.VISIBLE);
                } else {
                    searchView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initFragments() {
        searchTab = new SearchTab();
        favouriteTab = new FavouriteTab();
    }

    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        searchView = (SearchView) findViewById(R.id.searchView);
    }

    private class SectionPagerAdapter extends FragmentStatePagerAdapter {
        final int PAGE_COUNT = 2;
        final private String tabTitles[] = new String[]{"Search", "Favourite"};

        SectionPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return searchTab;

            }
            if (position == 1) {
                return favouriteTab;
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
