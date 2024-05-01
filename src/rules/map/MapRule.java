package rules.map;


import rules.Rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MapRule {
    private final Map<String, Rule> rulesMap;

    public MapRule(){
        rulesMap = new HashMap<>();
    }

    public void setValue(String key, String valueRule) throws IllegalArgumentException {
        Rule rule = rulesMap.get(key);
        if (rule == null){
            throw new RuntimeException("Clé '" + key + "' invalide!");
        }
        rule.setValue(valueRule);
    }
    public void set(String key, Rule rule) throws IllegalArgumentException {
        rulesMap.put(key,rule);
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
