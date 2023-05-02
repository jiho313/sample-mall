package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String toText(Date date) {
		if (date == null) {
			return "";
		}
		String formattedText = YYYYMMDD.format(date);
		return formattedText;
	}
}
