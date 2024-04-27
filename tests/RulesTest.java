import rules.*;

import java.util.ArrayList;
import java.util.List;

public class RulesTest {
    public static void main(String[] args) {
        /*List<Rule> maListedeRules  = new ArrayList<>();
        maListedeRules.add(new AffichageTexte("O"));
        maListedeRules.add(new NbEssais(10));

        Rule maRule = maListedeRules.get(0);
        Object maValeurDeRule = maRule.getValue();

        if (maRule instanceof RuleInteger) {
            int monEntier = (int) maValeurDeRule;
            System.out.println(monEntier);

        } else if (maRule instanceof RuleBoolean) {
            boolean monBooleen = (boolean) maValeurDeRule;
            System.out.println(monBooleen);

        } else {
            System.err.println("erreur");
        }*/

        TailleMaxCombinaison t = new TailleMaxCombinaison();
        t.setValue(4);
        System.out.println(t.getValue());

        AffichageTexte a = new AffichageTexte();
        a.setValue("O");
        System.out.println(a.getValue());

    }
}
