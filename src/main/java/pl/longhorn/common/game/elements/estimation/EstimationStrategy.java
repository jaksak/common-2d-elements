package pl.longhorn.common.game.elements.estimation;

import pl.longhorn.common.game.elements.position.Position;

public interface EstimationStrategy {

    int countEstimation(Position current, Position target);
}
