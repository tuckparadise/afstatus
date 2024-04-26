package com.rhb.api.controllers;

import com.rhb.api.models.CCReqBody;
import com.rhb.api.models.CCStatusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhb.api.service.CCStatusService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-26T06:53:14.739924Z[GMT]")
@RestController
public class StatusApiController implements StatusApi {

    private static final Logger log = LoggerFactory.getLogger(StatusApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StatusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    @Autowired
    CCStatusService service;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<CCStatusResponse> cCStatusTracking(
            @Parameter(in = ParameterIn.DEFAULT, description = "body", required=true, schema=@Schema())
            @RequestBody CCReqBody body,
            @Parameter(in = ParameterIn.HEADER , description = "Partner Email",
            required=true, schema=@Schema()) @RequestHeader(value = "respEmail") String respEmail) {
        ResponseEntity<CCStatusResponse> response = new ResponseEntity<CCStatusResponse>(HttpStatus.OK);
        String accept = request.getHeader("Accept");
        if (accept != null) {
            response = service.pfStatusRequest(body, respEmail);
        }

        return response;
    }

    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

}
