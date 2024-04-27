package rules;

public class PlsFoisMemeCouleur extends RuleBoolean implements Rule{
    private final static String demande = "Souhaitez-vous qu'il soit possible qu'il y ait plusieurs fois la même couleur dans la combinaison secrète ?";

    public PlsFoisMemeCouleur(){
        super(demande);
    }
}
