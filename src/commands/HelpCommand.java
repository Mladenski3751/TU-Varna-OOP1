package commands;

/**
 * Команда за извеждане на помощна информация за поддържаните команди.
 */
public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "help";
    }

    /**
     * Връща списък и кратко описание на всички команди.
     * @param args Не се използват.
     * @return Текст с помощна информация.
     */
    @Override
    public String execute(String[] args) {
        return "The following commands are supported:\n" +
                "open <file>                     opens <file>\n" +
                "close                           closes currently opened file\n" +
                "save                            saves the currently open file\n" +
                "save as <file>                  saves the currently open file in <file>\n" +
                "print                           prints all figures\n" +
                "create <figure> ...             creates a new figure\n" +
                "erase <n>                       erases figure number <n>\n" +
                "translate [<n>] vertical=<dy> horizontal=<dx>  translates figure(s)\n" +
                "within <option> ...             lists figures within a region\n" +
                "help                            prints this information\n" +
                "exit                            exits the program";
    }
}