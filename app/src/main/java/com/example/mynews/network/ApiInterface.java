package com.example.mynews.network;

import com.example.mynews.model.HeadLines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

	/*for headlines*/
	@GET("top-headlines")
	Call<HeadLines> getData(
			@Query("country") String country,
			@Query("apiKey") String apiKey
	);

	/*for category wise data*/
	@GET("top-headlines")
	Call<HeadLines> getEntertainmentData(
			@Query("country") String country,
			@Query("category") String category,
			@Query("apiKey") String apiKey
	);

}
