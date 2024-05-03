package jeu;

import io.save.SaveSignal;

public interface Partie<T> {

    T doTour() throws SaveSignal;
    boolean launchPartie();

}
