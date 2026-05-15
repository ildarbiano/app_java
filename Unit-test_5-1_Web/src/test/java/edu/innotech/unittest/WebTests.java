package edu.innotech.unittest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WebTests {
    WireMockServer wireMockServer;

    @BeforeEach
    void starter(){
        wireMockServer = new WireMockServer(5352);
        wireMockServer.start();
        configureFor("localhost", 5352);
        stubFor(get(urlMatching("/educ\\?sum=([0-9]*)"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("42")));
    }

    @AfterEach
    void stopper(){
        wireMockServer.stop();
    }

    @Test
    public void testRaiting() {

        Student stud = new Student("Ivan");
        stud.addMark(4);
        Assertions.assertEquals(42, stud.raiting());
    }
}
