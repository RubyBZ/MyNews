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
import com.example.mynews.listeners.ArticleListener;
import com.example.mynews.model.Article;
import com.example.mynews.R;
import com.example.mynews.utils.Utils;

import java.util.List;

public class HeadLinesAdapter extends RecyclerView.Adapter<HeadLinesAdapter.viewHolder> {

	List<Article> articlesList;
	Context context;
	ArticleListener articleListener;

	public HeadLinesAdapter(List<Article> articlesList, ArticleListener articleListener, Context context) {
		this.articlesList = articlesList;
		this.context = context;
		this.articleListener = articleListener;
	}

	@NonNull
	@Override
	public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view= LayoutInflater.from(context).inflate(R.layout.headlines_row,parent,false);
		return new viewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull HeadLinesAdapter.viewHolder holder, int position) {
		Article article=articlesList.get(position);

		String url=article.getUrl();
		holder.url.setText(url);

		holder.date.setText(Utils.DateFormat(article.getPublishedAt()));
		holder.title.setText(article.getTitle());
		holder.description.setText(article.getDescriptions());
		holder.publisherNewsName.setText(article.getSource().getName());

		String imageUrl=article.getImageUrl();
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

		holder.favoriteImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				articleListener.onArticleClicked(article);
			}
		});
	}

	@Override
	public int getItemCount() {
		return articlesList.size();
	}


	public class viewHolder extends RecyclerView.ViewHolder{

		ImageView headLineImage, favoriteImage;
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
			favoriteImage = itemView.findViewById(R.id.fav_btn);
		}
	}
}