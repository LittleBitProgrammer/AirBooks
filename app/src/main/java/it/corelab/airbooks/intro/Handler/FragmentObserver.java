package it.corelab.airbooks.intro.Handler;

import java.util.Observable;

public class FragmentObserver extends Observable {
    @Override
    public void notifyObservers() {
        setChanged(); // Set the changed flag to true, otherwise observers won't be notified.
        super.notifyObservers();
    }
}