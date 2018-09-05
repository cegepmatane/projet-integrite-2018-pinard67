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
}
