package pl.longhorn.common.game.elements.target;

import pl.longhorn.common.game.elements.position.Position;

public class PreciseReachedTargetStrategy implements ReachedTargetStrategy {
    @Override
    public boolean isEnoughTarget(Position currentPosition, Position targetPosition) {
        return currentPosition.equals(targetPosition);
    }
}
