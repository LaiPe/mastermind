package rules;

public class NbParties extends RuleInteger implements Rule{
    private final static String reponseErr = "Le nombre de parties doit être compris entre 1 et 10.";

    private final static String demande = "Entrez le nombre de parties (10 max.)";

    public NbParties() {
        super(reponseErr);
    }

    @Override
    public void setValue(String value) throws IllegalArgumentException {
        int v = Integer.parseInt(value);
        super.setValue(v, (v < 1 || v > 10));
    }

    @Override
    public String getDemande() {
        return demande;
    }
}
