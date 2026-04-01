package org.example;

public class Lesson3 {
    public static void main(String[] args) {
        // 1. ТОВАРЫ
        Product[] products = new Product[5];

        products[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        products[1] = new Product("iPhone 16 Pro Max", "15.03.2025",
                "Apple Inc.", "USA", 6299, false);
        products[2] = new Product("Xiaomi 15 Ultra", "10.01.2025",
                "Xiaomi", "China", 3599, true);
        products[3] = new Product("Google Pixel 9 Pro", "20.02.2025",
                "Google", "USA", 5499, false);
        products[4] = new Product("OnePlus 13", "05.04.2025",
                "OnePlus", "China", 3999, false);

        System.out.println("\n=== ТОВАРЫ ===\n");
        for (int i = 0; i < products.length; i++) {
            System.out.println("Товар " + (i + 1) + ":");
            products[i].displayInfo();
        }

        // 2. ПАРК
        System.out.println("\n=== АТТРАКЦИОНЫ ===\n");

        Park park = new Park("Центральный парк");

        Park.Attraction ferrisWheel = park.new Attraction("Колесо обозрения",
                "10:00-22:00", 350);
        Park.Attraction rollerCoaster = park.new Attraction("Американские горки",
                "11:00-21:00", 500);
        Park.Attraction carousel = park.new Attraction("Карусель",
                "09:00-23:00", 200);

        ferrisWheel.displayInfo();
        rollerCoaster.displayInfo();
        carousel.displayInfo();
    }
}

// Класс Product
class Product {
    String name;
    String manufactureDate;
    String manufacturer;
    String country;
    double price;
    boolean isBooked;

    public Product(String name, String manufactureDate, String manufacturer,
                   String country, double price, boolean isBooked) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isBooked = isBooked;
    }

    void displayInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата: " + manufactureDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + country);
        System.out.println("Цена: " + price + " руб.");
        String status = isBooked ? "ЗАБРОНИРОВАН" : "СВОБОДЕН";
        System.out.println("Статус: " + status);
        System.out.println();
    }
}

// Класс Park
class Park {
    private String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }

    class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        void displayInfo() {
            System.out.println("Парк: " + parkName);
            System.out.println("Аттракцион: " + name);
            System.out.println("Время: " + workingHours);
            System.out.println("Цена: " + price + " руб.");
            System.out.println();
        }
    }
}