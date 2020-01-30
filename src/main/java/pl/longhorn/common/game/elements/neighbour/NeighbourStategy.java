package pl.longhorn.common.game.elements.neighbour;

import pl.longhorn.common.game.elements.position.Position;

import java.util.List;

public interface NeighbourStategy {
    List<Position> getNeighbour(Position position);
}
