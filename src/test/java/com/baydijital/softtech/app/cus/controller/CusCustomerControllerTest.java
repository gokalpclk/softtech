package com.baydijital.softtech.app.cus.controller;

import com.baydijital.softtech.SofttechApplication;
import com.baydijital.softtech.app.BaseTest;
import com.baydijital.softtech.app.config.H2TestProfileJPAConfig;
import com.baydijital.softtech.app.cus.dto.CusCustomerSaveRequestDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerUpdateRequestDto;
import com.baydijital.softtech.app.gen.dto.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Gokalp on 9/11/22
165. Softtech Java Spring Bootcamp
• 69 / 71
TR
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {SofttechApplication.class, H2TestProfileJPAConfig.class})
class CusCustomerControllerTest extends BaseTest {
    private static final String BASE_PATH = "/api/v1/customers";


    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void save() throws Exception {
        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = CusCustomerSaveRequestDto.builder().name("test").surname("tst"). identityNo(1111111111L).password("12345678").build();

        String content = objectMapper.writeValueAsString(cusCustomerSaveRequestDto);

        MvcResult result = mockMvc.perform(post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);
        Assertions.assertTrue(isSuccess);
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc.perform(get(BASE_PATH + "/1").content("1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);
        Assertions.assertTrue(isSuccess);
    }

    @Test
    void findAll() throws Exception {
        MvcResult result = mockMvc.perform(get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        boolean isSuccess = isSuccess(result);
        Assertions.assertTrue(isSuccess);


    }




    @Test
    void deleteTest() throws Exception {
        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/552").content("552").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        //Assertions.assertTrue(isSuccess);
    }

    @Test
    void shouldNoDeleteWhenIdDoesNotExist() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/9999").content("9999").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void update() throws Exception {
        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = new CusCustomerUpdateRequestDto();
        cusCustomerUpdateRequestDto.setId(2052L);
        cusCustomerUpdateRequestDto.setName("test2");
        cusCustomerUpdateRequestDto.setSurname("test2");
        cusCustomerUpdateRequestDto.setIdentityNo(12345678918L);
        cusCustomerUpdateRequestDto.setPassword("abcdefgh");

        String content = objectMapper.writeValueAsString(cusCustomerUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}