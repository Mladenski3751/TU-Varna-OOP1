package IO;

import Shapes.Shape;
import Shapes.ShapeFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас за работа с файловата система.
 * Отговаря за зареждането и записването на фигури във формат SVG.
 */
public class SvgFileHandler {
    private final ShapeFactory factory = new ShapeFactory();

    /**
     * Зарежда фигури от SVG файл ред по ред.
     * @param filePath Път до файла.
     * @return Списък с десериализирани обекти от тип Shape.
     * @throws IOException При грешка при четене на файла.
     */
    public List<Shape> load(String filePath) throws IOException {
        List<Shape> loaded = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            Shape shape = parseSvgElement(line);
            if (shape != null) {
                loaded.add(shape);
            }
        }

        reader.close();
        return loaded;
    }

    /**
     * Записва текущото съдържание на редактора във файл.
     * @param filePath Път до файла за запис.
     * @param svgContent Низово представяне на SVG структурата.
     * @throws IOException При грешка при запис.
     */
    public void save(String filePath, String svgContent) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(svgContent);
        writer.close();
    }

    /**
     * Разпознава типа на SVG елемента и извиква съответния парсър.
     * @param line Низ от SVG файла.
     * @return Обект от тип Shape или null, ако елементът не се поддържа.
     */
    private Shape parseSvgElement(String line) {
        if (line.startsWith("<circle")) {
            return parseCircle(line);
        }
        if (line.startsWith("<line")) {
            return parseLine(line);
        }
        if (line.startsWith("<rect")) {
            return parseRect(line);
        }
        return null;
    }

    /**
     * Извлича стойността на атрибут от SVG таг.
     * @param line SVG ред.
     * @param name Име на атрибута (напр. "fill").
     * @return Стойността на атрибута като низ.
     */
    private String attr(String line, String name) {
        String search = name + "=\"";
        int start = line.indexOf(search);
        if (start == -1) return null;
        start += search.length();
        int end = line.indexOf("\"", start);
        return line.substring(start, end);
    }

    private Shape parseCircle(String line) {
        try {
            double cx = Double.parseDouble(attr(line, "cx"));
            double cy = Double.parseDouble(attr(line, "cy"));
            double r  = Double.parseDouble(attr(line, "r"));
            String color = attr(line, "fill");
            return factory.create("circle", new String[]{
                    String.valueOf(cx), String.valueOf(cy), String.valueOf(r), color
            });
        } catch (Exception e) {
            return null;
        }
    }

    private Shape parseLine(String line) {
        try {
            double x1 = Double.parseDouble(attr(line, "x1"));
            double y1 = Double.parseDouble(attr(line, "y1"));
            double x2 = Double.parseDouble(attr(line, "x2"));
            double y2 = Double.parseDouble(attr(line, "y2"));
            String color = attr(line, "stroke");
            return factory.create("line", new String[]{
                    String.valueOf(x1), String.valueOf(y1),
                    String.valueOf(x2), String.valueOf(y2), color
            });
        } catch (Exception e) {
            return null;
        }
    }

    private Shape parseRect(String line) {
        try {
            double x = Double.parseDouble(attr(line, "x"));
            double y = Double.parseDouble(attr(line, "y"));
            double w = Double.parseDouble(attr(line, "width"));
            double h = Double.parseDouble(attr(line, "height"));
            String color = attr(line, "fill");
            return factory.create("rectangle", new String[]{
                    String.valueOf(x), String.valueOf(y),
                    String.valueOf(w), String.valueOf(h), color
            });
        } catch (Exception e) {
            return null;
        }
    }
}