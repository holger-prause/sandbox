package com.movingimage24.cdi;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.movingimage24.cdi.dao.MovieDao;
import com.movingimage24.cdi.jms.JmsApp;
import com.movingimage24.mapping.jpa.Movie;


public class AppBean {
	@Inject
	MovieDao movieDao;
	
	@Inject
	JmsApp jmsApp;
	
	@Inject
	Logger logger;
	
	public void persist(Movie movie) {
		movieDao.persist(movie);
		//another change
		
		//jmsApp.send("perisisted: "+movie.getId());
		//List<String> messages = jmsApp.getMessages();
		
		if(movie.getName().equalsIgnoreCase("aaa"))
		{
			throw new RuntimeException("rollback");
		}
	}
	
	public List<Movie> list()
	{
		return movieDao.list();
	}
}
