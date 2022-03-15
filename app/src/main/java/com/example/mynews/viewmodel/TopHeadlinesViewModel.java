package com.example.mynews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynews.model.HeadLines;
import com.example.mynews.repository.ArticleRepository;
import static com.example.mynews.utils.Contants.COUNTRY;

public class TopHeadlinesViewModel extends AndroidViewModel {

	//todo - change the country by settings
	private String country = COUNTRY;
	private ArticleRepository articleRepository;
	private LiveData<HeadLines> topHeadlinesLiveData;

	public TopHeadlinesViewModel(@NonNull Application application) {
		super(application);

		articleRepository = new ArticleRepository();
		articleRepository.setHeadlinesCountry(country);

		this.topHeadlinesLiveData = articleRepository.getTopHeadlines();
	}

	public LiveData<HeadLines> getTopHeadlinesLiveData() {
		return topHeadlinesLiveData;
	}
}
