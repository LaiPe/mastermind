package rules;

public class NbJoueurs extends RuleInteger implements Rule{

    private final static String reponseErr = "Le nombre de joueurs doit être compris entre 2 et 6.";

    private final static String demande = "Entrez le nombre de joueurs (2 min. et 6 max.)";

    public NbJoueurs() {
        super(reponseErr);
    }

    @Override
    public void setValue(String value) throws IllegalArgumentException {
        int v = Integer.parseInt(value);
        super.setValue(v, (v < 2 || v > 6));
    }

    @Override
    public String getDemande() {
        return demande;
    }
}
