package com.ecommerce.sampleendpoint;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SampleRestController {

    @GET
    public String running() {
        return new Gson().toJson("running");
    }

}
