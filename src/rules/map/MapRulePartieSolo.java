package rules.map;

import rules.*;



public class MapRulePartieSolo extends MapRule{
    public MapRulePartieSolo(){
        rulesMap.put("nbEssais", new NbEssais());
        rulesMap.put("tailleMaxCombinaison", new TailleMaxCombinaison());
        rulesMap.put("nbCouleur", new NbCouleur());
        rulesMap.put("plsFoisMemeCouleur", new PlsFoisMemeCouleur());
        rulesMap.put("affichageTexte", new AffichageTexte());
    }
}
