import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class JumperTest {
    private static BoundedGrid<Actor> grid;

    @Before
    public void setUp() throws Exception {
        grid = new BoundedGrid<Actor>(10, 10);
    }

    @Test
    public void testMove() {
        Jumper jumper = new Jumper();
        // part1, 当jumper的前方两个格子为空的时候
        jumper.putSelfInGrid(grid, new Location(5, 5));
        jumper.move();
        // 由于默认方向为North, 所以预期位置为(3, 5)
        assertEquals(jumper.getLocation(), new Location(3, 5));
        jumper.removeSelfFromGrid();
        // part1 结束

        // part2, 当jumper前方第二个格子出界时
        jumper.putSelfInGrid(grid, new Location(0, 0));
        jumper.move();
        // 按照预期, jumper应该从grid中移除
        assertEquals(jumper.getGrid(), null);
        if (jumper.getGrid() != null) {
            jumper.removeSelfFromGrid();
        }
        // part2 结束
    }

    @Test
    public void testCanMove() {
        Jumper jumper = new Jumper();
        // part1, 当jumper的前方两个格子为空的时候
        jumper.putSelfInGrid(grid, new Location(5, 5));
        // 由于默认方向为North, 所以预期结果为true
        assertEquals(jumper.canMove(), true);
        jumper.removeSelfFromGrid();
        // part1 结束

        // part2, 当jumper前方第二个格子出界时
        jumper.putSelfInGrid(grid, new Location(0, 0));
        // 按照预期, 结果应该为false
        assertEquals(jumper.canMove(), false);
        jumper.removeSelfFromGrid();
        // part2 结束

        // part3, 当jumper的目标位置被花占据时
        jumper.putSelfInGrid(grid, new Location(3, 3));
        Flower flower = new Flower();
        flower.putSelfInGrid(grid, new Location(1, 3));
        // 按照预期, 结果应该为true
        assertEquals(jumper.canMove(), true);
        flower.removeSelfFromGrid();
        jumper.removeSelfFromGrid();
        // part3 结束

        // part4, 当jumper的目标位置被其他actor(包括Rock)占据时
        jumper.putSelfInGrid(grid, new Location(6, 6));
        Actor actor = new Actor();
        actor.putSelfInGrid(grid, new Location(4, 6));
        // 按照预期, 结果应该为false
        assertEquals(jumper.canMove(), false);
        actor.removeSelfFromGrid();
        jumper.removeSelfFromGrid();
        // part4 结束
    }
}
