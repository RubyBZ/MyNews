package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mynews.adapter.MyAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

	TabLayout tabLayout;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		MyAdapter myAdapter = new MyAdapter(this, getSupportFragmentManager());

		tabLayout = (TabLayout)findViewById(R.id.tabs);
		viewPager=(ViewPager)findViewById(R.id.view_pager);

		tabLayout.addTab(tabLayout.newTab().setText("Headlines").setIcon(R.drawable.ic_headlines));
		tabLayout.addTab(tabLayout.newTab().setText("Entertainment").setIcon(R.drawable.ic_entertainment));
		tabLayout.addTab(tabLayout.newTab().setText("Business").setIcon(R.drawable.ic_business));
		tabLayout.addTab(tabLayout.newTab().setText("Sports").setIcon(R.drawable.ic_sports));
		tabLayout.addTab(tabLayout.newTab().setText("Technology").setIcon(R.drawable.ic_technology));
		tabLayout.addTab(tabLayout.newTab().setText("Favorites").setIcon(R.drawable.ic_favorites));

		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		viewPager.setAdapter(myAdapter);
		//viewPager.setOffscreenPageLimit(3);
		//viewPager.setClipToPadding(false);
		//viewPager.setPageMargin(30);


		//Option 1
		//tabLayout.setupWithViewPager(viewPager);

		//Option 2
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}
			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});

	}
}