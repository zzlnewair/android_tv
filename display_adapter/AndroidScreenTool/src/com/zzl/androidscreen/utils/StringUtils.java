package com.zzl.androidscreen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static void doString(String input){
		
		System.out.println("input -------- " + input);
		Pattern p = Pattern.compile("\\d");
		Matcher matcher = p.matcher(input);
		
		if(matcher.matches()){
			String str1 = matcher.group(0);
			System.out.println("result  ====  " + str1);
		}
		
	}
	
}
