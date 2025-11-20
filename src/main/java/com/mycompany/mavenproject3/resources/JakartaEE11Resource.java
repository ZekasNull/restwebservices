package com.mycompany.mavenproject3.resources;

import data_objects.Studentits_Student;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author
 */
@Path("epok1")
public class JakartaEE11Resource {


	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("Test")
	public Studentits_Student pong()
	{
		DatabaseConnector DB = new DatabaseConnector();
		return DB.getStudentFromStudentits("venhul");
	}

	//private Studentits_Student student;

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("asd")
	public String pong2()
	{
		DatabaseConnector DB = new DatabaseConnector();
		var test = DB.getStudentFromStudentits("venhul");
		return "qwe3" + test;
	}

	@GET
	@Path("epok1")
	public Response epok1()
	{
		return Response
				.ok("Epok1")
				.build();
	}

	@GET
	@Path("epok2")
	public Response epok2()
	{
		return Response
				.ok("Epok2")
				.build();
	}
}
