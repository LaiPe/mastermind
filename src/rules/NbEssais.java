package rules;

public class NbEssais extends RuleInteger {
    private final static String message = "Le nombre d'essais doit être de 10 ou 12.";

    public NbEssais(int value) {
        super(value, !(value == 10 || value == 12), message);
    }
}
