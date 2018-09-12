package accesseur;

import Modele.Lieu;

import java.util.ArrayList;
import java.util.List;

public class LieuDAO {
    private List listeLieusTest = new ArrayList<Lieu>();

    public List<Lieu> simulelisterLieus() {
        listeLieusTest.add(new Lieu(1, "Matane", "228,5 km²", "14 462", "non"));
        listeLieusTest.add(new Lieu(2, "Quebec", "1 542 056 km2", "8 425 996", "non"));
        listeLieusTest.add(new Lieu(3, "Montréal", "431,5 km²", "1 741 000", "non"));
        listeLieusTest.add(new Lieu(4, "Ottawa", "2 778 km²", "947 031", "oui"));

        return listeLieusTest;
    }

    public void ajouterLieu(Lieu lieu) {
        listeLieusTest.clear();
        listeLieusTest = simulelisterLieus();
        this.listeLieusTest.add(lieu);
    }

    public void modifierLieu(Lieu lieu){
        for (int i = 0; i < this.listerLieu().size(); i++) {
            if (lieu.getId() == this.listerLieu().get(i).getId()) {
                this.listerLieu().set(i, lieu);
                break;
            }
        }
    }

    public List<Lieu> listerLieu() {
        return listeLieusTest;
    }
}
