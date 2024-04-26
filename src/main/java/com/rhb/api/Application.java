package com.rhb.api;


import com.amazonaws.auth.WebIdentityTokenCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.rhb.api.configuration.GetParameter;
import java.util.Arrays;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.oas.annotations.EnableOpenApi;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableOpenApi
@EnableWebMvc
@ComponentScan(basePackages = {"com.rhb.api", "com.rhb.api.controllers", "io.swagger.configuration"})
public class Application {


    private static final Log log = LogFactory.getLog(Application.class);

     static String strTokenkeyName = "tokenKeyName";
     static  String strCertKeyName = "certKeyName";
     static String apps = "/apps/sb/";

    static S3ObjectInputStream certis;
    static FileInputStream fis;
    static FileOutputStream fos ;
    static S3ObjectInputStream s3is ;
    static FileOutputStream fos1;

    public static void main(String[] args) throws IOException {
        String awsRoleArn = "AWS_ROLE_ARN";
        String awsWebIdentityTokenFile = "AWS_WEB_IDENTITY_TOKEN_FILE";
        new SpringApplication(Application.class).run(args);
            new Application().populateValues();
            String tokenKeyName = System.getProperty(strTokenkeyName);
            String bucketName = "flash-" + System.getenv("spring.profiles.active") + "-certificate";
            log.info("Downloading %s from S3 bucket %s...\n" + tokenKeyName + bucketName);
        String awsArnRole = System.getenv(awsRoleArn);
        String webIdentityTokenFilePath = System.getenv(awsWebIdentityTokenFile);
        WebIdentityTokenCredentialsProvider credentials = WebIdentityTokenCredentialsProvider.builder()
                .roleArn(awsArnRole)
                .webIdentityTokenFile(webIdentityTokenFilePath)
                .build();
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(credentials)
                .withRegion(Regions.AP_SOUTHEAST_1).build();
            S3Object o = s3.getObject(bucketName, tokenKeyName);
            s3is = o.getObjectContent();

            try (FileOutputStream fos = new FileOutputStream(new File(apps, tokenKeyName))) {
            byte[] readBuf = new byte[1024];
            int readLen;
            while ((readLen = s3is.read(readBuf)) > 0) {
                fos.write(readBuf, 0, readLen);
                log.info("File Content : " + Arrays.toString(readBuf));
            }
            fis = new FileInputStream(new File(apps, tokenKeyName));
            byte[] buffer = new byte[10];
            StringBuilder sb = new StringBuilder();
            while (fis.read(buffer) != -1) {
                sb.append(new String(buffer));
                buffer = new byte[10];
            }

            String content = sb.toString();

            System.setProperty("SecurityToken", content);
            log.info("tokenContent" + content);

            String keyName = System.getProperty(strCertKeyName);
            log.info("Downloading %s from S3 bucket %s...\n" + keyName + bucketName);

            S3Object certObj = s3.getObject(bucketName, keyName);
            certis = certObj.getObjectContent();
            fos1 = new FileOutputStream(apps + keyName);
            byte[] readBuf1 = new byte[1024];
            int readLen1;
            while ((readLen1 = certis.read(readBuf1)) > 0) {
                fos1.write(readBuf1, 0, readLen1);
            }

        } catch (Exception e) {
            log.error("<!> Exception occurred in reading secrets: " + e);
        } finally {
            certis.close();
            fos1.close();
            fis.close();
            fos.close();
            s3is.close();
        }

    }

    public void populateValues() {

        GetParameter parameter = new GetParameter();
        JSONObject flashErrorList;
        String ccStatusUrl;
        String certKeyName;
        String tokenKeyName;
        String invalidApplicationCode;

        String invalidApplication;

        String blankErrorCode;

        String dateFormatErrorCode;

        String lengthErrorCode;

        String invalidErrorCode;

        String invalidTypeErrorCode;

        String invalidCharacterErrorCode;

        String dateRangeErrorCode;

        String invalidValueErrorCode;

        String blankCheckErrorCode;

        String invalidDataTypeErrorCode;

        String invalidLengthErrorCode;

        String invalidFieldValueErrorCode;

        String invalidSpecialCharacterErrorCode;

        String invalidDateErrorCode;

        String dateMismatchErrorCode;

        String invalidForFutureDate;

        String blankErrorDesc;
        String datatypeErrorDesc;
        String lengthErrorDesc;
        String invalidValueErrorDesc;
        String invalidCharacterErrorDesc;
        String invalidDateValueErrorDesc;
        String appNotFoundErrorDesc;
        String invalidRequestErrorDesc;
        String invalidFormatErrorDesc;
        String invalidFromDateRangeErrorDesc;

        String invalidAppCode;
        String invalidApplicationMsg;
        String invalidReqCode;
        String invalidReqMsg;

        String sourceId;
        String extTrxnCd2;
        String applicationTypeCC;
        String sourceSystem;
        String alphanumericPattern;


        certKeyName = parameter.getParamAsString(strCertKeyName);
        System.setProperty(strCertKeyName, certKeyName);

        tokenKeyName = parameter.getParamAsString(strTokenkeyName);
        System.setProperty(strTokenkeyName , tokenKeyName);

        flashErrorList = parameter.getParamAsJson("flashErrorList");
        System.setProperty("flashErrorList", String.valueOf(flashErrorList));
        ccStatusUrl = parameter.getParamAsString("ccStatus.url");
        System.setProperty("ccStatusUrl", ccStatusUrl);
        invalidApplication = parameter.getParamAsString("invalid.application");
        System.setProperty("invalidApplication", invalidApplication);
        invalidApplicationCode = parameter.getParamAsString("invalid.application.code");
        System.setProperty("invalidApplicationCode", invalidApplicationCode);

        blankErrorCode = parameter.getParamAsString("blank.code");
        System.setProperty("blankErrorCode", blankErrorCode);
        dateFormatErrorCode = parameter.getParamAsString("invalid.format");
        System.setProperty("dateFormatErrorCode", dateFormatErrorCode);
        lengthErrorCode = parameter.getParamAsString("length.code");
        System.setProperty("lengthErrorCode", lengthErrorCode);
        invalidErrorCode = parameter.getParamAsString("invalid.code");
        System.setProperty("invalidErrorCode", invalidErrorCode);
        invalidTypeErrorCode = parameter.getParamAsString("datatype");
        System.setProperty("invalidTypeErrorCode", invalidTypeErrorCode);
        invalidCharacterErrorCode = parameter.getParamAsString("invalid.specialCharacterCode");
        System.setProperty("invalidCharacterErrorCode", invalidCharacterErrorCode);
        dateRangeErrorCode = parameter.getParamAsString("date.range");
        System.setProperty("dateRangeErrorCode", dateRangeErrorCode);
        invalidValueErrorCode = parameter.getParamAsString("future.date");
        System.setProperty("invalidValueErrorCode", invalidValueErrorCode);
        invalidAppCode = parameter.getParamAsString("invalid.application.code");
        System.setProperty("invalidAppCode", String.valueOf(invalidAppCode));
        invalidReqCode = parameter.getParamAsString("invalidReqCode");
        System.setProperty("invalidReqCode", invalidReqCode);

        blankCheckErrorCode = parameter.getParamAsString("blankCheck");
        System.setProperty("blankCheckErrorCode", blankCheckErrorCode);
        invalidDataTypeErrorCode = parameter.getParamAsString("invalid.dataTypeCheck");
        System.setProperty("invalidDataTypeErrorCode", invalidDataTypeErrorCode);
        invalidLengthErrorCode = parameter.getParamAsString("invalid.lengthCheck");
        System.setProperty("invalidLengthErrorCode", invalidLengthErrorCode);
        invalidFieldValueErrorCode = parameter.getParamAsString("invalid.fieldValue");
        System.setProperty("invalidFieldValueErrorCode", invalidFieldValueErrorCode);
        invalidSpecialCharacterErrorCode = parameter.getParamAsString("invalid.specialCharacter");
        System.setProperty("invalidSpecialCharacterErrorCode", invalidSpecialCharacterErrorCode);
        invalidDateErrorCode = parameter.getParamAsString("date.invalid");
        System.setProperty("invalidDateErrorCode", invalidDateErrorCode);
        dateMismatchErrorCode = parameter.getParamAsString("date.mismatch");
        System.setProperty("dateMismatchErrorCode", dateMismatchErrorCode);
        invalidForFutureDate = parameter.getParamAsString("date.future");
        System.setProperty("invalidForFutureDate", invalidForFutureDate);
        invalidApplicationMsg = parameter.getParamAsString("invalid.application");
        System.setProperty("invalidApplicationMsg", invalidApplicationMsg);
        invalidReqMsg = parameter.getParamAsString("invalidReqMsg");
        System.setProperty("invalidReqMsg", invalidReqMsg);

        // error description
        blankErrorDesc = parameter.getParamAsString("blankErrorDesc");
        System.setProperty("blankErrorDesc", blankErrorDesc);
        datatypeErrorDesc = parameter.getParamAsString("datatypeErrorDesc");
        System.setProperty("datatypeErrorDesc", datatypeErrorDesc);
        lengthErrorDesc = parameter.getParamAsString("lengthErrorDesc");
        System.setProperty("lengthErrorDesc", lengthErrorDesc);
        invalidValueErrorDesc = parameter.getParamAsString("invalidValueErrorDesc");
        System.setProperty("invalidValueErrorDesc", invalidValueErrorDesc);
        invalidCharacterErrorDesc = parameter.getParamAsString("invalidCharacterErrorDesc");
        System.setProperty("invalidCharacterErrorDesc", invalidCharacterErrorDesc);
        invalidDateValueErrorDesc = parameter.getParamAsString("invalidDateValueErrorDesc");
        System.setProperty("invalidDateValueErrorDesc", invalidDateValueErrorDesc);
        invalidFormatErrorDesc = parameter.getParamAsString("invalidFormatErrorDesc");
        System.setProperty("invalidFormatErrorDesc", invalidFormatErrorDesc);
        appNotFoundErrorDesc = parameter.getParamAsString("appNotFoundErrorDesc");
        System.setProperty("appNotFoundErrorDesc", appNotFoundErrorDesc);
        invalidRequestErrorDesc = parameter.getParamAsString("invalidRequestErrorDesc");
        System.setProperty("invalidRequestErrorDesc", invalidRequestErrorDesc);
        invalidFromDateRangeErrorDesc = parameter.getParamAsString("invalidFromDateRangeErrorDesc");
        System.setProperty("invalidFromDateRangeErrorDesc", invalidFromDateRangeErrorDesc);

        //default values
        sourceId = parameter.getParamAsString("sourceId");
        System.setProperty("sourceId", sourceId);

        extTrxnCd2 = parameter.getParamAsString("extTrxnCd2");
        System.setProperty("extTrxnCd2", extTrxnCd2);

        applicationTypeCC = parameter.getParamAsString("applicationTypeCC");
        System.setProperty("applicationTypeCC", applicationTypeCC);

        sourceSystem = parameter.getParamAsString("sourceSystem");
        System.setProperty("sourceSystem", sourceSystem);

        alphanumericPattern = parameter.getParamAsString("alphanumericPattern");
        System.setProperty("alphanumericPattern", alphanumericPattern);

    }

}

