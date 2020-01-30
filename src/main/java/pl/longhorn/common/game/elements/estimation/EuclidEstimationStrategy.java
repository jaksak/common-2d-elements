package pl.longhorn.common.game.elements.estimation;

import lombok.AllArgsConstructor;
import pl.longhorn.common.game.elements.position.Position;

@AllArgsConstructor
public class EuclidEstimationStrategy implements EstimationStrategy {

    private final int D;

    @Override
    public int countEstimation(Position current, Position target) {
        return D * (getXPart(current, target) + getYPart(current, target));
    }

    private int getYPart(Position current, Position target) {
        return (current.getY() - target.getY()) * (current.getY() - target.getY());
    }

    private int getXPart(Position current, Position target) {
        return (current.getX() - target.getX()) * (current.getX() - target.getX());
    }
}
