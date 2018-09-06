package Vue;

import Controleur.ControleurLieu;
import Modele.Lieu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class VueListeLieu extends Scene {

    private GridPane grilleLieus;
    private ControleurLieu controleurLieu = null;

    private Button actionNaviguerAjouterLieu;

    public VueListeLieu() {
        super(new GridPane(), 400, 400);
        Pane panneau = (Pane) this.getRoot();
        grilleLieus = new GridPane();
        controleurLieu = ControleurLieu.getInstance();
        panneau.getChildren().add(grilleLieus);
    }

    public void afficherListeLieu(List<Lieu> listeMoutons)
    {
        int numero = 0;
        this.grilleLieus.add(new Label("Ville : "), 0, numero);
        this.grilleLieus.add(new Label("Taille : "), 1, numero);
        this.grilleLieus.add(new Label("Habitants : "), 2, numero);
        this.grilleLieus.add(new Label("Capital :"), 3, numero);
        for(Lieu lieu : listeMoutons){
            numero++;
            this.grilleLieus.add(new Label(lieu.getVille()), 0, numero);
            this.grilleLieus.add(new Label(lieu.getTaille()), 1, numero);
            this.grilleLieus.add(new Label(lieu.getHabitant()), 2, numero);
            this.grilleLieus.add(new Label(lieu.getEstCapital()), 3, numero);
        }

        actionNaviguerAjouterLieu = new Button("Ajouter lieu");
        this.grilleLieus.add(actionNaviguerAjouterLieu,0,++numero);
        actionNaviguerAjouterLieu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleurLieu.getNavigateurDesVues().naviguerVersVueAjouterLieu();
            }
        });
    }
}