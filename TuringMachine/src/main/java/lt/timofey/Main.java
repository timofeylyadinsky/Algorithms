package lt.timofey;


import lt.timofey.execute.ExecutorTask1;
import lt.timofey.execute.ExecutorTask2;
import lt.timofey.model.TuringMachine;
import lt.timofey.utils.Reader;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ExecutorTask1.running();
        System.out.println("\n\n-------NEXT------\n\n");
        ExecutorTask2.running();
    }
}