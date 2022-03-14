package com.example.mynews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articles {

	@SerializedName("source")
	@Expose
	private Source source;

	@SerializedName("author")
	@Expose
	private String author;

	@SerializedName("title")
	@Expose
	private String title;

	@SerializedName("description")
	@Expose
	private  String descriptions;

	@SerializedName("urlToImage")
	@Expose
	private String imageUrl;

	@SerializedName("publishedAt")
	@Expose
	private String publishedAt;

	@SerializedName("url")
	@Expose
	private String url;

	public Articles(Source source, String author, String title, String descriptions, String imageUrl, String publishedAt, String url) {
		this.source = source;
		this.author = author;
		this.title = title;
		this.descriptions = descriptions;
		this.imageUrl = imageUrl;
		this.publishedAt = publishedAt;
		this.url = url;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}


	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}