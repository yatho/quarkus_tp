package org.formation.web;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(LivraisonController.class)
public class LivraisonControllerTest {

    @Test
    public void testLivraisonList() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", is(4),
                "[0].id", is(1),
                    "[1].id", is(2),
                    "[2].id", is(3),
                    "[3].id", is(4),
                    "[0].noCommande", is("1"));
    }
}
