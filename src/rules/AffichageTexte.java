package rules;

public class AffichageTexte extends RuleBoolean implements Rule{
    private final static String demande = "Souhaitez-vous que les indices soient textuels ? Attention, cela augmente la difficulté.";

    public AffichageTexte(){
        super(demande);
    }

}
