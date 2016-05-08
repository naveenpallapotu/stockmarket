package com.jpmc.sm.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

	/**
	 * Function used to load the stocks with + or - minutes given
	 */
	public static Date getCurrentDateTimePlusOrMinusMinutes(int minutes){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("E MMM dd hh:mm:ss z yyyy");
		DateTime CurrentdateTime = new DateTime();
		CurrentdateTime = CurrentdateTime.plusMinutes(minutes);
		CurrentdateTime.toString(formatter);
		return CurrentdateTime.toDate();
	}
}
