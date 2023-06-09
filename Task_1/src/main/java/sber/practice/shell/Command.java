package sber.practice.shell;

public interface Command {

    String getName();

    String execute(String[] args);

    String help();
}
