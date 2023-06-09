package sber.practice.shell;

import java.util.Locale;

public class DateCommand implements Command{

    private final String name = "date";

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
                case ("/day"):
                    return date.toString().split(" ")[0];
            }
            throw new IllegalArgumentException("Недопустимый аргумент. Введите 'date /help' для получения справки");
        } else {
            String[] strings = date.toString().split(" ");
            return strings[2] + " " + strings[1] + " " + strings[5];
        }
    }

    @Override
    public String help() {
        return "Команда 'date' - отображает текущую дату. \n" +
                "   /day - вывести день недели \n" +
                "   /help - вывести спраку по команде 'date'";
    }
}
