package rules;

public abstract class RuleBoolean implements Rule{
    private boolean value;
    private final static String reponseErr = "Veuillez entrer un caractère valide !";

    private final String demande;

    public RuleBoolean(String demande) {
        this.demande = demande;
    }

    @Override
    public void setValue(String key) throws IllegalArgumentException{
        if (key.equals("O") || key.equals("o")){
            value = true;
        } else if (key.equals("N") || key.equals("n")) {
            value = false;
        } else {
            throw new IllegalArgumentException(reponseErr);
        }
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public String getDemande(){
        return demande + " ('O' pour oui, 'N' pour non)";
    }
}
