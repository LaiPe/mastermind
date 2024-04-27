package listes;
import rules.*;

public class RulesList extends CapedList<Rule> {

    public RulesList(){
        super(5);
        addElement(new NbEssais());
        addElement(new TailleMaxCombinaison());
        addElement(new NbCouleur());
        addElement(new PlsFoisMemeCouleur());
        addElement(new AffichageTexte());
    }

}
