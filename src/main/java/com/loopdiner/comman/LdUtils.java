package com.loopdiner.comman;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class LdUtils {

	public LdUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getPicUrl(String picName){
		System.out.println("getPicUrl............");
		if(StringUtils.isEmpty(picName)){
			return picName;
		}
		String picUrl = null;
		if(picName.indexOf(".")<0){
			picName = picName + ".jpg";
		}
		if(picName.indexOf("http")<0){
			picUrl = LdConstants.PIC_URL + picName;
		}else{
			picUrl = picName;
		}
		return picUrl;
	}
	
	public static String getDateStr(String pattern,Date date){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);  
		String str=sdf.format(date);
		return str;
	}
	
	public static String getDateStr(){
		String pattern = "yyyy-MM-dd HH:mm:ss"; 
		return LdUtils.getDateStr(pattern, new Date());
	}

}
