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
    }

    private void startAnalysis() {
        this.fitness();

        this.sortByFitness();
    }

    private void fitness() {
        PopulationModel $population = PopulationModel.get();

        for (IndividualModel $individual : $population.$population) {
            $individual.fitness();
        }
    }

    private void sortByFitness() {
        PopulationModel $population = PopulationModel.get();

        // обрати внимание: компаратор вызывается без указания класса.
        // Это из-за статического импорта в начале файла
        $population.$population.sort(FitnessComparator);
    }
}
