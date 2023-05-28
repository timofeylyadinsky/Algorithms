package lt.timofey.task;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;


//[[0, 2, 3, 5], [1, 6, 7, 9], [4], [8]] 4
//        [Rectangle{width=16 ,height=12}, Rectangle{width=6 ,height=10}, Rectangle{width=1 ,height=9}, Rectangle{width=1 ,height=8}, Rectangle{width=18 ,height=4}, Rectangle{width=2 ,height=4}, Rectangle{width=2 ,height=3}, Rectangle{width=5 ,height=2}, Rectangle{width=19 ,height=1}, Rectangle{width=3 ,height=1}]
//        [[0, 2, 3, 5], [1, 7, 9], [4, 6], [8]] 4
//        [Rectangle{width=16 ,height=12}, Rectangle{width=6 ,height=10}, Rectangle{width=1 ,height=9}, Rectangle{width=1 ,height=8}, Rectangle{width=18 ,height=4}, Rectangle{width=2 ,height=4}, Rectangle{width=2 ,height=3}, Rectangle{width=5 ,height=2}, Rectangle{width=19 ,height=1}, Rectangle{width=3 ,height=1}]

public class ContainerAlgorithm {
    public static void main(String[] args) {
        new ContainerAlgorithm().print();
    }


    public DtoResultOfAlgorithm FirstFitDecreasingHigh (List<Rectangle> rectangleList, int containerWidth) {
        int level = 0;
        int levelNum = 1;
        int[] widthOfLevels = new int[rectangleList.size()];
        Arrays.fill(widthOfLevels, containerWidth);
        List<Integer>[] resultOfRectangle = new ArrayList[1];
        resultOfRectangle[0] = new ArrayList<>();
        //System.out.println(rectangleList);
        rectangleList.sort(Comparator.comparingInt(Rectangle::getHeight).reversed());
        //System.out.println(rectangleList);
        resultOfRectangle[level].add(0);
        widthOfLevels[0] -= rectangleList.get(0).getWidth();
        for(int i = 1; i < rectangleList.size(); i++) {
            level = 0;
            if(widthOfLevels[level]>=rectangleList.get(i).getWidth()){
                resultOfRectangle[level].add(i);
                widthOfLevels[level]-=rectangleList.get(i).getWidth();
            } else {
                while(true) {
                    level++;
                    if(level >= resultOfRectangle.length) resultOfRectangle = Arrays.copyOf(resultOfRectangle, level+1);
                    if(widthOfLevels[level]>=rectangleList.get(i).getWidth()){
                        if(resultOfRectangle[level]==null) resultOfRectangle[level] = new ArrayList<>();
                        resultOfRectangle[level].add(i);
                        widthOfLevels[level]-=rectangleList.get(i).getWidth();
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(resultOfRectangle) + " " + resultOfRectangle.length);
        System.out.println(rectangleList.toString());
        return new DtoResultOfAlgorithm(rectangleList, resultOfRectangle, containerWidth);
    }

    public DtoResultOfAlgorithm BestFitDecreasingHigh (List<Rectangle> rectangleList, int containerWidth) {
        int level = 0;
        int levelNum = 1;
        int[] widthOfLevels = new int[1];
        Arrays.fill(widthOfLevels, containerWidth);
        List<Integer>[] resultOfRectangle = new ArrayList[1];
        resultOfRectangle[0] = new ArrayList<>();
        //System.out.println(rectangleList);
        rectangleList.sort(Comparator.comparingInt(Rectangle::getHeight).reversed());
        //System.out.println(rectangleList);
        resultOfRectangle[level].add(0);
        widthOfLevels[0] -= rectangleList.get(0).getWidth();
        for(int i = 1; i < rectangleList.size(); i++) {
            int bestLevel = widthOfLevels.length;
            int bestWidth = containerWidth + 1;
            for(level = 0; level < widthOfLevels.length; level++){
                if(widthOfLevels[level]>=rectangleList.get(i).getWidth()){
                    if ((widthOfLevels[level]-rectangleList.get(i).getWidth() < bestWidth) && (widthOfLevels[level]-rectangleList.get(i).getWidth() >= 0)) {
                        bestLevel = level;
                        bestWidth = widthOfLevels[level]-rectangleList.get(i).getWidth();
                    }
                }else if(level+1 >= widthOfLevels.length){
                     widthOfLevels = Arrays.copyOf(widthOfLevels, widthOfLevels.length+1);
                    widthOfLevels[widthOfLevels.length-1] = containerWidth;
                    //bestLevel = level+1;
                }
            }
            if(bestLevel >= resultOfRectangle.length) resultOfRectangle = Arrays.copyOf(resultOfRectangle, bestLevel+1);
            if(resultOfRectangle[bestLevel]==null) resultOfRectangle[bestLevel] = new ArrayList<>();
            resultOfRectangle[bestLevel].add(i);
            widthOfLevels[bestLevel]-=rectangleList.get(i).getWidth();
        }
        System.out.println(Arrays.toString(resultOfRectangle) + " " + resultOfRectangle.length);
        System.out.println(rectangleList.toString());
        return new DtoResultOfAlgorithm(rectangleList, resultOfRectangle, containerWidth);

    }

    public void print(/*List<Rectangle> rectangleList, List<Integer>[] layers, int widthContainer*/) {
        JFrame j = new JFrame();
        j.setSize(640, 640);
        j.setVisible(true);
        j.add(new DrawingCanvas(j.getWidth(),j.getHeight()));
    }
}

class Rectangle{
    private int height;
    private int width;

    public Rectangle(){}

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                " ,height=" + height +
                '}';
    }

    public List<Rectangle> generateRandomRectangle(int size, int widthContainer) {
        List<Rectangle> rect = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < size; i++){
            rect.add(new Rectangle(rand.nextInt(rand.nextInt(widthContainer)+1)+1,rand.nextInt(rand.nextInt(widthContainer)+1)+1));
        }
        return rect;
    }

}


class DrawingCanvas extends JComponent {
    private int width;
    private int height;

    private int SIZE_OF_ARRAY = 10;

    private int WIDTH_OF_CONTAINER = 20;

    public DrawingCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
//        List<Rectangle> r = new ArrayList<>();
//        r.add(new Rectangle(1,10));
//        r.add(new Rectangle(3,15));
//        r.add(new Rectangle(2,1));
        Rectangle r = new Rectangle();
        List<Rectangle> list = r.generateRandomRectangle(SIZE_OF_ARRAY,WIDTH_OF_CONTAINER);
        DtoResultOfAlgorithm dto = new ContainerAlgorithm().FirstFitDecreasingHigh(list,WIDTH_OF_CONTAINER);
        List<Rectangle> rectangleList = dto.getRectangleList();
        int width = dto.widthContainer;

        List<Integer>[] layers = dto.getLayers();
        int scale = 10;
        //System.out.println(width*scale);
        Rectangle2D rect = new Rectangle2D.Double(0,0,width*scale, getHeight());
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);
        int currentStartWidth = 0;
        int currentStartHeight = 0;
        for(int i=0;i<layers.length;i++){
            for(int j = 0; j < layers[i].size(); j++) {
                Rectangle2D rect2 = new Rectangle2D.Double(currentStartWidth, currentStartHeight, rectangleList.get(layers[i].get(j)).getWidth()*scale, rectangleList.get(layers[i].get(j)).getHeight()*scale);

                Random rand = new Random(System.currentTimeMillis());
                int v = rand.nextInt(30);
                if(v > 25)  g2d.setColor(Color.BLUE);
                else if(v > 20) g2d.setColor(Color.DARK_GRAY);
                else if(v > 15) g2d.setColor(Color.RED);
                else if(v > 10) g2d.setColor(Color.CYAN);
                else if(v > 5) g2d.setColor(Color.ORANGE);
                else g2d.setColor(Color.MAGENTA);
                g2d.fill(rect2);
                g2d.setColor(Color.GREEN);
                //System.out.println(currentStartWidth + "  " + currentStartHeight + " "+  (currentStartWidth + rectangleList.get(layers[i].get(j)).getWidth()*scale) + " "+ (currentStartHeight +  rectangleList.get(layers[i].get(j)).getHeight()*scale));
                g2d.drawRect(currentStartWidth, currentStartHeight, rectangleList.get(layers[i].get(j)).getWidth()*scale,   rectangleList.get(layers[i].get(j)).getHeight()*scale);
                currentStartWidth+=(rectangleList.get(layers[i].get(j)).getWidth()*scale);
            }
            currentStartWidth = 0;
            currentStartHeight += rectangleList.get(layers[i].get(0)).getHeight()*scale;
        }



        /*BF*/
        dto = new ContainerAlgorithm().BestFitDecreasingHigh(list,WIDTH_OF_CONTAINER);
        layers = dto.getLayers();
        rectangleList = dto.getRectangleList();
        rect = new Rectangle2D.Double(width*scale*2,0,width*scale, getHeight());
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);
        currentStartWidth = width*scale*2;
        currentStartHeight = 0;
        for(int i=0;i<layers.length;i++){
            for(int j = 0; j < layers[i].size(); j++) {
                Rectangle2D rect2 = new Rectangle2D.Double(currentStartWidth, currentStartHeight, rectangleList.get(layers[i].get(j)).getWidth()*scale, rectangleList.get(layers[i].get(j)).getHeight()*scale);

                Random rand = new Random(System.currentTimeMillis());
                int v = rand.nextInt(30);
                if(v > 25)  g2d.setColor(Color.RED);
                else if(v > 20) g2d.setColor(Color.DARK_GRAY);
                else if(v > 15) g2d.setColor(Color.GREEN);
                else if(v > 10) g2d.setColor(Color.CYAN);
                else if(v > 5) g2d.setColor(Color.ORANGE);
                else g2d.setColor(Color.MAGENTA);
                g2d.fill(rect2);
                g2d.setColor(Color.BLUE);
                //System.out.println(currentStartWidth + "  " + currentStartHeight + " "+  (currentStartWidth + rectangleList.get(layers[i].get(j)).getWidth()*scale) + " "+ (currentStartHeight +  rectangleList.get(layers[i].get(j)).getHeight()*scale));
                g2d.drawRect(currentStartWidth, currentStartHeight, rectangleList.get(layers[i].get(j)).getWidth()*scale,   rectangleList.get(layers[i].get(j)).getHeight()*scale);
                currentStartWidth+=(rectangleList.get(layers[i].get(j)).getWidth()*scale);
            }
            currentStartWidth = width*scale*2;
            currentStartHeight += rectangleList.get(layers[i].get(0)).getHeight()*scale;
        }

    }
}

class DtoResultOfAlgorithm {
    private List<Rectangle> rectangleList;
    List<Integer>[] layers;
    int widthContainer;

    public DtoResultOfAlgorithm(List<Rectangle> rectangleList, List<Integer>[] layers, int widthContainer) {
        this.rectangleList = rectangleList;
        this.layers = layers;
        this.widthContainer = widthContainer;
    }

    public List<Rectangle> getRectangleList() {
        return rectangleList;
    }

    public List<Integer>[] getLayers() {
        return layers;
    }

    public int getWidthContainer() {
        return widthContainer;
    }
}