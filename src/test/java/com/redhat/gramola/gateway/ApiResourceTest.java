package com.redhat.gramola.gateway;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ApiResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/config")
          .then()
             .statusCode(200)
            //  .body(is("Hello RESTEasy"))
             ;
    }

}