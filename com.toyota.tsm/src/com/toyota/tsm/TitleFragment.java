package com.toyota.tsm;

import com.toyota.tsm.model.TitleDataModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleFragment extends Fragment {
	private FragmentActivity mActivity;
	private View mContentView;
	private TitleDataModel mModel;
	private int mIndex;
	private int mCount;
	private ImageView left, right;

	public static TitleFragment newInstance(int rid, String name) {
		TitleFragment fragment = new TitleFragment();

		Bundle args = new Bundle();
		args.putInt("rid", rid);
		args.putString("name", name);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();

		if (savedInstanceState != null) {
			// TODO

		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// if (mContentView == null) {
		mContentView = inflater.inflate(R.layout.frgm_title, container, false);
		 intview();
		return mContentView;
	}

	private void intview() {
		TextView mytv = (TextView) mContentView.findViewById(R.id.reg_title_tv);
		mytv.setText(getArguments().getString("name"));

		ImageView myim = (ImageView) mContentView
				.findViewById(R.id.reg_title_im);

		myim.setBackgroundResource(getArguments().getInt("rid"));

	}
}
