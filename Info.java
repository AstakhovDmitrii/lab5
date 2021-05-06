package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;

public class Info implements Command {
    @Override
    public void Execute(String command)  {
        Printer.getInstance().WriteLine("начало старта: " + Main.start.toString() + "\r\nкол-во элементов: " + Main.tickets.getTickets().size());
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
