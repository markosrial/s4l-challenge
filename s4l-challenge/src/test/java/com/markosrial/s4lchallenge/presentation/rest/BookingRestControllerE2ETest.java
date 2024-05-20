package com.markosrial.s4lchallenge.presentation.rest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class with all spring context up, and launching requests to APIs
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookingRestControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testPostStats_Empty() throws Exception {

        final String PATH = "/stats";
        final String payload = "[]";

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "avg_night": 0.0,
                            "min_night": 0.0,
                            "max_night": 0.0
                        }
                        """, true));
    }

    @Test
    void testPostStats_OK() throws Exception {

        final String PATH = "/stats";
        final String payload = """
                [
                    {
                        "request_id": "bookata_XY123",
                        "check_in": "2020-01-01",
                        "nights": 5,
                        "selling_rate": 200,
                        "margin": 20
                    },
                    {
                        "request_id": "kayete_PP234",
                        "check_in": "2020-01-04",
                        "nights": 4,
                        "selling_rate": 156,
                        "margin": 22
                    }
                ]
                """;

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "avg_night":8.29,
                            "min_night":8,
                            "max_night":8.58
                        }
                        """, true));
    }

    @Test
    void testPostStats_BadRequest() throws Exception {

        final String PATH = "/stats";
        final String payload = """
                [
                    {
                        "request_id": "bookata_XY123",
                        "check_in": "2020-01-01",
                        "nights": 5,
                        "selling_rate": 200,
                        "margin": null
                    }
                ]
                """;

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                            "message": "Error! Field statsPost.bookingRequest[0].margin must not be null"
                        }
                        """, true));
    }

    @Test
    void testPostStats_InternalError() throws Exception {

        final String PATH = "/stats";
        final String payload = """
                [
                    ,
                ]
                """;

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("""
                        {
                            "message": "Internal Server Error"
                        }
                        """, true));
    }

    @Test
    void testPostMaximize_Empty() throws Exception {

        final String PATH = "/maximize";
        final String payload = "[]";

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                             "request_ids": [],
                             "total_profit": 0.0,
                             "avg_night": 0.0,
                             "min_night": 0.0,
                             "max_night": 0.0
                        }
                        """, true));
    }

    @Test
    void testPostMaximize_Ok() throws Exception {

        final String PATH = "/maximize";
        String payload = """
                [
                    {
                        "request_id": "bookata_XY123",
                        "check_in": "2020-01-01",
                        "nights": 5,
                        "selling_rate": 200,
                        "margin": 20
                    },
                    {
                        "request_id": "kayete_PP234",
                        "check_in": "2020-01-04",
                        "nights": 4,
                        "selling_rate": 156,
                        "margin": 5
                    },
                    {
                        "request_id": "atropote_AA930",
                        "check_in": "2020-01-04",
                        "nights": 4,
                        "selling_rate": 150,
                        "margin": 6
                    },
                    {
                        "request_id": "acme_AAAAA",
                        "check_in": "2020-01-10",
                        "nights": 4,
                        "selling_rate": 160,
                        "margin": 30
                    }
                ]
                """;

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "request_ids":[
                                "bookata_XY123",
                                "acme_AAAAA"
                            ],
                            "total_profit": 88,
                            "avg_night": 10,
                            "min_night": 8,
                            "max_night": 12
                        }
                        """, true));
    }

    @Test
    void testPostMaximize_BadRequest() throws Exception {

        final String PATH = "/maximize";
        final String payload = """
                [
                    {
                        "request_id": null,
                        "check_in": "2020-01-01",
                        "nights": 5,
                        "selling_rate": 200,
                        "margin": 20
                    }
                ]
                """;

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                            "message": "Error! Field maximizePost.bookingRequest[0].requestId must not be null"
                        }
                        """, true));
    }

    @Test
    void testPostMaximize_InternalError() throws Exception {

        final String PATH = "/stats";
        final String payload = """
                [
                    ,
                ]
                """;

        this.mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("""
                        {
                            "message": "Internal Server Error"
                        }
                        """, true));
    }

}