package ru.netlolgy.tour.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netlolgy.tour.page.FormFilling;
import ru.netlolgy.tour.page.MainPage;

import static com.codeborne.selenide.Selenide.*;

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
        MainPage.mainPage();
        MainPage.choiceByCard();
        FormFilling.getApprovedCard();
        FormFilling.sendingValidPage();
        FormFilling.getBankApproval();
    }

    @Test
    void shouldCheckValidApprovedOnCredit() {
        MainPage.mainPage();
        MainPage.choiceOnCredit();
        FormFilling.getApprovedCard();
        FormFilling.sendingValidPage();
        FormFilling.getBankApproval();
    }

    @Test
    void shouldCheckValidDeclinedByCard() {
        MainPage.mainPage();
        MainPage.choiceByCard();
        FormFilling.getDeclinedCard();
        FormFilling.sendingValidPage();
        FormFilling.getBankRefusal();
    }

    @Test
    void shouldCheckValidDeclinedOnCredit() {
        MainPage.mainPage();
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.sendingValidPage();
        FormFilling.getBankRefusal();
    }

    @Test
    void shouldCheckInvalidByCard() {
        MainPage.mainPage();
        MainPage.choiceByCard();
        FormFilling.getRandomCard();
        FormFilling.sendingInvalidPage();
        FormFilling.invalidFieldsResponse();
    }

    @Test
    void shouldCheckInvalidOnCredit() {
        MainPage.mainPage();
        MainPage.choiceOnCredit();
        FormFilling.getRandomCard();
        FormFilling.sendingInvalidPage();
        FormFilling.invalidFieldsResponse();
    }

    @Test
    void shouldCheckEmptyByCard() {
        MainPage.mainPage();
        MainPage.choiceByCard();
        FormFilling.sendingEmptyPage();
        FormFilling.emptyFieldsResponse();
    }

    @Test
    void shouldCheckEmptyOnCredit() {
        MainPage.mainPage();
        MainPage.choiceOnCredit();
        FormFilling.sendingEmptyPage();
        FormFilling.emptyFieldsResponse();
    }

    @Test
    void shouldCheckInvalidMonthAndYearByCard() {
        MainPage.mainPage();
        MainPage.choiceByCard();
        FormFilling.getApprovedCard();
        FormFilling.sendingInvalidMonthAndYear();
        FormFilling.invalidMonthAndYearResponse();
    }

    @Test
    void shouldCheckInvalidMonthAndYearOnCredit() {
        MainPage.mainPage();
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.sendingInvalidMonthAndYear();
        FormFilling.invalidMonthAndYearResponse();
    }
}