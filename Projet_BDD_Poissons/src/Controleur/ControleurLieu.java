package Controleur;

import Modele.Lieu;
import Vue.NavigateurDesVues;
import Vue.VueAjouterLieu;
import Vue.VueLieu;
import Vue.VueListeLieu;
import accesseur.LieuDAO;

import java.util.List;

public class ControleurLieu {

    private NavigateurDesVues navigateurDesVues;

    private VueAjouterLieu vueAjouterLieu;
    private VueListeLieu vueListeLieu;
    private VueLieu vueLieu;

    private ControleurLieu() {}

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


        //test
        LieuDAO lieuDAO = new LieuDAO();
        List<Lieu> listeLieuTest = lieuDAO.listerLieu();
        this.vueListeLieu.afficherListeLieu(listeLieuTest);

        //test
        /*this.navigateurDesVues.naviguerVersVueAjouterLieu();
        this.navigateurDesVues.naviguerVersVueLieu();
        this.navigateurDesVues.naviguerVersVueListeLieu();*/
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
}
