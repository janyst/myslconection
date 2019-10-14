package com.ar.ml.weather.planet.application;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ar.ml.weather.planet.dao.FactoryDao;
import com.ar.ml.weather.planet.service.FactoryImpl;

@Path("/state") 
@Produces(MediaType.APPLICATION_JSON) 

public class HelloWorldRest {
	FactoryImpl factoryImpl;
	
	static Logger logger = Logger.getLogger(HelloWorldRest.class);
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String days) { 
		factoryImpl = new FactoryImpl();
		FactoryDao factoryDao = new FactoryDao();
		factoryImpl.getInstance();		
		logger.info("informacion para el dia: "+days);
		try {
			factoryDao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factoryImpl.stateDay(days);
    } 
}
