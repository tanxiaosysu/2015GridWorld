
/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.grid.Location;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class LocationDemo {
    public static void main(String[] args) {
        Location loc1 = new Location(4, 3);
        Location loc2 = new Location(3, 4);
        Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
        int dir = loc1.getDirectionToward(new Location(6, 5));
        System.out.println(dir);
    }

    private LocationDemo() {
        // never call
    }
}
