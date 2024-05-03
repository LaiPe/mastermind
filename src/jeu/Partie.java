package jeu;

import io.SaveSignal;

public interface Partie<T> {

    T doTour() throws SaveSignal;
    boolean launchPartie();

}
