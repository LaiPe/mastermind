import entities.Couleur;

import entities.Pion;
public class Main {
    public static void main(String[] args) {
        System.out.println(Couleur.VERT.getCodeANSI()+"C'est tout bon chef !");

        Pion monPion = new Pion(Couleur.BLEU);
        System.out.println(monPion);
    }
}