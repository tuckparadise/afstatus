package com.rhb.api.utilities;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import com.rhb.api.models.CCReqBody;
import com.rhb.api.models.CCStatusResHeader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


@Component
public class CCStatusValidaors {

    String blankErrorCode = "blankErrorCode";
    String strToDate = "toDate";
    String primaryID = "primaryID";
    String externalRefID = "externalRefID";
    String blankCheckErrorCode = "blankCheckErrorCode";
    String blankErrorDesc = "blankErrorDesc";
    String strFromDate = "fromDate";

    private static final Log log = LogFactory.getLog(CCStatusValidaors.class);
    public List<CCStatusResHeader> isValidPayload(CCReqBody body, String respEmail) {
        List<CCStatusResHeader> list = new ArrayList<>();
        if(Boolean.TRUE.equals(validatePrimaryID(body, list) && validateExternalRefID(body, list) &&
                validateDate(body, list) && validateRespEmail(respEmail, list))) {
            return list;
        }
        return list;
    }
    public Boolean validatePrimaryID(CCReqBody body, List<CCStatusResHeader> list) {
        if (body.getPrimaryID().isBlank() || body.getPrimaryID().isEmpty()) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty(blankErrorCode));
            messageList.setErrorMessage(System.getProperty(blankCheckErrorCode));
            messageList.setErrorTag(primaryID);
            messageList.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(messageList);
        } else if (!body.getPrimaryID().matches("\\d+")) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty("invalidTypeErrorCode"));
            messageList.setErrorMessage(System.getProperty("invalidDataTypeErrorCode"));
            messageList.setErrorTag(primaryID);
            messageList.setErrorDesc(System.getProperty("datatypeErrorDesc"));
            list.add(messageList);
        } else if (body.getPrimaryID().length() > 20) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty("lengthErrorCode"));
            messageList.setErrorMessage(System.getProperty("invalidLengthErrorCode"));
            messageList.setErrorTag(primaryID);
            messageList.setErrorDesc(System.getProperty("lengthErrorDesc"));
            list.add(messageList);
        }
        return true;
    }

    public Boolean validateExternalRefID(CCReqBody body, List<CCStatusResHeader> list) {
        if (body.getExternalRefID().isBlank() || body.getExternalRefID().isEmpty()) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty(blankErrorCode));
            messageList.setErrorMessage(System.getProperty(blankCheckErrorCode));
            messageList.setErrorTag(externalRefID);
            messageList.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(messageList);
        } else if (!body.getExternalRefID().matches(System.getProperty("alphanumericPattern"))) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty("invalidCharacterErrorCode"));
            messageList.setErrorMessage(System.getProperty("invalidSpecialCharacterErrorCode"));
            messageList.setErrorTag(externalRefID);
            messageList.setErrorDesc(System.getProperty("invalidCharacterErrorDesc"));
            list.add(messageList);
        } else if (body.getExternalRefID().length() > 30) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty("lengthErrorCode"));
            messageList.setErrorMessage(System.getProperty("invalidLengthErrorCode"));
            messageList.setErrorTag(externalRefID);
            messageList.setErrorDesc(System.getProperty("lengthErrorDesc"));
            list.add(messageList);
        }
        return true;
    }

    public Boolean validateRespEmail(String respEmail, List<CCStatusResHeader> list) {
        if (isValidRespEmail(respEmail)) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty(blankErrorCode));
            messageList.setErrorMessage(System.getProperty(blankCheckErrorCode));
            messageList.setErrorTag("respEmail");
            messageList.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(messageList);
        }
        return true;
    }

    public Boolean validateDate(CCReqBody body, List<CCStatusResHeader> list) {
        if (body.getFromDate().isEmpty() || body.getFromDate().isBlank()) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty(blankErrorCode));
            messageList.setErrorMessage(System.getProperty(blankCheckErrorCode));
            messageList.setErrorTag(strFromDate);
            messageList.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(messageList);
        }
        if (body.getToDate().isEmpty() || body.getToDate().isBlank()) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty(blankErrorCode));
            messageList.setErrorMessage(System.getProperty(blankCheckErrorCode));
            messageList.setErrorTag(strToDate);
            messageList.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(messageList);
        } else {
           if (Boolean.TRUE.equals(validateDateFormats(body, list)))
               log.info("");
        }
        return true;
    }

    public Boolean validateDateFormats(CCReqBody body, List<CCStatusResHeader> list) {
        if ((!body.getFromDate().isEmpty() && !body.getFromDate().isBlank()) && (
                !body.getToDate().isBlank() && !body.getToDate().isEmpty())) {
            LocalDate fromDate = validateDates(body.getFromDate(), list, strFromDate);
            LocalDate toDate = validateDates(body.getToDate(), list, strToDate);
            for (CCStatusResHeader header : list) {
                if (header.getErrorTag().equalsIgnoreCase(strFromDate) || header.getErrorTag().equalsIgnoreCase(strToDate))
                    return false;
            }
            if(checkFutureDate(fromDate, list, strFromDate) && checkFutureDate(toDate, list, strToDate)) {
               checkDate(fromDate, toDate, list);
            }
        }
        return true;
    }

    public void checkDate(LocalDate fromDate, LocalDate toDate, List<CCStatusResHeader> list) {
        if (fromDate.isAfter(toDate)) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty("dateRangeErrorCode"));
            messageList.setErrorMessage(System.getProperty("invalidForFutureDate"));
            messageList.setErrorTag(strFromDate);
            messageList.setErrorDesc(System.getProperty("invalidFromDateRangeErrorDesc"));
            list.add(messageList);
        }
    }
    public Boolean checkFutureDate(LocalDate date,List<CCStatusResHeader> list, String fieldName) {
        LocalDate today = LocalDate.now();
    if (date.isAfter(today)) {
        CCStatusResHeader messageList = new CCStatusResHeader();
        messageList.setErrorCode(System.getProperty("invalidValueErrorCode"));
        messageList.setErrorMessage(System.getProperty("invalidDateErrorCode"));
        messageList.setErrorTag(fieldName);
        messageList.setErrorDesc(System.getProperty("invalidDateValueErrorDesc"));
        list.add(messageList);
        return false;
    }
    return true;
    }
    public LocalDate validateDates(String date, List<CCStatusResHeader> list, String tag) {
        LocalDate fromDate = LocalDate.now();
        try {
            fromDate = LocalDate.parse(date);
            log.info(fromDate);
        } catch (DateTimeException e) {
            CCStatusResHeader messageList = new CCStatusResHeader();
            messageList.setErrorCode(System.getProperty("dateFormatErrorCode"));
            messageList.setErrorMessage(System.getProperty("dateMismatchErrorCode"));
            messageList.setErrorTag(tag);
            if (e.getLocalizedMessage().contains(":"))
                messageList.setErrorDesc(e.getLocalizedMessage().split(":")[1]);
            else
                messageList.setErrorDesc(System.getProperty("invalidFormatErrorDesc"));
            list.add(messageList);
        }
        return fromDate;
    }
    public boolean isValidRespEmail(String respEmail) {
        boolean value=false;
        if (respEmail == null || respEmail.isEmpty() || respEmail.isBlank()) {
            value=true;
        }
        return value;
    }
}



