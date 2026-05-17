package shapes;

/**
 * Фабрика за създаване на фигури по тип и масив от аргументи.
 * Използва {@link ShapeType} enum за разпознаване на типа.
 * Позволява лесно добавяне на нови типове фигури.
 */
public class ShapeFactory {

    /**
     * Създава фигура по подаден тип (като стринг) и аргументи.
     * Типът се преобразува към {@link ShapeType} чрез {@code fromLabel}.
     *
     * @param typeLabel типът на фигурата ("circle", "line", "rectangle")
     * @param args      масив от аргументи специфични за типа
     * @return новата фигура или null ако типът не е познат
     */
    public Shape create(String typeLabel, String[] args) {
        ShapeType type = ShapeType.fromLabel(typeLabel);
        if (type == null) {
            return null;
        }
        if (type == ShapeType.CIRCLE) {
            return createCircle(args);
        }
        if (type == ShapeType.LINE) {
            return createLine(args);
        }
        if (type == ShapeType.RECTANGLE) {
            return createRectangle(args);
        }
        return null;
    }

    /**
     * Създава кръг от аргументи: cx cy r color.
     *
     * @param args масив с параметри
     * @return нов Circle
     */
    private Shape createCircle(String[] args) {
        double cx    = Double.parseDouble(args[0]);
        double cy    = Double.parseDouble(args[1]);
        double r     = Double.parseDouble(args[2]);
        String color = args[3];
        return new Circle(cx, cy, r, color);
    }

    /**
     * Създава линия от аргументи: x1 y1 x2 y2 color.
     *
     * @param args масив с параметри
     * @return нова Line
     */
    private Shape createLine(String[] args) {
        double x1    = Double.parseDouble(args[0]);
        double y1    = Double.parseDouble(args[1]);
        double x2    = Double.parseDouble(args[2]);
        double y2    = Double.parseDouble(args[3]);
        String color = args[4];
        return new Line(x1, y1, x2, y2, color);
    }

    /**
     * Създава правоъгълник от аргументи: x y width height color.
     * bug: args[3] е height, но се ползва като color - трябва args[4]
     *
     * @param args масив с параметри
     * @return нов Rectangle
     */
    private Shape createRectangle(String[] args) {
        double x     = Double.parseDouble(args[0]);
        double y     = Double.parseDouble(args[1]);
        double w     = Double.parseDouble(args[2]);
        double h     = Double.parseDouble(args[3]);
        String color = args[4];
        return new Rectangle(x, y, w, h, color);
    }
}