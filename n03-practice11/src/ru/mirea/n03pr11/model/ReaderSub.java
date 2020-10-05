package ru.mirea.n03pr11.model;

public class ReaderSub {
    private Long num;
    private String name;
    private String surname;
    private String secondName;
    private String address;

    public ReaderSub(Long num, String name, String surname, String secondName, String address) {
        if (countLength(num) != 5)
            throw new IllegalArgumentException("Длина номера должна равняться 5");
        this.num = num;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.address = address;
    }

    private int countLength(Long num) {
        return String.valueOf(Math.abs(num)).length();
    }

    public Long getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "{" +
                "name = '" + name + '\'' +
                ", surname = '" + surname + '\'' +
                ", secondName = '" + secondName + '\'' +
                ", address = '" + address + '\'' +
                '}';
    }
}
