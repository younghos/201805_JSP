package kr.or.ddit.locales;

import static org.junit.Assert.*;

import java.util.TimeZone;

import org.junit.Test;

public class TimezoneTest {

	@Test
	public void timezoneTest() {
		/***Given***/
		

		/***When***/
		String[] timezones = TimeZone.getAvailableIDs();
		for(String timezone : timezones){
			System.out.println(timezone);
		}
		/***Then***/

	}

}
