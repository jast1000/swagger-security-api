package io.swagger.controller;

import io.swagger.model.VehiclesPositions;
import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Api(value = "positions", tags = "position", authorizations = { @Authorization("api_Key"), @Authorization("basicAuth") } )
@RequestMapping(value = "/v2")
public class PositionsControllerImpl implements PositionsController {

	private final Logger LOG = LoggerFactory.getLogger(PositionsControllerImpl.class);
	
    public PositionsControllerImpl() {
    }

    @ApiOperation(
    	value = "Vehicle position service", 
    	nickname = "addPositions", 
    	notes = "", 
    	tags= { "position" })
    @ApiResponses(
    	value = { 
	        @ApiResponse(code = 201, message = "Created"),
	        @ApiResponse(code = 401, message = "User not authorized"),
	        @ApiResponse(code = 500, message = "Internal server error")})
    @RequestMapping(
    	value = "/positions",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    public ResponseEntity<?> addPositions(HttpServletRequest request, @ApiParam(value = "Vehicle positions info" ,required=true ) @Valid @RequestBody VehiclesPositions body) {
    	//Get the API Key, That is not important because Google validate it 
    	String apiKey = request.getParameter("key");
    	LOG.info(apiKey);
    	
    	//The authorization value header should be validate in the business logic not here...
    	
    	//The Authorization header is very important, if it is null then reject the request 
    	String authorizationValue = request.getHeader("Authorization");
    	if (authorizationValue == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	LOG.info(authorizationValue);
    	
    	//If authorization header not is empty then the text should be start with "Basic "
    	if (!authorizationValue.contains("Basic ")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	
    	String valueBase64 = authorizationValue.replace("Basic ", "");
    	String userPassword = new String(Base64.getDecoder().decode(valueBase64));
    	
    	//If the value after decode the base64 not has ":" then the credentials are invalid
    	if (!userPassword.contains(":")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    	String[] userPasswordParts = userPassword.split(":");
    	LOG.info("User: {}, password: {}", userPasswordParts[0], userPasswordParts[1]);
        
    	return ResponseEntity.ok().build();
    }

}
