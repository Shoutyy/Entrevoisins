package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    public Neighbour neighbour;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favNeighbours = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getFavorite()) {
                favNeighbours.add(neighbour);
            }
        }
        return favNeighbours;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void deleteFavoriteNeighbour(Neighbour fNeighbour) {
        for (Neighbour neighbour : neighbours) {
            if (fNeighbour.getId() == neighbour.getId()) {
                neighbour.setFavorite(false);
            }
        }
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) { neighbours.remove(neighbour); }


    @Override
    public void setFavoriteNeighbour(Neighbour fNeighbour) {
        for (Neighbour neighbour : neighbours) {
            if (fNeighbour.getId() == neighbour.getId()) {
                neighbour.setFavorite(true);
            }
        }
    }
}
