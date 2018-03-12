package it.corelab.airbooks;

import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;

import it.corelab.airbooks.object.Review;

/**
 * Created by Roberto_Vecchio on 12/03/18.
 */

/* This listener will block any listView redraws utils unlock() is called */
private class ListViewPredrawListener implements ViewTreeObserver.OnPreDrawListener {

    private View view;
    private boolean locked;

    private ListViewPredrawListener(View view) {
        this.view = view;
    }

    public void lock() {
        if (!locked) {
            locked = true;
            view.getViewTreeObserver().addOnPreDrawListener(this);
        }
    }

    public void unlock() {
        if (locked) {
            locked = false;
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
    }

    @Override
    public boolean onPreDraw() {
        return false;
    }
}