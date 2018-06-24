package com.ubs.opsit.interviews;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BerlinClockTimeConverterTest {
	
	@Test
	public void checkTimeConverter() {
		assertThat(new BerlinClockTimeConverter().convertTime("00:00:00"), is(equalTo("Y\r\n" + 
				"OOOO\r\n" + 
				"OOOO\r\n" + 
				"OOOOOOOOOOO\r\n" + 
				"OOOO")));
		assertThat(new BerlinClockTimeConverter().convertTime("13:17:01"), is(equalTo("O\r\n" + 
				"RROO\r\n" + 
				"RRRO\r\n" + 
				"YYROOOOOOOO\r\n" + 
				"YYOO")));
		assertThat(new BerlinClockTimeConverter().convertTime("23:59:59"), is(equalTo("O\r\n" + 
				"RRRR\r\n" + 
				"RRRO\r\n" + 
				"YYRYYRYYRYY\r\n" + 
				"YYYY")));
		assertThat(new BerlinClockTimeConverter().convertTime("24:00:00"), is(equalTo("Y\r\n" + 
				"RRRR\r\n" + 
				"RRRR\r\n" + 
				"OOOOOOOOOOO\r\n" + 
				"OOOO")));
	}
	
	@Test
	public void validateInputParameter() {
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","00","00"}), is(equalTo(true)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","00"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","00","75"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","00","59"}), is(equalTo(true)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","00","60"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","75","00"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","59","00"}), is(equalTo(true)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","60","00"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","-01","00"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","-59","00"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","01","-59"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"00","-59","-1"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"25","01","00"}), is(equalTo(false)));
		assertThat(new BerlinClockTimeConverter().validateInputParameter(new String [] {"-1","00","00"}), is(equalTo(false)));
	}
	
	@Test
	public void checkBerlinTimeSeconds() {
		assertThat(new BerlinClockTimeConverter().getSeconds("0"), is(equalTo("Y")));
		assertThat(new BerlinClockTimeConverter().getSeconds("00"), is(equalTo("Y")));
		assertThat(new BerlinClockTimeConverter().getSeconds("10"), is(equalTo("Y")));
		assertThat(new BerlinClockTimeConverter().getSeconds("59"), is(equalTo("O")));
		assertThat(new BerlinClockTimeConverter().getSeconds("21"), is(equalTo("O")));
	}
	
	@Test
	public void checkBerlinTimeTopHour() {
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockHour("00"), is(equalTo("OOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockHour("13"), is(equalTo("RROO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockHour("5"), is(equalTo("ROOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockHour("16"), is(equalTo("RRRO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockHour("24"), is(equalTo("RRRR")));
	}
	
	@Test
	public void checkBerlinTimeBelowHour() {
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockHour("00"), is(equalTo("OOOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockHour("13"), is(equalTo("RRRO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockHour("5"), is(equalTo("OOOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockHour("16"), is(equalTo("ROOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockHour("24"), is(equalTo("RRRR")));
	}
	
	@Test
	public void checkBerlinTimeTopMinute() {
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("00"), is(equalTo("OOOOOOOOOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("59"), is(equalTo("YYRYYRYYRYY")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("05"), is(equalTo("YOOOOOOOOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("15"), is(equalTo("YYROOOOOOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("16"), is(equalTo("YYROOOOOOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("29"), is(equalTo("YYRYYOOOOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("15"), is(equalTo("YYROOOOOOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("30"), is(equalTo("YYRYYROOOOO")));
		assertThat(new BerlinClockTimeConverter().getTopRowOfTheBerlinClockMinute("45"), is(equalTo("YYRYYRYYROO")));
	}
	
	/**
	 * This method is 
	 */
	@Test
	public void checkBerlinTimeBelowMinute() {
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("00"), is(equalTo("OOOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("59"), is(equalTo("YYYY")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("05"), is(equalTo("OOOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("06"), is(equalTo("YOOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("17"), is(equalTo("YYOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("28"), is(equalTo("YYYO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("30"), is(equalTo("OOOO")));
		assertThat(new BerlinClockTimeConverter().getLowerRowOfTheBerlinClockMinute("45"), is(equalTo("OOOO")));
	}
}
