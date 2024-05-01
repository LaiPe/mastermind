import entities.Couleur;
import entities.Pion;
import gui.TerminalGUI;
import jeu.Plateau;
import listes.Combinaison;
import listes.CombinaisonSecrete;

public class GuiTest {
    public static void main(String[] args) {
        int tailleMax = 4;
        TerminalGUI gui = new TerminalGUI();
        gui.setAffichageTexte(false);

        CombinaisonSecrete cs = new CombinaisonSecrete(tailleMax);
        cs.addElement(new Pion(Couleur.ROUGE));
        cs.addElement(new Pion(Couleur.VERT));
        cs.addElement(new Pion(Couleur.MAGENTA));
        cs.addElement(new Pion(Couleur.VERT));

        Plateau plateau = new Plateau(cs,8,tailleMax);
        gui.afficherPlateau(plateau);

        Combinaison essai1 = new Combinaison(tailleMax);
        essai1.addElement(new Pion(Couleur.BLEU));
        essai1.addElement(new Pion(Couleur.BLANC));
        essai1.addElement(new Pion(Couleur.ROUGE));
        essai1.addElement(new Pion(Couleur.VERT));

        plateau.addEssai(essai1);

        gui.afficherPlateau(plateau);
        
    }
}
