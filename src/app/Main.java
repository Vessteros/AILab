package app;

import app.Entities.AlgorithmModel;
import app.Helpers.Input;

public class Main {
    public static void main(String[] args) {
        Input $input = new Input();

        AlgorithmModel.start($input);
    }
}
