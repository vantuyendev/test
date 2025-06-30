public class Book {
String title;
String author;
int numPages;
Book() { } ; // default constructor
public Book(String t, String a, int p) {
title = t;
author = a;
numPages = p;
}

// Getter methods
public String getTitle() {
    return title;
}

public String getAuthor() {
    return author;
}

public int getNumPages() {
    return numPages;
}

public static void testBook() {
    System.out.println("=== Testing Book Class ===");
    
    // Test default constructor
    Book emptyBook = new Book();
    System.out.println("Empty book created with default constructor");
    System.out.println("Empty book title: " + (emptyBook.getTitle() == null ? "null" : emptyBook.getTitle()));
    
    // Test parameterized constructor
    Book book1 = new Book("1984", "George Orwell", 328);
    Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 281);
    
    // Display book details
    System.out.println("Book 1:");
    System.out.println("  Title: " + book1.getTitle());
    System.out.println("  Author: " + book1.getAuthor());
    System.out.println("  Pages: " + book1.getNumPages());
    
    System.out.println("Book 2:");
    System.out.println("  Title: " + book2.getTitle());
    System.out.println("  Author: " + book2.getAuthor());
    System.out.println("  Pages: " + book2.getNumPages());
    
    System.out.println("=== End Testing Book Class ===\n");
}
}