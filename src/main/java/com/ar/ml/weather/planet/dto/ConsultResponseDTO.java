package com.ar.ml.weather.planet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultResponseDTO {
	
	@JsonProperty("dia")
	private int day;
	@JsonProperty("clima")
	private String weather;
	
	public ConsultResponseDTO (int day, String weather) {
		this.day = day;
		this.weather = weather;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
}
