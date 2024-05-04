import gui.GUI;
import gui.TerminalGUI;
import io.saves.Save;
import jeu.PartieSolo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveTest {
    public static void main(String[] args) {
        Path path = Paths.get("./tests/hello.txt");

        // Créer l'arborescence de répertoires si nécessaire
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.err.println("Erreur lors de la création des répertoires : " + e.getMessage());
        }

        // Créer le fichier
        try {
            Files.createFile(path);
            System.out.println("Le fichier a été créé avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
        }

    }
}
