import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    public Location next;
    public Location last;
    public boolean isEnd;
    public Stack<ArrayList<Location>> crossLocation;
    public Integer stepCount;
    boolean hasShown; // final message has been shown
    // 各个方向的概率,顺时针排列,依次为上右下左
    private int probablyDir[];

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public MazeBug() {
        setColor(Color.GREEN);
        last = new Location(0, 0);
        crossLocation = new Stack<ArrayList<Location>>();
        crossLocation.push(new ArrayList<Location>());
        hasShown = false;
        isEnd = false;
        probablyDir = new int[] { 0, 0, 0, 0 };
        stepCount = 0;
    }

    /**
     * Moves to the next location of the square.
     */
    @Override
    public void act() {
        boolean willMove = canMove();
        if (isEnd) {
            // to show step count when reach the goal
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            // increase step count when move
            stepCount++;
            // 把当前走过的位置加入栈顶的arrayList
            crossLocation.peek().add(getLocation());
            // 当前方向的概率+1
            probablyDir[getDirection() / 90]++;
        } else if (!willMove) {
            // 这时候必须一步一步返回栈顶arrayList的开头
            ArrayList<Location> curCrossed = crossLocation.peek();
            curCrossed.remove(curCrossed.size() - 1);
            next = curCrossed.get(curCrossed.size() - 1);
            move();
            stepCount++;
            // 当前方向反向的概率-1
            probablyDir[((getDirection() + Location.HALF_CIRCLE)
                    % Location.FULL_CIRCLE) / Location.RIGHT]--;
            if (curCrossed.size() == 1) {
                // 返回之后pop
                crossLocation.pop();
            }
        }
    }

    /**
     * Find all positions that can be move to.
     *
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        // 向上右下左四个方向找
        int dirs[] = {
                Location.AHEAD, Location.RIGHT, Location.HALF_CIRCLE,
                Location.LEFT };
        for (int i = 0; i < dirs.length; i++) {
            Location tarLoc = loc.getAdjacentLocation(dirs[i]);
            if (gr.isValid(tarLoc)) {
                if (gr.get(tarLoc) == null) {
                    valid.add(tarLoc);
                }
            }
        }
        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     *
     * @return true if this bug can move.
     */
    @Override
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        int dirs[] = {
                Location.AHEAD, Location.HALF_CIRCLE, Location.LEFT,
                Location.RIGHT };
        // 当终点在周围时,不能移动且isEnd应当为true
        for (int i = 0; i < dirs.length; i++) {
            Location tarLoc = getLocation().getAdjacentLocation(dirs[i]);
            if (gr.isValid(tarLoc) && gr.get(tarLoc) != null) {
                // Color直接用==计算竟然不可行(真是哔了dog...
                if (gr.get(tarLoc) instanceof Rock
                        && gr.get(tarLoc).getColor().equals(Color.RED)) {
                    isEnd = true;
                    return false;
                }
            }
        }
        ArrayList<Location> nextLocs = getValid(getLocation());
        // 当附近没有可以移动的位置时,不能移动
        if (nextLocs.size() == 0) {
            return false;
        }
        // 当可以移动的位置>1时,说明存在一个节点,这个节点应当被新建一个arraylist入栈
        if (nextLocs.size() > 1) {
            ArrayList<Location> newStackElem = new ArrayList<Location>();
            newStackElem.add(getLocation());
            crossLocation.push(newStackElem);
        }
        // 有可以移动的位置时,向概率最高的方向移动
        int maxProbLoc = 0;
        // 由于nextLocs不一定有4个location,所以只好循环判断取最大值
        for (int i = 0; i < nextLocs.size(); i++) {
            Location loc = nextLocs.get(i);
            int dirNum = getLocation().getDirectionToward(loc) / 90;
            if (probablyDir[dirNum] > probablyDir[maxProbLoc]) {
                maxProbLoc = i;
            }
        }
        next = nextLocs.get(maxProbLoc);
        return true;
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    @Override
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}
