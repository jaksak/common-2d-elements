package pl.longhorn.common.game.elements.target;

import pl.longhorn.common.game.elements.position.Position;

public class ImpreciseReachedTargetStrategy implements ReachedTargetStrategy {
    @Override
    public boolean isEnoughTarget(Position currentPosition, Position targetPosition) {
        return Math.abs(currentPosition.getX() - targetPosition.getX()) <= 1 && Math.abs(currentPosition.getY() - targetPosition.getY()) <= 1;
    }
}
