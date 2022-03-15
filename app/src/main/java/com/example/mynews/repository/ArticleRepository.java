package com.example.mynews.repository;

import static com.example.mynews.utils.Contants.API_KEY;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

//import com.example.mynews.adapter.HeadLinesAdapter;
import com.example.mynews.model.HeadLines;
import com.example.mynews.network.ApiRequest;
import com.example.mynews.network.ApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

	private static final String TAG = ArticleRepository.class.getSimpleName();
	final MutableLiveData<HeadLines> data = new MutableLiveData<>();

	private final ApiRequest apiRequest;
	private String category;
	private String country;

	public ArticleRepository() {
		apiRequest = ApiInstance.getRetrofitInstance().create((ApiRequest.class));
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

	public LiveData<HeadLines> getFavoritesHeadlines() {
		//get data from database
		return data;
	}
}
