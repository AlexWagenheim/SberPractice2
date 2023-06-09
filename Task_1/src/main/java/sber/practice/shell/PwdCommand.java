package sber.practice.shell;

import java.util.Locale;

public class PwdCommand implements Command{

    private final String name = "pwd";

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
                case ("/help"):
                    return help();
            }
            throw new IllegalArgumentException("Недопустимый аргумент. Введите 'pwd /help' для получения справки");
        } else {
            return ShellBase.getInstance().getCurrentDir();
        }
    }

    @Override
    public String help() {
        return "Команда 'pwd' - выводит текущую рабочую директорию.\n" +
                "   /help - вывести спраку по команде 'pwd'";
    }
}
