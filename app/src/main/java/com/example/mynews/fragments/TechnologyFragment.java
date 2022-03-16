package com.example.mynews.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.R;
import com.example.mynews.adapter.HeadLinesAdapter;
import com.example.mynews.listeners.ArticleListener;
import com.example.mynews.model.Article;
import com.example.mynews.viewmodel.TechnologyViewModel;

import java.util.ArrayList;
import java.util.List;

public class TechnologyFragment extends Fragment implements ArticleListener {

	private View view;
	private RecyclerView recyclerView;
	private HeadLinesAdapter adapter;
	private List<Article> articles = new ArrayList<>();
	private TechnologyViewModel technologyViewModel;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_technology, container, false);
		init();
		getArticles();

		return view;
	}

	private void init() {

		recyclerView=view.findViewById(R.id.technologyRecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		recyclerView.setHasFixedSize(true);
		adapter = new HeadLinesAdapter(articles,this, getContext());
		recyclerView.setAdapter(adapter);
		technologyViewModel = ViewModelProviders.of(this).get(TechnologyViewModel.class);

	}

	private void getArticles() {

		technologyViewModel.getTechnologyLiveData().observe(getViewLifecycleOwner(),headLines -> {
			if(headLines != null && headLines.getArticles()!= null
					&& !headLines.getArticles().isEmpty()) {

				List<Article> articleList = headLines.getArticles();
				articles.addAll(articleList);
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onArticleClicked(Article article) {
		Toast.makeText(getActivity(),"Article saved to Favorites",Toast.LENGTH_SHORT).show();
		technologyViewModel.saveToFavorites(article);
	}
}
