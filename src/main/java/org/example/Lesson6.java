package org.example;

import java.util.*;

public class Lesson6 {

    public static void main(String[] args) {
        // ========== ЗАДАНИЕ 1: СТУДЕНТЫ ==========
        System.out.println("=== ЗАДАНИЕ 1: СТУДЕНТЫ ===\n");

        List<Student> students = new ArrayList<>();

        students.add(new Student("Иванов", "Группа А", 1, Arrays.asList(5, 4, 5, 4)));
        students.add(new Student("Петров", "Группа А", 1, Arrays.asList(2, 3, 2, 3)));
        students.add(new Student("Сидоров", "Группа Б", 2, Arrays.asList(5, 5, 4, 5)));
        students.add(new Student("Козлов", "Группа Б", 2, Arrays.asList(2, 2, 2, 2)));
        students.add(new Student("Смирнов", "Группа В", 3, Arrays.asList(3, 3, 4, 3)));
        students.add(new Student("Васильев", "Группа В", 3, Arrays.asList(1, 2, 1, 2)));

        System.out.println("Список всех студентов:");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        System.out.println("\n--- Удаляем студентов со средним баллом < 3 ---");
        removeStudentsWithLowAverage(students);
        System.out.println("Список после удаления:");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        System.out.println("\n--- Переводим студентов на следующий курс ---");
        promoteStudentsWithGoodAverage(students);
        System.out.println("Список после перевода:");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        System.out.println("\n--- Студенты 2-го курса ---");
        printStudents(new HashSet<>(students), 2);

        System.out.println("\n--- Студенты 3-го курса ---");
        printStudents(new HashSet<>(students), 3);

        // ========== ЗАДАНИЕ 2: ТЕЛЕФОННЫЙ СПРАВОЧНИК ==========
        System.out.println("\n=== ЗАДАНИЕ 2: ТЕЛЕФОННЫЙ СПРАВОЧНИК ===\n");

        Phonebook phonebook = new Phonebook();

        phonebook.add("Иванов", "+7 (999) 123-45-67");
        phonebook.add("Иванов", "+7 (999) 765-43-21");
        phonebook.add("Петров", "+7 (888) 111-22-33");
        phonebook.add("Сидоров", "+7 (777) 555-66-77");

        System.out.println("Весь справочник:");
        phonebook.printAll();

        System.out.println("\nТелефоны Иванова:");
        for (String phone : phonebook.get("Иванов")) {
            System.out.println("  " + phone);
        }

        System.out.println("\nТелефоны Петрова:");
        for (String phone : phonebook.get("Петров")) {
            System.out.println("  " + phone);
        }

        System.out.println("\nТелефоны Сидорова:");
        for (String phone : phonebook.get("Сидоров")) {
            System.out.println("  " + phone);
        }

        System.out.println("\nТелефоны Козлова (нет в справочнике):");
        List<String> kozlovPhones = phonebook.get("Козлов");
        if (kozlovPhones.isEmpty()) {
            System.out.println("  Нет записей");
        } else {
            for (String phone : kozlovPhones) {
                System.out.println("  " + phone);
            }
        }
    }

    public static void removeStudentsWithLowAverage(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getAverageGrade() < 3.0) {
                iterator.remove();
            }
        }
    }

    public static void promoteStudentsWithGoodAverage(List<Student> students) {
        for (Student s : students) {
            if (s.getAverageGrade() >= 3.0) {
                s.promoteToNextCourse();
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println("  " + s.getName());
            }
        }
    }
}