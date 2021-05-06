package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;

public class Help implements Command {
    @Override
    public void Execute(String command) {
        for (Command a: Main.commands) {
            Printer.getInstance().WriteLine("команда: " + a.getName());
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
