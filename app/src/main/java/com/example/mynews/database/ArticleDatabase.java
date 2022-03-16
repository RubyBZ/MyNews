package com.example.mynews.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mynews.model.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ArticleDatabase extends RoomDatabase {

	public abstract ArticleDao articleDao();
	private static ArticleDatabase INSTANCE;

	public static ArticleDatabase getDatabase(final Context context) {

		if (INSTANCE == null) {
			synchronized (ArticleDatabase.class) {
				if (INSTANCE == null) {
					try {
						INSTANCE =
								Room.databaseBuilder(context.getApplicationContext(),
										ArticleDatabase.class,
										"article_database").build();
					} catch (Exception e) {
						e.printStackTrace();

					}

				}
			}
		}
		return INSTANCE;
	}
}
