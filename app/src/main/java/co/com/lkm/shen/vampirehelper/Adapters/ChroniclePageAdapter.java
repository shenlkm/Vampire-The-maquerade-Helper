package co.com.lkm.shen.vampirehelper.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.com.lkm.shen.vampirehelper.Fragments.CharacterFragment;

public class ChroniclePageAdapter extends FragmentStatePagerAdapter {

    private static String[] titles;

    public ChroniclePageAdapter(FragmentManager fm) {
        super(fm);
        titles = new String[]{"Characers", "Scenes"};
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new CharacterFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
