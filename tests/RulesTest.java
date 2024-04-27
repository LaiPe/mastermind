import rules.*;

import java.util.ArrayList;
import java.util.List;

public class RulesTest {
    public static void main(String[] args) {
        TailleMaxCombinaison t = new TailleMaxCombinaison();
        t.setValue("4");
        System.out.println(t.getValue());

        AffichageTexte a = new AffichageTexte();
        a.setValue("O");
        System.out.println(a.getValue());
        System.out.println(a.getDemande());

    }
}
