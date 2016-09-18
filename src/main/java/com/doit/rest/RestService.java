package com.doit.rest;

import com.doit.data.User;
import com.doit.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/user")
public class RestService {

    @Autowired
    private UserDao dao;

    public Response response(int statusCode, String origin) {
        return Response
                .status(statusCode)
                .header("Access-Control-Allow-Origin", origin)
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() {
        List<User> list = dao.getAll();
        return Response
                .status(200)
                .entity(list)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

    @GET
    @Path("/getByRole/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserListByRole(@PathParam("id") int id) {
        List<User> list = dao.getByRole(id);
        return Response
                .status(200)
                .entity(list)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int userId, @HeaderParam("origin") String origin) {
        User user = dao.get(userId);
        System.out.println(origin);
        return Response
                .status(200)
                .entity(user)
                .header("Access-Control-Allow-Origin", origin)
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user, @HeaderParam("origin") String origin) {
        boolean flag = dao.post(user);
        if (flag)
            return response(201, origin);
        else
            return response(500, origin);
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int userId, @HeaderParam("origin") String origin) {
        boolean flag = dao.delete(userId);
        if (flag)
            return response(200, origin);
        else
            return response(500, origin);
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User user, @HeaderParam("origin") String origin) {
        boolean flag = dao.put(id, user);
        if (flag)
            return response(200, origin);
        else
            return response(500, origin);
    }

    @OPTIONS
    @Path("/delete/{id}")
    public Response optionsDelete(@HeaderParam("origin") String origin) {
        return response(200, origin);
    }

    @OPTIONS
    @Path("/add")
    public Response optionsPost(@HeaderParam("origin") String origin) {
        return response(200, origin);
    }

    @OPTIONS
    @Path("/update/{id}")
    public Response optionsPut(@HeaderParam("origin") String origin) {
        return response(200, origin);
    }

}