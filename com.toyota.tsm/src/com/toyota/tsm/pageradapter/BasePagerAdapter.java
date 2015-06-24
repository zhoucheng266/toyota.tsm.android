package com.toyota.tsm.pageradapter;

import java.util.List;

import com.toyota.tsm.model.TabModel;
import com.toyota.tsm.pageindicator.IconPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * @description
 * @created 2015-2-4 下午6:00:50
 * @author ZZB
 */
public class BasePagerAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {

	private List<TabModel> mTabs;

	public void setTabs(List<TabModel> tabs) {
		mTabs = tabs;
	}

	public BasePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

	
		return isTabNull(position) ? null : mTabs.get(position).getFragment();
	}

	@Override
	public int getCount() {

		Log.v("sasa",
				"llllllgetCount___"
						+ String.valueOf(mTabs == null ? 0 : mTabs.size()));
		return mTabs == null ? 0 : mTabs.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		
		Log.v("sasa",
				"llllllgetPageTitle___"
						+ String.valueOf(isTabNull(position) ? "" : mTabs.get(position).getPageTitle()));
		return isTabNull(position) ? "" : mTabs.get(position).getPageTitle();
	}

	private boolean isTabNull(int position) {

		return mTabs == null || mTabs.get(position) == null;
	}

	@Override
	public int getIconResId(int index) {

		return isTabNull(index) ? 0 : mTabs.get(index).getIconResId();
	}

}
