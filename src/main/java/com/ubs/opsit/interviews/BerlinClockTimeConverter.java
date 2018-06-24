package com.ubs.opsit.interviews;

import java.util.ArrayList;

public class BerlinClockTimeConverter implements TimeConverter {
	
	private static final String 
		EMPTY_STRING = ""
	;
	
	private static final String 
		OUTPUT_DELIMITER = System.getProperty("line.separator")
	;
	
	private StringBuilder
		berlinClockTime
	;	
	
	@Override
	public String convertTime(String aTime) {
		berlinClockTime = new StringBuilder();
		if (aTime == null) {
			throw new IllegalArgumentException("The parameter aTime cannot be passed as null");
		}
		String arr[] = aTime.split(":");
		if (!validateInputParameter(arr)) {
			throw new IllegalArgumentException("The expected format for the parameter is HH:MM:SS");
		}
		appendDataWithNewLine(getSeconds(arr[2]));
		appendDataWithNewLine(getTopRowOfTheBerlinClockHour(arr[0]));
		appendDataWithNewLine(getLowerRowOfTheBerlinClockHour(arr[0]));
		appendDataWithNewLine(getTopRowOfTheBerlinClockMinute(arr[1]));
		berlinClockTime.append(getLowerRowOfTheBerlinClockMinute(arr[1]));
		return berlinClockTime.toString();
	}
	
	/**
	 * The method will append the data with
	 * the current system line separator.
	 * @param p_data The data that need to be
	 *        appended.
	 */
	void appendDataWithNewLine (String p_data) {
		if (berlinClockTime == null) {
			throw new IllegalArgumentException("The instance variable berlinClockTime cannot be null");
		}
		berlinClockTime.append(p_data).append(OUTPUT_DELIMITER);
	}
	
	/**
	 * This method is used to check
	 * whether the parameter passed is
	 * correct or not.
	 * @param arr The time that need to
	 *        be shown on the clock
	 * @return true if the validation passes
	 *         otherwise false
	 */
	boolean validateInputParameter(String[] arr) {
		boolean l_validationresult = true;
		if (arr.length != 3 || Integer.parseInt(arr[0]) > 24 || Integer.parseInt(arr[0]) < 0 ||
				Integer.parseInt(arr[1]) > 59 || Integer.parseInt(arr[1]) < 0 ||
				Integer.parseInt(arr[2]) > 59 || Integer.parseInt(arr[2]) < 0) {
			l_validationresult = false;
		}
		return l_validationresult;
	}
	
	/**
	 * The method will return "Y" if the
	 * parameter passed is divisible by 2
	 * and the remainder is 0 otherwise "O".
	 * @param seconds The seconds that need to
	 *        be shown on the clock
	 * @return Value of the lamp
	 */
	String getSeconds(String seconds) {
		if (Integer.parseInt(seconds) % 2 == 0) {
			return BerlinClockLamps.YELLOW.getDisplayValue();
		}
		return BerlinClockLamps.OFF.getDisplayValue();
	}
	/**
	 * The method will set the number of lamps of the 
	 * 1st row of the Berlin clock hour to red
	 * on performing the quotient operation on the passed
	 * parameter with 5. No. of lamps set to red will be
	 * equal to the quotient. If the quotient count is 0
	 * then all the lamps will be off.
	 * @param p_hour The hour that needs to be shown
	 *        on the clock.
	 * @return Value of the lamps
	 */
	String getTopRowOfTheBerlinClockHour(String hour) {
		ArrayList<String> l_berlinhourtop = new ArrayList<>(4);
		for (int l_i=0; l_i<4; l_i++) {
			l_berlinhourtop.add(BerlinClockLamps.OFF.getDisplayValue());
		}
		for (int l_i=0; l_i < (Integer.parseInt(hour)/5); l_i++) {
			l_berlinhourtop.set(l_i, BerlinClockLamps.RED.getDisplayValue());
		}
		return String.join(EMPTY_STRING, l_berlinhourtop);
	}
	
	/**
	 * The method will set the number of lamps of the 
	 * 2nd row of the Berlin clock hour to red
	 * on performing the remainder operation on the passed
	 * parameter with 5. No. of lamps set to red will be
	 * equal to the remainder. If the remainder count is 0
	 * then all the lamps will be off.
	 * @param p_hour The hour that needs to be shown
	 *        on the clock.
	 * @return Value of the lamps
	 */
	String getLowerRowOfTheBerlinClockHour(String hour) {
		ArrayList<String> l_berlinhourbelow = new ArrayList<>(4);
		for (int l_i=0; l_i<4; l_i++) {
			l_berlinhourbelow.add(BerlinClockLamps.OFF.getDisplayValue());
		}
		for (int l_i=0; l_i < (Integer.parseInt(hour)%5); l_i++) {
			l_berlinhourbelow.set(l_i, BerlinClockLamps.RED.getDisplayValue());
		}
		return String.join(EMPTY_STRING, l_berlinhourbelow);
	}
	
	/**
	 * The method will set the number of lamps of the 
	 * 1st row of the Berlin clock minute to yellow
	 * on performing the division operation on the passed
	 * parameter with 5. No. of lamps set to yellow will be
	 * equal to the quotient. If the quotient count is 0
	 * then all the lamps will be off. The 3rd, 6th and
	 * 9th lamps will be red to denote the first quarter,
	 * half and last quarter.
	 * @param p_minute The minute that needs to be shown
	 *        on the clock.
	 * @return Value of the lamps
	 */
	String getTopRowOfTheBerlinClockMinute(String p_minute) {
		int l_minute = Integer.parseInt(p_minute);
		ArrayList<String> l_berlinminutetop = new ArrayList<>(11);
		for (int l_i=0; l_i<11; l_i++) {
			l_berlinminutetop.add(BerlinClockLamps.OFF.getDisplayValue());
		}
		for (int l_i=0; l_i < l_minute/5; l_i++) {
			l_berlinminutetop.set(l_i, BerlinClockLamps.YELLOW.getDisplayValue());
		}
		for (int l_i=0, l_j=2; l_i < (l_minute/15); l_i++,l_j=l_j+3) {
			l_berlinminutetop.set(l_j, BerlinClockLamps.RED.getDisplayValue());
		}
		return String.join(EMPTY_STRING, l_berlinminutetop);
	}
	
	/**
	 * The method will set the number of lamps of the 
	 * 2nd row of the Berlin clock minute to yellow
	 * on performing the remainder operation on the passed
	 * parameter with 5. No. of lamps set to yellow will be
	 * equal to the remainder. If the remainder count is 0
	 * then all the lamps will be off.
	 * @param p_minute The minute that needs to be shown
	 *        on the clock.
	 * @return Value of the lamps
	 */
	String getLowerRowOfTheBerlinClockMinute(String p_minute) {
		ArrayList<String> l_berlinminutebelow = new ArrayList<>(4);
		for (int l_i=0; l_i<4; l_i++) {
			l_berlinminutebelow.add(BerlinClockLamps.OFF.getDisplayValue());
		}
		for (int l_i=0; l_i < (Integer.parseInt(p_minute)%5); l_i++) {
			l_berlinminutebelow.set(l_i, BerlinClockLamps.YELLOW.getDisplayValue());
		}
		return String.join(EMPTY_STRING, l_berlinminutebelow);
	}

}
