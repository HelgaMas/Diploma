package ru.netlolgy.tour.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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


    private static Faker faker = new Faker(new Locale("en"));

    public static void getApprovedCard() {
        cardNumber.setValue("4444444444444441");
    }

    public static void getDeclinedCard() {
        cardNumber.setValue("4444444444444442");
    }

    public static void getRandomCard() {
        String randomCard = faker.finance().creditCard();
        cardNumber.setValue(randomCard);
    }

    public static String getRandomName() {
        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String randomName = name + " " + surname;
        return randomName;
    }

    public static void sendingValidPage() {
        month.setValue(String.valueOf(faker.number().numberBetween(10,12)));
        year.setValue(String.valueOf(faker.number().numberBetween(22,26)));
        holder.setValue(getRandomName());
        cvc.setValue(String.valueOf(faker.number().numberBetween(111,999)));
        continueButton.click();
    }

    public static void getBankApproval() {
        notification.shouldHave(Condition.text
                ("Операция одобрена Банком."), Duration.ofSeconds(15));
    }

    public static void sendingInvalidPage() {
        month.setValue("1%");
        year.setValue("2#");
        holder.setValue("Ольга Маслова-Маслова12*");
        cvc.setValue("7-");
        continueButton.click();
    }

    public static void sendingInvalidMonthAndYear() {
        month.setValue("17");
        year.setValue("20");
        holder.setValue(getRandomName());
        cvc.setValue(String.valueOf(faker.number().numberBetween(111,999)));
        continueButton.click();
    }

    public static void getBankRefusal() {
        notification.shouldHave(Condition.text
                ("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15));
    }

    public static void invalidMonthAndYearResponse() {
        invalidMonth.shouldBe(Condition.visible);
        invalidYear.shouldBe(Condition.visible);
    }

    public static void sendingEmptyPage() {
        cardNumber.setValue("");
        month.setValue("");
        year.setValue("");
        holder.setValue("");
        cvc.setValue("");
        continueButton.click();
    }

    public static void invalidFieldsResponse() {
        emptyCardNumber.shouldBe(Condition.visible);
        emptyMonth.shouldBe(Condition.visible);
        emptyYear.shouldBe(Condition.visible);
        invalidHolder.shouldBe(Condition.visible);
        emptyCVC.shouldBe(Condition.visible);
    }

    public static void emptyFieldsResponse() {
        emptyCardNumber.shouldBe(Condition.visible);
        emptyMonth.shouldBe(Condition.visible);
        emptyYear.shouldBe(Condition.visible);
        emptyHolder.shouldBe(Condition.visible);
        emptyCVC.shouldBe(Condition.visible);
    }
}