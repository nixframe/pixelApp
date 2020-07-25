package pixel;

import javafx.scene.paint.Color;

import java.util.Random;

public class Point {
    private int x;
    private int y;
    private final Color color;

    public Point(int xPos, int yPos) {
        Random rand = new Random(System.currentTimeMillis());
        this.x = xPos;
        this.y = yPos;
        this.color = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Point {" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
