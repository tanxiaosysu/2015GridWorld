import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

/**
 * This class runs a world with additional grid choices.
 */
public final class SparseGridRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        // world.addGridClass("SparseBoundedGrid3");
        world.addGridClass("UnboundedGrid2");

        // world.setGrid(new UnboundedGrid2<Actor>());
        world.add(new Location(2, 2), new Critter());
        // System.out.println(world.getGrid().get(new Location(16, 16)));
        world.show();
    }
}
