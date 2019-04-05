package app.Entities;

import org.jetbrains.annotations.Contract;

public class TopologyService {

    private static TopologyService $instance = new TopologyService();

    private int[][] $topology;

    private int $pointsCount;

    /**
     * Приватный конструктор
     */
    @Contract(pure = true)
    private TopologyService() {}

    @Contract(pure = true)
    static TopologyService get() {
        return $instance;
    }

    int[][] getTopology() {
        return $topology;
    }

    TopologyService setTopology(int[][] $topology) {
        this.$topology = $topology;
        return this;
    }

    int getPointsCount() {
        return $pointsCount;
    }

    TopologyService setPointsCount(int $pointsCount) {
        this.$pointsCount = $pointsCount;
        return this;
    }
}
