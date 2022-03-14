package com.example.mynews.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynews.model.Articles;
import com.example.mynews.R;
import com.example.mynews.utils.Utils;

import java.util.List;

public class HeadLinesAdapter extends RecyclerView.Adapter<HeadLinesAdapter.viewHolder> {

	List<Articles> articlesList;
	Context context;

	public HeadLinesAdapter(List<Articles> articlesList, Context context) {
		this.articlesList = articlesList;
		this.context = context;
	}

	@NonNull
	@Override
	public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view= LayoutInflater.from(context).inflate(R.layout.headlines_row,parent,false);
		return new viewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull HeadLinesAdapter.viewHolder holder, int position) {
		Articles articles=articlesList.get(position);

		String url=articles.getUrl();
		holder.url.setText(url);

		holder.date.setText(Utils.DateFormat(articles.getPublishedAt()));
		holder.title.setText(articles.getTitle());
		holder.description.setText(articles.getDescriptions());
		holder.publisherNewsName.setText(articles.getSource().getName());

		String imageUrl=articles.getImageUrl();
		Glide.with(context)
				.load(imageUrl)
				.into(holder.headLineImage);

		//for redirect to web
		holder.url.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
				context.startActivity(intent);

			}
		});
	}

	@Override
	public int getItemCount() {
		return articlesList.size();
	}


	public class viewHolder extends RecyclerView.ViewHolder{

		ImageView headLineImage;
		TextView author,url,date,title,description,publisherNewsName;

		public viewHolder(@NonNull View itemView) {
			super(itemView);
			/*author=itemView.findViewById(R.id.authorName);*/
			url=itemView.findViewById(R.id.url);
			date=itemView.findViewById(R.id.date);
			title=itemView.findViewById(R.id.hTitle);
			description=itemView.findViewById(R.id.description);
			publisherNewsName=itemView.findViewById(R.id.newsName);
			headLineImage=itemView.findViewById(R.id.headlineImage);

		}
	}
}