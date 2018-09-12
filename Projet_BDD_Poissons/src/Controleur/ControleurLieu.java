package Controleur;

import Modele.Lieu;
import Vue.*;
import accesseur.LieuDAO;

import java.util.List;

public class ControleurLieu {

    private NavigateurDesVues navigateurDesVues;

    private VueAjouterLieu vueAjouterLieu;
    private VueEditerLieu vueEditerLieu;
    private VueListeLieu vueListeLieu;
    private VueLieu vueLieu;

    //test
    private LieuDAO lieuDAO = new LieuDAO();

    private ControleurLieu() {
    }

    private static ControleurLieu instance = null; //null est important

    public static ControleurLieu getInstance() {
        if (null == instance) {
            instance = new ControleurLieu();
        }
        return instance;
    }

    public void activerVues(NavigateurDesVues navigateurDesVues) {

        this.navigateurDesVues = navigateurDesVues;

        this.vueAjouterLieu = this.navigateurDesVues.getVueAjouterLieu();
        this.vueListeLieu = this.navigateurDesVues.getVueListeLieu();
        this.vueLieu = this.navigateurDesVues.getVueLieu();
        this.vueEditerLieu = this.navigateurDesVues.getVueEditerLieu();


        //test
        List<Lieu> listeLieuTest = lieuDAO.simulelisterLieus();
        this.vueListeLieu.afficherListeLieu(listeLieuTest);

        //test
        /*this.navigateurDesVues.naviguerVersVueAjouterLieu();
        this.navigateurDesVues.naviguerVersVueLieu();
        this.navigateurDesVues.naviguerVersVueListeLieu();*/
    }

    public void notifierActionAjouterLieu(Lieu lieuAjouter) {
        this.lieuDAO.ajouterLieu(lieuAjouter);
        this.vueListeLieu.afficherListeLieu(lieuDAO.listerLieu());
        this.navigateurDesVues.naviguerVersVueListeLieu();
    }

    public void notifierActionEditerLieu(Lieu lieu) {
        this.vueEditerLieu.updateVueEditerLieu(lieu);
        this.navigateurDesVues.naviguerVersVueEditerLieu();
    }

    public void notifierActionEnregistrerLieu(Lieu lieuModifier) {
        lieuDAO.modifierLieu(lieuModifier);
        vueListeLieu.afficherListeLieu(lieuDAO.listerLieu());
        this.navigateurDesVues.naviguerVersVueListeLieu();
    }


    public NavigateurDesVues getNavigateurDesVues() {
        return navigateurDesVues;
    }

    public void setNavigateurDesVues(NavigateurDesVues navigateurDesVues) {
        this.navigateurDesVues = navigateurDesVues;
    }

    public VueAjouterLieu getVueAjouterLieu() {
        return vueAjouterLieu;
    }

    public void setVueAjouterLieu(VueAjouterLieu vueAjouterLieu) {
        this.vueAjouterLieu = vueAjouterLieu;
    }

    public VueListeLieu getVueListeLieu() {
        return vueListeLieu;
    }

    public void setVueListeLieu(VueListeLieu vueListeLieu) {
        this.vueListeLieu = vueListeLieu;
    }

    public VueLieu getVueLieu() {
        return vueLieu;
    }

    public void setVueLieu(VueLieu vueLieu) {
        this.vueLieu = vueLieu;
    }

    public static void setInstance(ControleurLieu instance) {
        ControleurLieu.instance = instance;
    }

    public LieuDAO getLieuDAO() {
        return lieuDAO;
    }

    public void setLieuDAO(LieuDAO lieuDAO) {
        this.lieuDAO = lieuDAO;
    }

}
