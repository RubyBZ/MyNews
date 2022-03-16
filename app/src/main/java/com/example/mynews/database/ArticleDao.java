package com.example.mynews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mynews.model.Article;

import java.util.List;

@Dao
public interface ArticleDao {

	//@Query("SELECT * FROM articles")
	//LiveData<List<Article>> getFavoritesArticles();

	@Query("SELECT * FROM articles")
	List<Article> getFavoritesArticles();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertArticle(Article article);

	@Delete
	void deleteArticle(Article article);

	@Query("SELECT EXISTS (SELECT 1 FROM articles WHERE articleId=:id)")
	public int isFavorite(int id);

}
