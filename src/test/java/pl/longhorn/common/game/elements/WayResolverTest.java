package pl.longhorn.common.game.elements;

import pl.longhorn.common.game.elements.map.MapData;
import pl.longhorn.common.game.elements.map.Way;
import org.junit.Test;
import pl.longhorn.common.game.elements.neighbour.NonVerticalNeighbourStrategy;
import pl.longhorn.common.game.elements.position.Position;
import pl.longhorn.common.game.elements.estimation.DiagonalEstimationStrategy;
import pl.longhorn.common.game.elements.estimation.EuclidEstimationStrategy;
import pl.longhorn.common.game.elements.estimation.ManhattanEstimationStrategy;
import pl.longhorn.common.game.elements.neighbour.VerticalNeighbourStrategy;

import java.io.IOException;
import java.util.List;

public class WayResolverTest {

    WayResolver wayResolver = new WayResolverImpl(new NonVerticalNeighbourStrategy(), new ManhattanEstimationStrategy(7));
    WayResolver diagonalResolver = new WayResolverImpl(new VerticalNeighbourStrategy(), new DiagonalEstimationStrategy(7));
    WayResolver euclidResolver = new WayResolverImpl(new VerticalNeighbourStrategy(), new EuclidEstimationStrategy(7));

    @Test
    public void shouldGenerateStraightWay() throws IOException {
        MapData mapData = TestUtil.createMapData(new Position(34, 25), new Position(34, 77));
        Way way = wayResolver.findWay(mapData);
        TestUtil.createResult(mapData, way);
    }

    @Test
    public void shouldChooseLowCostWay() throws IOException {
        MapData mapData = TestUtil.createMapData(new Position(34, 19), new Position(34, 25));
        Way way = wayResolver.findWay(mapData);
        TestUtil.createResult(mapData, way);
    }

    @Test
    public void shouldGenerateWay() throws IOException {
        MapData mapData = TestUtil.createMapData(new Position(15, 7), new Position(11, 93));
        Way way = wayResolver.findWay(mapData);
        TestUtil.createResult(mapData, way);
    }

    @Test
    public void shouldGenerateEmptyWay() throws IOException {
        MapData mapData = TestUtil.createMapData(new Position(14, 15), new Position(14, 15));
        Way way = wayResolver.findWay(mapData);
        TestUtil.createResult(mapData, way);
    }

    @Test
    public void shouldGenerateNoWay() throws IOException {
        MapData mapData = TestUtil.createMapData(new Position(14, 15), new Position(15, 30));
        Way way = wayResolver.findWay(mapData);
        TestUtil.createResult(mapData, way);
    }

    @Test
    public void shouldGenerateDifferentEstimationStrategyWay() throws IOException {
        MapData mapData = TestUtil.createMapData(new Position(32, 36), new Position(63, 8));
        List<Way> ways = List.of(
                wayResolver.findWay(mapData),
                diagonalResolver.findWay(mapData),
                euclidResolver.findWay(mapData)
        );
        List<String> colors = List.of("blue", "green", "red");
        TestUtil.createResult(mapData, ways, colors);
    }
}
