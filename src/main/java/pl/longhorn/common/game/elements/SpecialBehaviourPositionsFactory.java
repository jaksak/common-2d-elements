package pl.longhorn.common.game.elements;

import pl.longhorn.common.game.elements.map.SpecialBehaviourPositions;
import pl.longhorn.common.game.elements.map.SpecialMapBehaviour;
import pl.longhorn.common.game.elements.position.Position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class SpecialBehaviourPositionsFactory {

    public static SpecialBehaviourPositions createCollisionAsNoMove(String collisions, Map<Position, SpecialMapBehaviour> specialMapBehaviours){
        SpecialMapBehaviour collision = SpecialMapBehaviour.collision();
        return create(collisions, specialMapBehaviours, collision);
    }

    public static SpecialBehaviourPositions createCollisionAsHardMove(String collisions, Map<Position, SpecialMapBehaviour> specialMapBehaviours, int price){
        SpecialMapBehaviour collision = SpecialMapBehaviour.price(price);
        return create(collisions, specialMapBehaviours, collision);
    }

    private static SpecialBehaviourPositions create(String collisions, Map<Position, SpecialMapBehaviour> specialMapBehaviours, SpecialMapBehaviour collisionBehaviour) {
        Arrays.stream(collisions.split(";"))
                .filter(Objects::nonNull)
                .filter(string -> !string.isBlank())
                .map(SpecialBehaviourPositionsFactory::toPosition)
                .forEach(position -> specialMapBehaviours.put(position, collisionBehaviour));
        return new SpecialBehaviourPositions(specialMapBehaviours);
    }

    private static Position toPosition(String txt) {
        int xy = Integer.parseInt(txt);
        return new Position(xy % 256, xy / 256);
    }
}
