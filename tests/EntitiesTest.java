import entities.Couleur;
import entities.Pion;
import entities.Result;

public class EntitiesTest {
    public static void main(String[] args) {
        //Test Couleur
        System.out.println(Couleur.VERT+"C'est tout bon chef !");

        //Test Pion
        Pion monPion = new Pion(Couleur.BLEU);
        System.out.println(monPion);

        //Test Result
        System.out.println(Result.VALIDE);
        System.out.println(Result.COULEUR);
        System.out.println(Result.INVALIDE);

    }
}