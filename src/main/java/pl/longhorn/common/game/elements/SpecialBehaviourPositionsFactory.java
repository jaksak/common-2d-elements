package pl.longhorn.common.game.elements;

import pl.longhorn.common.game.elements.map.SpecialBehaviourPositions;
import pl.longhorn.common.game.elements.map.SpecialMapBehaviour;
import pl.longhorn.common.game.elements.position.Position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class SpecialBehaviourPositionsFactory {

    public static SpecialBehaviourPositions create(String collisions, Map<Position, SpecialMapBehaviour> specialMapBehaviours) {
        SpecialMapBehaviour collision = SpecialMapBehaviour.collision();
        Arrays.stream(collisions.split(";"))
                .filter(Objects::nonNull)
                .filter(string -> !string.isBlank())
                .map(SpecialBehaviourPositionsFactory::toPosition)
                .forEach(position -> specialMapBehaviours.put(position, collision));
        return new SpecialBehaviourPositions(specialMapBehaviours);
    }

    private static Position toPosition(String txt) {
        int xy = Integer.parseInt(txt);
        return new Position(xy % 256, xy / 256);
    }
}
