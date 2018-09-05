package Modele;

public class Lieu {
    protected String ville;
    protected String taille;
    protected String habitant;
    protected String estCapital;

    public Lieu(String ville) {
        this.ville = ville;
    }

    public Lieu(String ville, String taille, String habitant, String estCapital) {
        this.ville = ville;
        this.taille = taille;
        this.habitant = habitant;
        this.estCapital = estCapital;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getHabitant() {
        return habitant;
    }

    public void setHabitant(String habitant) {
        this.habitant = habitant;
    }

    public String getEstCapital() {
        return estCapital;
    }

    public void setEstCapital(String estCapital) {
        this.estCapital = estCapital;
    }
}
