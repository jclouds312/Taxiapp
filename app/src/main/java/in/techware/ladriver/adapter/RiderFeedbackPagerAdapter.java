package in.techware.ladriver.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.techware.ladriver.fragments.CommentsFragment;
import in.techware.ladriver.fragments.IssuesFragment;

/**
 * Created by Jemsheer K D on 18 May, 2017.
 * Package in.techware.ladriver.adapter
 * Project LaTaxiDriver
 */

public class RiderFeedbackPagerAdapter extends FragmentPagerAdapter {


    private IssuesFragment issuesFragment;
    private CommentsFragment commentsFragment;

    public RiderFeedbackPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (issuesFragment == null) {
                    issuesFragment = new IssuesFragment();
                }
                return issuesFragment;
            case 1:
                if (commentsFragment == null) {
                    commentsFragment = new CommentsFragment();
                }
                return commentsFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}