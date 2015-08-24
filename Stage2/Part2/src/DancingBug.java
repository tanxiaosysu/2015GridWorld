
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

public class DancingBug extends Bug {
    private int steps;
    private int sideLength;
    private int[] turnList; // 转向数组
    private int currentTurn; // 当前的转向(数组下标)

    /**
     * Constructs a dancing bug with a given side length and a given direction
     * array.
     *
     * @param length
     *            the side length
     * @param turn
     *            the turn list
     */
    public DancingBug(int length, int[] turn) {
        if (turn == null) {
            turnList = new int[0];
        } else {
            turnList = turn.clone();
        }
        currentTurn = 0;
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location.
     */
    @Override
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            // 不能移动或者steps = sideLength时,会按照turnList转向继续走
            // turn的有效次数只有0-7,8次与0次等价,以此类推,故简化循环
            for (int i = 0; i < turnList[currentTurn] % 8; i++) {
                turn();
            }
            steps = 0;
            // 数组循环
            currentTurn = (currentTurn + 1) % turnList.length;
        }
    }
}
