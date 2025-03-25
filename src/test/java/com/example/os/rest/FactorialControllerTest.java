package com.example.os.rest;

import com.example.os.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FactorialController.class)
public class FactorialControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private MyService myService;

    @Test
    void getFactorials() throws Exception {
        //Given
        List<BigInteger> list = List.of(
                BigInteger.ONE,
                BigInteger.TWO,
                BigInteger.valueOf(6),
                BigInteger.valueOf(24),
                BigInteger.valueOf(120)
        );
        when(myService.factorials(5)).thenReturn(list);
        when(myService.getUnmodifying(list)).thenReturn(list);

        //Then
        ResultActions resultActions = mvc.perform(get("/factorials/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getFactorialsLinked() throws Exception {
        //Given
        List<BigInteger> list = List.of(
                BigInteger.ONE,
                BigInteger.TWO,
                BigInteger.valueOf(6),
                BigInteger.valueOf(24),
                BigInteger.valueOf(120)
        );
        when(myService.linkedFactorials(5)).thenReturn(list);
        when(myService.getUnmodifying(list)).thenReturn(list);

        //Then
        ResultActions resultActions = mvc.perform(get("/factorials/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void wrongRequest() throws Exception {
        mvc.perform(get("/factorials/wrong"))
                .andExpect(status().isBadRequest());
    }
}
