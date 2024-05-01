package assignments.assignment3.systemCLI;

import java.util.Scanner;
import assignments.assignment2.User;

public class AdminSystemCLI extends UserSystemCLI {

    // Method yang meng-override method handleMenu dari class UserSystemCLI
    @Override
    public boolean handleMenu(int command, User user){
        switch(command){
            case 1 -> handleTambahRestoran();
            case 2 -> handleHapusRestoran();
            case 3 -> {return false;}
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    // Method yang meng-override method displayMenu dari class UserSystemCLI
    @Override
    public void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    protected void handleTambahRestoran(){
        // Meng-import method handleTambahRestoran yang sudah tersedia di TP 2
        assignments.assignment2.MainMenu.handleTambahRestoran();
    }

    protected void handleHapusRestoran(){
        // Meng-import method handleHapusRestoran yang sudah tersedia di TP 2
        assignments.assignment2.MainMenu.handleHapusRestoran();
    }
}
