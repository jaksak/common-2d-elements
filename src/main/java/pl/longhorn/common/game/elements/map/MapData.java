package pl.longhorn.common.game.elements.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.longhorn.common.game.elements.position.Position;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class MapData {

    private Position start;
    private Position target;

    private int width;
    private int height;

    private int defaultPrice;

    private SpecialBehaviourPositions specialBehaviourPositions;

    public Optional<Integer> findPrice(Position position) {
        if (positionIsInMap(position)) {
            Optional<SpecialMapBehaviour> specialBehaviourPosition = specialBehaviourPositions.find(position);
            if (specialBehaviourPosition.isEmpty()) {
                return Optional.of(defaultPrice);
            } else {
                return Optional.ofNullable(specialBehaviourPosition.get().getPrice());
            }
        } else {
            return Optional.empty();
        }
    }

    private boolean positionIsInMap(Position position) {
        return position.getX() < width && position.getY() < height;
    }
}
