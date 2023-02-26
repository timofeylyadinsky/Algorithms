package lt.timofey.execute;

import lt.timofey.model.State;
import lt.timofey.model.TuringMachine;
import lt.timofey.utils.Reader;
import java.util.ArrayList;

//2.16. Г={a,b}. Перенести первый символ непустого слова P в конец слова.
public class ExecutorTask2 {
    public static void running(){
        String[] str = {"task2-test1.txt","task2-test2.txt","task2-test3.txt"};
        ArrayList<String> list = Reader.reader(str[1]);
        TuringMachine tm = new TuringMachine(list, 1);
        System.out.println(tm.toString());
        while(tm.getState() != State.Q0) {
            switch (tm.getState()) {
                case Q1: {
                    switch (tm.getTape().get(tm.getCurrentPosition())) {
                        case "a" -> {
                            tm.getTape().set(tm.getCurrentPosition(), "L");
                            tm.moveRight();
                            tm.setState(State.Q2);
                        }
                        case "b" -> {
                            tm.getTape().set(tm.getCurrentPosition(), "L");
                            tm.moveRight();
                            tm.setState(State.Q3);
                        }
                        default ->
                            tm.setState(State.Q0);
                    }
                    break;
                }
                case Q2:{
                    if ("L" .equals(tm.getTape().get(tm.getCurrentPosition()))) {
                        tm.getTape().set(tm.getCurrentPosition(), "a");
                        tm.setState(State.Q0);
                    } else {
                        tm.moveRight();
                        tm.setState(State.Q2);
                    }
                    break;
                }
                case Q3:{
                    if ("L" .equals(tm.getTape().get(tm.getCurrentPosition()))) {
                        tm.getTape().set(tm.getCurrentPosition(), "b");
                        tm.setState(State.Q0);
                    } else {
                        tm.moveRight();
                        tm.setState(State.Q3);
                    }
                    break;
                }
            }
            System.out.println(tm.toString());
        }
        System.out.println("\n---------FINAL-----------\n" + tm.toString());
    }
}
