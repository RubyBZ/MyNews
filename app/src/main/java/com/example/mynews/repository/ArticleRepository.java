package com.example.mynews.repository;

import static com.example.mynews.utils.Contants.API_KEY;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mynews.database.ArticleDao;
import com.example.mynews.database.ArticleDatabase;
import com.example.mynews.model.Article;
import com.example.mynews.model.HeadLines;
import com.example.mynews.network.ApiRequest;
import com.example.mynews.network.ApiInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

	private static final String TAG = ArticleRepository.class.getSimpleName();
	final MutableLiveData<HeadLines> data = new MutableLiveData<>();
	//private MutableLiveData<List<Article>> favoritesArticles = new MutableLiveData<>();
	private List<Article> favoritesArticles = new ArrayList<>();
	private final ArticleDao articleDao;
	private final ApiRequest apiRequest;
	private String category;
	private String country;

	public ArticleRepository(Application application) {
		apiRequest = ApiInstance.getRetrofitInstance().create((ApiRequest.class));
		ArticleDatabase database;
		database = ArticleDatabase.getDatabase(application);
		articleDao = database.articleDao();
	}

	public void setHeadlinesCountry(String country) {
		this.country = country;
	}

	public void setHeadlinesCategory(String category) {
		this.category = category;
	}

	public LiveData<HeadLines> getTopHeadlines() {
		apiRequest.getTopHeadlines(country, API_KEY)
				.enqueue(new Callback<HeadLines>() {
					@Override
					public void onResponse(Call<HeadLines> call, Response<HeadLines> response) {
						if(response.body()!= null){
							data.setValue(response.body());
						}
					}

					@Override
					public void onFailure(Call<HeadLines> call, Throwable t) {
						data.setValue(null);
					}
				});
		return data;
	}

	public LiveData<HeadLines> getTopHeadlinesByCategory() {
		apiRequest.getTopHeadlinesByCategory(country, category,API_KEY)
				.enqueue(new Callback<HeadLines>() {
					@Override
					public void onResponse(Call<HeadLines> call, Response<HeadLines> response) {
						if(response.body()!= null){
							data.setValue(response.body());
						}
					}

					@Override
					public void onFailure(Call<HeadLines> call, Throwable t) {
						data.setValue(null);
					}
				});
		return data;
	}

	//public LiveData<List<Article>> getFavoritesArticles() {
	public List<Article> getFavoritesArticles() {
	ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			favoritesArticles =  articleDao.getFavoritesArticles();
		});
		executor.shutdown();
		return favoritesArticles;
	}

	public void insertArticle(Article favoriteArticle) {

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			articleDao.insertArticle(favoriteArticle);

		});
		executor.shutdown();
	}

	public void deleteArticle(Article favoriteArticle) {

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			articleDao.deleteArticle(favoriteArticle);
		});
		executor.shutdown();
	}
}
