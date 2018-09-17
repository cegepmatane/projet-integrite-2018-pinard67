package Controleur;

import Accesseur.PoissonDAO;
import Modele.Lieu;
import Modele.Poisson;
import Vue.*;
import Accesseur.LieuDAO;

import java.util.List;

public class ControleurLieu {

    private NavigateurDesVues navigateurDesVues;

    private VueAjouterLieu vueAjouterLieu;
    private VueEditerLieu vueEditerLieu;
    private VueListeLieu vueListeLieu;
    private VueEditerPoisson vueEditerPoisson;
    private VueAjouterPoisson vueAjouterPoisson;
    private VueLieu vueLieu;

    private LieuDAO lieuDAO = new LieuDAO();
    private PoissonDAO poissonDAO = new PoissonDAO();

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
        this.vueEditerPoisson = this.navigateurDesVues.getVueEditerPoisson();
        this.vueAjouterPoisson = this.navigateurDesVues.getVueAjouterPoisson();

        //test
        List<Lieu> listeLieuTest = lieuDAO.listerLieu();
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

    public void notifierActionNaviguerEditerLieu(int idLieu) {
        this.vueEditerLieu.updateVueEditerLieu(this.lieuDAO.rapporterLieu(idLieu));
        this.navigateurDesVues.naviguerVersVueEditerLieu();
    }

    public void notifierActionEnregistrerLieu() {
        Lieu lieuModifier = this.navigateurDesVues.getVueEditerLieu().demanderLieu();
        lieuDAO.modifierLieu(lieuModifier);
        vueListeLieu.afficherListeLieu(lieuDAO.listerLieu());
        this.navigateurDesVues.naviguerVersVueListeLieu();
    }

    public List<Poisson> notifierListePoissonCelonLieu(Lieu lieu) {
        return this.poissonDAO.listerPoisson(lieu.getId());
    }

    public void notifierActionNaviguerEditerPoisson(int idPoisson) {
        this.vueEditerPoisson.updateVueEditerPoisson(this.poissonDAO.rapporterPoisson(idPoisson));
        this.navigateurDesVues.naviguerVersVueEditerPoisson();
    }

    public void notifierActionEnregistrerPoisson() {
        Poisson poissonModifier = this.navigateurDesVues.getVueEditerPoisson().demanderPoisson();
        int idLieu = poissonDAO.trouverLieuCelonPoisson(poissonModifier).getId();
        this.poissonDAO.modifierPoisson(poissonModifier);

        this.vueEditerLieu.updateVueEditerLieu(lieuDAO.rapporterLieu(idLieu));
        this.navigateurDesVues.naviguerVersVueEditerLieu();
    }

    public void notifierActionNaviguerAjouterPoisson(int idLieu) {
        //TODO
        Lieu lieuPoisson = lieuDAO.rapporterLieu(idLieu);
        this.vueAjouterPoisson.updateVueCelonLieu(lieuPoisson);
        this.navigateurDesVues.naviguerVersVueAjouterPoisson();
    }

    public void notifierActionAjouterPoisson() {
        poissonDAO.ajouterPoisson(vueAjouterPoisson.demanderPoisson());
        //TODO naviguer vers vue lieu
    }

    public NavigateurDesVues getNavigateurDesVues() {
        return navigateurDesVues;
    }
}
