import gui.GUI;
import gui.TerminalGUI;
import jeu.PartieSolo;
import rules.map.MapRule;
import rules.map.MapRulePartieSolo;

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
                    MapRule rules = GUI.choixRules(new MapRulePartieSolo());

                    GUI gui = new TerminalGUI((boolean) rules.getValue("affichageTexte"));

                    PartieSolo partieSolo = new PartieSolo(rules,gui);
                    partieSolo.launchPartie();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
