package com.ar.ml.weather.planet.helper;

import org.apache.log4j.Logger;

import com.ar.ml.weather.planet.model.Planet;
import com.ar.ml.weather.planet.model.TypeWeather;
import com.ar.ml.weather.planet.utils.Constant;
import com.ar.ml.weather.planet.utils.DecimalUtils;

public class WeatherPlanetHelper {
	private static final int X0 = 0;
	private static final int Y0 = 0;
	static Logger logger = Logger.getLogger(WeatherPlanetHelper.class);
	
	private Planet vulcano;
	private Planet feregi;
	private Planet betosoide;
	
	public Planet getVulcano() {
		return vulcano;
	}

	public void setVulcano(Planet vulcano) {
		this.vulcano = vulcano;
	}

	public Planet getFeregi() {
		return feregi;
	}

	public void setFeregi(Planet feregi) {
		this.feregi = feregi;
	}

	public Planet getBetosoide() {
		return betosoide;
	}

	public void setBetosoide(Planet betosoide) {
		this.betosoide = betosoide;
	}

	public String typeWeather(int days) {
		String response = null;
		double maxRain;
		String line = null;
		
		this.vulcano.getAngule(days);		
		this.feregi.getAngule(days);
		this.betosoide.getAngule(days);
		
		line = this.straightLine(this.getVulcano().getX(), this.getVulcano().getY(), this.getFeregi().getX(), this.getFeregi().getY(),
				this.getBetosoide().getX(), this.getFeregi().getY());
		maxRain = this.triangle(this.getVulcano().getX(), this.getVulcano().getY(), this.getFeregi().getX(), this.getFeregi().getY(),
				this.getBetosoide().getX(), this.getFeregi().getY(), X0, Y0);
		
		if(maxRain < 0) {
			response = Constant.TYPE_WEATHER_WINTER;
		}else if(line != null){
			response = line;
		}else{
			response = Constant.TYPE_WEATHER_FALL;
		}
		return response;
		
	}
	
	public double[] typeWeatherTWO(int days) {
		double triangle;
		String sunnyOrPerfDay = null;
		double[]listValue = new double[2];
		
		this.vulcano.getAngule(days);		
		this.feregi.getAngule(days);
		this.betosoide.getAngule(days);		
		
		triangle = this.triangle(this.getVulcano().getX(), this.getVulcano().getY(), this.getFeregi().getX(), this.getFeregi().getY(),
				this.getBetosoide().getX(), this.getFeregi().getY(), X0, Y0);
		
		if(triangle < 0) {
			listValue[0] = TypeWeather.RAIN.getValue();
			listValue[1] = triangle;
		}else {
			sunnyOrPerfDay = this.straightLine(this.getVulcano().getX(), this.getVulcano().getY(), this.getFeregi().getX(), this.getFeregi().getY(),
					this.getBetosoide().getX(), this.getFeregi().getY());
			if(sunnyOrPerfDay != null) {
				listValue[0] = 0;
				listValue[1] = 0;
			}else {
				listValue[0] = TypeWeather.NOINFORMATION.getValue();
				listValue[1] = 0;
			}
		}
		return listValue;
	}
	
	
	private String straightLine(double x1, double y1, double x2, double y2, double x3, double y3) {
		String typeLine = null;
		if(equationStraight(x1, y1, x2, y2, x3, y3)){
			typeLine = Constant.TYPE_WEATHER_SPRING;
			if(equationStraight(x1, y1, x2, y2, X0, Y0)) {
				typeLine = Constant.TYPE_WEATHER_SUMER;
			}			
		}
		return typeLine;				
	}
	
	//validar linea recta.
	private boolean equationStraight (double x1, double y1, double x2, double y2, double x3, double y3){		
	    //return ((y3 - y2) * (x2 - x1) ==  (y2 - y1) * (x3 - x2)) ;
		return (DecimalUtils.between((y3 - y2) * (x2 - x1), (y2 - y1) * (x3 - x2))) ;
	}
	
	//calcular area de un triangulo.
	private double area(double x1, double y1, double x2, double y2, double x3, double y3) {
       return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0); 
	}
	
	//validar si es un triangulo y su centro es 0,0
	private double triangle(double x1, double y1, double x2, double y2, double x3, double y3, double x0, double y0){		
		double perimeter = 0; 
		double area = area (x1, y1, x2, y2, x3, y3); //Calculate area of triangle points ABC 
        double a = area (x0, y0, x2, y2, x3, y3); //Calculate area of triangle points 0BC
        double b = area (x1, y1, x0, y0, x3, y3); //Calculate area of triangle points 0AC
        double c = area (x1, y1, x2, y2, x0, y0); //Calculate area of triangle points 0AB
        if(area == a + b + c)
        	perimeter = perimeterTriangle(x1, y1, x2, y2, x3, y3);
        return perimeter;
	}
	
	private double perimeterTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		double distanceAB = 0;
		double distanceAC = 0;
		double distanceBC = 0;		
		distanceAB = distanceTwoPoints(x1, y1, x2, y2);
		distanceAC = distanceTwoPoints(x1, y1, x3, y3);
		distanceBC = distanceTwoPoints(x2, y2, x3, y3);
		return distanceAB + distanceAC + distanceBC;		
	}
	
	private double distanceTwoPoints(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));	 
	}
}
