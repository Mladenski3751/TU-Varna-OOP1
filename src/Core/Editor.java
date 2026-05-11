package Core;

import Shapes.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * Класът Editor управлява списъка от фигури и изпълнява основните операции върху тях.
 * Служи като връзка между командите и данните.
 */
public class Editor {
    private final List<Shape> shapes = new ArrayList<>();

    /**
     * Добавя нова фигура към списъка.
     * @param shape Обект от тип Shape.
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * Генерира списък от низове с описанието на всички фигури.
     * @return Списък с номерирани описания на фигурите.
     */
    public List<String> getItemsList() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            result.add((i + 1) + ". " + shapes.get(i).describe());
        }
        return result;
    }

    /**
     * Премахва фигура от списъка по нейния пореден номер.
     * @param index Пореден номер (започващ от 1).
     * @return Съобщение за успех или грешка.
     */
    public String removeShape(int index) {
        if (index < 1 || index > shapes.size()) {
            return "Error: There is no figure number " + index + "!";
        }
        Shape removed = shapes.remove(index - 1);
        return "Erased a " + removed.getClass().getSimpleName().toLowerCase() + " (" + index + ")";
    }

    /**
     * Премества всички фигури в редактора.
     * @param dx Хоризонтално отместване.
     * @param dy Вертикално отместване.
     * @return Потвърдително съобщение.
     */
    public String translateAll(double dx, double dy) {
        for (Shape s : shapes) {
            s.shift(dx, dy);
        }
        return "Translated all figures";
    }

    /**
     * Премества само конкретна фигура.
     * @param index Пореден номер на фигурата.
     * @param dx Хоризонтално отместване.
     * @param dy Вертикално отместване.
     * @return Потвърдително съобщение.
     */
    public String translateOne(int index, double dx, double dy) {
        if (index < 1 || index > shapes.size()) {
            return "Error: There is no figure number " + index + "!";
        }
        shapes.get(index - 1).shift(dx, dy);
        return "Translated figure (" + index + ")";
    }

    /**
     * Намира фигури, намиращи се в правоъгълен регион.
     * @param x X на региона.
     * @param y Y на региона.
     * @param w Ширина на региона.
     * @param h Височина на региона.
     * @return Списък с описания на намерените фигури.
     */
    public List<String> findWithinRect(double x, double y, double w, double h) {
        List<String> found = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).isContainedInRect(x, y, w, h)) {
                found.add((i + 1) + ". " + shapes.get(i).describe());
            }
        }
        return found;
    }

    /**
     * Генерира пълното съдържание на SVG файл.
     * @return Низ във валиден SVG формат.
     */
    public String toSvg() {
        StringBuilder sb = new StringBuilder();
        sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");
        for (Shape s : shapes) {
            sb.append("  ").append(s.convertToSvg()).append("\n");
        }
        sb.append("</svg>");
        return sb.toString();
    }

    /** Изчиства всички фигури от паметта. */
    public void clear() {
        shapes.clear();
    }

    /** Проверява дали списъкът е празен. */
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    /** Връща броя на фигурите. */
    public int size() {
        return shapes.size();
    }
}