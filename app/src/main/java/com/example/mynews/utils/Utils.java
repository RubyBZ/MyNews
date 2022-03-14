package com.example.mynews.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static com.example.mynews.utils.Contants.COUNTRY;

public class Utils {

	public static String DateFormat(String oldstringDate){
		String newDate;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale(COUNTRY));
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldstringDate);
			newDate = dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			newDate = oldstringDate;
		}
		return newDate;
	}


}