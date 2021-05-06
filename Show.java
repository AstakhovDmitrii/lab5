package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;
import com.company.Models.Ticket;

public class Show implements Command {
    @Override
    public void Execute(String command) {
        if(Main.tickets.getTickets().size() != 0) {
            for (Ticket ticket : Main.tickets.getTickets().values()) {
                Printer.getInstance().WriteLine(ticket.toString());
            }
        }
        else{
            Printer.getInstance().WriteLine("ни одного элемента нет");
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
