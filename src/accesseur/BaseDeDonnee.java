package Accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDonnee {

    private static String BASEDEDONNEES_DRIVER;
    private static String BASEDEDONNEES_URL;
    private static String BASEDEDONNEES_USAGER;
    private static String BASEDEDONNEES_MOTDEPASSE;

    private Connection connection = null;

    private BaseDeDonnee() {
        this.BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
        this.BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/lieuPeche";
        this.BASEDEDONNEES_USAGER = "postgres";
        this.BASEDEDONNEES_MOTDEPASSE = "postgres";

        try {
            Class.forName(BASEDEDONNEES_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static BaseDeDonnee instance = null; //null est important

    public static BaseDeDonnee getInstance() {
        if (null == instance) {
            instance = new BaseDeDonnee();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
