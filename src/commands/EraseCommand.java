package commands;

import core.Editor;

/**
 * Команда за изтриване на фигура по пореден номер[cite: 135].
 */
public class EraseCommand implements Command {
    private final Editor editor;

    /**
     * Конструктор, свързващ командата с редактора.
     * @param editor Редакторът, от който ще се трие.
     */
    public EraseCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String getName() {
        return "erase";
    }

    /**
     * Изтрива фигура въз основа на подаден индекс.
     * @param args [0] Номерът на фигурата за изтриване.
     * @return Съобщение за резултата от операцията.
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Error: erase requires a figure number.";
        }
        try {
            int index = Integer.parseInt(args[0]);
            return editor.removeShape(index);
        } catch (NumberFormatException e) {
            return "Error: invalid figure number \"" + args[0] + "\".";
        }
    }
}