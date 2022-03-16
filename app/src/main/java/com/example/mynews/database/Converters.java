package com.example.mynews.database;

import androidx.room.TypeConverter;

import com.example.mynews.model.Source;

public class Converters {
	@TypeConverter
	public String fromSource(Source source) {
		return source.getName();
	}

	@TypeConverter
	public Source toSource(String name) {
		return new Source(name, name);
	}

}