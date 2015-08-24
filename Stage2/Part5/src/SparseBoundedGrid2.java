import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantMap;
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
    public SparseBoundedGrid2(int rows, int cols) {
        numCols = cols;
        numRows = rows;
        occupantMap = new HashMap<Location, E>();
    }

    @Override
    public E get(Location loc) {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
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
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
        return a;
    }

    @Override
    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    @Override
    public E put(Location loc, E obj) {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    @Override
    public E remove(Location loc) {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.remove(loc);
    }
}
