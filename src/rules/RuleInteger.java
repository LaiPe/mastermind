package rules;

public abstract class RuleInteger implements Rule {
    private final int value;

    public RuleInteger(int value, boolean rule, String message) {
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
