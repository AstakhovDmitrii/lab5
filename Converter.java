package com.company;

import com.company.Models.Printer;
import com.company.Models.Tickets;
import com.company.Models.Transform_date;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.transform.RegistryMatcher;

import java.io.File;
import java.io.FileOutputStream;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Converter {// по сути у этого класса всегда один обьект. И сам класс упрвляет им
    private final Persister Persister;// конвертер
    private static Converter instance;

    private Converter(){

        RegistryMatcher matchers = new RegistryMatcher();
        matchers.bind(ZonedDateTime.class, Transform_date.class);// создаем способ записи ZonedDateTime
        Strategy strategy = new AnnotationStrategy();
        Persister = new Persister( strategy , matchers );
    }

    public static Converter getInstance() {
        synchronized (instance) {
            if (instance == null) {
                instance = new Converter();
            }
        }
        return instance;
    }

    public void write(String path){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);// открываем файл
            Persister.write(Main.tickets, fileOutputStream);// пишем
            fileOutputStream.close();// закрываем
        }
        catch (Exception e){
            Printer.getInstance().WriteLine("нет такого файла");
        }
    }

    public void read(String path){
        try {
            Scanner scanner = new Scanner(new File(path));// построчно читаем
            String read = "";
            while (scanner.hasNext()){
                read += scanner.nextLine();
            }
            Main.tickets = Persister.read(Tickets.class, read);// и переделываем в обьект

        }
        catch (Exception e){
            Printer.getInstance().WriteLine("нет такого файла");
        }
    }

}
