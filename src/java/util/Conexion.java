/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConnection() {
        Connection cn = null;

        try {
            String databaseUrl = System.getenv("DATABASE_URL");

            if (databaseUrl == null) {
                throw new RuntimeException("DATABASE_URL no definida");
            }

            // Railway da mysql://user:pass@host:port/db
            // JDBC necesita jdbc:mysql://host:port/db
            if (databaseUrl.startsWith("mysql://")) {
                databaseUrl = databaseUrl.replace(
                    "mysql://",
                    "jdbc:mysql://"
                );
            }

            if (!databaseUrl.contains("?")) {
                databaseUrl += "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(databaseUrl);

            System.out.println("✅ Conectado a MySQL Railway");

        } catch (Exception e) {
            System.out.println("❌ Error conexión MySQL: " + e.getMessage());
        }

        return cn;
    }
}

