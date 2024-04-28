package rules.map;


import rules.*;

import java.util.HashMap;
import java.util.Map;


public abstract class MapRule {
    protected Map<String, Rule> rulesMap = new HashMap<>();

    public void set(String key, String valueRule) throws IllegalArgumentException {
        Rule rule = rulesMap.get(key);
        if (rule == null){
            throw new RuntimeException("Clé '" + key + "' invalide!");
        }
        rule.setValue(valueRule);
    }

    public Object get(String key) {
        return rulesMap.get(key).getValue();
    }
}
