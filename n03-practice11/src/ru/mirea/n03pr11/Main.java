package ru.mirea.n03pr11;

import ru.mirea.n03pr11.data.HashTableLinearProbing;
import ru.mirea.n03pr11.model.ReaderSub;

public class Main {

    public static void main(String[] args) {
        HashTableLinearProbing<Long, ReaderSub> table = new HashTableLinearProbing<>();
        // Вывод данных для демонстрации связей ключ-хеш
        table.hashFuncTest();

        ReaderSub reader01 = new ReaderSub(57101L, "Олег", "Олегов", "Олегович", "Волгоград"); // -> 1
        ReaderSub reader02 = new ReaderSub(99923L, "Петр", "Петров", "Петрович", "Санкт-Петербург"); // -> 15
        ReaderSub reader03 = new ReaderSub(25632L, "Сергей", "Сергеев", "Сергеевич", "Владивосток"); // -> 0
        ReaderSub reader04 = new ReaderSub(11715L, "Иван", "Иванов", "Иванович", "Тверь"); // -> 15
        ReaderSub reader05 = new ReaderSub(87627L, "Максим", "Максимов", "Максимович", "Псков"); // -> 7
        ReaderSub reader06 = new ReaderSub(99921L, "Семен", "Семенов", "Семенович", "Саратов"); // -> 5
        ReaderSub reader07 = new ReaderSub(10110L, "Егор", "Егоров", "Егорович", "Москва"); // -> 6
        ReaderSub reader08 = new ReaderSub(70402L, "Александр", "Алексндров", "Александрович", "Сочи"); // -> 10
        ReaderSub reader09 = new ReaderSub(79033L, "Георгий", "Георгиев", "Георгиевич", "Астрахань"); // -> 13
        ReaderSub reader10 = new ReaderSub(60035L, "Дмитрий", "Дмитриев", "Дмитриевич", "Йошкар-Ола"); // -> 15
        ReaderSub reader11 = new ReaderSub(97101L, "Юрий", "Юрьев", "Юрьевич", "Казань");
        ReaderSub reader12 = new ReaderSub(77742L, "Николай", "Николаев", "Николаевич", "Ростов-на-Дону");

        // Обработка коллизии (reader02 & reader04)
        table.add(reader01.getNum(), reader01);
        table.add(reader02.getNum(), reader02);
        table.add(reader03.getNum(), reader03);
        table.add(reader04.getNum(), reader04);
        table.print();

        // Вывод данных для демонстрации связей ключ-хеш
        table.hashFuncTest();

        // Добавление и удаление элементов
        table.add(reader05.getNum(), reader05);
        table.add(reader06.getNum(), reader06);
        table.add(reader07.getNum(), reader07);
        table.print();
        table.delete(10110L);
        table.delete(25632L);
        table.print();

        // Вывод данных для демонстрации связей ключ-хеш
        table.hashFuncTest();

        // Добавление в область удаленных элементов
        table.add(reader10.getNum(), reader10);
        table.add(reader11.getNum(), reader11);
        table.print();
        table.delete(57101L);
        table.print();
        System.out.println("SEARCH: " + table.search(97101L)); // -> Найдет
        System.out.println("SEARCH: " + table.search(57101L)); // -> Не найдет

        // Вывод данных для демонстрации связей ключ-хеш
        table.hashFuncTest();

        table.add(reader08.getNum(), reader08);
        table.add(reader09.getNum(), reader09);
        table.add(reader12.getNum(), reader12);
        // Добавление элементов с расширением массива
        table.add(reader01.getNum(), reader01);
        table.add(reader03.getNum(), reader03);
        table.print();

        System.out.println(table.getSize());
        table.hashFuncTest();
    }
}