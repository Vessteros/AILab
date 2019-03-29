package app.Entities;

import app.Helpers.*;
import org.jetbrains.annotations.*;

import static app.Entities.IndividualModel.FitnessComparator;

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

        try {
            $instance.start();
        } catch (Exception $e) {
            System.out.println($e.getMessage());
        }

    }

    /**
     * Начало работы программы
     *
     * На каждом этапе может выкинуться эксепшн по любой причине, это же жава
     * А я пока плох в типизации, так что пускай он все эксепшены прокидывает выше,
     * а там уже отловлю в едином блоке
     */
    private void start() throws Exception {
        this.getNecessaryInfo();

        this.setTopology();

        this.checkTopologySymmetrical();

        this.setStaticFields();

        this.spawnFirstGeneration();

        this.startAnalysis();
    }

    private void getNecessaryInfo() throws Exception {
        this.$input.getNecessaryInfo();
    }

    /**
     * Запишем в синглтон топологии значения
     */
    private void setTopology() {
        TopologyService.get()
                .setTopology(this.$input.$topology)
                .setPointsCount(this.$input.$pointsCount);
    }

    /**
     * Проставим все необходимые значения с консоли
     */
    private void setStaticFields() {
        PopulationModel.$populationCount = this.$input.$population;
        PopulationModel.$generationsCount = this.$input.$generations;

        IndividualModel.ChromosomeModel.$crossoverType = this.$input.$crossoverType;
        IndividualModel.ChromosomeModel.$genomeLength = this.$input.$genomeLength;
        IndividualModel.ChromosomeModel.$mutationChance = this.$input.$mutationChance;

        IndividualModel.ChromosomeModel.$firstGen = this.$input.$firstPoint;
        IndividualModel.ChromosomeModel.$lastGen = this.$input.$lastPoint;
    }

    /**
     * Создадим первичную популяцию
     */
    private void spawnFirstGeneration() throws Exception {
        PopulationModel.get().spawnFirstGeneration();
    }

    /**
     * Проверка на симметричность топологии
     */
    @Contract(pure = true)
    private void checkTopologySymmetrical() throws Exception {
        TopologyService $topology = TopologyService.get();

        for (int $i = 0; $i < $topology.getPointsCount(); $i++) {
            for (int $j = 0; $j < $topology.getPointsCount(); $j++) {
                if ($topology.getTopology()[$i][$j] != $topology.getTopology()[$j][$i]) {
                    throw new Exception("Топология не симметрична, проверьте введенные данные. Завершаю работу.");
                }
            }
        }

        System.out.println("Топология симметрична, начинаю расчет.");
    }

    /**
     * Вынес все аналитические необходимости в отдельный метод
     */
    private void startAnalysis() {
        this.fitness();

        this.increaseStep();

        this.sortByFitness();
    }

    /**
     * Вычисляем пути для всех особей в популяции
     */
    private void fitness() {
        PopulationModel $population = PopulationModel.get();

        for (IndividualModel $individual : $population.$population) {
            $individual.fitness();
        }
    }

    /**
     * Увеличим шаг
     */
    private void increaseStep() {
        int $step = PopulationModel.get().getStep() + 1;

        PopulationModel.get().setStep($step);
    }

    /**
     * Отсортировать в порядке возрастания результата работы фитнес-функции
     *
     * Обрати внимание: компаратор вызывается без указания класса.
     * Это из-за статического импорта в начале файла
     */
    private void sortByFitness() {
        this.sort();

        this.setCrossoverChance();

        this.printPopulation();
    }

    private void sort() {
        PopulationModel.get().$population.sort(FitnessComparator);
    }

    /**
     * Проставляем шанс скрещивания
     */
    private void setCrossoverChance() {
        int $point = (PopulationModel.$populationCount / 100);

        int $individualNumber = 1;

        for (IndividualModel $individual : PopulationModel.get().$population) {
            $individual.setCrossoverChance(100 - $point*$individualNumber);
            $individualNumber++;
        }
    }

    /**
     * Вывести популяцию в консоль
     */
    private void printPopulation() {
        PopulationModel $population = PopulationModel.get();

        int $individualNumber = 1;

        for (IndividualModel $ind : $population.$population) {
            System.out.printf("Особь %d: ", $individualNumber);
            for (IndividualModel.ChromosomeModel.GenomeModel $gen : $ind.$chromosome.$genome) {
                System.out.print($gen.$graphPoint + " ");
            }

            System.out.printf("Путь: %d ", $ind.getFitnessResult());
            System.out.printf("Шанс ск.: %f\n", $ind.getCrossoverChance());
            $individualNumber++;
        }
    }
}
