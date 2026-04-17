package Shapes;

public class ShapeFactory {

    public Shape create(String type, String[] args) {
        if (type.equals("circle")) {
            double cx = Double.parseDouble(args[0]);
            double cy = Double.parseDouble(args[1]);
            double r  = Double.parseDouble(args[2]);
            String color = args[3];
            return new Circle(cx, cy, r, color);
        }

        if (type.equals("line")) {
            double x1 = Double.parseDouble(args[0]);
            double y1 = Double.parseDouble(args[1]);
            double x2 = Double.parseDouble(args[2]);
            double y2 = Double.parseDouble(args[3]);
            String color = args[4];
            return new Line(x1, y1, x2, y2, color);
        }

        if (type.equals("rectangle")) {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double w = Double.parseDouble(args[2]);
            double h = Double.parseDouble(args[3]);
            String color = args[3];
            return new Rectangle(x, y, w, h, color);
        }

        return null;
    }
}