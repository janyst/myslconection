package com.ar.ml.weather.planet.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.ar.ml.weather.planet.dto.ConsultResponseDTO;
import com.ar.ml.weather.planet.helper.WeatherPlanetHelper;
import com.ar.ml.weather.planet.model.Planet;
import com.ar.ml.weather.planet.model.SummaryDay;
import com.ar.ml.weather.planet.utils.Constant;
import com.ar.ml.weather.planet.utils.Utility;



public class FactoryImpl {
	
	private static WeatherPlanetHelper weatherPlanetHelper; 
	static Logger logger = Logger.getLogger(FactoryImpl.class);
	
	public WeatherPlanetHelper getInstance() {
		if(weatherPlanetHelper == null) {
			weatherPlanetHelper = new WeatherPlanetHelper();			
			weatherPlanetHelper.setVulcano(new Planet(Constant.PLANET_VULCANO, 
					Constant.MOVEMENT_FOR_DAY_VULCANO, Constant.TYPE_MOVENT_POS,
					Constant.RADIO_VULCANO, Constant.ANGULE_INITIAL_VULCANO));
			weatherPlanetHelper.setFeregi(new Planet(Constant.PLANET_FEREGI, 
					Constant.MOVEMENT_FOR_DAYL_FEREGI, Constant.TYPE_MOVENT_NEG, 
					Constant.RADIO_FEREGI, Constant.ANGULE_INITIAL_FEREGI));
			weatherPlanetHelper.setBetosoide(new Planet(Constant.PLANET_BETOSOIDE, 
					Constant.MOVEMENT_FOR_DAY_BETOSOIDE, Constant.TYPE_MOVENT_NEG, 
					Constant.RADIO_BETOSOIDE, Constant.ANGULE_INITIAL_BETOSOIDE));
		}
		return weatherPlanetHelper;
	}
	
	public Response stateDay(String days) {
		int daysValidate;
		ConsultResponseDTO conDto = null;
		Status status = null;
		String weatherDay = null;
		
		weatherPlanetHelper = getInstance();		
		daysValidate = Utility.validateNumber(days);		
		if(daysValidate == 0) {
			conDto = new ConsultResponseDTO(daysValidate, Constant.ERROR_MESSAGE_BAD_REQUEST);
			status = Response.Status.BAD_REQUEST;
			logger.warn("el dia es incorrecto, se reporte en el body de la respuesa, dato ingresado por el user: "+days);
		}else {
			weatherDay = weatherPlanetHelper.typeWeather(daysValidate);
			conDto = new ConsultResponseDTO(daysValidate, weatherDay);
			status = Response.Status.OK;
			logger.info("Respuesta OK, "+weatherDay);
		}
		return Response.status(status).entity(conDto).build();
	}
	
	public SummaryDay stateDay(int days) {
		double[] listValue = weatherPlanetHelper.typeWeatherTWO(days);
		//return new SummaryDay(days, listValue[0], listValue[1]);
		return null;
	}
}
