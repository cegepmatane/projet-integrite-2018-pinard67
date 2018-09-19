package Accesseur;


import Modele.Lieu;
import Modele.Poisson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoissonDAO {

    List<Poisson> listePoissonTeste = new ArrayList<>();

    private static String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
    private static String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/lieuPeche";
    private static String BASEDEDONNEES_USAGER = "postgres";
    private static String BASEDEDONNEES_MOTDEPASSE = "postgres";

    private Connection connection = null;

    public PoissonDAO() {
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

    public List<Poisson> simulelisterLieus() {
        listePoissonTeste.add(new Poisson(1, "Saumon", "salmonidée", 58, 832));
        listePoissonTeste.add(new Poisson(1, "Truite", "salmonidée", 32, 234));
        listePoissonTeste.add(new Poisson(1, "Brochet", "salmonidée", 58, 832));
        listePoissonTeste.add(new Poisson(1, "Saumon", "salmonidée", 58, 832));

        return listePoissonTeste;
    }

    public List<Poisson> listerPoisson(int id_lieu) {
        List<Poisson> listePoisson = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);

            Statement requeteListePoisson = connection.createStatement();
            ResultSet curseurListePoisson = requeteListePoisson.executeQuery("SELECT * FROM poisson WHERE id_lieu = " + id_lieu);

            while (curseurListePoisson.next()) {
                String nom = curseurListePoisson.getString("nom");
                String famille = curseurListePoisson.getString("famille");
                int taille = curseurListePoisson.getInt("taille");
                int poids = curseurListePoisson.getInt("poids");
                int id = curseurListePoisson.getInt("id");
                int idLieu = curseurListePoisson.getInt("id_lieu");
                System.out.println("SQL DATA :  nom:" + nom + " famille:" + famille + " Taille : " + taille + " Poids : " + poids);
                listePoisson.add(new Poisson(id, nom, famille, taille, poids, id_lieu));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listePoisson;
    }

    public Poisson rapporterPoisson(int idPoisson) {
        try {
            Statement requetePoisson = connection.createStatement();
            String SQL_RAPPORTER_POISSON = "SELECT * FROM poisson WHERE id = " + idPoisson;
            System.out.println(SQL_RAPPORTER_POISSON);
            ResultSet curseurPoisson = requetePoisson.executeQuery(SQL_RAPPORTER_POISSON);
            curseurPoisson.next();

            int id = curseurPoisson.getInt("id");
            String nom = curseurPoisson.getString("nom");
            String famille = curseurPoisson.getString("famille");
            int taille = curseurPoisson.getInt("taille");
            int poids = curseurPoisson.getInt("poids");
            int id_lieu = curseurPoisson.getInt("id_lieu");

            System.out.println("Nom poisson : " + nom + " Famille : " + famille + " Taille : " + taille + " Poids : " + poids);
            Poisson poisson = new Poisson(id, nom, famille, taille, poids, id_lieu);
            return poisson;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modifierPoisson(Poisson poisson) {
        String SQL_REQUETE_UPDATE = "UPDATE poisson SET nom = '" + poisson.getNom() + "', famille = '" + poisson.getFamille() + "', taille = " + poisson.getTaille() + ", poids = " + poisson.getPoids() + " WHERE id = " + poisson.getId();

        System.out.println("SQL :" + SQL_REQUETE_UPDATE);

        Statement requeteModifierPoisson;
        try {
            requeteModifierPoisson = connection.createStatement();
            requeteModifierPoisson.execute(SQL_REQUETE_UPDATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lieu trouverLieuCelonPoisson(Poisson poisson) {
        String SQL_REQUETE_TROUVER_LIEU = "SELECT * FROM lieu WHERE id = (SELECT poisson.id_lieu FROM poisson WHERE id = " + poisson.getId() + ") ";

        System.out.println("SQL :" + SQL_REQUETE_TROUVER_LIEU);

        Statement requeteTouverLieuCelonfPoisson;
        try {
            requeteTouverLieuCelonfPoisson = connection.createStatement();

            ResultSet curseurPoisson = requeteTouverLieuCelonfPoisson.executeQuery(SQL_REQUETE_TROUVER_LIEU);
            curseurPoisson.next();

            int id = curseurPoisson.getInt("id");
            String ville = curseurPoisson.getString("ville");
            int taille = curseurPoisson.getInt("taille");
            int habitant = curseurPoisson.getInt("habitant");
            String estcapitale = curseurPoisson.getString("estcapitale");
            System.out.println("SQL DATA :  nom:" + ville + " taille:" + taille + " Habitants : " + habitant + " est capital : " + estcapitale);
            boolean booleanCapital = false;
            if (estcapitale.equals("oui"))booleanCapital=true;
            Lieu lieu = new Lieu(id, ville, taille, habitant, booleanCapital);
            return lieu;
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ajouterPoisson(Poisson poisson) {
        Statement requeteAjouterPoisson;
        try {
            String SQL_REQUETE_INSERT_POISSON = "INSERT into poisson(nom, famille,taille,poids,id_lieu) VALUES ('" + poisson.getNom() + "','" + poisson.getFamille() + "'," + poisson.getTaille() + "," + poisson.getPoids() + "," + poisson.getId_lieu() + ")";
            requeteAjouterPoisson = connection.createStatement();
            System.out.println("SQL : " + SQL_REQUETE_INSERT_POISSON);
            requeteAjouterPoisson.execute(SQL_REQUETE_INSERT_POISSON);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerPoisson(int idPoisson) {
        Statement requeteSupprimerPoisson;
        try {
            String SQL_REQUETE_DELETE_POISSON = "DELETE FROM poisson WHERE id = " + idPoisson;
            requeteSupprimerPoisson = connection.createStatement();
            System.out.println("SQL : " + SQL_REQUETE_DELETE_POISSON);
            requeteSupprimerPoisson.execute(SQL_REQUETE_DELETE_POISSON);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
