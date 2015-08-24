import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>FishCritter</code> found the nearest flower and step up to the flower
 * for food. The FishCritter brighten after eating food(flower), and darken
 * after every move. It may starve to death(darkest) and eat too much to
 * death(brightest). <br />
 */

public class FishCritter extends Critter {
    private static double BRIGHTENING_FACTOR = 0.01;
    private static double DARKENING_FACTOR = 0.05;

    public FishCritter() {
        setColor(Color.YELLOW);
    }

    // Only get the actor in front, left and right of fish
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int dirs[] = { Location.AHEAD, Location.RIGHT, Location.LEFT };
        ArrayList<Location> locs = new ArrayList<Location>();
        locs = getLocationsInDirections(dirs);
        for (Location loc : locs) {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }

    // If there is a flower in front, eat it. Fish brighten.
    @Override
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (a instanceof Flower) {
                setDirection(getLocation().getDirectionToward(a.getLocation()));
                a.removeSelfFromGrid();
                Color c = getColor();
                int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
                int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
                int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
                setColor(new Color(red, green, blue));
                // After a flower be ate, there will be a new flower add to the
                // grid in random location.
                Flower flower = new Flower();
                Random rand = new Random();
                Location putLoc = new Location(
                        new Integer(rand.nextInt(getGrid().getNumRows())),
                        new Integer(rand.nextInt(getGrid().getNumCols())));
                flower.putSelfInGrid(getGrid(), putLoc);
            }
        }
    }

    /**
     * A fish can only move forward, including HALF_LEFT and HALF_RIGHT. We
     * choose empty locations from the three locations.
     */
    @Override
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        int dirs[] = { Location.AHEAD, Location.HALF_LEFT,
                Location.HALF_RIGHT };
        ArrayList<Location> templocs = new ArrayList<Location>();
        templocs = getLocationsInDirections(dirs);
        if (templocs.size() == 0) {
            return locs;
        }
        for (Location l : templocs) {
            if (getGrid().get(l) == null) {
                locs.add(l);
            }
        }
        return locs;
    }

    /**
     * From the available locations, choose the location nearest to the found
     * flower. If no candidate, return the location of fish.
     * 
     * @param locations
     *            which the fish can move to
     */
    @Override
    public Location selectMoveLocation(ArrayList<Location> locs) {
        Location fishLoc = getLocation();
        if (locs.size() == 0)
            return fishLoc;
        Location flowerLoc = findNearestFlower();
        Location movLoc = new Location(0, 0);
        double distance = 999;
        double newDistance = 0;
        for (Location l : locs) {
            newDistance = Math.pow(l.getRow() - flowerLoc.getRow(), 2)
                    + Math.pow(l.getCol() - flowerLoc.getCol(), 2);
            if (newDistance < distance) {
                distance = newDistance;
                movLoc = l;
            }
        }
        return movLoc;
    }

    /**
     * First, turn to the location. Then, move to the location. If the location
     * is the location of fish, just turn half right or half left randomly. And,
     * determing if the fish is dead before every move. If dead, remove the
     * fish, else, darken the fish.
     * 
     * @param target
     *            location
     */
    @Override
    public void makeMove(Location loc) {
        Location curLoc = getLocation();
        if (curLoc == null) {
            return;
        }
        Color c = getColor();
        if (c.getRed() < 20 && c.getGreen() < 20 && c.getBlue() < 20) {
            removeSelfFromGrid();
            return;
        } else
            if (c.getRed() > 200 && c.getGreen() > 200 && c.getBlue() > 200) {
            removeSelfFromGrid();
            return;
        }
        int red = (int) (c.getRed() * (1 + BRIGHTENING_FACTOR));
        int green = (int) (c.getGreen() * (1 + BRIGHTENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 + BRIGHTENING_FACTOR));
        setColor(new Color(Math.min(red, 255), Math.min(green, 255),
                Math.min(blue, 255)));
        if (curLoc.equals(loc)) {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = 45;
            else
                angle = -45;
            setDirection(getDirection() + angle);
        } else {
            int dir = curLoc.getDirectionToward(loc);
            setDirection(dir);
            moveTo(loc);
        }
    }

    // Find location of the nearest flower by using breadth-first search.
    public Location findNearestFlower() {
        Location loc = getLocation();
        Grid g = getGrid();
        int factor;
        int maxRound = g.getNumCols();
        for (factor = 1; factor <= maxRound; factor++) {
            for (int row = loc.getRow() - factor; row <= loc.getRow()
                    + factor; row++) {
                for (int col = loc.getCol() - factor; col <= loc.getRow()
                        + factor; col++) {
                    Location l = new Location(row, col);
                    if (g.isValid(l) && g.get(l) != null) {
                        if (g.get(l) instanceof Flower) {
                            return l;
                        }
                    }
                }
            }
        }
        return loc;
    }

    // Get the nearest location in given direction
    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<Location>();
        if (directions.length == 0) {
            return locs;
        }
        Grid gr = getGrid();
        Location loc = getLocation();
        if (loc == null) {
            return locs;
        }
        for (int d : directions) {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }
}
