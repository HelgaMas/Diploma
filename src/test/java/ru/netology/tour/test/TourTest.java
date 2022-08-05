package ru.netology.tour.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import ru.netology.tour.data.SQLRequests;
import ru.netology.tour.page.FormFilling;
import ru.netology.tour.page.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }


    @Test
    void shouldCheckValidApprovedByCard() {
        MainPage.choiceByCard();
        FormFilling.getApprovedCard();
        FormFilling.checkValidPage();
        FormFilling.getBankApproval();

        Assertions.assertEquals("APPROVED", SQLRequests.getStatusByCard());
    }

    @Test
    void shouldCheckValidApprovedOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getApprovedCard();
        FormFilling.checkValidPage();
        FormFilling.getBankApproval();

        assertEquals("APPROVED", SQLRequests.getStatusOnCredit());

    }

    @Test
    void shouldCheckValidDeclinedByCard() {
        MainPage.choiceByCard();
        FormFilling.getDeclinedCard();
        FormFilling.checkValidPage();
        FormFilling.getBankRefusal();

        assertEquals("DECLINED", SQLRequests.getStatusByCard());
    }

    @Test
    void shouldCheckValidDeclinedOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.checkValidPage();
        FormFilling.getBankRefusal();

        assertEquals("DECLINED", SQLRequests.getStatusOnCredit());
    }

    @Test
    void shouldCheckInvalidByCard() {
        MainPage.choiceByCard();
        FormFilling.getRandomCard();
        FormFilling.checkInvalidPage();
        FormFilling.getAllInvalidFields();
    }

    @Test
    void shouldCheckInvalidOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getRandomCard();
        FormFilling.checkInvalidPage();
        FormFilling.getAllInvalidFields();
    }

    @Test
    void shouldCheckEmptyByCard() {
        MainPage.choiceByCard();
        FormFilling.getEmptyCard();
        FormFilling.checkEmptyPage();
        FormFilling.getEmptyFieldsResponse();
    }

    @Test
    void shouldCheckEmptyOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getEmptyCard();
        FormFilling.checkEmptyPage();
        FormFilling.getEmptyFieldsResponse();
    }

    @Test
    void shouldCheckInvalidMonthAndYearByCard() {
        MainPage.choiceByCard();
        FormFilling.getApprovedCard();
        FormFilling.checkInvalidMonthAndYear();
        FormFilling.getInvalidMonthAndYearResponse();
    }

    @Test
    void shouldCheckInvalidMonthAndYearOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.checkInvalidMonthAndYear();
        FormFilling.getInvalidMonthAndYearResponse();
    }

    @Test
    void shouldCheckInvalidHolderByCard() {
        MainPage.choiceByCard();
        FormFilling.getDeclinedCard();
        FormFilling.checkInvalidHolder();
        FormFilling.getAllInvalidHolder();
    }

    @Test
    void shouldCheckInvalidHolderOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.checkInvalidHolder();
        FormFilling.getAllInvalidHolder();
    }
}