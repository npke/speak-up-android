package vn.coderschool.speakup.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import vn.coderschool.speakup.model.Sound;

/**
 * Created by kenp on 06/04/2017.
 */

public class SoundFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Sound> vowelSounds;
    List<Sound> diphthongSounds;
    List<Sound> consonantSounds;

    String titles[] = {"Vowels", "Diphthongs", "Consonants"};

    public SoundFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SoundFragmentPagerAdapter(FragmentManager fm, List<Sound> vowels, List<Sound> diphthongs, List<Sound> consonants) {
        super(fm);
        this.vowelSounds = vowels;
        this.diphthongSounds = diphthongs;
        this.consonantSounds = consonants;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return PracticeSoundFragment.getInstance(vowelSounds);
        else if (position == 1)
            return PracticeSoundFragment.getInstance(diphthongSounds);
        return PracticeSoundFragment.getInstance(consonantSounds);
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
