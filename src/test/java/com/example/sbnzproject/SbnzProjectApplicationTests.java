package com.example.sbnzproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SbnzProjectApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KieContainer kieContainer;

    private KieSession kieSession;

    @BeforeEach
    public void setup() {
        kieSession = kieContainer.newKieSession();
    }

    @Test
    public void testProcessPositiveNumbers() throws Exception {
        mockMvc.perform(post("/api/drools/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(10, 20, 30))))
                .andExpect(status().isOk())
                .andExpect(content().string("Number of rules fired: 3"));
    }

    @Test
    public void testProcessNegativeNumbers() throws Exception {
        mockMvc.perform(post("/api/drools/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(-10, -20, -30))))
                .andExpect(status().isOk())
                .andExpect(content().string("Number of rules fired: 3"));
    }

    @Test
    public void testProcessMixedNumbers() throws Exception {
        mockMvc.perform(post("/api/drools/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(-10, 20, -30, 40))))
                .andExpect(status().isOk())
                .andExpect(content().string("Number of rules fired: 4"));
    }
}