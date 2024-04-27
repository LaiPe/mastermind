package rules;

public interface Rule {
    Object getValue();
    void setValue(String value) throws IllegalArgumentException;
    String getDemande();
}
