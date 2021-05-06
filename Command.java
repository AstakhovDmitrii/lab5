package com.company;

public interface Command {// интерфей который обьединяет все команды
    void Execute(String command) throws Exception;//метод для вызова команды
    String getName();// ее имя
}
