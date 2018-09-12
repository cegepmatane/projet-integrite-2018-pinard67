package Vue;

import Controleur.ControleurLieu;
import Modele.Lieu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VueEditerLieu extends Scene {

    protected TextField textValeurVille;
    protected TextField textValeurTaille;
    protected TextField textValeurHabitant;
    protected TextField textValeurEstCapital;
    
    private Button actionEnregistrerLieu;
    private GridPane grilleLieu;
    private Pane panneau;
    private ControleurLieu controleurLieu;

    private Lieu lieuAModifier;

    public VueEditerLieu(){
        super(new Pane(),400,400);
        panneau = (Pane) this.getRoot();
        grilleLieu = new GridPane();

        controleurLieu = ControleurLieu.getInstance();
    }

    public void updateVueEditerLieu(Lieu lieu){

        textValeurVille = new TextField(lieu.getVille());
        grilleLieu.add(new Label("Nom : "), 0, 0);
        grilleLieu.add(textValeurVille, 1, 0);

        textValeurTaille = new TextField(lieu.getTaille());
        grilleLieu.add(new Label("Taille : "), 0, 1);
        grilleLieu.add(textValeurTaille, 1, 1);

        textValeurHabitant = new TextField(lieu.getHabitant());
        grilleLieu.add(new Label("Habitants : "), 0, 2);
        grilleLieu.add(textValeurHabitant, 1, 2);

        textValeurEstCapital = new TextField(lieu.getEstCapital());
        grilleLieu.add(new Label("Est capitale : "), 0, 3);
        grilleLieu.add(textValeurEstCapital, 1, 3);
        
        actionEnregistrerLieu = new Button("Enregistrer");
        actionEnregistrerLieu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Lieu lieuModifier = new Lieu(lieu.getId(),textValeurVille.getText(),textValeurTaille.getText(),textValeurHabitant.getText(),textValeurEstCapital.getText());
                controleurLieu.notifierActionEnregistrerLieu(lieuModifier);
                controleurLieu.getNavigateurDesVues().naviguerVersVueListeLieu();
            }
        });

        grilleLieu.add(actionEnregistrerLieu,0,4);

        panneau.getChildren().add(grilleLieu);
    }
}
