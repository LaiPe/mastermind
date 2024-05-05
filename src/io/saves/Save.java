package io.saves;

import entities.Couleur;
import jeu.PartieMulti;
import jeu.PartieSolo;
import jeu.Plateau;
import listes.Combinaison;
import listes.CombinaisonSecrete;
import listes.TentativeResult;
import rules.Rule;
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

    private List<Integer> liPoints;
    private int indexJoueurEnCours;

    private List<DataJoueur> liJoueurs;


    public Save(String typePartie, int indexTourEnCours, MapRule rules){
        if (typePartie.equals(PartieSolo.class.getSimpleName())) {
            this.typePartie = typePartie;
            this.indexTourEnCours = indexTourEnCours;
            this.rules = rules;

            liJoueurs = new ArrayList<>();
        } else {
            throw new RuntimeException("Mauvais type de partie");
        }
    }
    public Save(String typePartie, int indexTourEnCours, MapRule rules, List<Integer> liPoints, int indexJoueurEnCours){
        if (typePartie.equals(PartieMulti.class.getSimpleName())) {
            this.typePartie = typePartie;
            this.indexTourEnCours = indexTourEnCours;
            this.rules = rules;
            this.liPoints = liPoints;
            this.indexJoueurEnCours = indexJoueurEnCours;

            liJoueurs = new ArrayList<>();
        } else {
            throw new RuntimeException("Mauvais type de partie");
        }
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
            if (typePartie.equals(PartieMulti.class.getSimpleName())){
                writer.newLine();
                writer.write(String.valueOf(indexJoueurEnCours));
            }
        }

        Path rules = Paths.get(getSavePathName() + "/rules.csv");
        Files.createFile(rules);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rules.toString()))) {
            for (Rule rule : this.rules.allRules()) {
                writer.write(rule.getClass().getSimpleName() + "," + rule.getValue().toString());
                writer.newLine();
            }
        }

        if (typePartie.equals(PartieMulti.class.getSimpleName())){
            //TODO écrire points.csv
        }

        for (int i = 0 ; i < liJoueurs.size() ; i++) {
            Path joueurDirectory = Paths.get(getSavePathName(), String.valueOf(i+1));
            Files.createDirectories(joueurDirectory);

            List<Couleur> couleurAutorisees = liJoueurs.get(i).getCouleurAutorisees();
            Path couleurs = Paths.get(joueurDirectory + "/couleurAutorisees.csv");
            Files.createFile(couleurs);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(couleurs.toString()))) {
                writer.write(String.valueOf(couleurAutorisees.get(0).getIndex()));
                for (int c = 1 ; c < couleurAutorisees.size() ; c++){
                    writer.write("," + couleurAutorisees.get(c).getIndex());
                }
            }

            Plateau plateau = liJoueurs.get(i).getPlateau();
            Path plateauFile = Paths.get(joueurDirectory + "/plateau.csv");
            Files.createFile(plateauFile);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(plateauFile.toString()))) {

                CombinaisonSecrete cs = plateau.getCombinaisonSecrete();
                StringBuilder sb = new StringBuilder();
                for (int c = 0 ; c < cs.getTaille() ; c++){
                    sb.append(cs.getElement(c).getCouleurPion().getIndex());
                }
                writer.write(sb.toString());
                writer.newLine();


                for (int c = 0 ; c < plateau.getNbEssais() ; c++){
                    StringBuilder sbEssais = new StringBuilder();
                    Combinaison combinaison = plateau.getCombinaisonEssai(c);
                    for (int d = 0 ; d < combinaison.getTaille() ; d++){
                        sbEssais.append(combinaison.getElement(d).getCouleurPion().getIndex());
                    }
                    sbEssais.append(",");
                    TentativeResult tresult = plateau.getResultEssai(c);
                    for (int d = 0 ; d < tresult.getTaille() ; d++){
                        sbEssais.append(tresult.getElement(d).getIndex());
                    }
                    writer.write(sbEssais.toString());
                    writer.newLine();
                }
                for (int c = plateau.getNbEssais() ; c < plateau.getNbEssaisMax() ; c++){
                    writer.write(".");
                    writer.newLine();
                }
            }
        }
    }
}
