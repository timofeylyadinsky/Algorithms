package lt.timofey.execute;


import lt.timofey.model.State;
import lt.timofey.model.TuringMachine;
import lt.timofey.utils.Reader;

import java.util.ArrayList;

//1.10. Г={a,b}. Заменить в P каждое вхождение a на bb.
public class ExecutorTask1 {
    public static void running(){
        String[] str = {"task1-test1.txt","task1-test2.txt","task1-test3.txt","task1-test4.txt","task1-test5.txt"};
        ArrayList<String> list = Reader.reader(str[4]);
        TuringMachine tm = new TuringMachine(list, 1);
        tm.setState(State.Q0);
        System.out.println(tm.toString());
        while(tm.getState() != State.Q5) {
            switch (tm.getState()) {
                case Q0 -> {
                    switch (tm.getTape().get(tm.getCurrentPosition())) {
                        case "a" -> {
                            tm.getTape().set(tm.getCurrentPosition(), "b");
                            tm.moveRight();
                            tm.setState(State.Q1);
                        }
                        case "b" -> {
                            tm.getTape().set(tm.getCurrentPosition(), "b");
                            tm.moveRight();
                            tm.setState(State.Q0);
                        }
                        default -> tm.setState(State.Q5);
                    }
                    break;
                }
                case Q1 -> {
                    if ("a".equals(tm.getTape().get(tm.getCurrentPosition()))) {
                        tm.getTape().set(tm.getCurrentPosition(), "L");
                        tm.getTape().add("L");
                        tm.moveRight();
                        tm.setState(State.Q2);
                    } else  if("b".equals(tm.getTape().get(tm.getCurrentPosition()))){
                        tm.getTape().set(tm.getCurrentPosition(), "L");
                        tm.getTape().add("L");
                        tm.moveRight();
                        tm.setState(State.Q3);
                    } else {
                        tm.getTape().set(tm.getCurrentPosition(), "b");
                        tm.getTape().add("L");
                        tm.moveRight();
                        tm.setState(State.Q0);
                    }
                }
                case Q2 -> {
                    switch (tm.getTape().get(tm.getCurrentPosition())){
                        case "a" -> {
                            tm.moveRight();
                            tm.setState(State.Q2);
                        }
                        case "b" -> {
                            tm.getTape().set(tm.getCurrentPosition(), "a");
                            tm.moveRight();
                            tm.setState(State.Q3);
                        }
                        default -> {
                            tm.getTape().set(tm.getCurrentPosition(), "a");
                            tm.moveLeft();

                            tm.setState(State.Q4);
                        }
                    }
                }
                case Q3 -> {
                    switch (tm.getTape().get(tm.getCurrentPosition())){
                        case "a" -> {
                            tm.getTape().set(tm.getCurrentPosition(), "b");
                            tm.moveRight();
                            tm.setState(State.Q2);
                        }
                        case "b" -> {
                            tm.moveRight();
                            tm.setState(State.Q3);
                        }
                        default -> {
                            tm.getTape().set(tm.getCurrentPosition(), "b");

                            tm.moveLeft();
                            tm.setState(State.Q4);
                        }
                    }
                }
                case Q4 -> {
                    switch (tm.getTape().get(tm.getCurrentPosition())){
                        case "a", "b" -> {
                            tm.moveLeft();
                            tm.setState(State.Q4);
                        }
                        default -> {
                            tm.getTape().set(tm.getCurrentPosition(), "b");
                            tm.moveRight();
                            tm.setState(State.Q0);
                        }
                    }
                }
            }
            System.out.println(tm.toString());
        }
        System.out.println("\n---------FINAL-----------\n" + tm.toString());
    }
}



