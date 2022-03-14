package com.example.mynews.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.mynews.utils.Contants.NEWS_API_URL;

public class ApiInstance {

	public static Retrofit retrofit=null;

	public  static Retrofit getRetrofitInstance(){

		if (retrofit==null){
			retrofit=new Retrofit.Builder()
					.baseUrl(NEWS_API_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
