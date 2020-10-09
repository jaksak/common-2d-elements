package pl.longhorn.common.game.elements;

import org.junit.Test;
import pl.longhorn.common.game.elements.estimation.DiagonalEstimationStrategy;
import pl.longhorn.common.game.elements.estimation.EuclidEstimationStrategy;
import pl.longhorn.common.game.elements.estimation.ManhattanEstimationStrategy;
import pl.longhorn.common.game.elements.map.MapData;
import pl.longhorn.common.game.elements.map.Way;
import pl.longhorn.common.game.elements.neighbour.NonVerticalNeighbourStrategy;
import pl.longhorn.common.game.elements.neighbour.VerticalNeighbourStrategy;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;


public class RandomWayResolverTest {

    WayResolver wayResolver = new WayResolverImpl(new NonVerticalNeighbourStrategy(), new ManhattanEstimationStrategy(10));
    WayResolver diagonalResolver = new WayResolverImpl(new NonVerticalNeighbourStrategy(), new DiagonalEstimationStrategy(7));
    WayResolver euclidResolver = new WayResolverImpl(new VerticalNeighbourStrategy(), new EuclidEstimationStrategy(7));

    @Test
    public void shouldGenerateWithDifferentEstimationWithDifferentWay() throws IOException {
        List<Way> ways = findWaysToRandomTarget();
        TestUtil.createResult(TestUtil.createRandomMapData(), ways, generateColors(ways.size()));
    }

    @Test
    public void shouldGenerateDifferentEstimationWithTheSameTarget() throws IOException {
        MapData mapData = findTargetWithWay();

        TestUtil.createResult(mapData, findWayToTheSameTarget(mapData), generateColors(3));

    }

    private List<Way> findWayToTheSameTarget(MapData mapData) {
        List<Way> ways = new LinkedList<>();
        tryUntilNull(() -> wayResolver.findWay(mapData), ways);
        tryUntilNull(() -> diagonalResolver.findWay(mapData), ways);
        tryUntilNull(() -> euclidResolver.findWay(mapData), ways);
        return ways;
    }

    private MapData findTargetWithWay() {
        Way way = null;
        MapData mapData = null;
        while (way == null) {
            mapData = TestUtil.createRandomMapData();
            way = wayResolver.findWay(mapData);
        }
        return mapData;
    }


    private List<Way> findWaysToRandomTarget() {
        List<Way> ways = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            tryUntilNull(() -> wayResolver.findWay(TestUtil.createRandomMapData()), ways);
            tryUntilNull(() -> diagonalResolver.findWay(TestUtil.createRandomMapData()), ways);
            tryUntilNull(() -> euclidResolver.findWay(TestUtil.createRandomMapData()), ways);
        }
        return ways;
    }

    private void tryUntilNull(Supplier<Way> supplier, List<Way> results) {
        tryUntilNull(supplier, results, 0);
    }

    private void tryUntilNull(Supplier<Way> supplier, List<Way> results, int i) {
        Way way = supplier.get();
        if (way != null) {
            results.add(way);
            System.out.println(i);
        } else {
            tryUntilNull(supplier, results, ++i);
        }
    }

    private List<String> generateColors(int size) {
        List<String> colors = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            colors.add(generateColor());
        }
        return colors;
    }

    private String generateColor() {
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        return String.format("#%06x", rand_num);
    }
}
