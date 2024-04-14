import entities.Couleur;
import entities.Pion;
import entities.Result;
import listes.Combinaison;
import listes.CombinaisonSecrete;
import listes.TentativeResult;

public class ListesTest {
    public static void main(String[] args) {

        //Test CombinaisonPions
        Combinaison maCombinaison = new Combinaison(6);
        maCombinaison.addElement(new Pion(Couleur.JAUNE));
        maCombinaison.addElement(new Pion(Couleur.MAGENTA));
        maCombinaison.addElement(new Pion(Couleur.VERT));
        System.out.println(maCombinaison);
        System.out.println(maCombinaison.getElement(2));

        //Test TentativeEssai
        TentativeResult monResultatdeTentative = new TentativeResult(3);
        monResultatdeTentative.addElement(Result.VALIDE);
        monResultatdeTentative.addElement(Result.COULEUR);
        monResultatdeTentative.addElement(Result.INVALIDE);
        System.out.println(monResultatdeTentative);

        //Test CombinaisonSecrete
        CombinaisonSecrete maCombinaisonSecrete = new CombinaisonSecrete(5);
        maCombinaisonSecrete.addElement(new Pion(Couleur.BLEU));
        maCombinaisonSecrete.addElement(new Pion(Couleur.NOIR));
        maCombinaisonSecrete.addElement(new Pion(Couleur.JAUNE));
        maCombinaisonSecrete.addElement(new Pion(Couleur.MAGENTA));
        maCombinaisonSecrete.addElement(new Pion(Couleur.VERT));
        System.out.println(maCombinaisonSecrete);
        System.out.println(maCombinaisonSecrete.decouverte());

        Combinaison monEssai = new Combinaison(5);
        monEssai.addElement(new Pion(Couleur.BLEU));
        monEssai.addElement(new Pion(Couleur.NOIR));
        monEssai.addElement(new Pion(Couleur.MAGENTA));
        monEssai.addElement(new Pion(Couleur.ROUGE));
        monEssai.addElement(new Pion(Couleur.VERT));
        System.out.println(monEssai);

        TentativeResult monResultat = maCombinaisonSecrete.compare(monEssai);
        System.out.println(monResultat);
        System.out.println(monResultat.description());
        System.out.println(maCombinaisonSecrete);
        System.out.println(maCombinaisonSecrete.decouverte());
    }
}
