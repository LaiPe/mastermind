package rules;

public class NbCouleur extends RuleInteger implements Rule {
    private final static String reponseErr = "Le nombre de couleur doit être compris entre 6 et 8.";

    public NbCouleur() {
        super(reponseErr);
    }
    public void setValue(Object value){
        int v = (int) value;
        super.setValue(v, (v < 6 || v > 8));
    }
}
