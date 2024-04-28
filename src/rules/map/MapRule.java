package rules.map;


import rules.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public abstract class MapRule {
    protected Map<String, Rule> rulesMap = new HashMap<>();

    public void set(String key, String valueRule) throws IllegalArgumentException {
        Rule rule = rulesMap.get(key);
        if (rule == null){
            throw new RuntimeException("Clé '" + key + "' invalide!");
        }
        rule.setValue(valueRule);
    }

    public Object getValue(String key){
        Rule rule = rulesMap.get(key);
        if (rule == null){
            throw new RuntimeException("Clé '" + key + "' invalide!");
        }
        return rule.getValue();
    }

    public Rule get(String key){
        Rule rule = rulesMap.get(key);
        if (rule == null){
            throw new RuntimeException("Clé '" + key + "' invalide!");
        }
        return rule;
    }

    public Set<Rule> allRules(){
        Set<Rule> rulesSet = new HashSet<>();

        for (Map.Entry<String, Rule> entry : rulesMap.entrySet()) {
            rulesSet.add(entry.getValue());
        }
        return rulesSet;
    }
}
