package pl.longhorn.common.game.elements.position;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class Position {
    private final int x;
    private final int y;
}
