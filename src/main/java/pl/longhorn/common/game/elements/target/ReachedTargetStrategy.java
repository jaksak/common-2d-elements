package pl.longhorn.common.game.elements.target;

import pl.longhorn.common.game.elements.position.Position;

public interface ReachedTargetStrategy {
    boolean isEnoughTarget(Position currentPosition, Position targetPosition);
}
