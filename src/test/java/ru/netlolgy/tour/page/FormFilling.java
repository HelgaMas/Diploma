package ru.netlolgy.tour.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import ru.netlolgy.tour.data.DataGenerator;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ru.netlolgy.tour.data.DataGenerator.*;


public class FormFilling {
    private static SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private static SelenideElement month = $("[placeholder=\"08\"]");
    private static SelenideElement year =  $("[placeholder=\"22\"]");
    private static SelenideElement holder = $x(("//span[contains(.,'Владелец')]/following-sibling::span/input"));
    private static SelenideElement cvc = $("[placeholder=\"999\"]");
    private static SelenideElement continueButton = $(withText("Продолжить"));
    private static SelenideElement notification = $(".notification__content");

    private static SelenideElement emptyCardNumber =$x("//*[contains(.,'Номер карты')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement emptyMonth = $x("//*[contains(.,'Месяц')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement invalidMonth = $(withText("Неверно указан срок действия карты"));
    private static SelenideElement invalidYear = $(withText("Истёк срок действия карты"));
    private static SelenideElement emptyYear = $x("//*[contains(.,'Год')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement emptyHolder = $x("//*[contains(.,'Владелец')]//span[contains(.,'Поле обязательно для заполнения')]");
    private static SelenideElement invalidHolder = $x("//*[contains(.,'Владелец')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement emptyCVC = $x("//*[contains(.,'CVC/CVV')]//span[contains(.,'Неверный формат')]");


    public static void getApprovedCard(){
        cardNumber.setValue(String.valueOf(approvedCard()));
    }

    public static void getDeclinedCard(){
        cardNumber.setValue(String.valueOf(declinedCard()));
    }

    public static void getEmptyCard(){
        cardNumber.setValue(String.valueOf(emptyCard()));
    }

    public static void getRandomCard(){
        cardNumber.setValue(String.valueOf(randomCard()));
    }


    public static void checkValidPage() {
        month.setValue(String.valueOf(getRandomValidMonth()));
        year.setValue(String.valueOf(getRandomValidYear()));
        holder.setValue(getRandomValidHolder());
        cvc.setValue(String.valueOf(getRandomValidCvc()));
        continueButton.click();
    }

    public static void getBankApproval() {
        notification.shouldHave(Condition.text
                ("Операция одобрена Банком."), Duration.ofSeconds(15));
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

    public static void getBankRefusal() {
        notification.shouldHave(Condition.text
                ("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15));
    }

    public static void getInvalidMonthAndYearResponse() {
        invalidMonth.shouldBe(Condition.visible);
        invalidYear.shouldBe(Condition.visible);
    }

    public static void checkEmptyPage() {
        cardNumber.setValue(String.valueOf(emptyCard()));
        month.setValue(String.valueOf(getEmptyMonth()));
        year.setValue(String.valueOf(getEmptyYear()));
        holder.setValue(String.valueOf(getEmptyHolder()));
        cvc.setValue(String.valueOf(getEmptyCvc()));
        continueButton.click();
    }

    public static void getAllInvalidFields() {
        emptyCardNumber.shouldBe(Condition.visible);
        emptyMonth.shouldBe(Condition.visible);
        emptyYear.shouldBe(Condition.visible);
        emptyCVC.shouldBe(Condition.visible);
    }

    public static void getEmptyFieldsResponse() {
        emptyCardNumber.shouldBe(Condition.visible);
        emptyMonth.shouldBe(Condition.visible);
        emptyYear.shouldBe(Condition.visible);
        emptyHolder.shouldBe(Condition.visible);
        emptyCVC.shouldBe(Condition.visible);
    }
    public static void checkInvalidHolder() {
        month.setValue(String.valueOf(getRandomValidMonth()));
        year.setValue(String.valueOf(getRandomValidYear()));
        holder.setValue(getInvalidHolder());
        cvc.setValue(String.valueOf(getRandomValidCvc()));
        continueButton.click();
    }

    public static void getAllInvalidHolder() {
        invalidHolder.shouldBe(Condition.visible);
    }
}