/**
 * Created by Zechen Wang on 2015/5/19.
 */

public class MyPoint {
    private double x;
    private double y;

    MyPoint(){

    }

    MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distance(MyPoint secondPoint){
        return distance(this, secondPoint);
    }

    static double distance(MyPoint p1, MyPoint p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    void setX(double x) {
        this.x = x;
    }

    void setY(double y) {
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}