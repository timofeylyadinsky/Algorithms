package lt.timofey.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/*
travelling without optimization: [City{x=2.0, y=3.0}, City{x=2.0, y=2.0}, City{x=0.0, y=0.0}, City{x=3.0, y=4.0}, City{x=4.0, y=1.0}, City{x=0.0, y=4.0}, City{x=1.0, y=1.0}, City{x=4.0, y=0.0}]
travel distance without optimization: 26.920811380715314
travelling with optimization: [City{x=2.0, y=3.0}, City{x=0.0, y=4.0}, City{x=3.0, y=4.0}, City{x=4.0, y=1.0}, City{x=4.0, y=0.0}, City{x=0.0, y=0.0}, City{x=1.0, y=1.0}, City{x=2.0, y=2.0}]
travel distance with optimization: 17.22677276241436
 */
public class TSP {
    public static void main(String[] args) {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(0,0));
        cityList.add(new City(1,1));
        cityList.add(new City(2,2));
        cityList.add(new City(2,3));
        cityList.add(new City(3,4));
        cityList.add(new City(0,4));
        cityList.add(new City(4,0));
        cityList.add(new City(4,1));

        Collections.shuffle(cityList);

        System.out.println("travelling without optimization: " + cityList.toString());
        System.out.println("travel distance without optimization: " +  TSP.calculateTravelDistance(cityList));

        TSP.optimizationOfTravelingBySecondChange(cityList);
        System.out.println("travelling with optimization: " + cityList.toString());
        System.out.println("travel distance with optimization: " +  TSP.calculateTravelDistance(cityList));

    }

    public static double calculateTravelDistance(List<City> travelMap) {
        double travelDistance = 0;
        for(int i = 0; i < travelMap.size()-1; i++) {
            travelDistance +=travelMap.get(i).distance(travelMap.get(i+1));
        }
        travelDistance += travelMap.get(0).distance(travelMap.get(travelMap.size()-1));
        return travelDistance;
    }

    public static void optimizationOfTravelingBySecondChange(List<City> travelMap) {
        boolean breakPoint = true;
        while(breakPoint) {
            breakPoint = false;
            for (int i = 0; i < travelMap.size() - 1; i++) {
                for (int j = i + 1; j < travelMap.size(); j++) {
                    double oldDistance = travelMap.get(i).distance(travelMap.get(i + 1)) + travelMap.get(j).distance(travelMap.get( (j + 1) % travelMap.size() ));
                    double newDistance = travelMap.get(i).distance(travelMap.get(j)) + travelMap.get(i + 1).distance(travelMap.get( (j + 1) %  travelMap.size() ));
                    if (oldDistance > newDistance) {
                        City cityTmp = travelMap.get(i + 1);
                        travelMap.set(i + 1, travelMap.get((j)));
                        travelMap.set(j, cityTmp);
                        breakPoint = true;
                    }
                }
            }
        }
    }
}

class City {
    private double x;
    private double y;
    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1-x2,2)-Math.pow(y1-y2,2));
    }
    public double distance(City city2) {
//        System.out.println(this.x + " : " + city2.x);
//        System.out.println(Math.pow(this.x - city2.x,2) + " : " + Math.pow(this.y-city2.y,2));
        return Math.sqrt(Math.pow(this.x- city2.x,2)+Math.pow(this.y-city2.y,2));
    }

    @Override
    public String toString() {
        return "City{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
