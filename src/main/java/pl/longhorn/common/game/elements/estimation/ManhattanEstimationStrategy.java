package pl.longhorn.common.game.elements.estimation;

import lombok.RequiredArgsConstructor;
import pl.longhorn.common.game.elements.position.Position;

@RequiredArgsConstructor
public class ManhattanEstimationStrategy implements EstimationStrategy {

    private final int D;


    @Override
    public int countEstimation(Position current, Position target) {
        return D * (Math.abs(current.getX() - target.getX()) + Math.abs(current.getY() - target.getY()));
    }
}
