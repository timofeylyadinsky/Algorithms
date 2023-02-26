package lt.timofey.model;

import java.util.ArrayList;

public class TuringMachine {
    private ArrayList<String> tape;
    private int currentPosition;

    private State state;

    public TuringMachine(ArrayList<String> str, int position){
        this.tape = str;
        this.currentPosition = position;
        this.state = State.Q1;
    }


    public ArrayList<String> getTape() {
        return tape;
    }

    public void setTape(ArrayList<String> tape) {
        this.tape = tape;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    public void moveRight() {
        if(currentPosition != tape.size()) currentPosition++;
    }

    public void moveLeft() {
        if(currentPosition != 0) currentPosition--;
    }

    public void set(String a){
        tape.set(currentPosition, a);
    }

    public String get(){
        return tape.get(currentPosition);
    }

    @Override
    public String toString() {
        String res = "State:\n";
        res = res + tape.toString() + "\n";
        ArrayList<String> str = new ArrayList<>();
        for(int i = 0; i<currentPosition;i++){
            str.add("-");
        }
        str.add("|");
        for(int i = currentPosition + 1; i<tape.size();i++){
            str.add("-");
        }
        res = res + str + "\n------------------\n";
        return res;
    }
}
