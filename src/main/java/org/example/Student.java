package org.example;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;           // имя
    private String group;          // группа
    private int course;            // курс (1, 2, 3...)
    private List<Integer> grades;  // оценки по предметам (список чисел)

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        if (grades == null || grades.isEmpty()) {
            return 0; // если оценок нет, средний балл 0
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public void promoteToNextCourse() {
        course++;
    }

    @Override
    public String toString() {
        return name + " (курс " + course + ", средний балл: " + getAverageGrade() + ")";
    }
}