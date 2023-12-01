package org.example;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;
import sun.net.httpserver.DefaultHttpServerProvider;

public class Main {
    private static DefaultHttpServerProvider GrizzlyHttpServerFactory;

    public static void main(String[] args) {
        String BASE_URI = "http://localhost:8080/";
        ResourceConfig resourceConfig = new ResourceConfig(BookResource.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),resourceConfig);
        System.out.println("Server started at: " + BASE_URI);



    }
}
