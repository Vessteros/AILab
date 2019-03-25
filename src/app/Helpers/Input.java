package app.Helpers;


import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Scanner;

public class Input implements InputInterface {

    private Scanner $input;

    public int $generations;
    public int $population;
    public int $genomeLength;
    public float $mutationChance;
    public int $crossoverType;
    public int $pointsCount;

    public String $valueRow;
    public int[][] $topology;

    /**
     * Конструктор
     */
    public Input() {
        this.setScanner();
    }

    /**
     * @return Input
     */
    public Input getNecessaryInfo() {
        this
//            .getGenerations()
//            .getPopulation()
//            .getGenomeLength()
//            .getMutationChance()
//            .getCrossoverType()
            .getTopology();

        return this;
    }

    /**
     * @return Input
     */
    public Input setScanner() {
        this.$input = new Scanner(System.in);

        return this;
    }

    /**
     * Запишем кол-во поколений
     *
     * @return Input
     */
    @Contract(" -> this")
    private Input getGenerations() {
        System.out.printf("Введите количество поколений: ");
        this.$generations = $input.nextInt();

        return this;
    }

    /**
     * Запишем количество особей в популяции
     *
     * @return Input
     */
    @Contract(" -> this")
    private Input getPopulation() {
        System.out.printf("Введите количество особей в популяции: ");
        this.$population = $input.nextInt();

        return this;
    }

    /**
     * @return Input
     */
    @Contract(" -> this")
    private Input getGenomeLength() {
        System.out.printf("Введите длину генома: ");
        this.$genomeLength = $input.nextInt();

        return this;
    }

    /**
     * @return Input
     */
    @Contract(" -> this")
    private Input getMutationChance() {
        System.out.printf("Введите шанс мутации: ");
        this.$mutationChance = $input.nextFloat();

        return this;
    }

    /**
     * @return Input
     */
    @Contract(" -> this")
    private Input getCrossoverType() {
        System.out.printf("Выберите тип кроссинговера: ");
        this.$crossoverType = $input.nextInt();

        return this;
    }

    /**
     *
     * @return Input
     */
    @Contract(" -> this")
    private Input getTopology() {
        System.out.printf("Введите количество узлов сети: ");
        this.$pointsCount = $input.nextInt();

        if (this.$pointsCount == 0) {
            return this;
        }

        // Для считывания строк нужен новый объект сканера, ибо конфликтует с newtInt
        Scanner $newScanner = new Scanner(System.in);
        this.$topology = new int[this.$pointsCount][this.$pointsCount];

        // для каждой строки
        for (int $i = 0; $i < this.$pointsCount; $i++) {

            System.out.printf("Введите %d строку значений (слитно через /): ", $i);
            this.$valueRow = $newScanner.nextLine();

            String[] $valueList = $valueRow.split("/");

            if ($valueList.length != this.$pointsCount) {
                System.out.printf("Количество элементов в строке не соответствует указанному количеству вершин в топологии");
                return this;
            }

            // для каждого столбца
            for (int $j = 0; $j < this.$pointsCount; $j++) {
                this.$topology[$j][$i] = Integer.parseInt($valueList[$j]);
                System.out.print(this.$topology[$j][$i]);
            }
        }

        return this;
    }
}
