package rules;

public class TailleMaxCombinaison extends RuleInteger implements Rule {
    private final static String message = "Le nombre de pions doit être de 4 ou 5.";

    public TailleMaxCombinaison() {
        super(message);
    }

    public void setValue(Object value){
        int v = (int) value;
        super.setValue(v, (v < 4 || v > 5));
    }
}
