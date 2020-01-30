package pl.longhorn.common.game.elements.estimation;

import lombok.AllArgsConstructor;
import pl.longhorn.common.game.elements.position.Position;

@AllArgsConstructor
public class DiagonalEstimationStrategy implements EstimationStrategy {

    private final int D;

    @Override
    public int countEstimation(Position current, Position target) {
        return D * Math.max(Math.abs(current.getX() - target.getX()), Math.abs(current.getY() - target.getY()));
    }
}
