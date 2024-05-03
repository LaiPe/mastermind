package jeu;

import io.saves.SaveSignal;

public interface Partie<T> {

    T doTour() throws SaveSignal;
    boolean launchPartie();

}
