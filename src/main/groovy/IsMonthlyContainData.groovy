package main.groovy

import groovy.sql.Sql

String dbDriver = args[0]
String dbServer = args[1]
String dbPort = args[2]
String dbUser = args[3]
String dbPassword = args[4]
def monthlyDb = args[5]

String connectionUrl = "jdbc:sqlserver://" + dbServer + ":" + dbPort +
        ";database=" + monthlyDb +
        ";user=" + dbUser +
        ";password=" + dbPassword;
Sql sql = Sql.newInstance(connectionUrl, dbDriver)

sql.eachRow("select count(*) from Account") { row ->
    assert row[0] > 0
}

sql.eachRow("select count(*) from Customer") { row ->
    assert row[0] > 0
}
