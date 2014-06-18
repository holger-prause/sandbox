/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.movingimage24.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.movingimage24.cdi.AppBean;
import com.movingimage24.mapping.jpa.Movie;

@Path("/")
@RequestScoped
public class MovieService {    
	@Inject
	AppBean appBean;

    @POST
    @Path("/movies/create")
    @Produces(MediaType.APPLICATION_JSON)
	@Transactional(value = TxType.REQUIRES_NEW)
    public void createMovie(Movie movie) 
	{
    	appBean.persist(movie);
    }
    
    @GET
    @Path("/movies/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(value = TxType.NEVER)
    public List<Movie> listAllMovies() {
    	return appBean.list();
    }
}
