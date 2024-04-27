package rules;

public abstract class RuleBoolean {
    private final boolean value;
    private final static String message = "Veuillez entrer un caractère valide !";

    public RuleBoolean(String key) {
        if (key.equals("O") || key.equals("o")){
            value = true;
        } else if (key.equals("N") || key.equals("n")) {
            value = false;
        } else {
            throw new IllegalArgumentException(message);
        }
    }

    public boolean getValue() {
        return value;
    }
}
