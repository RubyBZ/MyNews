package com.example.mynews.viewmodel;

import static com.example.mynews.utils.Contants.COUNTRY;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynews.model.Article;
import com.example.mynews.repository.ArticleRepository;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

	private String country = COUNTRY;
	private ArticleRepository articleRepository;
	//private LiveData<List<Article>> favoritesLiveData;

	public FavoritesViewModel(@NonNull Application application) {
		super(application);

		articleRepository = new ArticleRepository(getApplication());
		//articleRepository.setHeadlinesCountry(country);
		//articleRepository.setHeadlinesCategory(BUSINESS);

		//this.favoritesLiveData = articleRepository.getFavoritesArticles();
	}

	public List<Article> getFavoritesData() {

		return articleRepository.getFavoritesArticles();
		//return favoritesLiveData;
	}

	public void deleteFromFavorites(Article article) {
		articleRepository.deleteArticle(article);
	}
}