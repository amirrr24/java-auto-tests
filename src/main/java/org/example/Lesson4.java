package org.example;

    public class Lesson4 {
        public static void main(String[] args) {
            // ========== ЗАДАНИЕ 1: ЖИВОТНЫЕ ==========
            System.out.println("========== ЖИВОТНЫЕ ==========\n");

            // Создаём животных
            Dog dog = new Dog("Бобик");
            Cat cat1 = new Cat("Барсик");
            Cat cat2 = new Cat("Мурзик");

            // Проверяем бег и плавание
            dog.run(150);
            dog.swim(5);
            dog.run(600);      // должно быть больше лимита
            dog.swim(15);      // больше лимита

            cat1.run(150);
            cat1.run(250);     // больше лимита
            cat1.swim(5);      // кот не умеет плавать

            // Создаём миску с едой
            Bowl bowl = new Bowl(15);

            // Массив котов
            Cat[] cats = {cat1, cat2};

            // Пытаемся покормить котов (каждый хочет 10)
            System.out.println("\n=== КОРМЛЕНИЕ КОТОВ (первая попытка) ===");
            for (Cat cat : cats) {
                cat.eat(bowl, 10);
            }

            // Выводим сытость после первой попытки
            System.out.println("\n=== СЫТОСТЬ ПОСЛЕ ПЕРВОЙ ПОПЫТКИ ===");
            for (Cat cat : cats) {
                System.out.println(cat.getName() + " сыт: " + cat.isFull());
            }

            // Добавляем еды
            System.out.println("\n=== ДОБАВЛЯЕМ ЕДУ В МИСКУ ===");
            bowl.addFood(10);

            // Кормим тех, кто остался голодным
            System.out.println("\n=== КОРМЛЕНИЕ ГОЛОДНЫХ КОТОВ ===");
            for (Cat cat : cats) {
                if (!cat.isFull()) {
                    cat.eat(bowl, 10);
                }
            }

            // Итоговая сытость
            System.out.println("\n=== ИТОГОВАЯ СЫТОСТЬ ===");
            for (Cat cat : cats) {
                System.out.println(cat.getName() + " сыт: " + cat.isFull());
            }

            // Статистика
            System.out.println("\n=== СТАТИСТИКА ===");
            System.out.println("Всего животных: " + Animal.getAnimalCount());
            System.out.println("Собак: " + Dog.getDogCount());
            System.out.println("Котов: " + Cat.getCatCount());

            // ========== ЗАДАНИЕ 2: ГЕОМЕТРИЧЕСКИЕ ФИГУРЫ ==========
            System.out.println("\n\n========== ГЕОМЕТРИЧЕСКИЕ ФИГУРЫ ==========\n");

            Circle circle = new Circle(5.0, "Жёлтый", "Красный");
            Rectangle rectangle = new Rectangle(4.0, 6.0, "Синий", "Зелёный");
            Triangle triangle = new Triangle(3.0, 4.0, 5.0, "Оранжевый", "Чёрный");

            circle.printInfo();
            rectangle.printInfo();
            triangle.printInfo();
        }
    }

// ==================== ЗАДАНИЕ 1: ЖИВОТНЫЕ ====================

    class Animal {
        protected String name;
        protected static int animalCount = 0;
        protected int runLimit;
        protected int swimLimit;

        public Animal(String name) {
            this.name = name;
            animalCount++;
        }

        public void run(int distance) {
            if (distance <= runLimit) {
                System.out.println(name + " пробежал " + distance + " м.");
            } else {
                System.out.println(name + " не может пробежать " + distance + " м. (максимум " + runLimit + " м.)");
            }
        }

        public void swim(int distance) {
            if (swimLimit == 0) {
                System.out.println(name + " не умеет плавать");
            } else if (distance <= swimLimit) {
                System.out.println(name + " проплыл " + distance + " м.");
            } else {
                System.out.println(name + " не может проплыть " + distance + " м. (максимум " + swimLimit + " м.)");
            }
        }

        public static int getAnimalCount() {
            return animalCount;
        }
    }

    class Dog extends Animal {
        private static int dogCount = 0;

        public Dog(String name) {
            super(name);
            this.runLimit = 500;
            this.swimLimit = 10;
            dogCount++;
        }

        public static int getDogCount() {
            return dogCount;
        }
    }

    class Cat extends Animal {
        private static int catCount = 0;
        private boolean isFull;

        public Cat(String name) {
            super(name);
            this.runLimit = 200;
            this.swimLimit = 0;
            this.isFull = false;
            catCount++;
        }

        public void eat(Bowl bowl, int amount) {
            if (bowl.getFoodAmount() >= amount) {
                bowl.decreaseFood(amount);
                isFull = true;
                System.out.println(name + " поел и теперь сыт");
            } else {
                System.out.println(name + " не стал есть: в миске только " + bowl.getFoodAmount() + " еды, а нужно " + amount);
            }
        }

        public boolean isFull() {
            return isFull;
        }

        public String getName() {
            return name;
        }

        public static int getCatCount() {
            return catCount;
        }
    }

    class Bowl {
        private int foodAmount;

        public Bowl(int foodAmount) {
            this.foodAmount = Math.max(foodAmount, 0);
        }

        public void addFood(int amount) {
            if (amount > 0) {
                foodAmount += amount;
                System.out.println("В миску добавлено " + amount + " еды. Теперь в миске: " + foodAmount);
            }
        }

        public void decreaseFood(int amount) {
            if (amount <= foodAmount) {
                foodAmount -= amount;
                System.out.println("Из миски съедено " + amount + " еды. Осталось: " + foodAmount);
            }
        }

        public int getFoodAmount() {
            return foodAmount;
        }
    }

// ==================== ЗАДАНИЕ 2: ГЕОМЕТРИЧЕСКИЕ ФИГУРЫ ====================

    interface Shape {
        double getPerimeter();
        double getArea();

        default void printInfo() {
            System.out.println("Периметр: " + getPerimeter());
            System.out.println("Площадь: " + getArea());
        }
    }

    interface Colored {
        String getFillColor();
        String getBorderColor();
    }

    class Circle implements Shape, Colored {
        private double radius;
        private String fillColor;
        private String borderColor;

        public Circle(double radius, String fillColor, String borderColor) {
            this.radius = radius;
            this.fillColor = fillColor;
            this.borderColor = borderColor;
        }

        @Override
        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public String getFillColor() {
            return fillColor;
        }

        @Override
        public String getBorderColor() {
            return borderColor;
        }

        @Override
        public void printInfo() {
            System.out.println("=== КРУГ ===");
            System.out.println("Радиус: " + radius);
            System.out.println("Цвет заливки: " + fillColor);
            System.out.println("Цвет границы: " + borderColor);
            System.out.println("Периметр: " + getPerimeter());
            System.out.println("Площадь: " + getArea());
            System.out.println();
        }
    }

    class Rectangle implements Shape, Colored {
        private double width;
        private double height;
        private String fillColor;
        private String borderColor;

        public Rectangle(double width, double height, String fillColor, String borderColor) {
            this.width = width;
            this.height = height;
            this.fillColor = fillColor;
            this.borderColor = borderColor;
        }

        @Override
        public double getPerimeter() {
            return 2 * (width + height);
        }

        @Override
        public double getArea() {
            return width * height;
        }

        @Override
        public String getFillColor() {
            return fillColor;
        }

        @Override
        public String getBorderColor() {
            return borderColor;
        }

        @Override
        public void printInfo() {
            System.out.println("=== ПРЯМОУГОЛЬНИК ===");
            System.out.println("Ширина: " + width + ", Высота: " + height);
            System.out.println("Цвет заливки: " + fillColor);
            System.out.println("Цвет границы: " + borderColor);
            System.out.println("Периметр: " + getPerimeter());
            System.out.println("Площадь: " + getArea());
            System.out.println();
        }
    }

    class Triangle implements Shape, Colored {
        private double sideA;
        private double sideB;
        private double sideC;
        private String fillColor;
        private String borderColor;

        public Triangle(double sideA, double sideB, double sideC, String fillColor, String borderColor) {
            if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
                throw new IllegalArgumentException("Такого треугольника не существует!");
            }
            this.sideA = sideA;
            this.sideB = sideB;
            this.sideC = sideC;
            this.fillColor = fillColor;
            this.borderColor = borderColor;
        }

        @Override
        public double getPerimeter() {
            return sideA + sideB + sideC;
        }

        @Override
        public double getArea() {
            double p = getPerimeter() / 2;
            return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
        }

        @Override
        public String getFillColor() {
            return fillColor;
        }

        @Override
        public String getBorderColor() {
            return borderColor;
        }

        @Override
        public void printInfo() {
            System.out.println("=== ТРЕУГОЛЬНИК ===");
            System.out.println("Стороны: " + sideA + ", " + sideB + ", " + sideC);
            System.out.println("Цвет заливки: " + fillColor);
            System.out.println("Цвет границы: " + borderColor);
            System.out.println("Периметр: " + getPerimeter());
            System.out.println("Площадь: " + getArea());
            System.out.println();
        }
    }
