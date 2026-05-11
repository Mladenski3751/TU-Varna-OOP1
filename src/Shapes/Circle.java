package Shapes;

/**
 * Клас, представляващ геометрична фигура кръг.
 * Наследява базовия клас Shape и дефинира специфичните за кръг свойства и поведение.
 */
public class Circle extends Shape {
    private double cx, cy, r;

    /**
     * Конструктор за създаване на кръг.
     * @param cx X координата на центъра.
     * @param cy Y координата на центъра.
     * @param r Радиус на кръга.
     * @param color Цвят на запълване.
     */
    public Circle(double cx, double cy, double r, String color) {
        super(color);
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    @Override
    public void shift(double dx, double dy) {
        cx += dx;
        cy += dy;
    }

    @Override
    public String describe() {
        return String.format("circle %s %s %s %s", fmt(cx), fmt(cy), fmt(r), getColor());
    }

    @Override
    public String convertToSvg() {
        return String.format("<circle cx=\"%s\" cy=\"%s\" r=\"%s\" fill=\"%s\" />",
                fmt(cx), fmt(cy), fmt(r), getColor());
    }

    @Override
    public boolean isContainedInRect(double rx, double ry, double rw, double rh) {
        return (cx - r >= rx) && (cx + r <= rw) &&
                (cy - r >= ry) && (cy + r <= rh);
    }
}