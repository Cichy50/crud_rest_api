package com.cichy.crud_api.integration.controller;

import com.cichy.crud_api.dto.OwnerDto;
import com.cichy.crud_api.dto.commands.OwnerCommand;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    private static final String API_OWNERS = "/api/owners";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    OwnerCommand ownerCommand = new OwnerCommand("Adam","Kowalski","123456789");

    @Test
    void shouldAddUserAndReturnAllUsers(){
        //given
        RestAssuredMockMvc.given().
                body(ownerCommand).
                contentType(ContentType.JSON).
                when().
                post(API_OWNERS).
                then().
                statusCode(201);
        //when
        RestAssuredMockMvc.given()
                .when()
                .get(API_OWNERS)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldAddAndReturnUserWithGivenId(){
        //given
        OwnerDto ownerDto = RestAssuredMockMvc.given().
                body(ownerCommand).
                contentType(ContentType.JSON).
                when().
                post(API_OWNERS).
                then().
                statusCode(201)
                .extract()
                .as(OwnerDto.class);
        //when
        RestAssuredMockMvc.given()
                .when()
                .get(API_OWNERS + "/" + ownerDto.getOwnerId())
                .then()
                .statusCode(200);
    }

    @Test
    void shouldAddUserAndUpdate(){
        //given
        OwnerDto ownerDto = RestAssuredMockMvc.given().
                body(ownerCommand).
                contentType(ContentType.JSON).
                when().
                post(API_OWNERS).
                then().
                statusCode(201)
                .extract()
                .as(OwnerDto.class);

        OwnerCommand updatedOwner = new OwnerCommand("Michał","Kowalski","111111111");
        //when
        RestAssuredMockMvc.given()
                .body(updatedOwner)
                .contentType(ContentType.JSON)
                .when()
                .put(API_OWNERS + "/" + ownerDto.getOwnerId())
                .then()
                .statusCode(200);
    }

    @Test
    void shouldAddAndDeleteOwner(){
        //given
        OwnerDto ownerDto = RestAssuredMockMvc.given().
                body(ownerCommand).
                contentType(ContentType.JSON).
                when().
                post(API_OWNERS).
                then().
                statusCode(201)
                .extract()
                .as(OwnerDto.class);
        //when
        RestAssuredMockMvc.given()
                .when()
                .delete(API_OWNERS + "/" + ownerDto.getOwnerId())
                .then()
                .statusCode(204);
        //then
        RestAssuredMockMvc.given()
                .when()
                .get(API_OWNERS + "/" + ownerDto.getOwnerId())
                .then()
                .statusCode(404);

    }

}
