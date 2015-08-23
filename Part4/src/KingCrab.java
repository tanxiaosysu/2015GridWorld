import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and
 * moves. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter {
    public KingCrab() {
        setColor(Color.GREEN);
    }

    /**
     * Processes the elements of <code>actors</code>. New actors may be added to
     * empty locations. Implemented to "eat" (i.e. remove) selected actors that
     * are not rocks or critters. Override this method in subclasses to process
     * actors in a different way. <br />
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * 
     * @param actors
     *            the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (!canMoveAway(a)) {
                a.removeSelfFromGrid();
            }
        }
    }

    /**
     * 判断目标位置是否距离KingCrab足够远,是则为true
     * 
     * @param target
     *            location
     * @return true or false
     */
    public boolean ValidateLocation(Location loc) {
        // current location
        Location curLoc = getLocation();
        // 在外围第二圈意味着loc的col和row至少有一个与curLoc相差2
        if (Math.abs(loc.getCol() - curLoc.getCol()) == 2
                || Math.abs(loc.getRow() - curLoc.getRow()) == 2) {
            return true;
        }
        return false;
    }

    /**
     * 判断目标能否移开,能则直接移动
     * 
     * @param target
     *            actor
     * @return true or false
     */
    public boolean canMoveAway(Actor actor) {
        ArrayList<Location> locs = getGrid()
                .getEmptyAdjacentLocations(actor.getLocation());
        for (Location loc : locs) {
            if (ValidateLocation(loc)) {
                actor.moveTo(loc);
                return true;
            }
        }
        return false;
    }
}
