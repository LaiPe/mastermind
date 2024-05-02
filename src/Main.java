import gui.GUI;
import jeu.PartieMulti;
import jeu.PartieSolo;

public class Main {
    public static void main(String[] args) {

        GUI gui = GUI.choixGUI();

        boolean sigstop = false;
        boolean sigerr = false;
        while (!sigstop) {
            String res = gui.choixMenu(sigerr);
            switch (res) {
                case "1":
                    PartieSolo partieSolo = new PartieSolo(gui);
                    partieSolo.launchPartie();
                    sigerr = false;
                    break;
                case "2":
                    PartieMulti partieMulti = new PartieMulti(gui);
                    partieMulti.launchPartie();
                    sigerr = false;
                    break;
                case "q":
                    sigerr = false;
                    sigstop = true;
                    break;
                default:
                    sigerr = true;
            }
        }
    }
}
