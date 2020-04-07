package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

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

    /**
     * {@inheritDoc}
     */

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) { neighbour.setFavorite(false); }

    @Override
    public void deleteNeighbour(Neighbour neighbour) { neighbours.remove(neighbour); }


    @Override
    public void setFavoriteNeighbour(Neighbour fneighbour) {
        for (Neighbour neighbour : neighbours) {
            if (fneighbour.getName() == neighbour.getName()) {
                neighbour.setFavorite(fneighbour.getFavorite());
            }
        }
    }
}
