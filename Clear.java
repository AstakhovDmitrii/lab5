package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;

public class Clear implements Command {// ни одна команда не используется напрямую. То есть их получение происходит в момент исполнения
    @Override
    public void Execute(String command) throws Exception {
        Main.tickets.getTickets().clear();
        Printer.getInstance().WriteLine("список очищен");
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
