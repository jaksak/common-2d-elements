package pl.longhorn.common.game.elements.neighbour;

import pl.longhorn.common.game.elements.position.Position;

import java.util.LinkedList;
import java.util.List;

public class NonVerticalNeighbourStrategy implements NeighbourStategy {

    @Override
    public List<Position> getNeighbour(Position position) {
        List<Position> toReturn = new LinkedList<>();
        addIfExist(position, -1, 0, toReturn);
        addIfExist(position, 1, 0, toReturn);
        addIfExist(position, 0, -1, toReturn);
        addIfExist(position, 0, 1, toReturn);
        return toReturn;
    }

    private void addIfExist(Position position, int xTranslation, int yTranslation, List<Position> positions) {
        int x = position.getX() + xTranslation;
        int y = position.getY() + yTranslation;
        if (x > -1 && y > -1) {
            positions.add(new Position(x, y));
        }
    }
}
