package com.rhb.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.rhb.api.models.*;

import com.rhb.api.utilities.CCStatusValidaors;


import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.squareup.okhttp.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class CCStatusServiceImpl implements CCStatusService {

    FileInputStream fis;
    String systemUnavailable = "Back-end system is currently not available";

    @Autowired
    private CCStatusValidaors validator;

    private static final Log log = LogFactory.getLog(CCStatusServiceImpl.class);

    @Override
    public ResponseEntity<CCStatusResponse> pfStatusRequest(CCReqBody request, String respEmail) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        ResponseEntity<CCStatusResponse> finalResponse = new ResponseEntity<>(HttpStatus.OK);
        CCStatusResponse partnerResponse = new CCStatusResponse();
        try {
            List<CCStatusResHeader> validatorResponse = validator.isValidPayload(request, respEmail);
            log.info("Validations completed:");
            CCStatusResponse response = new CCStatusResponse();
            response.setHeader(validatorResponse);
            if (!response.getHeader().isEmpty()) {
                log.info("Validations failed:");
                return ResponseEntity.badRequest().body(response);
            }

            EAIRequestHeader header = new EAIRequestHeader();

            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
            String partnerCode = "";
            AtomicLong value = new AtomicLong(100001);
            header.setMessageVersionNo(1.0D);
            header.setMessageRefNo(System.getProperty("applicationTypeCC") + partnerCode + date + time + value.getAndIncrement());
            header.setSourceID(System.getProperty("sourceId"));
            header.setLoginID("");
            header.setPan("");
            header.setExtTransactionCode(System.getProperty("extTrxnCd2"));
            header.setDplName("");
            header.setFiller("");
            header.setRecordLength("");
            header.setNoOfRecords("");
            header.setTransactionDate(Integer.valueOf(date));
            header.setTransactionTime(Integer.valueOf(time));
            header.setDestQueueManager("");
            header.setDestQueue("");
            header.setErrorCode("");
            header.setSystemStatus("");
            header.setErrorMessage("");
            header.setTerminalID("");
            header.setReplyRefNo("");
            header.setOverrideTellerId("");
            EAIRequest eaiRequest = new EAIRequest();
            eaiRequest.setHeader(header);

            EAIRequestBody eaiRequestBody = new EAIRequestBody();
            eaiRequestBody.setUsername("");
            eaiRequestBody.setExtRefID(request.getExternalRefID());
            eaiRequestBody.setPassword("");
            eaiRequestBody.setFromDate(format.format(dateFormat.parse(request.getFromDate())));
            eaiRequestBody.setProductCode(System.getProperty("applicationTypeCC"));
            eaiRequestBody.setPartnerEmail(respEmail);
            eaiRequestBody.setPrimaryIDNo(request.getPrimaryID());
            eaiRequestBody.setToDate(format.format(dateFormat.parse(request.getToDate())));
            eaiRequestBody.setSourceSystemCode(System.getProperty("sourceSystem"));
            eaiRequest.setBody(eaiRequestBody);

            log.info("Populated values");
            String xml = createXmlString(eaiRequest);

            log.info("Request to EAI : " + xml);
            String eaiResponse = callSoapService(xml);
            log.info("Response :" + eaiResponse);
            finalResponse = setResponse(eaiResponse, partnerResponse);

        } catch (JsonProcessingException | ParseException e) {
            e.printStackTrace();
            CCStatusResHeader resHeader = new CCStatusResHeader();
            resHeader.setErrorDesc(systemUnavailable);
            resHeader.setErrorTag("");
            resHeader.setErrorMessage(systemUnavailable);
            resHeader.setErrorCode("500");
            List<CCStatusResHeader> list = new ArrayList<>();
            list.add(resHeader);
            partnerResponse.setHeader(list);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(partnerResponse);
        } catch (IOException | JSONException  e) {
            log.info(e.getMessage());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return finalResponse;
    }

    public ResponseEntity<CCStatusResponse> setResponse(String eaiResponse, CCStatusResponse partnerResponse) throws JsonProcessingException, JSONException {
        CCFlashResponse statusResponse;
        if (eaiResponse.isBlank() || eaiResponse.isEmpty()) {
            CCStatusResHeader resHeader = new CCStatusResHeader();
            resHeader.setErrorDesc(systemUnavailable);
            resHeader.setErrorTag("");
            resHeader.setErrorMessage(systemUnavailable);
            resHeader.setErrorCode("500");
            List<CCStatusResHeader> list = new ArrayList<>();
            list.add(resHeader);
            partnerResponse.setHeader(list);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(partnerResponse);
        }
        char quote = '"';
        eaiResponse = eaiResponse.replace("<?xml version=" + quote + "1.0" + quote + " encoding=" + quote + "UTF-8" + quote + " standalone=" + quote + "no" + quote + "?>", "").
                replace("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:eai=\"http://www.eai.rhb.com\">", "").
                replace("<soapenv:Body>", "").
                replace("</soapenv:Body>", "").
                replace("<MsgList>", "").
                replace("</MsgList>", "").replace("<MsgList/>", "").
                replace("<FaclityList>","").replace("<Faclity>","").
                replace("<Product/>","").replace("<TotalFinancingAmount/>","").
                replace("</Faclity>","").replace("</FaclityList>","").
                replace("</soapenv:Envelope>", "").trim();

        XmlMapper xmlMapper = new XmlMapper();
        AnnotationIntrospector aiJaxb = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        xmlMapper.setAnnotationIntrospector(aiJaxb);
        statusResponse = xmlMapper.readValue(eaiResponse, CCFlashResponse.class);
        log.info("Response header : " + statusResponse.getHeader());
        log.info("Response Body : " + statusResponse.getBody());
        List<CCStatusResHeader> resHeaderList = new ArrayList<>();
        if (!statusResponse.getHeader().getMsgList().isEmpty()) {
            for (int i = 0; i < statusResponse.getHeader().getMsgList().size(); i++) {
                CCStatusResHeader resHeader = new CCStatusResHeader();
                resHeader.setErrorCode(statusResponse.getHeader().getMsgList().get(i).getMessageCode());
                resHeader.setErrorDesc(statusResponse.getHeader().getMsgList().get(i).getMessageDesc());
                String[] tag = statusResponse.getHeader().getMsgList().get(i).getMessageDesc().split(" ");
                resHeader.setErrorTag(tag[0]);
                if (new JSONObject(System.getProperty("flashErrorList")).has(statusResponse.getHeader().getMsgList().get(i).getMessageCode().substring(2, 5))) {
                    resHeader.setErrorMessage(new JSONObject(System.getProperty("flashErrorList")).opt(statusResponse.getHeader().getMsgList().get(i).getMessageCode().substring(2, 5)).toString());
                } else {
                    resHeader.setErrorMessage(statusResponse.getHeader().getMsgList().get(i).getMessageDesc());
                }
                resHeaderList.add(resHeader);
            }
            partnerResponse.setHeader(resHeaderList);
            return ResponseEntity.badRequest().body(partnerResponse);
        }
        else if (!statusResponse.getBody().isEmpty()) {
            log.info("Success response from flash");
            List<CCStatusResBody> resBodyList = new ArrayList<>();
            for (int j = 0; j < statusResponse.getBody().size(); j++) {
                CCStatusResBody resBody = new CCStatusResBody();
                resBody.setWorkflowDate(statusResponse.getBody().get(j).getWorkflowDate());
                resBody.setApplicationStatus(statusResponse.getBody().get(j).getApplicationStatus());
                resBody.setAppNumber(statusResponse.getBody().get(j).getAppNumber());
                resBody.setWorkflowStatus(statusResponse.getBody().get(j).getWorkflowStatus());
                resBody.setCustomerMobilePhoneNo(statusResponse.getBody().get(j).getCustomerMobilePhoneNo());
                resBody.setWorkflowDesc(statusResponse.getBody().get(j).getWorkflowDesc());
                resBody.setSystemDecision(statusResponse.getBody().get(j).getSystemDecision());
                resBody.setWorkflowComment(statusResponse.getBody().get(j).getWorkflowComment());
                resBody.setAppDate(statusResponse.getBody().get(j).getAppDate());
                resBody.setCustomerName(statusResponse.getBody().get(j).getCustomerName());
                resBody.setCustomerId(statusResponse.getBody().get(j).getCustomerId());
                resBodyList.add(resBody);
            }
            partnerResponse.setBody(resBodyList);
        }
        else if (statusResponse.getHeader().getMsgList().isEmpty() && statusResponse.getBody().isEmpty()) {
            log.info("No response from flash");
            CCStatusResHeader resHeader = new CCStatusResHeader();
            resHeader.setErrorCode(System.getProperty("invalidApplicationCode"));
            resHeader.setErrorMessage(System.getProperty("invalidApplicationMsg"));
            resHeader.setErrorDesc(System.getProperty("appNotFoundErrorDesc"));
            resHeader.setErrorTag("");
            resHeaderList.add(resHeader);
            partnerResponse.setHeader(resHeaderList);
            return ResponseEntity.unprocessableEntity().body(partnerResponse);
        }
        return ResponseEntity.ok(partnerResponse);
    }

    private SOAPMessage createSOAPHeader(SOAPMessage soapMessage) {
        // Create the security element
        try {
            SOAPElement soapHeader = soapMessage.getSOAPHeader();

            SOAPElement securityElement = soapHeader.addChildElement("Security",
                    "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

            securityElement.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

            soapHeader = addBinarySecurityToken(securityElement, System.getProperty("SecurityToken"));
            log.info(soapHeader);
        } catch (SOAPException e) {
            log.info(e.getMessage());
        }
        return soapMessage;
    }


    private SOAPElement addBinarySecurityToken(SOAPElement securityElement,
                                               String cert) {
        try {
            SOAPElement binarySecurityToken = securityElement.addChildElement("BinarySecurityToken", "wsse");
            binarySecurityToken.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
            binarySecurityToken.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
            binarySecurityToken.addTextNode(cert);
        } catch (SOAPException e) {
            log.info(e.getMessage());
        }
        return securityElement;
    }

    private String createXmlString(EAIRequest request) {
        String xmlString = "";

        try {
            JAXBContext context = JAXBContext.newInstance(EAIRequest.class);
            Marshaller m = context.createMarshaller();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            m.marshal(request, doc);
            MessageFactory mfactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = mfactory.createMessage();
            SOAPBody soapBody = soapMessage.getSOAPBody();
            soapBody.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
            soapBody.addDocument(doc);
            var baos = new ByteArrayOutputStream();
            createSOAPHeader(soapMessage).writeTo(baos);
            xmlString = baos.toString(StandardCharsets.UTF_8);
            xmlString= xmlString.replace("&#13;"," ");
            xmlString= xmlString.replace("&#0;","");
            xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + xmlString;
        } catch (JAXBException | SOAPException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return xmlString;
    }

    private static X509Certificate loadCertificate(File certificateFile) throws IOException, CertificateException {
        try (FileInputStream inputStream = new FileInputStream(certificateFile)) {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(inputStream);
        }
    }

    private static SSLSocketFactory createSslSocketFactory(KeyStore trustStore) throws GeneralSecurityException {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);
        TrustManager[] trustManagers = tmf.getTrustManagers();

        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, trustManagers, null);
        return sslContext.getSocketFactory();
    }

    public String callSoapService(String soapRequest) throws IOException, GeneralSecurityException {
        String responseString;
        String outputString = "";
        String formattedSOAPResponse = "";

            URL url = new URL(System.getProperty("ccStatusUrl"));
            if(url.toString().startsWith("http://")){
                URLConnection connection = url.openConnection();
                HttpURLConnection httpConn = (HttpURLConnection) connection;
              try (  ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                  byte[] buffer = soapRequest.getBytes();
                  bout.write(buffer);
                  byte[] b = bout.toByteArray();
                  httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
                  httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
                  httpConn.setRequestMethod("POST");
                  httpConn.setDoOutput(true);
                  httpConn.setDoInput(true);
                  OutputStream out = httpConn.getOutputStream();
                  // Write the content of the request to the outputstream of the HTTP Connection.
                  out.write(b);
                  out.close();
                  // Ready with sending the request.
                  // Read the response.
                  InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8);
                  BufferedReader in = new BufferedReader(isr);
                  // Write the SOAP message response to a String.
                  while ((responseString = in.readLine()) != null) {
                      outputString = outputString.concat(responseString);
                  }
                  // Write the SOAP message formatted to the console.
                  formattedSOAPResponse = formatXML(outputString);
              }
            }
            else if (url.toString().startsWith("https://")) {
                // Create a new trust store, use getDefaultType for .jks files or "pkcs12" for .p12 files
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                // Create a new trust store, use getDefaultType for .jks files or "pkcs12" for .p12 files
                trustStore.load(null, null);

                // If you comment out the following, the request will fail
                trustStore.setCertificateEntry(
                        "test",
                        // To test, download the certificate from stackoverflow.com with your browser
                        loadCertificate(new File("/apps/sb/" + System.getProperty("certKeyName")))
                );

                fis = new FileInputStream(("/apps/sb/" + System.getProperty("certKeyName")));
                byte[] buffer = new byte[10];
                StringBuilder sb = new StringBuilder();
                while (fis.read(buffer) != -1) {
                    sb.append(new String(buffer));
                    log.info(sb);
                    buffer = new byte[10];
                }

                SSLSocketFactory sslSocketFactory = createSslSocketFactory(trustStore);

                OkHttpClient client = new OkHttpClient().
                        setSslSocketFactory(sslSocketFactory);
                MediaType mediaType = MediaType.parse("application/xml");
                RequestBody body = RequestBody.create(mediaType, soapRequest);
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Content-Type", "application/xml")
                        .build();
                Response response = client.newCall(request).execute();

                // Write the SOAP message formatted to the console.
                formattedSOAPResponse = formatXML(response.body().string());
            }

        return formattedSOAPResponse;
    }

    private static String formatXML(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            log.info(e.getMessage());
        }
        return null;
    }

    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            dbf.setExpandEntityReferences(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            log.info(e.getMessage());
        }
        return null;
    }


}
