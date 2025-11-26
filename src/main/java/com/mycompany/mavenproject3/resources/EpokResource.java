/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.resources;

import data_objects.epok.Epok_Modul;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("epok")
public class EpokResource {

	private final static DatabaseConnector db = new DatabaseConnector();

	// skriv typ /resources/epok/moduler?kurskod=1
	@GET
	@Path("moduler")
	@Produces(MediaType.APPLICATION_JSON)
	public static ArrayList<Epok_Modul> getModul(@QueryParam("kurskod") String kurskod)
	{

		if (kurskod == null || kurskod.isEmpty())
		{
			return new ArrayList<>();
		}

		return db.getModulesFromEpok(kurskod);
	}
}
