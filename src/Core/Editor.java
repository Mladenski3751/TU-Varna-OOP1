package Core;

import Shapes.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Editor {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public List<String> getItemsList() {
        List<String> report = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            report.add((i + 1) + ". " + shapes.get(i).describe());
        }
        return report;
    }

    public String removeShape(int index) {
        if (index >= 1 && index <= shapes.size()) {
            Shape removed = shapes.remove(index - 1);
            return "Successfully erased " + removed.getClass().getSimpleName().toLowerCase() + " (" + index + ")";
        }
        return "Error: There is no figure number " + index + "!";
    }

    public void translateShapes(Integer index, double dx, double dy) {
        if (index == null) {
            // Преместване на всички [cite: 135]
            for (Shape s : shapes) {
                s.shift(dx, dy);
            }
        } else if (index >= 1 && index <= shapes.size()) {
            // Преместване само на една [cite: 135]
            shapes.get(index - 1).shift(dx, dy);
        }
    }

    public List<String> findWithinRectangle(double x, double y, double w, double h) {
        List<String> found = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).isContainedInRect(x, y, w, h)) {
                found.add((i + 1) + ". " + shapes.get(i).describe());
            }
        }
        return found;
    }

    public String getFullSvgCode() {
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
}
