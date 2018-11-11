package co.com.lkm.shen.vampirehelper.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.com.lkm.shen.vampirehelper.Fragments.CharacterFragment;

public class ChroniclePageAdapter extends FragmentStatePagerAdapter {

    private static String[] titles = new String[]{"Characers, Scenes"};

    public ChroniclePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new CharacterFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Character";
            case 1:
                return "Scene";
        }
        return null;
    }
}
