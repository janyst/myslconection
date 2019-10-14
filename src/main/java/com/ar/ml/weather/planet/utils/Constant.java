package com.ar.ml.weather.planet.utils;

public class Constant {
	public static final String TYPE_WEATHER_WINTER = "L";
	public static final String TYPE_WEATHER_SUMER = "S";
	public static final String TYPE_WEATHER_SPRING = "O";
	public static final String TYPE_WEATHER_FALL = "P";	
	
	public static final String TYPE_WEATHER_WINTER_DESCRIPTION = "Lluvia";
	public static final String TYPE_WEATHER_SUMER_DESCRIPTION = "Sequia";
	public static final String TYPE_WEATHER_SPRING_DESCRIPTION = "Optimo";
	public static final String TYPE_WEATHER_FALL_DESCRIPTION = "Posible precipitacion";	
	
	public static final String PLANET_VULCANO = "VULCANO";
	public static final String PLANET_FEREGI = "FEREGI";
	public static final String PLANET_BETOSOIDE = "BETOSOIDE";
	
	public static final int ANGULE_INITIAL_VULCANO = 40;
	public static final int ANGULE_INITIAL_FEREGI = 46;
	public static final int ANGULE_INITIAL_BETOSOIDE = 48;
	
	public static final int MOVEMENT_FOR_DAY_VULCANO = 5;
	public static final int MOVEMENT_FOR_DAYL_FEREGI = 1;
	public static final int MOVEMENT_FOR_DAY_BETOSOIDE = 3;
	
	public static final int RADIO_VULCANO = 1000;
	public static final int RADIO_FEREGI = 500;
	public static final int RADIO_BETOSOIDE = 2000;
	
	public static final int TYPE_MOVENT_POS = 1;
	public static final int TYPE_MOVENT_NEG = -1;
	
	public static final String ERROR_MESSAGE_BAD_REQUEST = "Datos incorrectos, por favor validar su solicitud";
}
