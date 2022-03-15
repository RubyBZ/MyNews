package com.example.mynews.viewmodel;

import static com.example.mynews.utils.Contants.COUNTRY;
import static com.example.mynews.utils.Contants.ENTERTAINMENT;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynews.model.HeadLines;
import com.example.mynews.repository.ArticleRepository;

public class EntertainmentViewModel extends AndroidViewModel {

	//todo - change the country by settings
	private String country = COUNTRY;
	private ArticleRepository articleRepository;
	private LiveData<HeadLines> entertainmentLiveData;

	public EntertainmentViewModel(@NonNull Application application) {
		super(application);

		articleRepository = new ArticleRepository();
		articleRepository.setHeadlinesCountry(country);
		articleRepository.setHeadlinesCategory(ENTERTAINMENT);

		this.entertainmentLiveData = articleRepository.getTopHeadlinesByCategory();
	}

	public LiveData<HeadLines> getEntertainmentLiveData() {
		return entertainmentLiveData;
	}
}
