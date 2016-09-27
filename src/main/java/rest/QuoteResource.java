/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author xboxm
 */
@Path("quote")
public class QuoteResource {

    private static Map<Integer,String> quotes = new HashMap() {
        {
            put(1,"Friends are kisses blown to us by angels");
            put(2,"Do not take life too seriously. You will never get out of it alive");
            put(3,"Behind every great man, is a woman rolling her eyes");
            put(4, "Hunger is the best seasoning.");
            put(5, "A dog bitten by a snake is afraid of sausages.");
            put(6, "The worst blind is the one who doesn't want to see. ");
        }
    };

    @Context
    private UriInfo context;
    private Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of GenericResource
     */
    public QuoteResource() {
    }

    /**
     * Retrieves representation of an instance of exercises.module3restful.GenericResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    public String getText(){
        return "Hello from get";
    }
    
    @GET
    @Path("random")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRandom() {
        //TODO return proper representation object
        Random rand = new Random();
        return quotes.get(rand.nextInt(6)+1);
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") int id) {
        //TODO return proper representation object
        String str = quotes.get(id);
        String jsonQuote = gson.toJson(str);
        return jsonQuote;
    }
    
//    @POST
//    @Consumes(MediaType.TEXT_PLAIN)
//    public void changeQuote(String msg, int id) {
//        quotes.replace(id, msg);
//    }
//    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteQuote(@PathParam("id") int id) {
        quotes.remove(id);
        return "We deleted quote with id: " + String.valueOf(id);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param msg 
     * @return  
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String makeQuote(String msg) {
        int newId = quotes.size()+1;
        quotes.put(newId, msg);
        return "We added a new quote with id: " + String.valueOf(newId);
    }
    
}
