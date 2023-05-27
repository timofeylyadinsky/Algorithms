package lt.timofey.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BackpackAlgorithm {
    public static void main(String[] args) {
        //new Item().generateItems();

        ArrayList<Item> item = new ArrayList<>();
        item.add(new Item(1,5,6));
        item.add(new Item(8,42,3));
        item.add(new Item(4,24,2));
        item.add(new Item(2,11,5));
        runBackpackAlgorithm(item, 12);
    }

    public static void runBackpackAlgorithm(ArrayList<Item> items, int bagVolume) {
        int[][] matrixOfPrice = new int[items.size()][bagVolume+1];
        int[][] matrixOfWeight = new int[items.size()][bagVolume+1];
        int loop = 0;
        for (Item item : items) {
            for(int i = 0; i <= bagVolume; i++) {
                int az = Math.min(item.getCount(), i / item.getWeight());
                if(loop == 0){
                    matrixOfPrice[loop][i] = item.getPrice() * az;
                    matrixOfWeight[loop][i] = item.getWeight() * az;
                }else{
                    int maxValue = 0;
                    int currentWeight = 0;
                    for (int j = 0; j <= i; j++) {
                        int akz = Math.min(item.getCount(), j / item.getWeight());
                        int wky = item.getPrice() * akz + matrixOfPrice[loop-1][i-j];
                        if (wky > maxValue) {
                            maxValue = wky;
                            currentWeight = j;
                        }
                    }
                    matrixOfPrice[loop][i] = maxValue;
                    matrixOfWeight[loop][i] = currentWeight;
                }
            }
            loop++;
        }
        //System.out.println(Arrays.deepToString(matrixOfPrice));
        printResult(matrixOfPrice);
    }

    public static void printResult(int[][] matrixOfPrice) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < matrixOfPrice[0].length; i++) {
            str.append("\t").append(i);
        }
        str.append("\n");
        for(int i = 0; i < matrixOfPrice.length; i++) {
            str.append("W").append(i+1).append("\t");
            for (int j = 0; j < matrixOfPrice[i].length; j++) {
                str.append(matrixOfPrice[i][j]).append("\t");
            }
            str.append("\n");
        }
        System.out.println(str);
    }
}

class Item {
    private int weight;
    private int price;
    private int count;

    public Item(){}

    public Item(int weight, int price, int count) {
        this.weight = weight;
        this.price = price;
        this.count = count;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    public List<Item> generateItems() {
        int c = 20;
        final int MAX_PRICE = 100;
        final int MAX_COUNT = 100;
        final int MAX_WEIGHT = 20;
        List<Item> itemList = new ArrayList<>(c);
        for(int i = 0; i < c; i++) {
            Random rand = new Random(System.currentTimeMillis());
            itemList.add(new Item(rand.nextInt(MAX_WEIGHT)+1, rand.nextInt(MAX_PRICE) + 1, rand.nextInt(MAX_COUNT) + 1));
        }
        return itemList;
    }
}