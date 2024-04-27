package rules;

public abstract class RuleBoolean{
    private boolean value;
    private final static String message = "Veuillez entrer un caractère valide !";

    public RuleBoolean() {}

    public void setValue(String key) {
        if (key.equals("O") || key.equals("o")){
            value = true;
        } else if (key.equals("N") || key.equals("n")) {
            value = false;
        } else {
            throw new IllegalArgumentException(message);
        }
    }
    public void setValue(Object value) {
        this.value = (boolean) value;
    }

    public Boolean getValue() {
        return value;
    }
}
