package com.example.mynews.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.R;
import com.example.mynews.adapter.HeadLinesAdapter;
import com.example.mynews.listeners.ArticleListener;
import com.example.mynews.model.Article;
import com.example.mynews.viewmodel.FavoritesViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements ArticleListener {

	private View view;
	private RecyclerView recyclerView;
	private HeadLinesAdapter adapter;
	private List<Article> articleList = new ArrayList<>();
	private FavoritesViewModel favoritesViewModel;

	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_favorites, container, false);
		init();
		getArticles();

		return view;
	}

	private void init() {

		recyclerView = view.findViewById(R.id.favoritesRecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		recyclerView.setHasFixedSize(true);
		adapter = new HeadLinesAdapter(articleList,this, getContext());
		recyclerView.setAdapter(adapter);
		favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

	}

	private void getArticles() {

		//favoritesViewModel.getFavoritesLiveData().observe(getViewLifecycleOwner(), articles -> {
		//	if(!articles.isEmpty()) {
		//		articles.addAll(articles);
		//		adapter.notifyDataSetChanged();
		//		Toast.makeText(getActivity(),"***** Favorites - getArticles - articles.! isEmpty() *******",Toast.LENGTH_SHORT).show();

		//	} else {
		//		Toast.makeText(getActivity(),"***** Favorites - getArticles - articles.isEmpty() *******",Toast.LENGTH_SHORT).show();

		//	}
		//});
		//articleList = favoritesViewModel.getFavoritesLiveData();
		articleList.addAll(favoritesViewModel.getFavoritesData());
		adapter.notifyDataSetChanged();

		//favoritesViewModel.getFavoritesLiveData().observe(getViewLifecycleOwner(), articles -> {
		//Toast.makeText(getActivity(),"***** Favorites - getArticles *******",Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onArticleClicked(Article article) {
		Toast.makeText(getActivity(),"Article removed from Favorites",Toast.LENGTH_SHORT).show();
		favoritesViewModel.deleteFromFavorites(article);
		articleList.addAll(favoritesViewModel.getFavoritesData());
		adapter.notifyDataSetChanged();

	}
}

