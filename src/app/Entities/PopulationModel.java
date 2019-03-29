package app.Entities;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

/**
 * Я решил сделать синглтоном популяцию потому, что в каждой части программы
 * должен быть доступен конкретно текущий набор особей.
 * Изменение популяции в одной части программы должно изменить ее для всех остальных
 * Singleton
 */
class PopulationModel {
    private static PopulationModel $instance = new PopulationModel();

    static int $populationCount = 0;
    static int $generationsCount = 0;

    ArrayList<IndividualModel> $population = new ArrayList<>();

    /**
     * Закрытый конструктор синглтона
     */
    private PopulationModel() {}

    /**
     * Метод для получения объекта синглтона
     * Суть синглтона в том, что во всей программе не может быть больше одного объекта текущего класса
     * В пхп это достаточно безобидная вещь, которая всего лишь вносит глобальный контекст во флоу программы
     * В жаве есть многопоточность, и, если ты обратишься к синглтону и будешь изменять его состояние
     * из разных потоков, то может образоваться гонка(полный пиздец, на сколько я знаю из статей)
     *
     * Так что с силой приходит ответственность, жи есть, братка
     *
     * @return PopulationModel
     */
    @Contract(pure = true)
    static PopulationModel get() {
        return $instance;
    }

    /**
     * Генерация первого поколения
     */
    void spawnFirstGeneration() throws Exception {
        if (PopulationModel.$populationCount == 0) {
            throw new Exception("Не указано количество особей в популяции");
        }

        for (int $i = 0; $i < PopulationModel.$populationCount; $i++) {
            this.$population.add(new IndividualModel());
        }
    }
}
