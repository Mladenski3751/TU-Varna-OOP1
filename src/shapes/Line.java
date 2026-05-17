package shapes;

/**
 * Клас, представляващ геометрична линия.
 * Дефинира линия чрез две крайни точки и цвят на контура.
 */
public class Line extends Shape {
    private double x1, y1, x2, y2;

    /**
     * Конструктор за създаване на линия.
     *
     * @param x1    X координата на начална точка.
     * @param y1    Y координата на начална точка.
     * @param x2    X координата на крайна точка.
     * @param y2    Y координата на крайна точка.
     * @param color Цвят на линията.
     */
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
        boolean p1ok = x1 >= rx && x1 < rx + rw && y1 >= ry && y1 < ry + rh;
        boolean p2ok = x2 >= rx && x2 < rx + rw && y2 >= ry && y2 < ry + rh;
        return p1ok && p2ok;
    }

    @Override
    public boolean isContainedInCircle(double cx, double cy, double r) {
        double d1 = Math.sqrt((x1 - cx) * (x1 - cx) + (y1 - cy) * (y1 - cy));
        double d2 = Math.sqrt((x2 - cx) * (x2 - cx) + (y2 - cy) * (y2 - cy));
        return d1 <= r && d2 <= r;
    }
}