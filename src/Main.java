import gui.GUI;
import jeu.PartieMulti;
import jeu.PartieSolo;

public class Main {
    public static void main(String[] args) {

        GUI gui = GUI.choixGUI();

        boolean validRes = false;
        while (!validRes) {
            String res = gui.choixMenu();
            switch (res) {
                case "1":
                    PartieSolo partieSolo = new PartieSolo(gui);
                    partieSolo.launchPartie();
                    break;
                case "2":
                    PartieMulti partieMulti = new PartieMulti(gui);
                    partieMulti.launchPartie();
                    break;
                case "q":
                    validRes = true;
                    break;
                default:
                    gui.afficherErreur("Choix invalide, veuillez rentrer un caractère valide.");
            }
        }
    }
}
