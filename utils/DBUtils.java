package utils;

import io.cucumber.java.it.Ma;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    static Connection connection;
    static ResultSet resultSet;

    /**
     * This method creates a connection to the database
     * param sqlQuery
     *
     * @return List<Map < K, V>>
     */
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(ConfigReader.getPropertyValue("dbUrl"),
                    ConfigReader.getPropertyValue("dbUsername"),
                    ConfigReader.getPropertyValue("dbPassword"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet getResultSet(String sqlQuery) {
        try {
            resultSet = getConnection().createStatement().executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static List<Map<String, String>> listOfMapsFromDb(String sqlQuery) {
        List<Map<String, String>> listOfRowMaps = new ArrayList<>();
        Map<String, String> rowMap;
        try {
            resultSet = getResultSet(sqlQuery);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                rowMap = new LinkedHashMap<>();
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    rowMap.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                    listOfRowMaps.add(rowMap);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfRowMaps;
    }

    public static Map<String, String> mapFromDb(String sqlQuery) {

        Map<String, String> rowMap = new LinkedHashMap<>();
        try {
            resultSet = getResultSet(sqlQuery);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();

            resultSet.next();
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                rowMap.put(rsMetaData.getColumnName(i), resultSet.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowMap;
    }

    //select emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where employee_ud=987654;
}
