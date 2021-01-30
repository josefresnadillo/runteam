package com.runteam.core.api;

import java.math.BigDecimal;
import com.runteam.core.api.Error;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-30T21:50:04.103897+01:00[Europe/Madrid]")public interface UsersApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Add a new user to Runteam", notes = "", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful operation", response = User.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    User addUser(@Valid User user);

    @DELETE
    @Path("/{userId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Deletes a user", notes = "", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "UserId not found", response = Error.class),
        @ApiResponse(code = 409, message = "User owns a team or a challenge, so it can not be deleted. Delete teams or challenges owned by the user first", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    void deleteUser(@PathParam("userId") @ApiParam("UserId of the user to delete") String userId);

    @GET
    @Path("/{userId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Find a single user by ID", notes = "Returns a single user", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = User.class),
        @ApiResponse(code = 404, message = "UserId not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    User getUserById(@PathParam("userId") @ApiParam("ID of user to retrieve") String userId);

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Retrieve users", notes = "Returns users", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ssuccessful operation", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "No results", response = Void.class, responseContainer = "List"),
        @ApiResponse(code = 206, message = "successful operation results not completed", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class, responseContainer = "List") })
    List<User> getUsers(@QueryParam("username")   @ApiParam("Filter by username")  String username,@QueryParam("status")   @ApiParam("Filter by status")  List<String> status,@QueryParam("offset")   @ApiParam("Offset in the result list")  BigDecimal offset,@QueryParam("limit")   @ApiParam("Size of the result list")  BigDecimal limit,@QueryParam("sortField")   @ApiParam("Sort criteria - field (NAME, CREATIONDATE, METERS, ELEVATION, SECONDS, BEST5k, BEST10k, BESTMM, BESTM)")  List<String> sortField,@QueryParam("sortOrder")   @ApiParam("Sort criteria - order (ASC, DESC)")  String sortOrder);

    @PUT
    @Path("/{userId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Updates a user in Runteam", notes = "", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = User.class),
        @ApiResponse(code = 404, message = "UserId not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    User updateUser(@PathParam("userId") @ApiParam("ID of user to be updated") String userId,@Valid User user);
}
