package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;


public class DetailFavoriteNeighbourEvent {


    public Neighbour neighbour;

    public DetailFavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}