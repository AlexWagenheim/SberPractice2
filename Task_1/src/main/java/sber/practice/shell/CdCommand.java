package sber.practice.shell;

import java.io.File;
import java.util.Locale;

public class CdCommand implements Command{

    private final String name = "cd";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] args) {
        if (args.length > 2) {
            throw new IllegalArgumentException("Недопустимое число аргументов");
        } else if (args.length == 2) {
            switch (args[1].toLowerCase(Locale.ROOT)) {
                case ("/.."):
                    String currentDir = ShellBase.getInstance().getCurrentDir();
                    int lastIndex = currentDir.lastIndexOf("\\");
                    String newDirectory = currentDir.substring(0, lastIndex);

                    int index = newDirectory.lastIndexOf("\\");
                    if (index == -1) {
                        newDirectory += "\\";
                    }
                    ShellBase.getInstance().setCurrentDir(newDirectory);

                    return newDirectory;
                case ("/help"):
                    return help();
                default:
                    if (!(args[1].equals("/") || args[1].equals("\\") )) {
                        File directory = new File(args[1]);
                        int lastSlashIndex = args[1].lastIndexOf("\\");
                        if (lastSlashIndex == -1) {
                            args[1] += "\\";
                        }
                        if (directory.exists()) {
                            ShellBase.getInstance().setCurrentDir(args[1]);
                            return args[1];
                        }
                    }
            }
            throw new IllegalArgumentException("Недопустимый аргумент. Введите 'cd /help' для получения справки");
        } else {
            throw new IllegalArgumentException("Команда 'cd' не может быть вызвана без аргументов. Введите 'cd /help' для получения справки");
        }
    }

    @Override
    public String help() {
        return "Команда 'cd' - позволяет сменить текущую рабочую директорию.\n" +
                "   /.. - подняться на уровень выше \n" +
                "   /help - вывести спраку по команде 'cd'" +
                "   если после команды 'help' указать полный путь к каталогу, то он станет текущей рабочей директорией";
    }
}
