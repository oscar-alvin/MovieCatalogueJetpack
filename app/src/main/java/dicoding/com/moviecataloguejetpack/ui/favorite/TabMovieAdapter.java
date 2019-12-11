package dicoding.com.moviecataloguejetpack.ui.favorite;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabMovieAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentlistTitle = new ArrayList<>();

    public TabMovieAdapter(FragmentManager fm, int behaviour) {
        super(fm, behaviour);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentlistTitle.get(position);
    }

    public void addFragment(Fragment f, String t) {
        fragmentList.add(f);
        fragmentlistTitle.add(t);
    }
}
