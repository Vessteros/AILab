package app.Enteties;

import app.Helpers.*;
import org.jetbrains.annotations.Contract;

public class AlgorithmModel {

    private Input $input;

    /**
     * Конструктор
     *
     * @param $input Input
     */
    private AlgorithmModel(Input $input) {
        this.$input = $input;
    }

    /**
     * Точка входа в программу
     *
     * @param $input Input
     */
    public static void start(Input $input) {
        AlgorithmModel $instance = new AlgorithmModel($input);
        System.out.println("Зашли в статический start");
        $instance.start();
    }

    /**
     * @return AlgorithmModel
     */
    @Contract(" -> this")
    private AlgorithmModel start() {
        this.$input.getNecessaryInfo();

        System.out.println(this.$input.$pointsCount);
        for (int[] $row : this.$input.$topology) {
            for (int $point : $row) {
                System.out.println($point);
            }
        }

        return this;
    }
}
