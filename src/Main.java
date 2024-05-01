import gui.GUI;
import jeu.PartieMulti;
import jeu.PartieSolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        GUI gui = GUI.choixGUI();

        boolean validRes = false;
        while (!validRes) {
            System.out.println("1. Mode Solo");
            System.out.println("2. Mode Multi");
            System.out.println("q. Quitter");
            try {
                String res = reader.readLine();
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
