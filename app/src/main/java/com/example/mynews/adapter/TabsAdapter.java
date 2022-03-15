package com.example.mynews.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mynews.fragments.BusinessFragment;
import com.example.mynews.fragments.EntertainmentFragment;
import com.example.mynews.fragments.FavoritesFragment;
import com.example.mynews.fragments.TopHeadlinesFragment;
import com.example.mynews.fragments.SportsFragment;
import com.example.mynews.fragments.TechnologyFragment;

public class TabsAdapter extends FragmentPagerAdapter {

	private int tabs_size = 6;
	private final Context mContext;

	public TabsAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				TopHeadlinesFragment headLinesFragment = new TopHeadlinesFragment();
				return headLinesFragment;
			case 1:
				EntertainmentFragment entertainmentFragment = new EntertainmentFragment();
				return entertainmentFragment;
			case 2:
				BusinessFragment businessFragment = new BusinessFragment();
				return businessFragment;
			case 3:
				SportsFragment sportsFragment = new SportsFragment();
				return sportsFragment;
			case 4:
				TechnologyFragment technologyFragment = new TechnologyFragment();
				return technologyFragment;
			case 5:
				FavoritesFragment favoritesFragment = new FavoritesFragment();
				return favoritesFragment;

			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return tabs_size;
	}
/*
	@Override
	public float getPageWidth (int position) {
		return 0.93f;
	}
*/
	//@Override
	//public CharSequence getPageTitle(int position) {
	//	return "Page " + position;
	//}
}
