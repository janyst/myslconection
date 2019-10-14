package com.ar.ml.weather.planet.model;

public class SummaryDay {
	private int day;
	private String typeWeather;
	private Double perimeter;
	
	public SummaryDay(int day, String typeWeather, Double perimeter) {
		this.day = day;
		this.typeWeather = typeWeather;
		this.perimeter = perimeter;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getTypeWeather() {
		return typeWeather;
	}
	public void setTypeWeather(String typeWeather) {
		this.typeWeather = typeWeather;
	}
	public Double getPerimeter() {
		return perimeter;
	}
	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}
}
