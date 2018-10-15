package kr.or.ddit.etc;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void test() {
		/*** Given ***/
		String contentDisposition = "form-data; name=\"profile\"; filename=\"brown.png\"";

		/*** When ***/
		String fileName = "";
		String[] splits = contentDisposition.split("; ");
		for (String str : splits) {
			if(str.indexOf("filename=") >=0){
				fileName = str.substring(10, str.lastIndexOf("\""));
			}
		}

		/*** Then ***/
		assertEquals("brown.png", fileName);
	}

}
