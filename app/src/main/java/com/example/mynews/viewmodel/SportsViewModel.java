package com.example.mynews.viewmodel;

import static com.example.mynews.utils.Contants.COUNTRY;
import static com.example.mynews.utils.Contants.SPORTS;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynews.model.HeadLines;
import com.example.mynews.repository.ArticleRepository;

public class SportsViewModel extends AndroidViewModel {

	//todo - change the country by settings
	private String country = COUNTRY;
	private ArticleRepository articleRepository;
	private LiveData<HeadLines> sportsLiveData;

	public SportsViewModel(@NonNull Application application) {
		super(application);

		articleRepository = new ArticleRepository();
		articleRepository.setHeadlinesCountry(country);
		articleRepository.setHeadlinesCategory(SPORTS);

		this.sportsLiveData = articleRepository.getTopHeadlinesByCategory();
	}

	public LiveData<HeadLines> getSportsLiveData() {
		return sportsLiveData;
	}
}
