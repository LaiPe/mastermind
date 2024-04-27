import gui.GUI;
import gui.TerminalGUI;
import jeu.PartieSolo;
import listes.RulesList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("1. Mode Solo");

            String res = reader.readLine();
            switch (res) {
                case "1":
                    RulesList rules = GUI.choixRules();

                    GUI gui = new TerminalGUI((boolean) rules.getElement(4).getValue());

                    PartieSolo partieSolo = new PartieSolo(rules,gui);
                    partieSolo.launchPartie();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
