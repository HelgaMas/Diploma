package ru.netology.tour.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class SQLRequests {

    @SneakyThrows
    public static String getStatusByCard() {
        var dbUrl = System.getProperty("db.url");
        var dbUser = System.getProperty("db.user");
        var dbPassword = System.getProperty("db.password");
        var byCardStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";

        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(dbUrl,dbUser, dbPassword);
        ) {
            var status1 = runner.query(conn, byCardStatus, new ScalarHandler<String>());
            return status1;
        }
    }

    @SneakyThrows
    public static String getStatusOnCredit() {
        var dbUrl = System.getProperty("db.url");
        var dbUser = System.getProperty("db.user");
        var dbPassword = System.getProperty("db.password");
        var creditStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";

        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(dbUrl,dbUser, dbPassword);
        ) {
            var status2 = runner.query(conn, creditStatus, new ScalarHandler<String>());
            return status2;
        }
    }
}