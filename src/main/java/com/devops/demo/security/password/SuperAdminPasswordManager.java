package com.devops.demo.security.password;

import java.util.Scanner;

public class SuperAdminPasswordManager {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {  // ✅ Fix: Use try-with-resources
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("Encrypted: " + encryptPassword(password));
        }
    }

    private static String encryptPassword(String password) {
        return password + "Encrypted"; // Replace with actual encryption logic
    }
}
