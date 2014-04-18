package main.groovy

import groovy.sql.Sql

String dbDriver = args[0]
String dbServer = args[1]
String dbPort = args[2]
String dbUser = args[3]
String dbPassword = args[4]
def cycleDb = args[5]
def monthlyDb = args[6]

def dbToClear = [cycleDb, monthlyDb]

dbToClear.each { dbname ->
    String connectionUrl = "jdbc:sqlserver://" + dbServer + ":" + dbPort +
            ";database=" + dbname +
            ";user=" + dbUser +
            ";password=" + dbPassword;
    Sql sql = Sql.newInstance( connectionUrl, dbDriver)
    sql.execute('delete from Account')
    sql.execute('delete from Customer')

    sql.eachRow("select count(*) from Account") { row ->
        assert row[0] == 0
    }

    sql.eachRow("select count(*) from Customer") { row ->
        assert row[0] == 0
    }
}