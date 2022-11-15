package com.comestic.utils;

import java.util.concurrent.ThreadLocalRandom;

public class StringRandom {
 public static String nextString(int length) {
	 String temp = "";
	 int randomNum;
	 for(int i = 0; i < length; i++) {
		 randomNum = ThreadLocalRandom.current().nextInt(65, 91);
		 temp += Character.toString((char) randomNum);
	 }
	 return temp;
 }
}
