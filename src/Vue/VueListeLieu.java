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
        super(new GridPane(), 490, 400);
        Pane panneau = (Pane) this.getRoot();
        grilleLieus = new GridPane();
        controleurLieu = ControleurLieu.getInstance();
        panneau.getChildren().add(grilleLieus);
    }

    public void afficherListeLieu(List<Lieu> listeLieu) {
        this.grilleLieus.getChildren().clear();

        int numero = 0;
        this.grilleLieus.add(new Label("Ville : "), 0, numero);
        this.grilleLieus.add(new Label("Taille : (en km2)"), 1, numero);
        this.grilleLieus.add(new Label("Habitants : "), 2, numero);
        this.grilleLieus.add(new Label("Capital :"), 3, numero);
        this.grilleLieus.add(new Label(""), 4, numero);
        for (Lieu lieu : listeLieu) {
            numero++;
            this.grilleLieus.add(new Label(lieu.getVille()), 0, numero);
            this.grilleLieus.add(new Label("" + lieu.getTaille()), 1, numero);
            this.grilleLieus.add(new Label("" + lieu.getHabitant()), 2, numero);
            this.grilleLieus.add(new Label(lieu.getEstCapital()), 3, numero);

            Button actionEditer = new Button("Editer");
            actionEditer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleurLieu.notifierActionNaviguerEditerLieu(lieu.getId());
                    System.out.println("Notifier navigation modifier");
                }
            });
            this.grilleLieus.add(actionEditer, 4, numero);

            Button actionSupprimerLieu = new Button("Supprimer");
            actionSupprimerLieu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controleurLieu.notifierActionNaviguerEditerLieu(lieu.getId());
                    System.out.println("Notifier action Supprimer Lieu");
                }
            });
            this.grilleLieus.add(actionSupprimerLieu, 5, numero);
        }

        actionNaviguerAjouterLieu = new Button("Ajouter lieu");

        this.grilleLieus.add(actionNaviguerAjouterLieu, 0, ++numero);

        actionNaviguerAjouterLieu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controleurLieu.getNavigateurDesVues().naviguerVersVueAjouterLieu();
            }
        });
    }
}