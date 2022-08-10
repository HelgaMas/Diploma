package ru.netology.tour.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.tour.data.SQLRequests;
import ru.netology.tour.page.FormFilling;
import ru.netology.tour.page.MainPage;
import ru.netology.tour.page.Responses;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourOnCreditTest {

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
    void shouldCheckValidApprovedOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getApprovedCard();
        FormFilling.checkValidPage();
        Responses.getBankApproval();

        assertEquals("APPROVED", SQLRequests.getStatusOnCredit());
    }

    @Test
    void shouldCheckValidDeclinedOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.checkValidPage();
        Responses.getBankRefusal();

        assertEquals("DECLINED", SQLRequests.getStatusOnCredit());
    }

    @Test
    void shouldCheckInvalidOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getRandomCard();
        FormFilling.checkInvalidPage();
        Responses.getAllInvalidFields();
    }

    @Test
    void shouldCheckEmptyOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.checkEmptyPage();
        Responses.getEmptyFieldsResponse();
    }

    @Test
    void shouldCheckInvalidMonthAndYearOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.checkInvalidMonthAndYear();
        Responses.getInvalidMonthAndYearResponse();
    }

    @Test
    void shouldCheckInvalidHolderOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getDeclinedCard();
        FormFilling.checkInvalidHolder();
        Responses.getInvalidHolder();
    }

    @Test
    void shouldCheckEmptyAndFilledOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.checkEmptyPage();
        Responses.getEmptyFieldsResponse();

        FormFilling.getApprovedCard();
        FormFilling.checkValidPage();
        FormFilling.checkInvalidElementsOnPage();
        Responses.getBankApproval();

        assertEquals("APPROVED", SQLRequests.getStatusByCard());
    }

    @Test
    void shouldCheckDeletingDataOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.getApprovedCard();
        FormFilling.checkValidPage();
        Responses.getBankApproval();
        FormFilling.checkEmptyInputElements();

        assertEquals("APPROVED", SQLRequests.getStatusByCard());
    }

    @Test
    void shouldCheckZeroValuesOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.checkPageWithZeroValues();
        Responses.getZeroValuesResponse();
    }

    @Test
    void shouldCheckShortCardNumberOnCredit() {
        MainPage.choiceOnCredit();
        FormFilling.checkShortCard();
        Responses.getZeroValuesResponse();
    }
}