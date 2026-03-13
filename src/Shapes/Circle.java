package Shapes;

public class Circle extends Shape {
    private double centerX, centerY, radius;

    public Circle(double cx, double cy, double r, String color) {
        super(color);
        this.centerX = cx;
        this.centerY = cy;
        this.radius = r;
    }

    @Override
    public void shift(double x, double y) {
        this.centerX += x;
        this.centerY += y;
    }

    @Override
    public String convertToSvg() {
        return String.format("<circle cx=\"%s\" cy=\"%s\" r=\"%s\" fill=\"%s\" />",
                formatNum(centerX), formatNum(centerY), formatNum(radius), getColor());
    }

    @Override
    public String describe() {
        return String.format("circle %s %s %s %s",
                formatNum(centerX), formatNum(centerY), formatNum(radius), getColor());
    }

    private String formatNum(double val) {
        if (val == (long) val) {
            return String.format("%d", (long) val);
        } else {
            return String.format("%s", val);
        }
    }

    @Override
    public boolean isContainedInRect(double rx, double ry, double rw, double rh) {
        return (centerX - radius >= rx) && (centerX + radius <= rx + rw) &&
                (centerY - radius >= ry) && (centerY + radius <= ry + rh);
    }
}
