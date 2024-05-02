import gui.GUI;
import jeu.PartieMulti;
import jeu.PartieSolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
                    System.out.println("Choix invalide, veuillez rentrer un caractère valide.");
            }
        }
    }
}
