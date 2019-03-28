package app.Entities;

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

        $instance.start();
    }

    /**
     * Начало работы программы
     */
    private void start() {
        // в свойствах инпута сейчас лежат все данные с консоли
        this.$input.getNecessaryInfo();

        // если не симметрична, выходим из программы
        if (!this.checkTopologySymmetrical()) {
            return;
        }

        PopulationModel $population = PopulationModel.get();
    }

    /**
     * Проверка на симметричность топологии
     *
     * @return boolean
     */
    private boolean checkTopologySymmetrical() {
        for (int $i = 0; $i < this.$input.$pointsCount; $i++) {
            for (int $j = 0; $j < this.$input.$pointsCount; $j++) {
                if (this.$input.$topology[$i][$j] != this.$input.$topology[$j][$i]) {
                    System.out.println("Топология не симметрична, проверьте введенные данные.");
                    System.out.println("Завершаю работу.");

                    return false;
                }
            }
        }

        return true;
    }
}
