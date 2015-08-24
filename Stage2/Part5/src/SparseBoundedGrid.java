import java.util.ArrayList;
import java.util.LinkedList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    private ArrayList<LinkedList<OccupantInCol>> occupantArray;
    private int numRows;
    private int numCols;

    /**
     * Constructs an empty sparse bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        numCols = cols;
        numRows = rows;
        occupantArray = new ArrayList<LinkedList<OccupantInCol>>();
        for (int i = 0; i < numRows; i++) {
            occupantArray.add(new LinkedList<OccupantInCol>());
        }
        // System.out.println(occupantArray.size());
    }

    @Override
    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException(
                    "Location " + loc + " is not valid");
        // get the linked list of the row, like the getOccupiedLocations method
        LinkedList<OccupantInCol> tarRow = occupantArray.get(loc.getRow());
        // if tarRow is not null, we can use a foreach to find it.
        if (tarRow != null) {
            for (OccupantInCol occupant : tarRow) {
                if (occupant.getCol() == loc.getCol()) {
                    return (E) occupant.getOccupant();
                }
            }
        }
        // if can't find, we have to return a null.
        return null;
    }

    @Override
    public int getNumCols() {
        return numCols;
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++) {
            // get the linked list of the row
            LinkedList<OccupantInCol> everyRow = occupantArray.get(r);
            // if everyRow is not null, it must has at least one of occupants.
            if (everyRow != null) {
                for (OccupantInCol occupant : everyRow) {
                    theLocations.add(new Location(r, occupant.getCol()));
                }
            }
        }
        return theLocations;
    }

    @Override
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException(
                    "Location " + loc + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        // if oldOccupant have an object, we should remove it
        if (oldOccupant != null) {
            remove(loc);
        }
        occupantArray.get(loc.getRow())
                .add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException(
                    "Location " + loc + " is not valid");

        // Remove the object from the grid.
        E ret = get(loc);
        // if there is nothing in this location, we should return null.
        if (ret == null) {
            return ret;
        }
        // get the linked list of the row, like the getOccupiedLocations method
        LinkedList<OccupantInCol> tarRow = occupantArray.get(loc.getRow());
        for (OccupantInCol occupant : tarRow) {
            if (occupant.getCol() == loc.getCol()) {
                tarRow.remove(occupant);
                return ret;
            }
        }
        return null;
    }
}
