package ru.netlolgy.tour.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    private static Faker faker = new Faker(new Locale("en"));



    @Value
    public static class CardInfo {
        public String cardNumber;
    }

    public static CardInfo approvedCard() {
        return new CardInfo("4444444444444441");
    }

    public static CardInfo declinedCard() {
        return new CardInfo("4444444444444442");
    }

    public static CardInfo emptyCard() {
        return new CardInfo("");
    }

    public static String randomCard() {
        String randomCard = faker.finance().creditCard();
        return randomCard;
    }

    @Value
    public static class HolderInfo {
        public String holder;
    }

    public static String getRandomValidHolder() {
        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String randomName = name + " " + surname;
        return randomName;
    }

    public static String getInvalidHolder() {
        String holder = "Маслова Ольга12+";
        return holder;
    }

    public static String getEmptyHolder() {
        String holder = "";
        return holder;
    }

    @Value
    public static class MonthInfo {
        public String month;
    }

    public static int getRandomValidMonth() {
        int month = faker.number().numberBetween(10, 12);
        return month;
    }

    public static MonthInfo getInvalidMonth() {
        return new MonthInfo("1%");
    }

    public static MonthInfo getFalseMonth() {
        return new MonthInfo("17");
    }

    public static MonthInfo getEmptyMonth() {
        return new MonthInfo("");
    }

    @Value
    public static class YearInfo {
        public String year;
    }

    public static int getRandomValidYear() {
        int month = faker.number().numberBetween(22, 26);
        return month;
    }

    public static YearInfo getInvalidYear() {
        return new YearInfo("2#");
    }

    public static YearInfo getPastYear() {
        return new YearInfo("20");
    }

    public static YearInfo getEmptyYear() {
        return new YearInfo("");
    }

    @Value
    public static class CvcInfo {
        public String cvc;
    }

    public static int getRandomValidCvc() {
        int cvc = faker.number().numberBetween(111, 999);
        return cvc;
    }

    public static CvcInfo getInvalidCvc() {
        return new CvcInfo("7-");
    }

    public static CvcInfo getEmptyCvc() {
        return new CvcInfo("");
    }
}