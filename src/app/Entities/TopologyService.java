package app.Entities;

import org.jetbrains.annotations.Contract;

public class TopologyService {

    private static TopologyService ourInstance = new TopologyService();

    @Contract(pure = true)
    public static TopologyService getInstance() {
        return ourInstance;
    }

    /**
     * Приватный конструктор
     */
    private TopologyService() {}
}
