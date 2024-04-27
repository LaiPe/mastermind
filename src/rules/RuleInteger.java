package rules;

public abstract class RuleInteger implements Rule {
    private int value;
    private final String reponseErr;

    public RuleInteger(String reponseErr) {
        this.reponseErr = reponseErr;
    }

    protected void setValue(int value, boolean rule) throws IllegalArgumentException{
        if (rule){
            throw new IllegalArgumentException(reponseErr);
        } else {
            this.value = value;
        }
    }

    public Integer getValue() {
        return value;
    }
}
