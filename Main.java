package com.company;

import com.company.Models.*;
import org.reflections.Reflections;
import  com.company.Models.Pole;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static Tickets tickets = new Tickets();// по сути обертка для HashSet<Product>. В классе обьясню смысл
    public static LocalDateTime start;//старт проги
    public static ArrayList<Command> commands = new ArrayList<>();// массив команд. Тут применен принцип полиморфизма.
    public static int ids = 0;//id каждого обьекта
    public static String error = "поле введено неверно. Заменено на ";


    public static ArrayList<Pole> get_fields(Object object) {// функция, в которой мы получаем доступ ко всем полям обьекта(Нужна для облегчения ввода) Глянь в файлик по рефлексии
        ArrayList<Pole> poles = new ArrayList<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if(!field.getName().equals("creationDate") && !field.getName().equals("id")) {// пропускаем id и creationdate т.к. их не нужно заполнять
                    //если примитив, или энам, или некоторые сложные типы, то идет сюда
                    if (field.getType().isPrimitive() || field.getType().isEnum() || field.getType() == Integer.class || field.getType() == String.class || field.getType() == Float.class || field.getType() == Long.class || field.getType() == Double.class) {
                        poles.add(new Pole(field, object));
                    } else {// если это обьект, то мы раскладываем его на примитивы
                        poles.addAll(get_fields(field.get(object)));
                    }
                }
            }
        }
        catch (Exception ignored){

        }
        return poles;
    }

    public static Ticket Set_Fields() {
        Ticket product = new Ticket();//создаем перемнные
        product.setCoordinates(new Coordinates());
        product.setPerson(new Person());
        try {
            ArrayList<Pole> poles = get_fields(product);
            for (Pole pole : poles) {
                pole.getField().setAccessible(true);
                System.out.println("введите поле " + pole.getField().getName());
                while (true) {//вводим поле. Если оно одно из типов, приводим его к этому типу
                    try {
                        if (pole.getField().getType() == int.class)
                            pole.getField().set(pole.getMain(), Integer.parseInt(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == double.class || pole.getField().getType() == Double.class)
                            pole.getField().set(pole.getMain(), Double.parseDouble(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == long.class)
                            pole.getField().set(pole.getMain(), Float.parseFloat(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == TicketType.class) {
                            for (TicketType TicketType : TicketType.values())
                                System.out.print("\t\t\t\t" + TicketType);
                            System.out.println();
                            pole.getField().set(pole.getMain(), TicketType.valueOf(Printer.getInstance().ReadLine()));
                        }
                        else if(pole.getField().getType() == Float.class || pole.getField().getType() == float.class)
                            pole.getMain().getClass().getDeclaredMethod("set" + pole.getField().getName().substring(0,1).toUpperCase() + pole.getField().getName().substring(1), Float.class).invoke(pole.getMain(), Float.parseFloat(Printer.getInstance().ReadLine()));
                        else
                            pole.getField().set(pole.getMain(), pole.getField().getType().getDeclaredMethod("valueOf", Object.class).invoke(pole.getMain(), Printer.getInstance().ReadLine()));
                        break;
                    } catch (IllegalArgumentException a) {
                        System.out.println("введите поле " + pole.getField().getName() + " еще раз");
                    }
                }
            }
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            System.out.println("что то поломалось ");
            e.printStackTrace();
        }
        return product;
    }



    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        for (Class<? extends Command> class1 : (new Reflections("com.company.Commands")).getSubTypesOf(Command.class)) {// получаем все классы наследуемые от command
            commands.add(class1.getConstructor().newInstance());// добавляем
        }

        start = LocalDateTime.now();// начало старта проги



        Printer.getInstance().WriteLine(System.getenv("laba"));
        Converter.getInstance().read(System.getenv("laba"));//читаем с файла

        while (true){//бесуонечный цикл ввода команд
            try {
                Printer.getInstance().WriteLine("введите команду");
                String next = Printer.getInstance().ReadLine().trim();// очищаем начало и конец строки от пробелов, чтобы команда такого вида " Show" имела место быть
                boolean isCommand = false;// провверка на то будет ли такая команда
                for (Command command : commands) {// перебор всех команд. По сути это как for, только java проходит каждый обьект списка, и выдает нам его сама
                    if (next.startsWith(command.getName()) || next.startsWith(command.getName().toLowerCase(Locale.ROOT))) {//проверка на команду(LowerCase для того чтобы можно было ввести
                        // и так Show и так show)
                        command.Execute(next);// запуск команды
                        isCommand = true;//чтобы не выводилось, что нет такой команды
                    }
                }
                if (!isCommand) {
                    Printer.getInstance().WriteLine("нет такой команды");
                }
            }
            catch (Exception e){
                Printer.getInstance().WriteLine("вы каким то образом поломали программу, что ж вы за человек");
            }
        }
    }
}
