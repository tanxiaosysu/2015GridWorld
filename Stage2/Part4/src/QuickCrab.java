import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and
 * moves. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter {
    public QuickCrab() {
        setColor(Color.BLUE);
    }

    /**
     * Finds the valid two adjacent locations of this critter in different
     * directions.
     * 
     * @param directions
     *            - an array of directions (which are relative to the current
     *            direction)
     * @return a set of valid locations that are neighbors of the current
     *         location in the given directions
     */
    @Override
    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        // current location
        Location currentLoc = getLocation();
        // current direction
        int currentDir = getDirection();
        for (int d : directions) {
            Location firstLoc = currentLoc.getAdjacentLocation(currentDir + d);
            if (gr.isValid(firstLoc)) {
                // 相邻的d侧格子有效->加入list
                locs.add(firstLoc);
                if (gr.get(firstLoc) == null) {
                    // 相邻的下一个d侧格子有效->加入list
                    Location secondLoc = firstLoc
                            .getAdjacentLocation(currentDir + d);
                    if (gr.isValid(secondLoc)) {
                        locs.add(secondLoc);
                    }
                }
            }
        }
        return locs;
    }
}
