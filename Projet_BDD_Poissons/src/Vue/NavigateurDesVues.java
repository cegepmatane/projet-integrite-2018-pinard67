package Vue;

import Controleur.ControleurLieu;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application {

    private VueAjouterLieu vueAjouterLieu;
    private VueListeLieu vueListeLieu;
    private VueLieu vueLieu;

    private ControleurLieu controleurMouton;

    public NavigateurDesVues() {
        this.vueAjouterLieu = new VueAjouterLieu();
        this.vueListeLieu = new VueListeLieu();
        this.vueLieu = new VueLieu();
    }

    private Stage stade = null;

    @Override
    public void start(Stage stade) throws Exception {

        this.stade = stade;

        stade.setScene(this.vueListeLieu);
        stade.show();

        controleurMouton = controleurMouton.getInstance();
        controleurMouton.activerVues(this);

        this.naviguerVersVueAjouterLieu();
        this.naviguerVersVueLieu();
        this.naviguerVersVueListeLieu();
    }

    public void naviguerVersVueLieu() {
        stade.setScene(vueLieu);
        stade.show();
    }

    public void naviguerVersVueAjouterLieu() {
        stade.setScene(vueAjouterLieu);
        stade.show();
    }

    public void naviguerVersVueListeLieu() {
        stade.setScene(vueListeLieu);
        stade.show();
    }

    public VueAjouterLieu getVueAjouterLieu() {
        return vueAjouterLieu;
    }

    public VueListeLieu getVueListeLieu() {
        return vueListeLieu;
    }

    public VueLieu getVueLieu() {
        return vueLieu;
    }


}