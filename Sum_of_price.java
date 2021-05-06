package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;
import com.company.Models.Ticket;

public class Sum_of_price  implements Command {
    @Override
    public void Execute(String command) {
        float count = 0;
        for (Ticket ticket: Main.tickets.getTickets().values()) {
            count += ticket.getPrice();
        }
        Printer.getInstance().WriteLine(count);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
