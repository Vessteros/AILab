package app;

import app.Entities.AlgorithmModel;
import app.Helpers.Input;

public class Main {
    public static void main(String[] args) {
        Input $input = new Input();

        AlgorithmModel.start($input);
    }
}

/*
1/5/15/8/7
5/2/7/12/32
15/7/3/4/17
8/12/4/9/8
7/32/17/8/11
*/
