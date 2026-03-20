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
        this.x1 += dx; this.y1 += dy;
        this.x2 += dx; this.y2 += dy;
    }

    @Override
    public String describe() {
        return String.format("line %s %s %s %s %s",
                formatNum(x1), formatNum(y1), formatNum(x2), formatNum(y2), getColor());
    }

    @Override
    public String convertToSvg() {
        return String.format("<line x1=\"%.1f\" y1=\"%.1f\" x2=\"%.1f\" y2=\"%.1f\" stroke=\"%s\" />",
                x1, y1, x2, y2, getColor());
    }

    @Override
    public boolean isContainedInRect(double rx, double ry, double rw, double rh) {
        // Линията е вътре, ако и двете й точки са в правоъгълника
        return (x1 >= rx && x1 <= rx + rw && y1 >= ry && y1 <= ry + rh) &&
                (x2 >= rx && x2 <= rx + rw && y2 >= ry && y2 <= ry + rh);
    }

    private String formatNum(double val) {
        return (val == (long) val) ? String.format("%d", (long) val) : String.format("%s", val);
    }
}
