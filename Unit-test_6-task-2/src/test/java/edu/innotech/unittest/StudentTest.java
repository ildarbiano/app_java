package edu.innotech.unittest;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentTest {

    private WireMockServer wireMockServer;
    private static final int TEST_PORT = 5353;

    @BeforeAll
    void startWireMock() {
        wireMockServer = new WireMockServer(TEST_PORT);
        wireMockServer.start();
        configureFor("localhost", TEST_PORT);

        Student.setMockPort(TEST_PORT);

        assertTrue(wireMockServer.isRunning());
        System.out.println("WireMock running on port " + TEST_PORT);
    }

    @AfterAll
    void stopWireMock() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    @BeforeEach
    void setupStubs() {
        wireMockServer.resetAll();

        // ОДИН универсальный stub для checkGrade с динамическим ответом
        stubFor(get(urlPathEqualTo("/checkGrade"))
                .willReturn(ok().withBody("true")));

        // Специальный stub для некорректных оценок (должен быть после универсального)
        stubFor(get(urlPathEqualTo("/checkGrade"))
                .withQueryParam("grade", matching("-\\d+"))
                .willReturn(ok().withBody("false")));

        stubFor(get(urlPathEqualTo("/checkGrade"))
                .withQueryParam("grade", matching("\\d{3,}"))
                .willReturn(ok().withBody("false")));

        // stub для educ - возвращает сумму
        stubFor(get(urlPathEqualTo("/educ"))
                .willReturn(ok().withBody("155")));
    }

    @Test
    void testCorrectGradeAdded() throws Exception {
        System.out.println("Testing correct grade 85");
        Student student = new Student("Иван");
        student.addGrade(85);

        assertTrue(student.getGrades().contains(85));
        assertEquals(1, student.getGrades().size());
    }

    @Test
    void testIncorrectGradeNotAdded() {
        System.out.println("Testing incorrect grade -10");
        Student student = new Student("Мария");

        assertThrows(IllegalArgumentException.class, () -> student.addGrade(-10));
        assertTrue(student.getGrades().isEmpty());

        assertThrows(IllegalArgumentException.class, () -> student.addGrade(150));
        assertTrue(student.getGrades().isEmpty());
    }

    @Test
    void testRaitingReturnsExpectedValue() throws Exception {
        System.out.println("Testing raiting calculation");
        Student student = new Student("Пётр");
        student.addGrade(70);
        student.addGrade(85);

        int rating = student.raiting();
        assertEquals(155, rating);
    }

    @Test
    void testMultipleGradesWithCorrectAndIncorrect() {
        System.out.println("Testing multiple grades");
        Student student = new Student("Анна");

        assertDoesNotThrow(() -> student.addGrade(90));
        assertDoesNotThrow(() -> student.addGrade(75));
        assertEquals(2, student.getGrades().size());

        assertThrows(IllegalArgumentException.class, () -> student.addGrade(150));

        assertTrue(student.getGrades().contains(90));
        assertTrue(student.getGrades().contains(75));
        assertFalse(student.getGrades().contains(150));
    }
}