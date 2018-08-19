package com.sps.monitoringsales.model;

/**
 * Created by sigit on 24/06/2018.
 */

public class TukarHadiahEvent {
    private boolean tertukar;

    public TukarHadiahEvent(boolean tertukar) {
        this.setTertukar(tertukar);
    }


    public boolean isTertukar() {
        return tertukar;
    }

    public void setTertukar(boolean tertukar) {
        this.tertukar = tertukar;
    }
}
