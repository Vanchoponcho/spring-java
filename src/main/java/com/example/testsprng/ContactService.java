package com.example.testsprng;

import com.example.testsprng.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ContactService {
    private List<String> contacts;

    @Autowired
    private Config config;

    public ContactService() {
        this.contacts = new ArrayList<>();
    }

    public void addContactFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type contact format 'fio;phoneNumber;email'");
        String input = scanner.nextLine();
        String[] contactInfo = input.split(";");
        if (contactInfo.length == 3) {
            String formattedContact = contactInfo[0].trim() + " | " + contactInfo[1].trim() + " | " + contactInfo[2].trim();
            contacts.add(formattedContact);
            System.out.println("Контакт успешно добавлен: " + formattedContact);
        } else {
            System.out.println("Неверный формат ввода, контакт не был добавлен, используйте следующий формат: 'fio;phoneNumber;email'");
        }
    }
    public void loadContactsFromFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(config.getContactsFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] contactInfo = line.split(";");
                if (contactInfo.length == 3) {
                    String formattedContact = contactInfo[0].trim() + " | " + contactInfo[1].trim() + " | " + contactInfo[2].trim();
                    contacts.add(formattedContact);
                } else {
                    System.out.println("Неверный формат строки в файле: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке контактов: " + e.getMessage());
        }
    }

    public void saveContactsToFile() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(config.getContactsFilePath()))) {
            for (String contact : contacts) {
                // Разделяем контакт на части
                String[] contactParts =  contact.split("\\s*\\|\\s*");
                if (contactParts.length == 3) {
                    // Форматируем строку для записи
                    String formattedContact = contactParts[0].trim() + ";" + contactParts[1].trim() + ";" + contactParts[2].trim();
                    bw.write(formattedContact);
                    bw.newLine();
                } else {
                    System.out.println("Неверный формат контакта: " + contact);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении контактов: " + e.getMessage());
        }
    }

    public void deleteContactByEmail(String email) {
        contacts.removeIf(contact -> contact.contains(email));
        System.out.println("Контакт успешно удален.");
    }

    public void displayContacts() {
        System.out.println("Список контактов:");
        for (String contact : contacts) {
            System.out.println(contact);
        }
    }
}
