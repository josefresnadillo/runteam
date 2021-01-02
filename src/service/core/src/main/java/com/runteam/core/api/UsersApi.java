package com.runteam.core.api;

import com.runteam.core.api.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/users")
@Api(description = "the users API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-02T16:39:11.316253+01:00[Europe/Madrid]")public interface UsersApi {

    @POST
    @Consumes({ "application/json" })
    @ApiOperation(value = "Add a new user to Runteam", notes = "", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    void addUser(@Valid User user);

    @DELETE
    @Path("/{userId}")
    @ApiOperation(value = "Deletes a user", notes = "", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 404, message = "User not found", response = Void.class) })
    void deleteUser(@PathParam("userId") @ApiParam("User id to delete") String userId);

    @GET
    @Path("/{userId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Find user by ID", notes = "Returns a single user", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = User.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 404, message = "User not found", response = Void.class) })
    User getUserById(@PathParam("userId") @ApiParam("ID of user to return") String userId);

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Retrieve users", notes = "Returns users", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "User not found", response = Void.class, responseContainer = "List") })
    List<User> getUsers();

    @PUT
    @Path("/{userId}")
    @Consumes({ "application/json" })
    @ApiOperation(value = "Updates a user in Runteam", notes = "", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    void updateUser(@PathParam("userId") @ApiParam("ID of user that needs to be updated") String userId,@Valid User user);
}
