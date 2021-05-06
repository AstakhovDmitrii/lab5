package com.company.Commands;
import com.company.Command;
import com.company.Main;
import com.company.Models.Printer;
import com.company.Models.Ticket;
import java.util.Arrays;
import java.util.Comparator;

public class Print_field_descending_person  implements Command {
    @Override
    public void Execute(String command) {
        try {
            Object[] arrays = Main.tickets.getTickets().values().toArray();
            Arrays.sort(arrays, Comparator.comparing(o -> ((Ticket)o)));// сортируем массив по убыванию
            for (Object ticket : arrays) {// выводим элементы
                Printer.getInstance().WriteLine(ticket.toString());
            }
        }
        catch (Exception e){
            Printer.getInstance().WriteLine(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
