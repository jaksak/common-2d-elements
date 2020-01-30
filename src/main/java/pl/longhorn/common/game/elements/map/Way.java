package pl.longhorn.common.game.elements.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.longhorn.common.game.elements.position.Position;

import java.util.List;

@Getter
@AllArgsConstructor
public class Way {
    private List<Position> steps;
}
