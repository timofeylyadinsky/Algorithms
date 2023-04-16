package lt.timofey;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[][] firstEquationPowers = {{1,0,2,2,2},{0,0,0,1,0},{0,0,1,0,2},{0,0,1,2,2},{1,2,2,1,2}};
        int[][] secondEquationPowers = {{1,1,2,2,2},{2,1,2,2,2},{0,2,1,1,1},{1,2,1,2,0},{0,0,0,0,1}};
        int[][] thirdEquationPowers = {{0,2,1,2,0},{2,0,2,2,0},{1,2,1,1,0},{0,0,2,0,2},{0,0,0,0,1}};
        //int[][] fourthEquationPowers = {{0,0,0,0,1},{1,0,1,1,0},{0,2,1,0,2},{0,1,2,0,2},{2,0,2,1,2}};
        new EquationSolver(firstEquationPowers, -50).start();
        //new EquationSolver(secondEquationPowers, -50).start();
        //new EquationSolver(thirdEquationPowers, -50).start();
    }

//    Second Array
//    Best solutionEquation{x1=-2, x2=-2, x3=-1, x4=-2, x5=-2}
//    Iteration: 2203 solution: 0.0
//////
// Second Array
//    Best solutionEquation{x1=-1, x2=-35, x3=-76, x4=-50, x5=-50}
//    Iteration: 87813 solution: 0.0
///////
// Third Array
//Best solutionEquation{x1=-16, x2=-6, x3=-1, x4=-1, x5=-18}
//    Iteration: 5302 solution: 0.0
/////////
// Third Array
//Best solutionEquation{x1=-1, x2=-1, x3=-1, x4=-92, x5=-7}
//    Iteration: 1908 solution: 0.0
//////

}
class Equation {
   public int x1;
   public int x2;
   public int x3;
   public int x4;
   public int x5;
   private final Random rand = new Random(System.currentTimeMillis());

    @Override
    public String toString() {
        return "Equation{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", x4=" + x4 +
                ", x5=" + x5 +
                '}';
    }

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

   public int generateGenome(int minValue, int maxValue) {
        return rand.nextInt(maxValue) + minValue;
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
    private final int minValue = -100;
    private final int maxValue = 100;
    private final int populationCount = 10;
    private final double mutationChanceForGoodOffspring = 0.5;
    private final double mutationChanceForBadOffspring = 0.75;
    private int[][] powers;
    private int result;

    public EquationSolver(int[][] powers, int result) {
        this.powers = powers;
        this.result = result;
    }

    private double solutionError(Equation equation) {
        return Math.abs(
                Math.pow(equation.x1, powers[0][0])*Math.pow(equation.x2, powers[0][1])*Math.pow(equation.x3, powers[0][2])*Math.pow(equation.x4, powers[0][3])*Math.pow(equation.x5, powers[0][4]) + Math.pow(equation.x1, powers[1][0])*Math.pow(equation.x2, powers[1][1])*Math.pow(equation.x3, powers[1][2])*Math.pow(equation.x4, powers[1][3])*Math.pow(equation.x5, powers[1][4]) + Math.pow(equation.x1, powers[2][0])*Math.pow(equation.x2, powers[2][1])*Math.pow(equation.x3, powers[2][2])*Math.pow(equation.x4, powers[2][3])*Math.pow(equation.x5, powers[2][4]) + Math.pow(equation.x1, powers[3][0])*Math.pow(equation.x2, powers[3][1])*Math.pow(equation.x3, powers[3][2])*Math.pow(equation.x4, powers[3][3])*Math.pow(equation.x5, powers[3][4]) + Math.pow(equation.x1, powers[4][0])*Math.pow(equation.x2, powers[4][1])*Math.pow(equation.x3, powers[4][2])*Math.pow(equation.x4, powers[4][3])*Math.pow(equation.x5, powers[4][4]) - result
        );
    }

    private double fitnessFunction(Equation equation) {
        return 1/Math.abs(0.00000001 + ((double) solutionError(equation)));
    }

    public void start() {
        List<Equation> population = new Equation().generatePopulation(populationCount, minValue, maxValue);
        double bestSolutionError = fitnessFunction(population.get(0));
        Equation bestSolution = population.get(0);
        int iteration = 0;
        while(bestSolutionError < 1) {
            List<Equation> newPopulation = selection(new ArrayList<>(population));
            List<Equation> nextPopulation = substitution(population, newPopulation);
            double newBestSolutionError = fitnessFunction(nextPopulation.get(0));
            Equation newBestSolution = nextPopulation.get(0);
            if (newBestSolutionError > bestSolutionError) {
                bestSolution = newBestSolution;
                bestSolutionError = newBestSolutionError;
            }
            population = nextPopulation;
            iteration++;
        }
        System.out.println("Best solution" + bestSolution.toString() + "\nIteration: " + iteration + " solution: " + solutionError(bestSolution)+"\n//////\n");


    }


    public ArrayList<Equation> substitution(List<Equation> oldPopulation, List<Equation> newPopulation) {
        List<Equation> substitutedPopulation = new ArrayList<>();
        List<Double> errorsOfEquations = new ArrayList<>();
        oldPopulation.addAll(newPopulation);
        for (Equation i : oldPopulation) {
            errorsOfEquations.add(fitnessFunction(i));
        }
        Collections.sort(errorsOfEquations);
        Collections.reverse(errorsOfEquations);
        for(int i = 0; i < populationCount; i++) {
            for(Equation j : oldPopulation) {
                if(fitnessFunction(j) == errorsOfEquations.get(i)) substitutedPopulation.add(j);
                if(substitutedPopulation.size() >= populationCount) break;
            }
            if(substitutedPopulation.size() >= populationCount) break;
        }
        return new ArrayList<>(substitutedPopulation);
    }
    public List<Equation> selection(ArrayList<Equation> old) {
        List<Equation> children = new ArrayList<>();
        List<Double> errorsOfEquations = new ArrayList<>();
        double total = 0;
        for (Equation i : old) {
            double tmp = fitnessFunction(i);
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
        for(int i = 0; i < populationCount; i++) {
            double choose = rand.nextDouble(1) + 0;
            int position = 0;
            for(Double j : lineOfChance) {
                if (j > choose) {
                    children.add(old.get(position));
                    break;
                } else position++;
            }
        }
        children = crossover((ArrayList<Equation>)children);
        children = mutation((ArrayList<Equation>)children);
        //System.out.println("");
        return children;
    }
    public List<Equation> crossover(ArrayList<Equation> old) {
        List<Equation> newChildren = new ArrayList<>();
        double crossoverChoose = 0.5;
        Random rand  = new Random(System.currentTimeMillis());
        for(int i = 0; i < old.size() - 1; i+=2){
            Equation parent1 = old.get(i);
            Equation parent2 = old.get(i+1);
            double tmp = rand.nextDouble();
            if (tmp > crossoverChoose) {
                newChildren.add(new Equation(parent1.x1, parent1.x2, parent1.x3, parent2.x4, parent2.x5));
                newChildren.add(new Equation(parent2.x1, parent2.x2, parent2.x3, parent1.x4, parent1.x5));
            } else {
                newChildren.add(new Equation(parent1.x1, parent1.x2, parent2.x3, parent1.x4, parent1.x5));
                newChildren.add(new Equation(parent2.x1, parent2.x2, parent1.x3, parent1.x4, parent1.x5));
            }
        }
        return newChildren;
    }

    private List<Equation> mutation(ArrayList<Equation> old) {
        return old.stream().map(i -> fitnessFunction(i) > 0.5 ? new Equation(
                isTrue(mutationChanceForGoodOffspring) ? i.generateGenome(minValue, maxValue): i.x1,
                isTrue(mutationChanceForGoodOffspring) ? i.generateGenome(minValue, maxValue): i.x2,
                isTrue(mutationChanceForGoodOffspring) ? i.generateGenome(minValue, maxValue): i.x3,
                isTrue(mutationChanceForGoodOffspring) ? i.generateGenome(minValue, maxValue): i.x4,
                isTrue(mutationChanceForGoodOffspring) ? i.generateGenome(minValue, maxValue): i.x5
                ):new Equation(
                isTrue(mutationChanceForBadOffspring) ? i.generateGenome(minValue, maxValue): i.x1,
                isTrue(mutationChanceForBadOffspring) ? i.generateGenome(minValue, maxValue): i.x2,
                isTrue(mutationChanceForBadOffspring) ? i.generateGenome(minValue, maxValue): i.x3,
                isTrue(mutationChanceForBadOffspring) ? i.generateGenome(minValue, maxValue): i.x4,
                isTrue(mutationChanceForBadOffspring) ? i.generateGenome(minValue, maxValue): i.x5
        )).collect(Collectors.toList());
    }

    private boolean isTrue(double mutationValue) {
        Random rand = new Random();
        return rand.nextDouble(1)+0 < mutationValue;
    }
}