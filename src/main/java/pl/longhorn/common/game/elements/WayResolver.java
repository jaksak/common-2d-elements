package pl.longhorn.common.game.elements;

import pl.longhorn.common.game.elements.map.MapData;
import pl.longhorn.common.game.elements.map.Way;

public interface WayResolver {
    Way findWay(MapData mapData);
}
