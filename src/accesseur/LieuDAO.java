package accesseur;

import Modele.Lieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO {
    private List listeLieusTest = new ArrayList<Lieu>();

    private static String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
    private static String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/lieuPeche";
    private static String BASEDEDONNEES_USAGER = "postgres";
    private static String BASEDEDONNEES_MOTDEPASSE = "09021999";

    private Connection connection = null;

    public LieuDAO() {
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

    public List<Lieu> simulelisterLieus() {
        listeLieusTest.add(new Lieu(1, "Matane", 228, 14462, "non"));
        listeLieusTest.add(new Lieu(2, "Quebec", 721 , 8425996, "non"));
        listeLieusTest.add(new Lieu(3, "Montréal", 431, 1741000, "non"));
        listeLieusTest.add(new Lieu(4, "Ottawa", 2778, 947031, "oui"));

        return listeLieusTest;
    }

    public List<Lieu> listerLieu() {
        List<Lieu> listeLieu = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);

            Statement requeteListeLieu = connection.createStatement();
            ResultSet curseurListeLieu = requeteListeLieu.executeQuery("SELECT * FROM lieu");

            while (curseurListeLieu.next()) {
                int id = curseurListeLieu.getInt("id");
                String ville = curseurListeLieu.getString("ville");
                int taille = curseurListeLieu.getInt("taille");
                int habitant = curseurListeLieu.getInt("habitant");
                String estcapitale = curseurListeLieu.getString("estcapitale");
                System.out.println("nom:" + ville + " taille:" + taille + " Habitants : " + habitant + " est capital : " + estcapitale);
                listeLieu.add(new Lieu(id, ville, taille, habitant, estcapitale));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeLieu;
    }

    public void ajouterLieu(Lieu lieu) {
        Statement requeteAjouterMouton = null;
        try {
            String REQUETE_INSERT = "INSERT into lieu(ville, habitant, taille, estcapitale) VALUES ('" + lieu.getVille() + "'," + lieu.getHabitant() + ", " + lieu.getTaille() + ",'" + lieu.getEstCapital() + "');";
            requeteAjouterMouton = connection.createStatement();
            System.out.println("SQL : "+REQUETE_INSERT);
            requeteAjouterMouton.execute("INSERT into lieu(ville, habitant,taille,estcapitale) VALUES ('" + lieu.getVille() + "'," + lieu.getHabitant() + "," + lieu.getTaille() + ",'" + lieu.getEstCapital() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierLieu(Lieu lieu) {
        List<Lieu> listeLieu = this.listerLieu();
        for (int i = 0; i < listeLieu.size(); i++) {
            if (lieu.getId() == listeLieu.get(i).getId()) {
                this.listerLieu().set(i, lieu);
                break;
            }
        }
    }
}
