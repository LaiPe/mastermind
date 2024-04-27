package rules;

public class NbEssais extends RuleInteger implements Rule {
    private final static String reponseErr = "Le nombre d'essais doit être de 10 ou 12.";

    private final static String demande = "Entrez le nombre d'essais maximum (10 ou 12)";

    public NbEssais() {
        super(reponseErr);
    }

    @Override
    public void setValue(String value){
        int v = Integer.parseInt(value);
        super.setValue(v, !(v == 10 || v == 12));
    }

    @Override
    public String getDemande() {
        return demande;
    }
}
