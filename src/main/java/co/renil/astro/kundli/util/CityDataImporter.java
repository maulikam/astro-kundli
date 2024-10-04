package co.renil.astro.kundli.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CityDataImporter {
//    public static void main(String[] args) {
//        String jdbcURL = "jdbc:postgresql://localhost:5432/mydatabase";
//        String username = "myuser";
//        String password = "secret";
//
//        String csvFilePath = "cities.csv";
//
//        int batchSize = 20;
//
//        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
//            connection.setAutoCommit(false);
//
//            String sql = "INSERT INTO cities (city_name, state_name, latitude, longitude, timezone) VALUES (?, ?, ?, ?, ?)";
//            try (PreparedStatement statement = connection.prepareStatement(sql);
//                 BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath))) {
//
//                String lineText;
//                int count = 0;
//
//                lineReader.readLine(); // Skip header line
//
//                while ((lineText = lineReader.readLine()) != null) {
//                    String[] data = lineText.split(",");
//                    String cityName = data[0];
//                    String stateName = data[1];
//                    float latitude = Float.parseFloat(data[2]);
//                    float longitude = Float.parseFloat(data[3]);
//                    String timezone = data[4];
//
//                    statement.setString(1, cityName);
//                    statement.setString(2, stateName);
//                    statement.setFloat(3, latitude);
//                    statement.setFloat(4, longitude);
//                    statement.setString(5, timezone);
//
//                    statement.addBatch();
//
//                    if (count % batchSize == 0) {
//                        statement.executeBatch();
//                    }
//                }
//
//                statement.executeBatch();
//                connection.commit();
//            } catch (IOException ex) {
//                System.err.println(ex);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
}

