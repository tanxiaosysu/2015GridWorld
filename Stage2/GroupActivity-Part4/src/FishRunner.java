
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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class FishRunner {
    public static void main(String[] args) {
        int initFlowers = 10;
        int initFishs = 25;
        ActorWorld world = new ActorWorld();
        world.setGrid(new BoundedGrid<>(25, 25));
        world.add(new Location(2, 8), new Rock(Color.PINK));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        for (int i = 0; i < initFlowers; i++) {
            world.add(new Flower());
        }
        for (int i = 0; i < initFishs; i++) {
            world.add(new FishCritter());
        }
        world.show();
    }

    private FishRunner() {
        // never use
    }
}
