package accesseur;

import Modele.Lieu;

import java.util.ArrayList;
import java.util.List;

public class LieuDAO {
    private List listeLieusTest = new ArrayList<Lieu>();

    private List<Lieu> simulelisterLieus(){
        listeLieusTest.add(new Lieu("Matane","228,5 km²","14 462","non"));
        listeLieusTest.add(new Lieu("Quebec","1 542 056 km2","8 425 996","non"));
        listeLieusTest.add(new Lieu("Montréal","431,5 km²","1 741 000","non"));
        listeLieusTest.add(new Lieu("Ottawa","2 778 km²","947 031","oui"));

        return listeLieusTest;
    }
    private void simulerAjouterLieu(Lieu lieu){
        listeLieusTest.add(lieu);
    }

    public void ajouterLieu(Lieu lieu){this.simulerAjouterLieu(lieu);}

    public List<Lieu> listerLieu(){return this.simulelisterLieus();}
}
