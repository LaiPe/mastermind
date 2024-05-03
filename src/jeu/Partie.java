package jeu;

import io.saves.SaveSignal;

public interface Partie {

    void doTour() throws SaveSignal;
    void launchPartie();

}
