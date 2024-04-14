import entities.Couleur;
import entities.Pion;
import listes.Combinaison;
import listes.CombinaisonSecrete;

public class Main {
    public static void main(String[] args) {
        //Test Couleur
        System.out.println(Couleur.VERT.getCodeANSI()+"C'est tout bon chef !");

        //Test Pion
        Pion monPion = new Pion(Couleur.BLEU);
        System.out.println(monPion);

        //Test CombinaisonPions
        Combinaison maCombinaison = new Combinaison(6);
        maCombinaison.addPion(new Pion(Couleur.JAUNE));
        maCombinaison.addPion(new Pion(Couleur.MAGENTA));
        maCombinaison.addPion(new Pion(Couleur.VERT));
        System.out.println(maCombinaison);
        System.out.println(maCombinaison.getPion(2));

        //Test CombinaisonSecrete
        CombinaisonSecrete maCombinaisonSecrete = new CombinaisonSecrete(5);
        maCombinaisonSecrete.addPion(new Pion(Couleur.BLEU));
        maCombinaisonSecrete.addPion(new Pion(Couleur.NOIR));
        maCombinaisonSecrete.addPion(new Pion(Couleur.JAUNE));
        maCombinaisonSecrete.addPion(new Pion(Couleur.MAGENTA));
        maCombinaisonSecrete.addPion(new Pion(Couleur.VERT));
        System.out.println(maCombinaisonSecrete);
        System.out.println(maCombinaisonSecrete.decouverte());

        Combinaison monEssai = new Combinaison(5);
        monEssai.addPion(new Pion(Couleur.BLEU));
        monEssai.addPion(new Pion(Couleur.NOIR));
        monEssai.addPion(new Pion(Couleur.MAGENTA));
        monEssai.addPion(new Pion(Couleur.JAUNE));
        monEssai.addPion(new Pion(Couleur.VERT));
        System.out.println(monEssai);

        maCombinaisonSecrete.compare(monEssai);
        System.out.println(maCombinaisonSecrete);
        System.out.println(maCombinaisonSecrete.decouverte());

    }
}