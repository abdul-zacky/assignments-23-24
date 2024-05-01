package assignments.assignment3.systemCLI;

import java.util.Scanner;
import assignments.assignment2.User;

public abstract class UserSystemCLI {
    protected Scanner input;
    public void run() {
        boolean isLoggedIn = true;
        while (isLoggedIn) {
            displayMenu();
            int command = input.nextInt();
            input.nextLine();
            isLoggedIn = handleMenu(command);
        }
    }
    public abstract void displayMenu();
    // Gunakan parameter command dan user agar method ini bisa digunakan di class yang meng-extends abstract class ini
    public abstract boolean handleMenu(int command, User user);
}