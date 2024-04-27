package rules;

public class NbEssais extends RuleInteger implements Rule {
    private final static String message = "Le nombre d'essais doit être de 10 ou 12.";

    public NbEssais() {
        super(message);
    }
    public void setValue(Object value){
        int v = (int) value;
        super.setValue(v, !(v == 10 || v == 12));
    }
}
