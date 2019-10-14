package com.ar.ml.weather.planet.utils;

import org.apache.log4j.Logger;

public class Utility {
	private static Logger logger = Logger.getLogger(Utility.class);
	
	public static int validateNumber(String value) {
		int valorReturn = 0;
		try {
			int number = Integer.parseInt(value);
			if(number > 0)
				valorReturn = number;
		}catch (Exception e) {
			logger.error("el valor ingresado no es el esperado.");
		}
		return valorReturn;
	}

}
