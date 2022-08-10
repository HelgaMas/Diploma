package ru.netology.tour.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class Responses {

    private static SelenideElement notification = $(".notification__content");
    private static SelenideElement invalidCardNumber = $x("//*[contains(.,'Номер карты')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement invalidMonth = $x("//*[contains(.,'Месяц')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement wrongMonth = $(withText("Неверно указан срок действия карты"));
    private static SelenideElement expiredYear = $(withText("Истёк срок действия карты"));
    private static SelenideElement invalidYear = $x("//*[contains(.,'Год')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement emptyHolder = $x("//*[contains(.,'Владелец')]//span[contains(.,'Поле обязательно для заполнения')]");
    private static SelenideElement invalidHolder = $x("//*[contains(.,'Владелец')]//span[contains(.,'Неверный формат')]");
    private static SelenideElement invalidCVC = $x("//*[contains(.,'CVC/CVV')]//span[contains(.,'Неверный формат')]");


    public static void getBankApproval() {
        notification.shouldHave(Condition.text
                ("Операция одобрена Банком."), Duration.ofSeconds(15));
    }

    public static void getBankRefusal() {
        notification.shouldHave(Condition.text
                ("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15));
    }

    public static void getInvalidMonthAndYearResponse() {
        wrongMonth.shouldBe(Condition.visible);
        expiredYear.shouldBe(Condition.visible);
    }

    public static void getAllInvalidFields() {
        invalidCardNumber.shouldBe(Condition.visible);
        invalidMonth.shouldBe(Condition.visible);
        invalidYear.shouldBe(Condition.visible);
        invalidCVC.shouldBe(Condition.visible);
    }

    public static void getEmptyFieldsResponse() {
        invalidCardNumber.shouldBe(Condition.visible);
        invalidMonth.shouldBe(Condition.visible);
        invalidYear.shouldBe(Condition.visible);
        emptyHolder.shouldBe(Condition.visible);
        invalidCVC.shouldBe(Condition.visible);
    }

    public static void getInvalidHolder() {
        invalidHolder.shouldBe(Condition.visible);
    }

    public static void getZeroValuesResponse() {
        invalidCardNumber.shouldBe(Condition.visible);
        wrongMonth.shouldBe(Condition.visible);
        expiredYear.shouldBe(Condition.visible);
        invalidHolder.shouldBe(Condition.visible);
    }
}