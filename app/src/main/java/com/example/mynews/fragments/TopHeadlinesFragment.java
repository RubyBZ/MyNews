package com.example.mynews.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.R;
import com.example.mynews.adapter.HeadLinesAdapter;
import com.example.mynews.model.Articles;
import com.example.mynews.viewmodel.TopHeadlinesViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopHeadlinesFragment extends Fragment {

	private View view;
	private RecyclerView recyclerView;
	private HeadLinesAdapter adapter;
	private List<Articles> articles = new ArrayList<>();
	private TopHeadlinesViewModel topHeadlinesViewModel;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_top_headlines, container, false);
		init();
		getArticles();

		return view;
	}

	private void init() {

		recyclerView=view.findViewById(R.id.topHeadlinesRecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		recyclerView.setHasFixedSize(true);
		adapter = new HeadLinesAdapter(articles,getContext());
		recyclerView.setAdapter(adapter);
		topHeadlinesViewModel = ViewModelProviders.of(this).get(TopHeadlinesViewModel.class);

	}

	private void getArticles() {

		topHeadlinesViewModel.getTopHeadlinesLiveData().observe(this,headLines -> {
			if(headLines != null && headLines.getArticles()!= null
					&& !headLines.getArticles().isEmpty()) {

				List<Articles> articleList = headLines.getArticles();
				articles.addAll(articleList);
				adapter.notifyDataSetChanged();
			}
		});
	}
}

