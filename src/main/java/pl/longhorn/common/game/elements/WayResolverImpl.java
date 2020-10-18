package pl.longhorn.common.game.elements;

import lombok.RequiredArgsConstructor;
import lombok.val;
import pl.longhorn.common.game.elements.map.MapData;
import pl.longhorn.common.game.elements.map.Node;
import pl.longhorn.common.game.elements.map.Way;
import pl.longhorn.common.game.elements.map.WeightNode;
import pl.longhorn.common.game.elements.position.Position;
import pl.longhorn.common.game.elements.estimation.EstimationStrategy;
import pl.longhorn.common.game.elements.neighbour.NeighbourStrategy;

import java.util.*;

@RequiredArgsConstructor
public class WayResolverImpl implements WayResolver {

    private final NeighbourStrategy neighbourStrategy;
    private final EstimationStrategy estimationStrategy;

    @Override
    public Way findWay(MapData mapData) {
        Queue<WeightNode> open = prepareToExamine(mapData);
        Set<Position> closed = new HashSet<>();

        while (!open.isEmpty()) {
            WeightNode parent = open.remove();
            if (examinedNodeReachTarget(parent, mapData)) {
                return createWay(parent);
            }
            List<Position> neighbours = neighbourStrategy.getNeighbour(parent.getNode().getPosition());
            List<WeightNode> neighboursByPrice = sortByPrice(neighbours, closed, parent, mapData);
            addToOpen(open, neighboursByPrice);
            closed.add(parent.getNode().getPosition());
        }
        return null;
    }

    private void addToOpen(Queue<WeightNode> open, List<WeightNode> neighboursByPrice) {
        for (WeightNode neighbour : neighboursByPrice) {
                    val node = getNodeByPosition(neighbour.getNode().getPosition(), open);
                    if (node.isPresent() && node.get().getPrice() > neighbour.getPrice()) {
                        open.remove(node.get());
                        open.add(neighbour);
                    } else if (node.isEmpty()) {
                        open.add(neighbour);
                    }
                }
    }

    private List<WeightNode> sortByPrice(List<Position> neighbours, Set<Position> closed, WeightNode parent, MapData mapData) {
        List<WeightNode> toReturn = new LinkedList<>();
        for(Position position : neighbours){
            if (notContainsPosition(position, closed)) {
                val price = getPrice(position, parent, mapData);
                price.ifPresent(integer -> toReturn.add(new WeightNode(parent.getNode().goTo(position), integer)));
            }
        }
        toReturn.sort(Comparator.comparingInt(WeightNode::getPrice));
        return toReturn;
    }

    private Optional<WeightNode> getNodeByPosition(Position position, Queue<WeightNode> open) {
        return open.stream()
                .filter(weightNode -> weightNode.getNode().getPosition().equals(position))
                .findAny();
    }

    private Optional<Integer> getPrice(Position position, WeightNode parent, MapData mapData) {
        Optional<Integer> priceToGoToNode = mapData.findPrice(position);
        return priceToGoToNode.map(integer -> parent.getPrice() + integer + estimationStrategy.countEstimation(position, mapData.getTarget()));
    }

    private boolean notContainsPosition(Position needle, Set<Position> haystack) {
        return !haystack.contains(needle);
    }

    private Way createWay(WeightNode examinedNode) {
        List<Position> steps = new LinkedList<>();
        Node toAdd = examinedNode.getNode();
        while (toAdd != null) {
            steps.add(toAdd.getPosition());
            toAdd = toAdd.getParent();
        }
        Collections.reverse(steps);
        return new Way(steps);
    }

    private boolean examinedNodeReachTarget(WeightNode weightNode, MapData mapData) {
        return mapData.reachEnoughTarget(weightNode.getNode().getPosition());
    }

    private Queue<WeightNode> prepareToExamine(MapData mapData) {
        Queue<WeightNode> weightNodes = new PriorityQueue<>(Comparator.comparingInt(WeightNode::getPrice));
        weightNodes.add(WeightNode.start(mapData.getStart()));
        return weightNodes;
    }
}