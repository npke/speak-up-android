package vn.coderschool.speakup.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import vn.coderschool.speakup.model.Conversation;

/**
 * Created by kenp on 06/04/2017.
 */

class ConversationFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Conversation> beginnerConversations;
    List<Conversation> immediateConversations;
    List<Conversation> advancedConversations;

    private CharSequence[] titles = {"Beginner", "Immediate", "Advanced"};

    public ConversationFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ConversationFragmentPagerAdapter(FragmentManager fm, List<Conversation> beginnerConversations,
                                            List<Conversation> immediateConversations,
                                            List<Conversation> advancedConversations) {
        super(fm);
        this.beginnerConversations = beginnerConversations;
        this.immediateConversations = immediateConversations;
        this.advancedConversations = advancedConversations;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return ConversationListFragment.getInstance(beginnerConversations);
        else if (position == 1)
            return ConversationListFragment.getInstance(immediateConversations);
        return ConversationListFragment.getInstance(advancedConversations);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
