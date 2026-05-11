package Commands;

import Core.Editor;
import java.util.List;

/**
 * Команда за извеждане на информация за всички фигури на екрана[cite: 135].
 */
public class PrintCommand implements Command {
    private final Editor editor;

    /**
     * Конструктор, приемащ препратка към редактора.
     * @param editor Редакторът, съдържащ фигурите.
     */
    public PrintCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String getName() {
        return "print";
    }

    /**
     * Извлича всички фигури от редактора и ги форматира като текст[cite: 135].
     * @param args Не приема допълнителни аргументи.
     * @return Списък с фигурите или съобщение, че няма заредени такива.
     */
    @Override
    public String execute(String[] args) {
        if (editor.isEmpty()) {
            return "No figures are currently loaded.";
        }
        List<String> items = editor.getItemsList();
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString().trim();
    }
}