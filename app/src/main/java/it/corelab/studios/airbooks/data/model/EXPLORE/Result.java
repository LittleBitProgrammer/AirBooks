package it.corelab.studios.airbooks.data.model.EXPLORE;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

        @SerializedName("recents")
        @Expose
        private Recents recents;
        @SerializedName("world")
        @Expose
        private World world;

        public Recents getRecents() {
            return recents;
        }

        public void setRecents(Recents recents) {
            this.recents = recents;
        }

        public World getWorld() {
            return world;
        }

        public void setWorld(World world) {
            this.world = world;
        }

    }
