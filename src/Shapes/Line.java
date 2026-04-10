package Shapes;

public class Line extends Shape {
    private double x1, y1, x2, y2;

    public Line(double x1, double y1, double x2, double y2, String color) {
        super(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void shift(double dx, double dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    @Override
    public String describe() {
        return String.format("line %s %s %s %s %s",
                fmt(x1), fmt(y1), fmt(x2), fmt(y2), getColor());
    }

    @Override
    public String convertToSvg() {
        return String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" stroke=\"%s\" />",
                fmt(x1), fmt(y1), fmt(x2), fmt(y2), getColor());
    }

    @Override
    public boolean isContainedInRect(double rx, double ry, double rw, double rh) {
        // bug: строго < вместо <= - линия точно на границата не се засича
        boolean p1ok = x1 >= rx && x1 < rx + rw && y1 >= ry && y1 < ry + rh;
        boolean p2ok = x2 >= rx && x2 < rx + rw && y2 >= ry && y2 < ry + rh;
        return p1ok && p2ok;
    }
}