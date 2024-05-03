package io.saves;

public class Save {
    public static void doSave(){
        //Prends en argument une Map conenant tout ce qu'il y a à sauvegarder => Polymorphisme d'appel (appelé par PartieSolo ou par PartieMulti)

        //TODO Creer l'arborescence du répertoire de sauvegarde à partir de la date et l'heure (si existe déja, annoter avec un entier entre parenthèses)
        // exemple : "mastermind/saves/03-05-2024-11-51/" ; "mastermind/saves/03-05-2024-11-51 (2)/" ; "mastermind/saves/03-05-2024-11-51 (3)/"

        //TODO À la racine de ce répertoire, dans un fichier général "savedata.txt" :
            //TODO écrire le type de partie: Solo ou Multi
            //TODO écrire les couleurs autorisees par leur index d'énum  (1 -> Noir; 2-> Rouge; ...)
            //TODO Si nbParties existe dans la Map, ecrire nbParties, sinon écrire 1.
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

    }
}
