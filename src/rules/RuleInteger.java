package rules;

public abstract class RuleInteger implements Rule {
    private int value;
    private final String message;

    public RuleInteger(String message) {
        this.message = message;
    }

    protected void setValue(int value, boolean rule) {
        if (rule){
            throw new IllegalArgumentException(message);
        } else {
            this.value = value;
        }
    }

    public Integer getValue() {
        return value;
    }
}
