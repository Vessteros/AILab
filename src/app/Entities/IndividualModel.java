package app.Entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IndividualModel {

    public ChromosomeModel $chromosome;

    IndividualModel() {
        this.$chromosome = new ChromosomeModel();
    }

    /**
     * Модель хромосомы
     */
    public static class ChromosomeModel {

        /**
         * Все эти данные должны быть одинаковы для всех объектов этого класса -> static
         */
        static int $genomeLength;

        static int $firstGen;
        static int $lastGen;

        /**
         * Сама цепочка генов
         */
        ArrayList<GenomeModel> $genome = new ArrayList<>();

        /**
         * Генерация хромосомы
         */
        ChromosomeModel() {
            this.$genome.add(GenomeModel.setFirstGen());

            for (int $i = 1; $i < ChromosomeModel.$genomeLength - 1; $i++) {
                this.$genome.add(GenomeModel.setGen());
            }

            this.$genome.add(GenomeModel.setLastGen());
        }

        /**
         * Класс гена, каждый объект этого класса кладется в родительский массив
         * и является частью генома конкретного индивида
         *
         * Почему создание генов через статические методы, а не сразу через перегрузку?
         * Хощется
         */
        static class GenomeModel {

            /**
             * Вершина графа
             */
            int $graphPoint;

            /**
             * Жи перегрузка
             */
            GenomeModel() {
                this.$graphPoint = (int) (Math.random() * ChromosomeModel.$lastGen);
            }

            GenomeModel(int $pointValue) {
                this.$graphPoint = $pointValue;
            }

            /**
             * Создание рядового гена в геноме.
             *
             * @return GenomeModel
             */
            @NotNull
            @Contract(" -> new")
            static GenomeModel setGen() {
                return new GenomeModel();
            }

            /**
             * Создание первого гена в геноме.
             * Первый и последний гены должны быть изначально установлены пользователем,
             * поэтому нельзя создать стандартный ген.
             *
             * P.S. можно было создать одним методом первый и последний гены, но мне лень писать логику
             *
             * @return GenomeModel
             */
            @NotNull
            @Contract(" -> new")
            static GenomeModel setFirstGen() {
                return new GenomeModel(ChromosomeModel.$firstGen);
            }

            /**
             * Создание последнего гена в геноме.\
             *
             * @return GenomeModel
             */
            @NotNull
            @Contract(" -> new")
            static GenomeModel setLastGen() {
                return new GenomeModel(ChromosomeModel.$lastGen);
            }
        }
    }
}
