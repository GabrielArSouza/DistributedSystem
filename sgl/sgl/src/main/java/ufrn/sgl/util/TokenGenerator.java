package ufrn.sgl.util;

import java.util.Random;

public class TokenGenerator {

	public static String getToken () {
	    int leftLimit = 48; // 0
	    int rightLimit = 122; // z
	    int targetStringLength = 71;
	  
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = getASymbol(leftLimit, rightLimit);
	        buffer.append((char) randomLimitedInt);
	    }
	    
	    String generatedString = buffer.toString();
	    System.out.println(generatedString);
	    return generatedString;
	}
	
	private static int getASymbol (int leftLimit, int rightLimit) {
		Random random = new Random();
		int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
		if ( isIgnoredSymbol(randomLimitedInt) ) { return getASymbol(leftLimit, rightLimit); }
		else return randomLimitedInt;
	}
	
	private static boolean isIgnoredSymbol ( int value ) {
		if ( value > 57 && value < 65) return true;
		else if ( value > 90 && value < 97 ) return true;
		else return false;
	}
	
}
