package rules;

public class TailleMaxCombinaison extends RuleInteger{
    private final static String message = "Le nombre de pions doit être de 4 ou 5.";

    public TailleMaxCombinaison(int value) {
        super(value, (value < 4 || value > 5), message);
    }
}
