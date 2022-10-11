package com.baydijital.softtech.app;

import com.baydijital.softtech.app.gen.dto.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

/**
 * @author Gokalp on 9/11/22
 */
public class BaseTest {

    protected ObjectMapper objectMapper;

    protected RestResponse getRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        RestResponse restResponse = objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        return restResponse;
    }

    protected static boolean isSuccess(RestResponse restResponse) {
        boolean isSuccess = restResponse.isSuccess();
        return isSuccess;
    }

    protected boolean isSuccess(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        RestResponse restResponse = getRestResponse(result);

        boolean isSuccess = isSuccess(restResponse);
        return isSuccess;
    }

}
