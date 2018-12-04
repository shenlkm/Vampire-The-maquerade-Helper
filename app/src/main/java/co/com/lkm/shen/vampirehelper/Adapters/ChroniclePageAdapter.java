package co.com.lkm.shen.vampirehelper.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.com.lkm.shen.vampirehelper.Fragments.CharacterFragment;
import co.com.lkm.shen.vampirehelper.Fragments.SceneFragment;

public class ChroniclePageAdapter extends FragmentStatePagerAdapter {

    private static String[] titles;
    private static long mId;

    public ChroniclePageAdapter(FragmentManager fm, long id) {
        super(fm);
        titles = new String[]{"Scenes", "Characers"};
        mId = id;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = SceneFragment.newInstance(mId);
                break;
            case 1:
                fragment =  CharacterFragment.newInstance(mId);
                break;
        }
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
