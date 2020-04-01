package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */

@RunWith(JUnit4.class)

public class NeighbourServiceTest {

    private NeighbourApiService service;
    private List<Neighbour> neighbourList;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        List<Neighbour> neighbourList = new ArrayList<>();
        for (Neighbour neighbour : service.getNeighbours()) {
            if (neighbour.getFavorite()) {
                neighbourList.add(neighbour);
            }
        }

    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighbourWithSuccess() {
        List<Neighbour> neighbourList = new ArrayList<>();
        for (Neighbour neighbour : service.getNeighbours()) {
            if (neighbour.getFavorite()) {
                neighbourList.add(neighbour);
            }
        }
        Neighbour neighbour = neighbourList.get(0);
        assertTrue(neighbour.getFavorite());
    }

   /* @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        List<Neighbour> neighbourList = new ArrayList<>();
        for (Neighbour neighbour : service.getNeighbours()) {
            if (neighbour.getFavorite()) {
                neighbourList.add(neighbour);
            }
        }
        Neighbour neighbourToDelete = neighbourList.get(0);
        neighbourList.deleteNeighbour(neighbourToDelete);
        assertFalse(neighbourList.getNeighbours().contains(neighbourToDelete));
    } */
}