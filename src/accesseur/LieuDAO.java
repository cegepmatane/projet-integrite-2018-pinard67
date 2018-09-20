package Accesseur;

import Modele.Lieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO {
    private Connection connection = null;
    private List listeLieusTest = new ArrayList<Lieu>();

    public LieuDAO() {
        connection = BaseDeDonnee.getInstance().getConnection();
    }

    public List<Lieu> simulelisterLieus() {
        listeLieusTest.add(new Lieu(1, "Matane", 228, 14462, false));
        listeLieusTest.add(new Lieu(2, "Quebec", 721, 8425996, false));
        listeLieusTest.add(new Lieu(3, "Montréal", 431, 1741000, false));
        listeLieusTest.add(new Lieu(4, "Ottawa", 2778, 947031, true));

        return listeLieusTest;
    }

    public List<Lieu> listerLieu() {
        List<Lieu> listeLieu = new ArrayList<>();
        try {
            Statement requeteListeLieu = connection.createStatement();
            ResultSet curseurListeLieu = requeteListeLieu.executeQuery("SELECT * FROM lieu");

            while (curseurListeLieu.next()) {
                int id = curseurListeLieu.getInt("id");
                String ville = curseurListeLieu.getString("ville");
                int taille = curseurListeLieu.getInt("taille");
                int habitant = curseurListeLieu.getInt("habitant");
                String estcapitale = curseurListeLieu.getString("estcapitale");
                //System.out.println("SQL DATA :  nom:" + ville + " taille:" + taille + " Habitants : " + habitant + " est capital : " + estcapitale);
                boolean booleanCapital = false;
                if (estcapitale.equals("oui")) booleanCapital = true;
                listeLieu.add(new Lieu(id, ville, taille, habitant, booleanCapital));
            }
            System.out.println("Liste BDD a jours");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeLieu;
    }

    public void ajouterLieu(Lieu lieu) {
        Statement requeteAjouterLieu = null;
        String stringCapital = "non";
        if (lieu.getEstCapital())stringCapital="oui";
        try {
            String SQL_REQUETE_INSERT = "INSERT into lieu(ville, habitant,taille,estcapitale) VALUES ('" + lieu.getVille() + "'," + lieu.getHabitant() + "," + lieu.getTaille() + ",'" + stringCapital + "')";
            requeteAjouterLieu = connection.createStatement();
            System.out.println("SQL : " + SQL_REQUETE_INSERT);
            requeteAjouterLieu.execute(SQL_REQUETE_INSERT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierLieu(Lieu lieu) {
        //List<Lieu> listeLieu = this.listerLieu();
        String stringCapital = "non";
        if (lieu.getEstCapital())stringCapital="oui";

        String SQL_REQUETE_UPDATE = "UPDATE lieu SET ville = '" + lieu.getVille() + "', taille = " + lieu.getTaille() + ", habitant = " + lieu.getHabitant() + ", estcapitale = '" + stringCapital+ "' WHERE id = " + lieu.getId();

        System.out.println("SQL :" + SQL_REQUETE_UPDATE);

        //this.listerLieu().set(i, lieu);
        Statement requeteModifierLieu = null;
        try {
            requeteModifierLieu = connection.createStatement();
            requeteModifierLieu.execute(SQL_REQUETE_UPDATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerLieu(int idLieu) {
        Statement requeteSupprimerLieu;
        try {
            String SQL_REQUETE_DELETE_LIEU = "DELETE FROM lieu WHERE id = " + idLieu;
            requeteSupprimerLieu = connection.createStatement();
            System.out.println("SQL : " + SQL_REQUETE_DELETE_LIEU);
            requeteSupprimerLieu.execute(SQL_REQUETE_DELETE_LIEU);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lieu rapporterLieu(int idLieu){
        try {
            Statement requeteLieu = connection.createStatement();
            String SQL_RAPPORTER_LIEU = "SELECT * FROM lieu WHERE id = " + idLieu;
            System.out.println(SQL_RAPPORTER_LIEU);
            ResultSet curseurLieu = requeteLieu.executeQuery(SQL_RAPPORTER_LIEU);
            curseurLieu.next();

            int id = curseurLieu.getInt("id");
            String ville = curseurLieu.getString("ville");
            int taille = curseurLieu.getInt("taille");
            int habitant = curseurLieu.getInt("habitant");
            String estcapitale = curseurLieu.getString("estcapitale");

            System.out.println("Lieu : " + ville + " Taille : " + taille + " Habitant : " + habitant + " Capitale : " + estcapitale);
            boolean booleanCapital = false;
            if (estcapitale.equals("oui")) booleanCapital = true;
            Lieu lieu = new Lieu(id, ville, taille, habitant, booleanCapital);
            return lieu;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
