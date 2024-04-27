package rules;

public class TailleMaxCombinaison extends RuleInteger implements Rule {
    private final static String reponseErr = "Le nombre de pions doit être de 4 ou 5.";

    public TailleMaxCombinaison() {
        super(reponseErr);
    }

    public void setValue(Object value){
        int v = (int) value;
        super.setValue(v, (v < 4 || v > 5));
    }
}
