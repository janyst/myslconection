package com.ar.ml.weather.planet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ar.ml.weather.planet.model.SummaryDay;

public class FactoryDao {
	private final String TRUNCATE_TABLE = "TRUNCATE TABLE `SUMMARY-WEATHER`.RAIN_DAY ; TRUNCATE TABLE `SUMMARY-WEATHER`.SUMMARY_WEATHER";		
	private Connection con = null;
	
	public List<SummaryDay> getRegister (List<SummaryDay> lisSummaryDays) {
		PreparedStatement preparedStatement = null;
		String[] arrySql = null;
		List<SummaryDay> listsuDays = null;
		try{
			arrySql = this.getSQL(lisSummaryDays);
			if(arrySql != null) {
				this.getConnection();
				preparedStatement = con.prepareStatement(TRUNCATE_TABLE);
				
				preparedStatement.executeUpdate();	//truncate table.			
				preparedStatement.executeUpdate(arrySql[0]); //insert SUMMARY_WEATHER				
				if(arrySql[1] != null)//insert RAIN_DAY
					preparedStatement.executeUpdate(arrySql[1]);
				listsuDays = this.getSummaryDay();
				
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listsuDays;
	}
	
	public List<SummaryDay> getSummaryDay() {
		ResultSet rs = null;	
		PreparedStatement preparedStatement = null;
		List<SummaryDay> listSummaryDays = null;
		
		try {
			if(con == null) {
				this.getConnection();					
			}
			preparedStatement = con.prepareStatement(this.getSql());
			rs = preparedStatement.executeQuery(this.getSql());
			
			listSummaryDays = new ArrayList<SummaryDay>();
			while(rs.next()) {
				listSummaryDays.add(new SummaryDay(rs.getInt("NUMBER_DAY"), rs.getString("DESCRIPTION_WEATHER"), rs.getDouble("PERIMETER")));
			}	
			this.getClose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSummaryDays;
	}
	
	public void getConnection() throws Exception{	
		try {
            this.con = DriverManager.getConnection("jdbc:mysql://database-weather.cp02rueziard.us-east-1.rds.amazonaws.com:3306/SUMMARY-WEATHER?user=admin&password=admin123");			
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
	}
	
	private void getClose() throws Exception{	
		try {
			this.con.close();			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	private String[] getSQL(List<SummaryDay> listSummary) {
		StringBuffer insertSummaryWeather = null;
		StringBuffer insertMaxRain = null;
		String[] arrySql = null;
		
		if(!listSummary.isEmpty()) {
			arrySql = new String[3];
			insertSummaryWeather = new StringBuffer();		
			insertSummaryWeather.append("INSERT INTO `SUMMARY-WEATHER`.SUMMARY_WEATHER (ID, ID_TYPE_WEATHER) ");
			for(SummaryDay summary : listSummary) {			
				insertSummaryWeather.append("VALUES ("+summary.getDay()+","+summary.getTypeWeather()+"), ");
				if(summary.getPerimeter() != null) {
					if(insertMaxRain == null) {
						insertMaxRain = new StringBuffer();
						insertMaxRain.append("INSERT INTO `SUMMARY-WEATHER`.RAIN_DAY (ID, PERIMETER) ");
					}
					insertMaxRain.append("VALUES ("+summary.getDay()+","+summary.getPerimeter()+"), ");
				}					
			}
			arrySql[0] = insertSummaryWeather.toString();
			arrySql[1] = insertMaxRain.toString();
			arrySql[2] = this.getSql();
		}
		return arrySql;
	}
	
	private String getSql() {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT ");
		sql.append("B.ID ID_WEATHER, ");
		sql.append("B.DESCRIPTION DESCRIPTION_WEATHER, ");
		sql.append("A.ID NUMBER_DAY, ");
		sql.append("C.PERIMETER PERIMETER ");
		sql.append("FROM `SUMMARY-WEATHER`.SUMMARY_WEATHER A ");
		sql.append("INNER JOIN `SUMMARY-WEATHER`.TYPE_WEATHER B ON B.ID = A.ID_TYPE_WEATHER ");
		sql.append("LEFT JOIN `SUMMARY-WEATHER`.RAIN_DAY C ON C.ID = A.ID ");
		return sql.toString();
	}
	
	public static void main(String[] args) {
		FactoryDao factoryDao = new FactoryDao();
		try {
			factoryDao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
