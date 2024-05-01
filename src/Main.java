import gui.GUI;
import jeu.PartieSolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        GUI gui = GUI.choixGUI();

        try {
            System.out.println("1. Mode Solo");
            String res = reader.readLine();

            switch (res) {
                case "1":
                    PartieSolo partieSolo = new PartieSolo(gui);
                    partieSolo.launchPartie();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
