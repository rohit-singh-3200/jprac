package Studentdb;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private static final String FILE_NAME = "students.dat";
    private ArrayList<Student> students;
    private final Scanner sc = new Scanner(System.in);

    public StudentManager() {
        students = loadFromFile();
    }

    public void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        students.add(new Student(id, name, age));
        saveToFile();
        System.out.println("Student added.");
    }

    public void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        for (Student s : students) {
            if (s.id == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        for (Student s : students) {
            if (s.id == id) {
                sc.nextLine();
                System.out.print("Enter new name: ");
                s.name = sc.nextLine();
                System.out.print("Enter new age: ");
                s.age = sc.nextInt();
                saveToFile();
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        for (Student s : students) {
            if (s.id == id) {
                students.remove(s);
                saveToFile();
                System.out.println("Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No student data.");
            return;
        }
        for (Student s : students) {
            s.display();
        }
    }

    // --- File Handling ---
    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private ArrayList<Student> loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data. Starting fresh.");
            return new ArrayList<>();
        }
    }
}

