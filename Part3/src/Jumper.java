import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>Jumper</code> can move forward two cells in each move. <br />
 */
public class Jumper extends Bug {
    public Jumper() {
        setColor(Color.YELLOW);
    }

    /**
     * Judge if the jumper can move.
     */
    @Override
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        /*
         * next1: the closest location of current location on current direction.
         * next2: the second location of current location on current direction.
         */
        Location next1 = loc.getAdjacentLocation(getDirection());
        Location next2 = next1.getAdjacentLocation(getDirection());
        if (!gr.isValid(next2)) {
            return false;
        }
        Actor neighbor2 = gr.get(next2);
        return (neighbor2 == null || neighbor2 instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }

    /**
     * Move the jumper.
     */
    @Override
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        // get the location two cells in front of the jumper.
        Location next1 = loc.getAdjacentLocation(getDirection());
        Location next2 = next1.getAdjacentLocation(getDirection());
        if (gr.isValid(next2)) {
            moveTo(next2);
        } else {
            removeSelfFromGrid();
        }
    }
}
