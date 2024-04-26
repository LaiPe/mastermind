
import gui.TerminalGUI;
import jeu.PartieSolo;


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
                    PartieSolo partieSolo = new PartieSolo(10,4,6,false , new TerminalGUI(false));
                    partieSolo.launchPartie();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
