package com.example.mynews.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.R;
import com.example.mynews.adapter.HeadLinesAdapter;
import com.example.mynews.model.Articles;
import com.example.mynews.model.HeadLines;
import com.example.mynews.network.ApiInstance;
import com.example.mynews.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mynews.utils.Contants.API_KEY;
import static com.example.mynews.utils.Contants.BUSINESS;
import static com.example.mynews.utils.Contants.COUNTRY;

public class BusinessFragment extends Fragment {

	private View view;
	private List<Articles> articlesList=new ArrayList<>();;
	private RecyclerView recyclerView;
	private HeadLinesAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_business, container, false);

		initComponents();

		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		retriveJSON(COUNTRY,BUSINESS,API_KEY);
		return view;

	}

	private void retriveJSON(String country, String business, String apikey) {

		ApiInterface apiInterface= ApiInstance.getRetrofitInstance().create(ApiInterface.class);
		Call<HeadLines> call=apiInterface.getEntertainmentData(country, business, apikey);

		call.enqueue(new Callback<HeadLines>() {
			@Override
			public void onResponse(Call<HeadLines> call, Response<HeadLines> response) {
				if (response.isSuccessful() && response.body().getArticlesList() !=null){
					articlesList.clear();
					articlesList=response.body().getArticlesList();
					adapter=new HeadLinesAdapter(articlesList,getContext());
					recyclerView.setAdapter(adapter);
				}

			}

			@Override
			public void onFailure(Call<HeadLines> call, Throwable t) {
				Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT)
						.show();

			}
		});

	}

	private void initComponents() {
		recyclerView=view.findViewById(R.id.businessRecyclerView);
	}
}
