package com.example.mynews.viewmodel;

import static com.example.mynews.utils.Contants.BUSINESS;
import static com.example.mynews.utils.Contants.COUNTRY;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynews.model.HeadLines;
import com.example.mynews.repository.ArticleRepository;

public class BusinessViewModel extends AndroidViewModel {

	//todo - change the country by settings
	private String country = COUNTRY;
	private ArticleRepository articleRepository;
	private LiveData<HeadLines> businessLiveData;

	public BusinessViewModel(@NonNull Application application) {
		super(application);

		articleRepository = new ArticleRepository();
		articleRepository.setHeadlinesCountry(country);
		articleRepository.setHeadlinesCategory(BUSINESS);

		this.businessLiveData = articleRepository.getTopHeadlinesByCategory();
	}

	public LiveData<HeadLines> getBusinessLiveData() {
		return businessLiveData;
	}
}
