import entities.Couleur;

import entities.Pion;
import entities.CombinaisonPions;
public class Main {
    public static void main(String[] args) {
        System.out.println(Couleur.VERT.getCodeANSI()+"C'est tout bon chef !");

        Pion monPion = new Pion(Couleur.BLEU);
        System.out.println(monPion);

        CombinaisonPions maCombinaison = new CombinaisonPions(6);
        maCombinaison.addPion(new Pion(Couleur.JAUNE));
        maCombinaison.addPion(new Pion(Couleur.MAGENTA));
        maCombinaison.addPion(new Pion(Couleur.VERT));
        System.out.println(maCombinaison);
        System.out.println(maCombinaison.getPion(2));
        System.out.println(maCombinaison.getPion(4));
    }
}