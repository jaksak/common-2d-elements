package pl.longhorn.common.game.elements.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpecialMapBehaviour {
    private Integer price;

    public static SpecialMapBehaviour collision() {
        return new SpecialMapBehaviour(null);
    }
}
