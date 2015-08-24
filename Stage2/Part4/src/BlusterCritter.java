import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

/**
 * A <code>RockHound</code> moves like a critter but it only eats rock. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter {
    // A default courage of critters around the bluster.
    private int courage;

    public BlusterCritter(int c) {
        courage = c;
    }

    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy neighboring grid locations. Override this method in subclasses to
     * look elsewhere for actors to process.<br />
     * Postcondition: The state of all actors is unchanged.
     * 
     * @return a list of actors that this critter wishes to process.
     */
    @Override
    public ArrayList<Actor> getActors() {
        // current location
        Location curLoc = getLocation();
        ArrayList<Actor> actors = new ArrayList<Actor>();
        // 内圈8个cells
        actors = getGrid().getNeighbors(curLoc);
        // 内圈四个角的neighbors, 涵盖整个两圈
        actors.addAll(getGrid().getNeighbors(
                new Location(curLoc.getRow() - 1, curLoc.getCol() - 1)));
        actors.addAll(getGrid().getNeighbors(
                new Location(curLoc.getRow() - 1, curLoc.getCol() + 1)));
        actors.addAll(getGrid().getNeighbors(
                new Location(curLoc.getRow() + 1, curLoc.getCol() - 1)));
        actors.addAll(getGrid().getNeighbors(
                new Location(curLoc.getRow() + 1, curLoc.getCol() + 1)));
        // 使用Set去重
        Set<Actor> actorSet = new HashSet<Actor>();
        for (Actor eachActor : actors) {
            // exclude itself
            if (eachActor != this) {
                actorSet.add(eachActor);
            }
        }
        // 最终返回actors
        actors.clear();
        actors.addAll(actorSet);
        return actors;
    }

    /**
     * Changing this critter's color to be brighter or darker.
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        // 选择Critters
        int critCount = 0;
        for (Actor eachActor : actors) {
            if (eachActor instanceof Critter) {
                critCount++;
            }
        }
        if (critCount < courage) {
            setColor(brighter(getColor()));
        } else {
            setColor(darker(getColor()));
        }
    }

    /**
     * make the color brighter
     */
    private Color brighter(Color c) {
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        // if RGB = 254, it can't be added with 2.
        red += (red < 254) ? 2 : 0;
        green += (green < 254) ? 2 : 0;
        blue += (blue < 254) ? 2 : 0;
        return new Color(red, green, blue);
    }

    /**
     * make the color darker
     */
    private Color darker(Color c) {
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        // if RGB = 1, it can't be subed with 2.
        red -= (red > 2) ? 2 : 0;
        green -= (green > 2) ? 2 : 0;
        blue -= (blue > 2) ? 2 : 0;
        return new Color(red, green, blue);
    }
}
