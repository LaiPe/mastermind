package io.saves;

import entities.Couleur;
import jeu.Plateau;

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
    private Map<String,Object> dataSave;

    private static final String rootSavesPath = "./saves";
    private Path savePath;

    public Save(){
        dataSave = new HashMap<>();
        dataSave.put("liJoueurs", new ArrayList<DataJoueur>());
    }


    private class DataJoueur{
        private Map<String, Object> dataJoueur;

        public DataJoueur(Plateau plateau, List<Couleur> couleurAutorisees){
            dataJoueur = new HashMap<>();
            dataJoueur.put("plateau", plateau);
            dataJoueur.put("couleurAutorisees", couleurAutorisees);
        }

        public Plateau getPlateau(){
            return (Plateau) dataJoueur.get("plateau");
        }
        public List<Couleur> getCouleurAutorisees(){
            return (List<Couleur>) dataJoueur.get("couleurAutorisees");
        }
    }

    public void addDataJoueur(Plateau plateau, List<Couleur> couleurAutorisees){
        ((ArrayList<DataJoueur>) dataSave.get("liJoueurs")).add(new DataJoueur(plateau,couleurAutorisees));
    }
    public DataJoueur getDataJoueur(int index){
        return ((ArrayList<DataJoueur>) dataSave.get("liJoueurs")).get(index);
    }

    public void addNbJoueurs(Integer nbJoueurs){
        dataSave.put("nbJoueurs", nbJoueurs);
    }
    public void addIndexTourEnCours(Integer indexTourEnCours){
        dataSave.put("indexTourEnCours", indexTourEnCours);
    }
    public void addTypePartie(String typePartie){
        dataSave.put("typePartie", typePartie);
    }

    public String getSavePathName() {
        return savePath.toString();
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

        Files.createFile(Paths.get(getSavePathName() + "/savedata.txt"));
        
            //TODO écrire le type de partie: Solo ou Multi

            //TODO Si nbParties existe dans la Map, ecrire nbParties.
            //TODO Si indexTourEnCours existe dans la Map, écrire indexTourEnCours.




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
