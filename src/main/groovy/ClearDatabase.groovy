package main.groovy

import groovy.sql.Sql

def dbToClear = ["pey_CMS_staging_cycle", "pey_CMS_staging_monthly"]

String dbDriver = args[0]
String dbServer = args[1]
String dbPort = args[2]
String dbUser = args[3]
String dbPassword = args[4]

dbToClear.each { dbname ->
    String connectionUrl = "jdbc:sqlserver://" + dbServer + ":" + dbPort +
            ";database=" + dbname +
            ";user=" + dbUser +
            ";password=" + dbPassword;
    Sql sql = Sql.newInstance( connectionUrl, dbDriver)
    sql.execute('delete from Account')
    sql.execute('delete from Customer')
}