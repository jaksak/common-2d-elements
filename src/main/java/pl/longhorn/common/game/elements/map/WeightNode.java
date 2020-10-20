package pl.longhorn.common.game.elements.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.common.game.elements.position.Position;

@Getter
@AllArgsConstructor
public class WeightNode {

    private final Node node;
    private final int price;

    public static WeightNode start(Position position) {
        return new WeightNode(Node.start(position), 0);
    }
}
