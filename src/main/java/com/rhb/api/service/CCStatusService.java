package com.rhb.api.service;


import com.rhb.api.models.CCReqBody;
import com.rhb.api.models.CCStatusResponse;


import org.springframework.http.ResponseEntity;

public interface CCStatusService {

    ResponseEntity<CCStatusResponse> pfStatusRequest(CCReqBody body, String respEmail);

}
