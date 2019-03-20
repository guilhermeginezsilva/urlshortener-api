package br.com.ginezgit.urlshortenerapi.util;

import java.util.Collections;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameterException;

public class StringUtils {

	public enum Side {
		LEFT, RIGHT;
	}
	
	public static String padding(String stringToPad, int finalTargetLength, Character paddingChar, Side paddingSide) {
		
		validatePaddingInput(stringToPad, finalTargetLength, paddingChar, paddingSide);
		
		String paddingTemplate = String.join("", Collections.nCopies(finalTargetLength, paddingChar.toString()));
		switch(paddingSide) {
			case LEFT:
				return paddingTemplate.substring(stringToPad.length()) + stringToPad;
			case RIGHT:
				return stringToPad + paddingTemplate.substring(stringToPad.length()) ;
		}
		return stringToPad;
	}

	private static void validatePaddingInput(String stringToPad, int finalTargetLength, Character paddingChar,
			Side paddingSide) {
		if(stringToPad == null || paddingChar == null || paddingSide == null) {
			throw new InvalidParameterException("All the parameters are mandatory on validatePaddingInput method, null is not allowed");
		}
		if(finalTargetLength < stringToPad.length()) {
			throw new InvalidParameterException("Target length is lower than value's length");
		}
	}
	
}
