package commands;

import core.Editor;
import shapes.Shape;
import shapes.ShapeFactory;

/**
 * Команда за създаване на нова фигура.
 */
public class CreateCommand implements Command {
    private final Editor editor;
    private final ShapeFactory factory;

    /**
     * Инициализира командата и фабриката за фигури.
     * @param editor Редакторът, в който ще се добави фигурата.
     */
    public CreateCommand(Editor editor) {
        this.editor = editor;
        this.factory = new ShapeFactory();
    }

    @Override
    public String getName() {
        return "create";
    }

    /**
     * Парсва аргументите и добавя нова фигура чрез фабриката.
     * @param args Аргументи: [0] тип фигура, [1...] параметри на фигурата.
     * @return Съобщение за успешно създаване или грешка.
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            return "Error: not enough arguments for create.";
        }

        String type = args[0];
        String[] shapeArgs = new String[args.length - 1];
        System.arraycopy(args, 1, shapeArgs, 0, shapeArgs.length);

        Shape shape = factory.create(type, shapeArgs);
        if (shape == null) {
            return "Error: unknown figure type \"" + type + "\".";
        }

        editor.addShape(shape);
        return "Successfully created " + type + " (" + editor.size() + ")";
    }
}