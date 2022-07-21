package ru.netlolgy.tour.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public static void mainPage() {
        $(".heading").shouldHave(Condition.text("Путешествие дня"));
        $(withText("Купить")).click();
    }

    public static void choiceByCard() {
        $(withText("Оплата по карте")).shouldBe(Condition.visible);
    }

    public static void choiceOnCredit() {
        $(withText("Купить в кредит")).shouldBe(Condition.visible);
    }
}
