package lt.timofey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[][] firstEquationPowers = {{1,0,2,2,2},{0,0,0,1,0},{0,0,1,0,2},{0,0,1,2,2},{1,2,2,1,2}};
        int[][] secondEquationPowers = {{1,1,2,2,2},{2,1,2,2,2},{0,2,1,1,1},{1,2,1,2,0},{0,0,0,0,1}};
        int[][] thirdEquationPowers = {{0,2,1,2,0},{2,0,2,2,0},{1,2,1,1,0},{0,0,2,0,2},{0,0,0,0,1}};
        //Arrays.stream(thirdEquationPowers).forEach(i -> System.out.println(Arrays.toString(i)));
        System.out.println(firstEquationPowers[0][2]);
        new EquationSolver().start(firstEquationPowers, -50);
    }



}
class Equation {
   public int x1;
   public int x2;
   public int x3;
   public int x4;
   public int x5;
   private final Random rand = new Random(System.currentTimeMillis());

    public Equation(int x1, int x2, int x3, int x4, int x5) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
    }
    public Equation(){}

    public Equation generateOne(int minValue, int maxValue) {
       return new Equation(rand.nextInt(maxValue) + minValue, rand.nextInt(maxValue) + minValue,rand.nextInt(maxValue) + minValue, rand.nextInt(maxValue) + minValue,rand.nextInt(maxValue) + minValue);
   }

   public ArrayList<Equation> generatePopulation(int size, int minValue, int maxValue) {
        ArrayList<Equation> list = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            list.add(generateOne(minValue, maxValue));
        }
        return list;
   }
}

class EquationSolver {
    private int minValue = -100;
    private int maxValue = 100;
    private int populationCount = 100;

    private double countFitnessFunction(int[][] powers, Equation equation, int result) {
        return Math.abs(
                Math.pow(equation.x1, powers[0][0])*Math.pow(equation.x2, powers[0][1])*Math.pow(equation.x3, powers[0][2])*Math.pow(equation.x4, powers[0][3])*Math.pow(equation.x5, powers[0][4]) + Math.pow(equation.x1, powers[1][0])*Math.pow(equation.x2, powers[1][1])*Math.pow(equation.x3, powers[1][2])*Math.pow(equation.x4, powers[1][3])*Math.pow(equation.x5, powers[1][4]) + Math.pow(equation.x1, powers[2][0])*Math.pow(equation.x2, powers[2][1])*Math.pow(equation.x3, powers[2][2])*Math.pow(equation.x4, powers[2][3])*Math.pow(equation.x5, powers[2][4]) + Math.pow(equation.x1, powers[3][0])*Math.pow(equation.x2, powers[3][1])*Math.pow(equation.x3, powers[3][2])*Math.pow(equation.x4, powers[3][3])*Math.pow(equation.x5, powers[3][4]) + Math.pow(equation.x1, powers[4][0])*Math.pow(equation.x2, powers[4][1])*Math.pow(equation.x3, powers[4][2])*Math.pow(equation.x4, powers[4][3])*Math.pow(equation.x5, powers[4][4]) - result
        );
    }

    private double getSolutionError(int[][] powers, Equation equation, int result) {
        return 1/(1.0+(double) countFitnessFunction(powers, equation, result));
    }

    public void start(int[][] powers, int result) {
        List<Equation> population = new Equation().generatePopulation(populationCount, minValue, maxValue);
        selection(new ArrayList<>(population), powers, result);
    }

    public List<Equation> selection(ArrayList<Equation> old, int[][] powers, int result) {
        List<Equation> children = new ArrayList<>();
        List<Double> errorsOfEquations = new ArrayList<>();
        double total = 0;
        for (Equation i : old) {
            double tmp = getSolutionError(powers, i, result);
            errorsOfEquations.add(tmp);
            total += tmp;
        }
        List<Double> chance = new ArrayList<>();
        double currentPositionOnLine = 0;
        List<Double> lineOfChance = new ArrayList<>();
        for (double i : errorsOfEquations) {
            chance.add(i/total);
            currentPositionOnLine += i/total;
            lineOfChance.add(currentPositionOnLine);
        }
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < 10; i++) {//ten new childrens
            double choose = rand.nextDouble(1) + 0;
            int position = 0;
            for(Double j : lineOfChance) {
                if (j > choose) {
                    children.add(old.get(position));
                    break;
                } else position++;
            }
        }
        System.out.println("");
        return children;
    }
}