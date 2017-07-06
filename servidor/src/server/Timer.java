package server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Timer {
	static long timePeriodInMillis = 120000;
	
	public Timer() {		
	}
	
	public static boolean testTimeout(long millis){
		if((Calendar.getInstance().getTimeInMillis()-millis)>=timePeriodInMillis){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void start(Player player){
		player.setTimestamp(Calendar.getInstance().getTimeInMillis());
	}
	
	public static void stop(Player player){
		player.setTimestamp(0);
	}
	
	
}
