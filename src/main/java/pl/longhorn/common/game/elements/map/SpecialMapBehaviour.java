package pl.longhorn.common.game.elements.map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecialMapBehaviour {
    private Integer price;

    public static SpecialMapBehaviour collision() {
        return new SpecialMapBehaviour(null);
    }

    public static SpecialMapBehaviour price(int price) {
        return new SpecialMapBehaviour(price);
    }
}
