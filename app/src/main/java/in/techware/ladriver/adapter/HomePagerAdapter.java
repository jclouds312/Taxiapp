package in.techware.ladriver.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import in.techware.ladriver.fragments.AccountsFragment;
import in.techware.ladriver.fragments.EarningsFragment;
import in.techware.ladriver.fragments.HomeFragment;
import in.techware.ladriver.fragments.RatingsFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private HomeFragment homeFragment;
    private EarningsFragment earningsFragment;
    private RatingsFragment ratingsFragment;
    private AccountsFragment accountsFragment;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                return homeFragment;
            case 1:
                if (earningsFragment == null) {
                    earningsFragment = new EarningsFragment();
                }
                return earningsFragment;
            case 2:
                if (ratingsFragment == null) {
                    ratingsFragment = new RatingsFragment();
                }
                return ratingsFragment;
            case 3:
                if (accountsFragment == null) {
                    accountsFragment = new AccountsFragment();
                }
                return accountsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
