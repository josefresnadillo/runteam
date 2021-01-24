package com.runteam.core.api;

import java.math.BigDecimal;
import com.runteam.core.api.Error;
import com.runteam.core.api.Team;
import com.runteam.core.api.UserTeamMember;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/teams")
@Api(description = "the teams API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-24T20:28:43.552700+01:00[Europe/Madrid]")public interface TeamsApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Add a new team to Runteam", notes = "", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful operation", response = Team.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    Team addTeam(@Valid Team team);

    @PUT
    @Path("/{teamId}/users/{userId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Add a team member to the team", notes = "", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "TeamId or UserId not found (see error)", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    void addTeamMember(@PathParam("teamId") @ApiParam("ID of the team to be updated") String teamId,@PathParam("userId") @ApiParam("ID of the user to be a new member of the team") String userId);

    @DELETE
    @Path("/{teamId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Deletes a team", notes = "", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "TeamId not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    void deleteTeam(@PathParam("teamId") @ApiParam("TeamId to delete") String teamId);

    @DELETE
    @Path("/{teamId}/users/{userId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Deletes a team member", notes = "", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "TeamId or UserId not found (see error)", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    void deleteTeamMember(@PathParam("teamId") @ApiParam("TeamId to delete") String teamId,@PathParam("userId") @ApiParam("ID of the user to be deleted of the team") String userId);

    @GET
    @Path("/{teamId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Find a single team by ID", notes = "Returns a single team", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Team.class),
        @ApiResponse(code = 404, message = "UserId not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    Team getTeamById(@PathParam("teamId") @ApiParam("ID of team to return") String teamId);

    @GET
    @Path("/{teamId}/users")
    @Produces({ "application/json" })
    @ApiOperation(value = "Retrieve team members", notes = "Returns team members", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserTeamMember.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "No results", response = Void.class, responseContainer = "List"),
        @ApiResponse(code = 206, message = "Successful operation results not completed", response = UserTeamMember.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "TeamId not found", response = Error.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class, responseContainer = "List") })
    List<UserTeamMember> getTeamMembers(@PathParam("teamId") @ApiParam("ID of team to return") String teamId,@QueryParam("status")   @ApiParam("Filter by status")  List<String> status,@QueryParam("offset")   @ApiParam("Offset in the result list")  BigDecimal offset,@QueryParam("limit")   @ApiParam("Size of the result list")  BigDecimal limit,@QueryParam("sortField")   @ApiParam("Sort criteria - field (NAME, CREATIONDATE, METERS, ELEVATION, SECONDS, BEST5K, BEST10K, BESTMM, BESTM)")  List<String> sortField,@QueryParam("sortOrder")   @ApiParam("Sort criteria - order (ASC, DESC)")  String sortOrder);

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Retrieve teams", notes = "Returns teams", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Team.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "No results", response = Void.class, responseContainer = "List"),
        @ApiResponse(code = 206, message = "successful operation results not completed", response = Team.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class, responseContainer = "List") })
    List<Team> getTeams(@QueryParam("owner.id")   @ApiParam("Filter criteria by owner id")  String ownerId,@QueryParam("status")   @ApiParam("Filter by status")  List<String> status,@QueryParam("offset")   @ApiParam("Offset in the result list")  BigDecimal offset,@QueryParam("limit")   @ApiParam("Size of the result list")  BigDecimal limit,@QueryParam("sortField")   @ApiParam("Sort criteria - field (NAME, CREATIONDATE, METERS, ELEVATION, SECONDS)")  List<String> sortField,@QueryParam("sortOrder")   @ApiParam("Sort criteria - order (ASC, DESC)")  String sortOrder);

    @PUT
    @Path("/{teamId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Updates a team in Runteam", notes = "", tags={ "teams" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Team.class),
        @ApiResponse(code = 404, message = "UserId not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    Team updateTeam(@PathParam("teamId") @ApiParam("ID of team to be updated") String teamId,@Valid Team team);
}
