package com.example.mynews.network;

import com.example.mynews.model.HeadLines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

	//api news top headlines
	@GET("top-headlines")
	Call<HeadLines> getTopHeadlines(
			@Query("country") String country,
			@Query("apiKey") String apiKey
	);

	//api news top headlines by category
	@GET("top-headlines")
	Call<HeadLines> getTopHeadlinesByCategory(
			@Query("country") String country,
			@Query("category") String category,
			@Query("apiKey") String apiKey
	);

}
