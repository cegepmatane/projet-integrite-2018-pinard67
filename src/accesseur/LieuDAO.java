package Accesseur;

import Modele.Lieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO {
    private Connection connection = null;
    //private List listeLieusTest = new ArrayList<Lieu>();

    public LieuDAO() {
        connection = BaseDeDonnee.getInstance().getConnection();
    }

    public List<Lieu> listerLieu() {
        List<Lieu> listeLieu = new ArrayList<>();
        try {
            String SQL_LISTER_LIEU = "SELECT * FROM lieu";
            Statement requeteListeLieu = connection.createStatement();
            ResultSet curseurListeLieu = requeteListeLieu.executeQuery(SQL_LISTER_LIEU);

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
        PreparedStatement requetePreparereAjouterLieu = null;
        String stringCapital = "non";
        if (lieu.getEstCapital()) stringCapital = "oui";
        try {
            String SQL_PREPARER_REQUETE_INSERT = "INSERT into lieu(ville, habitant,taille,estcapitale) VALUES (?,?,?,?)";
            requetePreparereAjouterLieu = connection.prepareStatement(SQL_PREPARER_REQUETE_INSERT);

            requetePreparereAjouterLieu.setString(1, lieu.getVille());
            requetePreparereAjouterLieu.setInt(3, lieu.getHabitant());
            requetePreparereAjouterLieu.setInt(2, lieu.getTaille());
            requetePreparereAjouterLieu.setString(4, stringCapital);

            System.out.println("SQL : " + SQL_PREPARER_REQUETE_INSERT);
            requetePreparereAjouterLieu.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierLieu(Lieu lieu) {
        //List<Lieu> listeLieu = this.listerLieu();
        String stringCapital = "non";
        if (lieu.getEstCapital()) stringCapital = "oui";
        String SQL_REQUETE_PREPARE_UPDATE_LIEU = "UPDATE lieu SET ville = ?, taille = ?, habitant = ?, estcapitale = ? WHERE id = ?";

        System.out.println("SQL PREPARER :" + SQL_REQUETE_PREPARE_UPDATE_LIEU);

        //this.listerLieu().set(i, lieu);
        PreparedStatement requetePreparerModifierLieu = null;
        try {
            requetePreparerModifierLieu = connection.prepareStatement(SQL_REQUETE_PREPARE_UPDATE_LIEU);

            requetePreparerModifierLieu.setString(1, lieu.getVille());
            requetePreparerModifierLieu.setInt(2, lieu.getTaille());
            requetePreparerModifierLieu.setInt(3, lieu.getHabitant());
            requetePreparerModifierLieu.setString(4, stringCapital);
            requetePreparerModifierLieu.setInt(5, lieu.getId());

            requetePreparerModifierLieu.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerLieu(int idLieu) {
        PreparedStatement requetePreparerSupprimerLieu;
        try {
            String SQL_REQUETE_PREPARER_DELETE_LIEU = "DELETE FROM lieu WHERE id = ?";
            requetePreparerSupprimerLieu = connection.prepareStatement(SQL_REQUETE_PREPARER_DELETE_LIEU);
            requetePreparerSupprimerLieu.setInt(1, idLieu);
            System.out.println("SQL PREPARER : " + SQL_REQUETE_PREPARER_DELETE_LIEU);

            requetePreparerSupprimerLieu.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lieu rapporterLieu(int idLieu) {
        try {
            String SQL_PREPARER_RAPPORTER_LIEU = "SELECT * FROM lieu WHERE id = ?";
            PreparedStatement requetePreparerRapporterLieu = connection.prepareStatement(SQL_PREPARER_RAPPORTER_LIEU);
            requetePreparerRapporterLieu.setInt(1, idLieu);
            System.out.println("SQL PREPARER : " + SQL_PREPARER_RAPPORTER_LIEU);
            ResultSet curseurLieu = requetePreparerRapporterLieu.executeQuery();
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

    /*public List<Lieu> simulelisterLieus() {
        listeLieusTest.add(new Lieu(1, "Matane", 228, 14462, false));
        listeLieusTest.add(new Lieu(2, "Quebec", 721, 8425996, false));
        listeLieusTest.add(new Lieu(3, "Montréal", 431, 1741000, false));
        listeLieusTest.add(new Lieu(4, "Ottawa", 2778, 947031, true));

        return listeLieusTest;
    }*/
}
