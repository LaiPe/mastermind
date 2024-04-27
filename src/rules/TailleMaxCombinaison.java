package rules;

public class TailleMaxCombinaison extends RuleInteger implements Rule {
    private final static String reponseErr = "Le nombre de pions doit être de 4 ou 5.";

    private final static String demande = "Entrez le nombre de pions par combinaison (4 ou 5)";

    public TailleMaxCombinaison() {
        super(reponseErr);
    }

    @Override
    public void setValue(Object value){
        int v = (int) value;
        super.setValue(v, (v < 4 || v > 5));
    }
    @Override
    public String getDemande() {
        return demande;
    }
}
