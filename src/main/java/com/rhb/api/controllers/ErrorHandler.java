package com.rhb.api.controllers;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.rhb.api.models.CCStatusResHeader;
import com.rhb.api.models.CCStatusResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {

    private static final Log LOG = LogFactory.getLog(ErrorHandler.class);
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleAllUncaughtException(
            NullPointerException exception,
            WebRequest request) {
        exception.printStackTrace();
        CCStatusResponse response = new CCStatusResponse();
        CCStatusResHeader header = new CCStatusResHeader();
        List<CCStatusResHeader> list = new ArrayList<>();
        header.setErrorDesc(System.getProperty("invalidRequestErrorDesc"));
        header.setErrorCode(System.getProperty("invalidReqCode"));
        header.setErrorMessage(System.getProperty("invalidReqMsg"));
        header.setErrorTag("Request Payload");
        list.add(header);
        response.setHeader(list);
        Gson gson = new Gson();
        return gson.toJson(response);
    }
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody String handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        exception.printStackTrace();
        CCStatusResponse response = new CCStatusResponse();
        Gson gson = new Gson();
        List<CCStatusResHeader> list = new ArrayList<>();
        CCStatusResHeader messageList = new CCStatusResHeader();
        messageList.setErrorCode(System.getProperty("dataTypeErrorCode"));

        String[] errorArray = exception.getLocalizedMessage().split("->");
        LOG.info(errorArray.length);
        if (errorArray.length > 0) {
            String error = errorArray[errorArray.length - 1];
            String[] errorArr = error.split("\"");
            if (errorArr.length > 1) {
                messageList.setErrorMessage(System.getProperty("invalidDataTypeErrorCode"));
                messageList.setErrorTag(errorArr[1]);
                messageList.setErrorDesc(System.getProperty("datatypeErrorDesc"));
            }
            else {
                messageList.setErrorMessage(System.getProperty("invalidReqMsg"));
                messageList.setErrorTag("Json Format Error");
                messageList.setErrorDesc(System.getProperty("invalidRequestErrorDesc"));
            }
        }

        list.add(messageList);
        response.setHeader(list);

        return gson.toJson(response);
    }
}
