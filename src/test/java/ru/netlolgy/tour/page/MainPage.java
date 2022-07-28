package ru.netlolgy.tour.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {


    public static void choiceByCard() {
        $(".heading").shouldHave(Condition.text("Путешествие дня"));
        $(withText("Купить")).click();
        $(withText("Оплата по карте")).shouldBe(Condition.visible);
    }

    public static void choiceOnCredit() {
        $(".heading").shouldHave(Condition.text("Путешествие дня"));
        $(withText("Купить в кредит")).click();
        $(withText("Кредит по данным карты")).shouldBe(Condition.visible);
    }
}
