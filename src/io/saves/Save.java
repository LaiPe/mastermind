package io.saves;

import entities.Couleur;
import jeu.PartieMulti;
import jeu.Plateau;
import rules.map.MapRule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Save {
    private static final String rootSavesPath = "./saves";
    private Path savePath;


    private String typePartie;
    private int indexTourEnCours;
    private MapRule rules;

    private List<DataJoueur> liJoueurs;


    public Save(String typePartie, int indexTourEnCours){
        this.typePartie = typePartie;
        this.indexTourEnCours = indexTourEnCours;

        liJoueurs = new ArrayList<>();
    }

    public String getSavePathName() {
        return savePath.toString();
    }


    private class DataJoueur{
        Plateau plateau;
        List<Couleur> couleurAutorisees;

        public DataJoueur(Plateau plateau, List<Couleur> couleurAutorisees){
            this.plateau = plateau;
            this.couleurAutorisees = couleurAutorisees;
        }

        public Plateau getPlateau(){
            return plateau;
        }
        public List<Couleur> getCouleurAutorisees(){
            return couleurAutorisees;
        }
    }
    public void addDataJoueur(Plateau plateau, List<Couleur> couleurAutorisees){
        liJoueurs.add(new DataJoueur(plateau, couleurAutorisees));
    }






    private String generateDirectoryName() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        return currentTime.format(formatter);
    }
    // Méthode pour générer le nom de répertoire avec un index
    private String generateDirectoryNameWithIndex(int index) {
        return generateDirectoryName() + " (" + index + ")";
    }
    private Path createSaveDirectory() throws IOException {
        // Générer le nom de répertoire
        String directoryName = generateDirectoryName();

        // Vérifier s'il existe déjà dans le répertoire racine
        Path directoryPath = Paths.get(rootSavesPath, directoryName);
        int index = 1;
        while (Files.exists(directoryPath)) {
            directoryName = generateDirectoryNameWithIndex(index++);
            directoryPath = Paths.get(rootSavesPath, directoryName);
        }

        // Créer le répertoire
        Files.createDirectories(directoryPath);

        return directoryPath;
    }

    public void doSave() throws IOException{

        savePath = createSaveDirectory();

        Path savedata = Paths.get(getSavePathName() + "/savedata.txt");
        Files.createFile(savedata);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(savedata.toString()))) {
            writer.write(typePartie);
            writer.newLine();
            writer.write(String.valueOf(indexTourEnCours));
        }

        //TODO écrire rules "rules.txt"

        if (typePartie.equals(PartieMulti.class.getSimpleName())){
            //TODO écrire points
        }


        //TODO Si nbParties existe dans la Map, i = nbJoueurs, sinon i = 1.
        // for i = nbJoueurs ; i > 0 ;i-- :

            //TODO Creer un répertoire nommé "i"
            // exemple : "mastermind/saves/03-05-2024-11-51/1/"; "mastermind/saves/03-05-2024-11-51/2/" (pour une partie de 2 joueurs)

            //TODO Dans ce répertoire, créer un fichier qui représente le plateau :
            // représentation des combinaisons par leur index d'énum  (1 -> Noir; 2-> Rouge; ...)
            // première ligne : combinason secrète + \n
            // Ensuite pour chaque essai: Combnaison essai ; Tentative result associé + \n
            // Enfin: Pour chaque essai vide => écrire un point + \n

            //TODO écrire les couleurs autorisees par leur index d'énum  (1 -> Noir; 2-> Rouge; ...)
    }
}
