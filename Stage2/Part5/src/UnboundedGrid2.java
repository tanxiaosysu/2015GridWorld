
/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number
 * of rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray; // the array storing the grid elements
    private int size; // the grid's current size

    /**
     * Constructs an empty unbounded grid with the default 16 dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     */
    public UnboundedGrid2() {
        size = 16;
        occupantArray = new Object[size][size];
    }

    @Override
    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException(
                    "Location " + loc + " is not valid");
        // isValid只能判断位置是否合法,不能判断位置是不是在当前的size范围内
        if (loc.getCol() >= size || loc.getRow() >= size) {
            // System.out.println("null!\n");
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable
                                                              // warning
    }

    @Override
    public int getNumCols() {
        return -1;
    }

    @Override
    public int getNumRows() {
        return -1;
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        // Look at all grid locations.
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
        return theLocations;
    }

    @Override
    public boolean isValid(Location loc) {
        // row and col must >= 0
        return (0 <= loc.getRow() && 0 <= loc.getCol());
    }

    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException(
                    "Location " + loc + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        // 位置超出当前范围,调用resize
        int minSize = Math.max(loc.getCol(), loc.getRow()) + 1;
        if (size < minSize) {
            resize(minSize);
        }
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException(
                    "Location " + loc + " is not valid");
        // isValid只能判断位置是否合法,不能判断位置是不是在当前的size范围内
        if (loc.getCol() >= size || loc.getRow() >= size) {
            return null;
        }
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

    /**
     * resize the matrix
     * 
     * @param minSize:
     *            size must >= minSize
     */
    public void resize(int minSize) {
        // System.out.println("resize!\n");
        /*
         * store previous array & objects Hint: must use the old size to get
         * occupied locations!
         */
        Object[][] temp = occupantArray.clone();
        ArrayList<Location> locs = new ArrayList<Location>();
        locs = getOccupiedLocations();
        // double size until it is satisfied with the parameter
        while (size < minSize) {
            size *= 2;
        }
        occupantArray = new Object[size][size];
        for (Location location : locs) {
            // copy objects to new array
            occupantArray[location.getRow()][location
                    .getCol()] = temp[location.getRow()][location.getCol()];
        }
    }
}
