package pl.longhorn.common.game.elements.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.longhorn.common.game.elements.position.Position;

import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class SpecialBehaviourPositions {

    private final Map<Position, SpecialMapBehaviour> specialMapBehaviourByPosition;

    public Optional<SpecialMapBehaviour> find(Position position) {
        return Optional.ofNullable(specialMapBehaviourByPosition.get(position));
    }
}
