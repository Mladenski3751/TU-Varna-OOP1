package commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Реестър, който съхранява всички налични команди в приложението.
 * Позволява лесно извличане и проверка на команди по техните имена.
 */
public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Добавя нова команда към списъка с поддържани команди.
     * @param command Обектът на командата.
     */
    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Извлича обекта на командата по нейното име.
     * @param name Името на търсената команда.
     * @return Обектът на командата или null, ако не е намерена.
     */
    public Command get(String name) {
        return commands.get(name);
    }

    /**
     * Проверява дали дадена команда е регистрирана в системата.
     * @param name Името за проверка.
     * @return true, ако командата съществува; false в противен случай.
     */
    public boolean has(String name) {
        return commands.containsKey(name);
    }
}