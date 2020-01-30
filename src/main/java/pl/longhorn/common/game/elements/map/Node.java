package pl.longhorn.common.game.elements.map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.longhorn.common.game.elements.position.Position;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Node {
    private Position position;
    private Node parent;

    public static Node start(Position position) {
        return new Node(position, null);
    }

    public Node goTo(Position nextPosition) {
        return new Node(nextPosition, this);
    }
}
