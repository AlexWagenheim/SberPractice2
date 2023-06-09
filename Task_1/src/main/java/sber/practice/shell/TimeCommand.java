package sber.practice.shell;

import java.util.Locale;

public class TimeCommand implements Command{

    private final String name = "time";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] args) {
        java.util.Date date = new java.util.Date();
        if (args.length > 2) {
            throw new IllegalArgumentException("Недопустимое число аргументов");
        } else if (args.length == 2) {
            switch (args[1].toLowerCase(Locale.ROOT)) {
                case ("/help"):
                    return help();
                case ("/zone"):
                    return date.toString().split(" ")[4];
            }
            throw new IllegalArgumentException("Недопустимый аргумент. Введите 'time /help' для получения справки");
        } else {
            String[] strings = date.toString().split(" ");
            return strings[3];
        }
    }

    @Override
    public String help() {
        return "Команда 'time' - отображает текущее время. \n" +
                "   /zone - вывести часовой пояс \n" +
                "   /help - вывести спраку по команде 'time'";
    }
}
