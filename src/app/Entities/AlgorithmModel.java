package app.Entities;

import app.Helpers.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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
        }catch (Exception $e) {
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
        // в свойствах инпута сейчас лежат все данные с консоли
        this.$input.getNecessaryInfo();

        this.setTopology(this.$input);

        // если не симметрична, выходим из программы
        this.checkTopologySymmetrical();
        this.setStaticFields();

        PopulationModel $population = PopulationModel.get();

        $population.spawnFirstGeneration();

        this.startAnalysis($population);
    }

    /**
     * Запишем в синглтон топологии значения
     *
     * @param $input Input
     */
    private void setTopology(@NotNull Input $input) {
        TopologyService.get().setTopology($input.$topology);
        TopologyService.get().setPointsCount($input.$pointsCount);
    }

    /**
     * Проставим все необходимые значения из ввода
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


    private void startAnalysis(@NotNull PopulationModel $population) {
        for (IndividualModel $individual : $population.$population) {
            this.fitness($individual);
        }
    }

    private void fitness(@NotNull IndividualModel $individual) {
        int $fitnessResult = 0;

        ArrayList<IndividualModel.ChromosomeModel.GenomeModel> $genome = $individual.$chromosome.$genome;

        for (int $i = 0; $i < $genome.size() - 1; $i++) {
//            $fitnessResult +=
        }
    }
}
