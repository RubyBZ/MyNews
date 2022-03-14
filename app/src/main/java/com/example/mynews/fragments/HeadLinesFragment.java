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
import static com.example.mynews.utils.Contants.COUNTRY;

public class HeadLinesFragment extends Fragment {

	private View view;
	private RecyclerView recyclerView;
	private HeadLinesAdapter adapter;
	private List<Articles> articles=new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view= inflater.inflate(R.layout.fragment_headlines, container, false);

		initComponents();

		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		retriveJSON(COUNTRY,API_KEY);
		return view;
	}

	private void retriveJSON(String country, String apikey) {
		ApiInterface apiInterface= ApiInstance.getRetrofitInstance().create(ApiInterface.class);

		Call<HeadLines> call=apiInterface.getData(country, apikey);
		call.enqueue(new Callback<HeadLines>() {
			@Override
			public void onResponse(Call<HeadLines> call, Response<HeadLines> response) {
				if (response.isSuccessful() && response.body().getArticlesList() !=null){
					articles.clear();
					articles=response.body().getArticlesList();
					adapter=new HeadLinesAdapter(articles,getContext());
					recyclerView.setAdapter(adapter);
				}
			}

			@Override
			public void onFailure(Call<HeadLines> call, Throwable t) {
				Toast.makeText(getContext(), "Something went wroung", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

	private void initComponents() {
		recyclerView=view.findViewById(R.id.headLinesRecyclerView);
	}
}

