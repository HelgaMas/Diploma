package ru.netology.tour.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class SQLRequests {

    @SneakyThrows
    public static String getStatusByCard() {
        var byCardStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";

        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db",
                        "helga", "diplom");
        ) {
            var status1 = runner.query(conn, byCardStatus, new ScalarHandler<String>());
            return status1;
        }
    }

    @SneakyThrows
    public static String getStatusOnCredit() {
        var creditStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";

        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db",
                        "helga", "diplom");
        ) {
            var status2 = runner.query(conn, creditStatus, new ScalarHandler<String>());
            return status2;
        }
    }
}