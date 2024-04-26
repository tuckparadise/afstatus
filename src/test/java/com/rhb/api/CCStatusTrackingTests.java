package com.rhb.api;


import com.rhb.api.models.CCReqBody;
import com.rhb.api.models.CCStatusResHeader;
import com.rhb.api.service.CCStatusService;
import com.rhb.api.models.CCStatusReqHeader;
import com.rhb.api.models.CCStatusRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.w3c.dom.Document;

@SpringBootTest
class CCStatusTrackingTests {

    @Autowired
    Environment env;

    List<CCStatusResHeader> expectedList = new ArrayList<>();

    @Autowired
    CCStatusService service;

    public CCReqBody populateReqBodyValues(){
        CCReqBody body = new CCReqBody();
        body.setExternalRefID("201910011915280000000000000250");
        body.setFromDate("2012-04-12");
        body.setPrimaryID("8809010178900099880");
        body.setToDate("2016-06-17");
        return body;
    }

    public void populateValues() {

        System.setProperty("flashErrorList", "");
        System.setProperty("pfStatusUrl", "http://172.30.84.240:8083/APIFlash");
        System.setProperty("invalidApplication", "Application not found");
        System.setProperty("invalidApplicationCode", "API1012");
        System.setProperty("blankErrorCode", "API1002");
        System.setProperty("dateFormatErrorCode", "API1008");
        System.setProperty("lengthErrorCode", "API1004");
        System.setProperty("invalidErrorCode", "API1005");
        System.setProperty("invalidTypeErrorCode", "API1003");
        System.setProperty("invalidCharacterErrorCode", "API1006");
        System.setProperty("dateRangeErrorCode", "API1010");
        System.setProperty("invalidValueErrorCode", "API1011");

        System.setProperty("blankCheckErrorCode", "Mandatory field cannot be blank");
        System.setProperty("invalidDataTypeErrorCode", "Input data is invalid data type");
        System.setProperty("invalidLengthErrorCode", "Invalid data field length");
        System.setProperty("invalidFieldValueErrorCode", "Provide valid field value");
        System.setProperty("invalidSpecialCharacterErrorCode", "Invalid Characters");
        System.setProperty("invalidDateErrorCode", "Invalid Date Value");
        System.setProperty("dateMismatchErrorCode", "Invalid File Format");
        System.setProperty("invalidForFutureDate", "Invalid Date Range");
    }

    public CCStatusReqHeader populateReqHeaderValues(){
        CCStatusReqHeader header = new CCStatusReqHeader();
        header.setMessageRefNo("PLECO000120220611012345000001");
        header.setDestQueue("RHB.DCMS.EAI.DCMS.APPLSTATUSRES.QL");
        header.setDestQueueManager("PMYQMB01");
        header.setDplName(null);
        header.setErrorCode(null);
        header.setErrorMessage(null);
        header.setExtTransactionCode("REAIDCI1");
        header.setFiller(null);
        header.setLoginID(808048);
        header.setNoOfRecords(null);
        header.setOverrideTellerId(null);
        header.setMessageVersionNo(1.0);
        header.setPan(null);
        header.setRecordLength(null);
        header.setReplyRefNo(null);
        header.setSourceID("MERC");
        header.setTerminalID(null);
        header.setTransactionDate(11082019);
        header.setTransactionTime(031735);

        return header;
    }

    @DisplayName("externalRefID field is null - throws error: [Mandatory field (externalRefID) cannot be blank]")
    @Test
    void invalidExternalRefIDNull() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        populateValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorTag("externalRefID");
        messageList.setErrorDesc("Provide the correct value for this mandatory field");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setExternalRefID("");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("externalRefID field invalid - throws error: [Invalid Characters]")
    @Test
    void invalidExternalRefID() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("invalidCharacterErrorCode"));
        messageList.setErrorMessage(System.getProperty("invalidSpecialCharacterErrorCode"));
        messageList.setErrorTag("externalRefID");
        messageList.setErrorDesc("Input data should contain only alphanumeric characters");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setExternalRefID("abc!@#$123");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("externalRefID Id No invalid length - throws error: [Invalid data field length]")
    @Test
    void invalidExternalRefIDLength() {
        CCStatusResHeader messageList = new CCStatusResHeader  ();
        populateValues();
        messageList.setErrorCode(System.getProperty("lengthErrorCode"));
        messageList.setErrorMessage(System.getProperty("invalidLengthErrorCode"));
        messageList.setErrorTag("externalRefID");
        messageList.setErrorDesc("Maximum of 30 characters are allowed");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setExternalRefID("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("toDate field is null - throws error: [Mandatory field (toDate) cannot be blank]")
    @Test
    void invalidToDateNull() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorTag("toDate");
        messageList.setErrorDesc("Please provide the value for mandatory field");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setToDate("");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("toDate field invalid - throws error: [Invalid format]")
    @Test
    void invalidToDate2() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("dateFormatErrorCode"));
        messageList.setErrorMessage(System.getProperty("dateMismatchErrorCode"));
        messageList.setErrorTag("toDate");
        messageList.setErrorDesc("Provide date in YYYY-MM-DD format");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setToDate("2024/06/12");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("toDate field invalid - throws error: [Invalid Date Value]")
    @Test
    void invalidToDate1() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("invalidValueErrorCode"));
        messageList.setErrorMessage(System.getProperty("invalidDateErrorCode"));
        messageList.setErrorTag("toDate");
        messageList.setErrorDesc("Date cannot be in the future");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setToDate("2024-06-12");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("fromDate field is null - throws error: [Mandatory field (fromDate) cannot be blank]")
    @Test
    void invalidFromDateNull() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorTag("fromDate");
        messageList.setErrorDesc("Please provide the value for mandatory field");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setFromDate("");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("fromDate field invalid - throws error: [Invalid format]")
    @Test
    void invalidFromDate2() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("dateFormatErrorCode"));
        messageList.setErrorMessage(System.getProperty("dateMismatchErrorCode"));
        messageList.setErrorTag("fromDate");
        messageList.setErrorDesc("Provide date in YYYY-MM-DD format");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setFromDate("2024/06/12");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }



    @DisplayName("fromDate field invalid - throws error: [Invalid Date Value]")
    @Test
    void invalidFromDate1() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("invalidValueErrorCode"));
        messageList.setErrorMessage(System.getProperty("invalidDateErrorCode"));
        messageList.setErrorTag("fromDate");
        messageList.setErrorDesc("Date cannot be in the future");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setFromDate("2024-06-12");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("fromDate field invalid - throws error: [Invalid Date Range]")
    @Test
    void invalidFromDateToDate() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("dateRangeErrorCode"));
        messageList.setErrorMessage(System.getProperty("invalidForFutureDate"));
        messageList.setErrorTag("fromDate, toDate");
        messageList.setErrorDesc("Input date range is invalid");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setFromDate("2022-06-12");
        body.setToDate("2019-02-24");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }



    @DisplayName("Primary Id No field is null - throws error: [Mandatory field (primaryID) cannot be blank]")
    @Test
    void invalidprimaryIDNull() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorTag("primaryID");
        messageList.setErrorDesc("Provide the correct value for this mandatory field");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setPrimaryID(null);
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("Primary Id No invalid - throws error: [API1003 - Invalid Field Value]")
    @Test
    void invalidprimaryID1() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(env.getProperty("invalidTypeErrorCode"));
        messageList.setErrorMessage(env.getProperty("invalidDataTypeErrorCode"));
        messageList.setErrorTag("primaryID");
        messageList.setErrorDesc("Only numbers are allowed");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setPrimaryID("12A#$b3");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));


    }

    @DisplayName("Primary Id No invalid length - throws error: [API1004 - Invalid data field length]")
    @Test
    void invalidprimaryIDLength() {
        CCStatusResHeader messageList = new CCStatusResHeader ();
        populateValues();
        messageList.setErrorCode(env.getProperty("lengthErrorCode"));
        messageList.setErrorMessage(env.getProperty("invalidLengthErrorCode"));
        messageList.setErrorTag("primaryID");
        messageList.setErrorDesc("Only 12-20 characters are allowed.");
        expectedList.add(messageList);
        CCReqBody body = populateReqBodyValues();
        CCStatusReqHeader header = populateReqHeaderValues();
        body.setPrimaryID("12345");
        CCStatusRequest request = new CCStatusRequest();
        request.setHeader(header);
        request.setBody(body);
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.pfStatusRequest(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }



    public static void main(String[] args){
        CCStatusRequest request = new CCStatusRequest();

        CCReqBody reqBody = new CCReqBody();
        reqBody.setPrimaryID("123L");
        reqBody.setExternalRefID("54567");
        request.setBody(reqBody);

        CCStatusReqHeader reqHeader = new CCStatusReqHeader();
        reqHeader.setMessageRefNo("543");
        request.setHeader(reqHeader);
        try {
            JAXBContext context = JAXBContext.newInstance(CCStatusRequest.class);
            Marshaller m = context.createMarshaller();


            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            m.marshal(request,  doc);

            MessageFactory mfactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = mfactory.createMessage();
            SOAPBody soapBody = soapMessage.getSOAPBody();
            soapBody.addDocument(doc);

            var baos = new ByteArrayOutputStream();
            soapMessage.writeTo(baos);
            var str = baos.toString(StandardCharsets.UTF_8);

            str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + str;
            System.out.println("SOAPMessage: {} \n "+ str);
        } catch (JAXBException | ParserConfigurationException | SOAPException | IOException e) {
            e.printStackTrace();
        }
    }
}
