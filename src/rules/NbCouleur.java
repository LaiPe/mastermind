package rules;

public class NbCouleur extends RuleInteger{
    private final static String message = "Le nombre de couleur doit être compris entre 6 et 8.";

    public NbCouleur(int value) {
        super(value, (value < 6 || value > 8), message);
    }
}
