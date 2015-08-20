
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

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends Bug {
    private int steps;
    private int sideLength;
    private int sides; // 添加一个private int变量记录走过的边数,"Z"有3条边

    /**
     * Constructs a z bug that with a given side length
     * 
     * @param length
     *            the side length
     */
    public ZBug(int length) {
        setDirection(Location.EAST); // 初始方向为东
        sides = 0;
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the "z".
     */
    @Override
    public void act() {
        // 已走完的情况下,没有条件分支可以执行,因此bug不会移动
        if (sides <= 2 && steps < sideLength) {
            // 未走完的情况下如果不能移动,同样没有条件分支执行,bug也不会移动
            if (canMove()) {
                move();
                steps++;
            }
        } else if (steps == sideLength) {
            if (sides == 0) {
                setDirection(Location.SOUTHWEST);
            } else if (sides == 1) {
                setDirection(Location.EAST);
            }
            steps = 0;
            sides++; // 若已走完,steps与sideLength相等,则sides自增到3
        }
    }
}
