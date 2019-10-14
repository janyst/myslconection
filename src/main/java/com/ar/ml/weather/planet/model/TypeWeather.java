package com.ar.ml.weather.planet.model;

public enum TypeWeather {
	RAIN("lluvia",1.0),
	DROUGHT("sequia",2.0),
	PERFECT("clima perfecto", 3.0),
	NOINFORMATION("no poseemos informacion", 4.0);

	private String typeWeather;
	private double value;
	private TypeWeather(String typeWeather, double value) {
		this.typeWeather = typeWeather;
		this.value = value;
	}
	public String getTypeWeather() {
		return typeWeather;
	}
	public void setTypeWeather(String typeWeather) {
		this.typeWeather = typeWeather;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
