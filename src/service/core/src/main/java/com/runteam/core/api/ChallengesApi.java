package com.runteam.core.api;

import java.math.BigDecimal;
import com.runteam.core.api.Challenge;
import com.runteam.core.api.ChallengeTeamMember;
import com.runteam.core.api.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/challenges")
@Api(description = "the challenges API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-30T21:50:04.103897+01:00[Europe/Madrid]")public interface ChallengesApi {

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Add a new challenge to Runteam", notes = "", tags={ "challenges" })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful operation", response = Challenge.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    Challenge addChallenge(@Valid Challenge challenge);

    @PUT
    @Path("/{challengeId}/teams/{teamId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Add a team member to the challenge", notes = "", tags={ "challenges" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "ChallengeId or TeamId not found (see error)", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    void addChallengeMember(@PathParam("challengeId") @ApiParam("ID of the challenge to be updated") String challengeId,@PathParam("teamId") @ApiParam("ID of the team to be a new member of the challenge") String teamId);

    @DELETE
    @Path("/{challengeId}/teams/{teamId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Deletes a challenge member", notes = "", tags={ "challenges" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "ChallengeId or TeamId not found (see error)", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    void deleteChallengeMember(@PathParam("challengeId") @ApiParam("ChallengeId to delete") String challengeId,@PathParam("teamId") @ApiParam("ID of the team to be deleted of the challenge") String teamId);

    @GET
    @Path("/{challengeId}")
    @Produces({ "application/json" })
    @ApiOperation(value = "Retrieve a single challenge", notes = "Return a single challenge", tags={ "challenges" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Challenge.class),
        @ApiResponse(code = 404, message = "Challenge Id not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    Challenge getChallenge(@PathParam("challengeId") @ApiParam("Id of the challenge to retrieve") String challengeId);

    @GET
    @Path("/{challengeId}/teams")
    @Produces({ "application/json" })
    @ApiOperation(value = "Retrieve challenge members", notes = "Returns challenge members", tags={ "challenges" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = ChallengeTeamMember.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "No results", response = Void.class, responseContainer = "List"),
        @ApiResponse(code = 206, message = "successful operation results not completed", response = ChallengeTeamMember.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "ChallengeId not found", response = Error.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class, responseContainer = "List") })
    List<ChallengeTeamMember> getChallengeMembers(@PathParam("challengeId") @ApiParam("ID of the challenge to return") String challengeId,@QueryParam("status")   @ApiParam("Filter by status")  List<String> status,@QueryParam("offset")   @ApiParam("Offset in the result list")  BigDecimal offset,@QueryParam("limit")   @ApiParam("Size of the result list")  BigDecimal limit,@QueryParam("sortField")   @ApiParam("Sort criteria - field (NAME, CREATIONDATE, METERS, ELEVATION, SECONDS)")  List<String> sortField,@QueryParam("sortOrder")   @ApiParam("Sort criteria - order (ASC, DESC)")  String sortOrder);

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Retrieve challenges", notes = "Returns challenges", tags={ "challenges" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Challenge.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "No results", response = Void.class, responseContainer = "List"),
        @ApiResponse(code = 206, message = "Successful operation results not completed", response = Challenge.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class, responseContainer = "List") })
    List<Challenge> getChallenges(@QueryParam("status")   @ApiParam("Filter by status")  List<String> status,@QueryParam("offset")   @ApiParam("Offset in the result list")  BigDecimal offset,@QueryParam("limit")   @ApiParam("Size of the result list")  BigDecimal limit,@QueryParam("sortField")   @ApiParam("Sort criteria - field (NAME, CREATIONDATE, METERS, ELEVATION, SECONDS, TAG)")  List<String> sortField,@QueryParam("sortOrder")   @ApiParam("Sort criteria - order (ASC, DESC)")  String sortOrder,@QueryParam("owner.id")   @ApiParam("Filter criteria by owner id")  String ownerId);

    @PUT
    @Path("/{challengeId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Updates a challenge in Runteam", notes = "", tags={ "challenges" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Challenge.class),
        @ApiResponse(code = 404, message = "UserId not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    Challenge updateChallenge(@PathParam("challengeId") @ApiParam("ID of the challenge to be updated") String challengeId,@Valid Challenge challenge);
}
