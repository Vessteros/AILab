package app.Helpers;


import app.Entities.Crossover;
import org.jetbrains.annotations.Contract;

import java.util.Scanner;

public class Input implements InputInterface {

    private Scanner $input;

    public int $generations;
    public int $population;
    public int $genomeLength;
    public float $mutationChance;
    public int $crossoverType;
    public int $pointsCount;

    public int[][] $topology;

    public int $firstPoint;
    public int $lastPoint;

    /**
     * Конструктор
     */
    public Input() {
        this.setScanner();
    }

    public Input getNecessaryInfo() throws Exception {
        this
            .getGenerations()
            .getPopulation()
            .getGenomeLength()
            .getMutationChance()
            .getCrossoverType()
            .getTopology()
            .getPoints();

        return this;
    }

    public Input setScanner() {
        this.$input = new Scanner(System.in);

        return this;
    }

    @Contract(" -> this")
    private Input getGenerations() {
        System.out.println("Введите количество поколений: ");
        this.$generations = $input.nextInt();
        System.out.println("Количество поколений установлено.\n");

        return this;
    }

    @Contract(" -> this")
    private Input getPopulation() {
        System.out.println("Введите количество особей в популяции: ");
        this.$population = $input.nextInt();
        System.out.println("Количество особей установлено.\n");

        return this;
    }

    /**
     * @return Input
     */
    @Contract(" -> this")
    private Input getGenomeLength() {
        System.out.println("Введите длину генома: ");
        this.$genomeLength = $input.nextInt();
        System.out.println("Длина генома установлена.\n");

        return this;
    }

    @Contract(" -> this")
    private Input getMutationChance() {
        System.out.println("Введите шанс мутации (float запятая): ");
        this.$mutationChance = $input.nextFloat();
        System.out.println("Шанс мутации установлен.\n");

        return this;
    }

    @Contract(" -> this")
    private Input getCrossoverType() {
        System.out.println("Выберите тип кроссинговера: ");
        this.$crossoverType = $input.nextInt();
        System.out.println("Установлен кроссовер типа " + Crossover.valueOf("type" + this.$crossoverType) + "\n");

        return this;
    }

    @Contract(" -> this")
    private Input getTopology() throws Exception {
        System.out.println("Введите количество узлов сети: ");
        this.$pointsCount = $input.nextInt();

        if (this.$pointsCount == 0) {
            throw new Exception("Решение задачи на пустом множестве элементов топологии не имеет смысла. Завершаю работу.");
        }

        System.out.println("Количество узлов сети установлено.\n");

        // Для считывания строк нужен новый объект сканера, ибо конфликтует с newtInt
        Scanner $newScanner = new Scanner(System.in);
        this.$topology = new int[this.$pointsCount][this.$pointsCount];

        // для каждой строки
        for (int $i = 0; $i < this.$pointsCount; $i++) {

            System.out.printf("Введите %d строку значений (слитно через /): ", $i+1);
            String $valueRow = $newScanner.nextLine();

            String[] $valueList = $valueRow.split("/");

            if ($valueList.length != this.$pointsCount) {
                throw new Exception("Количество элементов в строке не соответствует указанному количеству генов в геноме. Завершаю работу.");
            }

            // для каждого столбца
            for (int $j = 0; $j < this.$pointsCount; $j++) {
                this.$topology[$j][$i] = Integer.parseInt($valueList[$j]);
            }
        }

        System.out.println("Топология установлена.\n");

        return this;
    }

    @Contract(" -> this")
    private Input getPoints() {
        System.out.println("Введите первый узел: ");
        this.$firstPoint = $input.nextInt();

        System.out.println("Введите последний узел: ");
        this.$lastPoint = $input.nextInt();
        return this;
    }
}
