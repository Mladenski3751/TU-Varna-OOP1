package Shapes;

public class Rectangle extends Shape {
    private double x, y, width, height;

    public Rectangle(double x, double y, double width, double height, String color) {
        super(color);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void shift(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public String convertToSvg() {
        return String.format("<rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" fill=\"%s\" />",
                fmt(x), fmt(y), fmt(width), fmt(height), getColor());
    }

    @Override
    public String describe() {
        return String.format("rectangle %s %s %s %s %s",
                fmt(x), fmt(y), fmt(height), fmt(width), getColor());
    }

    @Override
    public boolean isContainedInRect(double rx, double ry, double rw, double rh) {
        return x >= rx && y >= ry &&
                x + width <= rx + rw &&
                y + height <= ry + rh;
    }
}
