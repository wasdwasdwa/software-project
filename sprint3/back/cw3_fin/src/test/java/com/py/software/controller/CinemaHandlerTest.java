package com.py.software.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
//@WebMvcTest(MovieHandler.class)
@SpringBootTest
public class CinemaHandlerTest {

    private static MockMvc mockMvc;

    @BeforeAll
    static void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new MovieHandler()).build();
    }


    @Test
    public void shouldReturnRightMovies() throws Exception {
        String responseString = mockMvc.perform(
                get("/movie/movie_id/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id")
        ).andExpect((ResultMatcher) status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

    }
}