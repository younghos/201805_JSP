package kr.or.ddit.util;

public class StringUtil {
	/**
	* Method : getFileNAmeFromHeader
	* 작성자 : pc24
	* 변경이력 :
	* @param contentDisposition
	* @return
	* Method 설명 : contentDisposition 에서 fileName을 추출
	*/
	public static String getFileNAmeFromHeader(String contentDisposition){
		String fileName = "";
		
		String[] splits = contentDisposition.split("; ");
		
		for (String splits_str : splits) {
			if(splits_str.indexOf("filename=") >=0){
				fileName = splits_str.substring(10, splits_str.lastIndexOf("\""));
			}
		}
		return fileName;
	}
}
