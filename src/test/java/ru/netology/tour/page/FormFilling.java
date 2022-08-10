package ru.netology.tour.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.tour.data.DataGenerator.*;


public class FormFilling {
    private static SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private static SelenideElement month = $("[placeholder=\"08\"]");
    private static SelenideElement year = $("[placeholder=\"22\"]");
    private static SelenideElement holder = $x(("//span[contains(.,'Владелец')]/following-sibling::span/input"));
    private static SelenideElement cvc = $("[placeholder=\"999\"]");
    private static SelenideElement continueButton = $(withText("Продолжить"));

    private static ElementsCollection inputInvalid = $$(".input_invalid");
    private static ElementsCollection inputControl = $$(".input__control");


    public static void getApprovedCard() {
        cardNumber.setValue(String.valueOf(approvedCard()));
    }

    public static void getDeclinedCard() {
        cardNumber.setValue(String.valueOf(declinedCard()));
    }

    public static void getRandomCard() {
        cardNumber.setValue(String.valueOf(randomCard()));
    }

    public static void getZeroCard() {
        cardNumber.setValue(String.valueOf(zeroCard()));
    }

    public static void checkValidPage() {
        month.setValue(String.valueOf(getRandomValidMonth()));
        year.setValue(String.valueOf(getRandomValidYear()));
        holder.setValue(getRandomValidHolder());
        cvc.setValue(String.valueOf(getRandomValidCvc()));
        continueButton.click();
    }

    public static void checkInvalidPage() {
        month.setValue(String.valueOf(getInvalidMonth()));
        year.setValue(String.valueOf(getInvalidYear()));
        holder.setValue(getInvalidHolder());
        cvc.setValue(String.valueOf(getInvalidCvc()));
        continueButton.click();
    }

    public static void checkInvalidMonthAndYear() {
        month.setValue(String.valueOf(getFalseMonth()));
        year.setValue(String.valueOf(getPastYear()));
        holder.setValue(getRandomValidHolder());
        cvc.setValue(String.valueOf(getRandomValidCvc()));
        continueButton.click();
    }

    public static void checkShortCard() {
        cardNumber.setValue(String.valueOf(someSymbolsCard()));
        month.setValue(String.valueOf(getFalseMonth()));
        year.setValue(String.valueOf(getPastYear()));
        holder.setValue(getInvalidHolder());
        cvc.setValue(String.valueOf(getInvalidCvc()));
        continueButton.click();
    }

    public static void checkEmptyPage() {
        cardNumber.setValue(String.valueOf(emptyCard()));
        month.setValue(String.valueOf(getEmptyMonth()));
        year.setValue(String.valueOf(getEmptyYear()));
        holder.setValue(getEmptyHolder());
        cvc.setValue(String.valueOf(getEmptyCvc()));
        continueButton.click();
    }

    public static void checkInvalidHolder() {
        month.setValue(String.valueOf(getRandomValidMonth()));
        year.setValue(String.valueOf(getRandomValidYear()));
        holder.setValue(getInvalidHolder());
        cvc.setValue(String.valueOf(getRandomValidCvc()));
        continueButton.click();
    }

    public static void checkPageWithZeroValues() {
        cardNumber.setValue(String.valueOf(zeroCard()));
        month.setValue(String.valueOf(getZeroMonth()));
        year.setValue(String.valueOf(getZeroYear()));
        holder.setValue(getZeroHolder());
        cvc.setValue(String.valueOf(getZeroCvc()));
        continueButton.click();
    }

    public static void checkInvalidElementsOnPage() {
        inputInvalid.shouldHave(size(0));
    }

    public static void checkEmptyInputElements() {
        inputControl.forEach(inputElement -> {
            inputElement.shouldBe(empty);
        });
    }
}