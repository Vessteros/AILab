package app.Entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;

public class IndividualModel {

    ChromosomeModel $chromosome;

    private float $crossoverChance = 0;

    private int $fitnessResult;

    public float getCrossoverChance() {
        return $crossoverChance;
    }

    public void setCrossoverChance(float $crossoverChance) {
        this.$crossoverChance = $crossoverChance;
    }

    int getFitnessResult() {
        return $fitnessResult;
    }

    void fitness() {
        TopologyService $topology = TopologyService.get();
        ArrayList<IndividualModel.ChromosomeModel.GenomeModel> $genome = this.$chromosome.$genome;

        for (int $i = 0; $i < $genome.size() - 1; $i++) {
            this.$fitnessResult +=  $topology.getTopology()[$genome.get($i).$graphPoint][$genome.get($i+1).$graphPoint];
        }
    }

    /**
     * Конструктор
     */
    IndividualModel() {
        this.$chromosome = new ChromosomeModel();
    }

    IndividualModel(ChromosomeModel $chromosome) {
        this.$chromosome = $chromosome;
    }

    /**
     * Лямбда выражение, изначально был анонимный классец, но IDE предложила заменить, выглядит дорого - богато,
     * поэтому пускай (просто стрелочка, сама понимаешь)
     */
    static Comparator<IndividualModel> FitnessComparator = ($individual1, $individual2) -> {
        int $fitnessResult1 = $individual1.$fitnessResult;
        int $fitnessResult2 = $individual2.$fitnessResult;

        return $fitnessResult1 - $fitnessResult2;
    };

    /**
     * Модель хромосомы
     */
    static class ChromosomeModel {

        /**
         * Все эти данные должны быть одинаковы для всех объектов этого класса -> static
         */
        static int $genomeLength;
        static int $crossoverType;
        static float $mutationChance;

        static int $firstGen;
        static int $lastGen;

        private int $fitnessResult;

        public int getFitnessResult() {
            return $fitnessResult;
        }

        public ChromosomeModel setFitnessResult(int $fitnessResult) {
            this.$fitnessResult = $fitnessResult;

            return this;
        }

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
