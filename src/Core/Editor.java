package Core;

import Shapes.Shape;
import java.util.ArrayList;
import java.util.List;

public class Editor {
    private final List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public List<String> getItemsList() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            result.add((i + 1) + ". " + shapes.get(i).describe());
        }
        return result;
    }

    public String removeShape(int index) {
        if (index < 1 || index > shapes.size()) {
            return "Error: There is no figure number " + index + "!";
        }
        Shape removed = shapes.remove(index - 1);
        return "Erased a " + removed.getClass().getSimpleName().toLowerCase() + " (" + index + ")";
    }

    public String translateAll(double dx, double dy) {
        for (Shape s : shapes) {
            s.shift(dx, dy);
        }
        return "Translated all figures";
    }

    public String translateOne(int index, double dx, double dy) {
        if (index < 1 || index > shapes.size()) {
            return "Error: There is no figure number " + index + "!";
        }
        shapes.get(index - 1).shift(dx, dy);
        return "Translated figure (" + index + ")";
    }

    public List<String> findWithinRect(double x, double y, double w, double h) {
        List<String> found = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).isContainedInRect(x, y, w, h)) {
                found.add((i + 1) + ". " + shapes.get(i).describe());
            }
        }
        return found;
    }

    public String toSvg() {
        StringBuilder sb = new StringBuilder();
        sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");
        for (Shape s : shapes) {
            sb.append("  ").append(s.convertToSvg()).append("\n");
        }
        sb.append("</svg>");
        return sb.toString();
    }

    public void clear() {
        shapes.clear();
    }

    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    public int size() {
        return shapes.size();
    }
}