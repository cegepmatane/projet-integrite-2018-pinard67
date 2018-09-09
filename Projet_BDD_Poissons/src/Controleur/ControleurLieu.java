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
        this.vueEditerLieu = this.navigateurDesVues.getVueEditerLieu();


        //test
        List<Lieu> listeLieuTest = lieuDAO.listerLieu();
        this.vueListeLieu.afficherListeLieu(listeLieuTest);

        //test
        /*this.navigateurDesVues.naviguerVersVueAjouterLieu();
        this.navigateurDesVues.naviguerVersVueLieu();
        this.navigateurDesVues.naviguerVersVueListeLieu();*/
    }

    public void notifierActionEditerLieu(int numero){
        System.out.println("Numero : "+numero);
        for (int i = 0; i < this.lieuDAO.listerLieu().size(); i++){
            if (numero == i){
                System.out.println("Ville : "+lieuDAO.listerLieu().get(i).getVille());
                vueEditerLieu.updateVueEditerLieu(lieuDAO.listerLieu().get(i));
                break;
            }
        }
    }

    public void notifierActionEnregistrerLieu() {

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
