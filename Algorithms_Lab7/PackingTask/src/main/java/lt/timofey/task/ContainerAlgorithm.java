package lt.timofey.task;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

public class ContainerAlgorithm {
    public static void main(String[] args) {
//        JFrame j = new JFrame();
//        j.setSize(640, 640);
//        j.setVisible(true);
//        List<Rectangle> r = new ArrayList<>();
//        r.add(new Rectangle(1,10));
//        r.add(new Rectangle(3,15));
//        r.add(new Rectangle(2,1));
//        new ContainerAlgorithm().FirstFitDecreasingHigh(r,20);

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

}


class DrawingCanvas extends JComponent {
    private int width;
    private int height;

    public DrawingCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        List<Rectangle> r = new ArrayList<>();
        r.add(new Rectangle(1,10));
        r.add(new Rectangle(3,15));
        r.add(new Rectangle(2,1));
        DtoResultOfAlgorithm dto = new ContainerAlgorithm().FirstFitDecreasingHigh(r,20);
        List<Rectangle> rectangleList = dto.getRectangleList();
        int width = dto.widthContainer;

        List<Integer>[] layers = dto.getLayers();
        int scale = 10;
        System.out.println(width*scale);
        Rectangle2D rect = new Rectangle2D.Double(0,0,width*scale, getHeight());
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);
        int currentStartWidth = 0;
        int currentStartHeight = 0;
        for(int i=0;i<layers.length;i++){
            for(int j = 0; j < layers[i].size(); j++) {
                Rectangle2D rect2 = new Rectangle2D.Double(currentStartWidth, currentStartHeight, rectangleList.get(j).getWidth()*scale, rectangleList.get(j).getHeight()*scale);

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
                System.out.println(currentStartWidth + "  " + currentStartHeight + " "+  (currentStartWidth + rectangleList.get(j).getWidth()*scale) + " "+ (currentStartHeight +  rectangleList.get(j).getHeight()*scale));
                g2d.drawRect(currentStartWidth, currentStartHeight, rectangleList.get(j).getWidth()*scale,   rectangleList.get(j).getHeight()*scale);
                currentStartWidth+=(rectangleList.get(j).getWidth()*scale);
            }
            currentStartWidth = 0;
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