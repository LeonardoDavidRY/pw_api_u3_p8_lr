package uce.edu.web.api.controller;

import java.time.Duration;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/generate")
public class TokenController {

    @GET
    @Path("")
    public String generar() {
        
        return Jwt.issuer("Http://uce.edu.ec").upn("micorreo@uce.edu.ec").groups("admin").expiresIn(Duration.ofSeconds(30)).sign();

    }

}
