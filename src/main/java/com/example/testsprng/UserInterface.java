package com.example.testsprng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInterface {
    @Autowired
    private ContactService contactService;

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Вывести в консоль список контактов");
            System.out.println("2. Добавить контакт");
            System.out.println("3. Удалить контакт по email");
            System.out.println("4. Загрузить контакты из файла");
            System.out.println("5. Сохранить контакты в файл");
            int choice = scanner.nextInt();

            if (choice == 1) {
                contactService.displayContacts();
            } else if (choice == 2) {
                contactService.addContactFromConsole();
            } else if (choice == 3) {
                System.out.println("Введите email контакта для удаления:");
                String emailToDelete = scanner.next();
                contactService.deleteContactByEmail(emailToDelete);
            } else if (choice == 4) {
                contactService.loadContactsFromFile();
                System.out.println("Загрузка из файла успешна");
            } else if (choice == 5) {
                contactService.saveContactsToFile();
                System.out.println("Сохранение в файл успешно");
            }

            else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
            System.out.println("Желаете продолжить? (да/нет)");

            String continueChoice = scanner.next();
            if (continueChoice.equalsIgnoreCase("нет")) {
                break;
            } else if (continueChoice.equalsIgnoreCase("да")) {
            } else {
                System.out.println("Введено неверное значение, допустимые значения 'Да' или 'Нет', работа программы продолжена");
            }
        }
    }
}
