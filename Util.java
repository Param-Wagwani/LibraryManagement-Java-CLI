import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util{
	public static int getNumOfDaysFromMillis(long millis){
		return (int)(millis / (24*60*60*1000));
	}

	public static Date parseDate(String date){
		return parseDate(date, "yyyy-MM-dd");
	}

	public static Date parseDate(String date, String format){

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			return sdf.parse(date);
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
	}
}