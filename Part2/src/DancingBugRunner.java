
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

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains dancing bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class DancingBugRunner {
    public static void main(String[] args) {
        // dancinA's turn list
        int turnA[] = { 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1 };
        // dancinB's turn list
        int turnB[] = { 5, 7, 6, 4, 3, 9, 2, 0, 100, 85, 43, 20 };
        ActorWorld world = new ActorWorld();
        DancingBug dancingA = new DancingBug(6, turnA);
        dancingA.setColor(Color.ORANGE);
        DancingBug dancingB = new DancingBug(3, turnB);
        world.setGrid(new BoundedGrid<Actor>(20, 20));
        world.add(new Location(7, 8), dancingA);
        world.add(new Location(10, 14), dancingB);
        world.show();
    }

    private DancingBugRunner() {
        // never call
    }
}
