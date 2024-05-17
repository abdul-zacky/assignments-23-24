package assignments.assignment3.systemCLI;

<<<<<<< HEAD
import java.util.Scanner;
import assignments.assignment2.User;

public class AdminSystemCLI extends UserSystemCLI {

    // Method yang meng-override method handleMenu dari class UserSystemCLI
    @Override
    public boolean handleMenu(int command, User user){
        switch(command){
=======
import java.util.Arrays;

import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;

public class AdminSystemCLI extends UserSystemCLI {

    @Override
    boolean handleMenu(int command) {
        switch (command) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            case 1 -> handleTambahRestoran();
            case 2 -> handleHapusRestoran();
            case 3 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

<<<<<<< HEAD
    // Method yang meng-override method displayMenu dari class UserSystemCLI
    @Override
    public void displayMenu() {
=======
    @Override
    void displayMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

<<<<<<< HEAD
    protected void handleTambahRestoran(){
        // Meng-import method handleTambahRestoran yang sudah tersedia di TP 2
        assignments.assignment2.MainMenu.handleTambahRestoran();
    }

    protected void handleHapusRestoran(){
        // Meng-import method handleHapusRestoran yang sudah tersedia di TP 2
        assignments.assignment2.MainMenu.handleHapusRestoran();
=======
    private void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran---------------");
        Restaurant restaurant = null;
        while (restaurant == null) {
            String namaRestaurant = getValidRestaurantName();
            restaurant = new Restaurant(namaRestaurant);
            restaurant = handleTambahMenuRestaurant(restaurant);
        }
        restoList.add(restaurant);
        System.out.print("Restaurant " + restaurant.getNama() + " Berhasil terdaftar.");
    }

    private Restaurant handleTambahMenuRestaurant(Restaurant restoran) {
        System.out.print("Jumlah Makanan: ");
        int jumlahMenu = Integer.parseInt(input.nextLine().trim());
        boolean isMenuValid = true;
        for (int i = 0; i < jumlahMenu; i++) {
            String inputValue = input.nextLine().trim();
            String[] splitter = inputValue.split(" ");
            String hargaStr = splitter[splitter.length - 1];
            boolean isDigit = checkIsDigit(hargaStr);
            String namaMenu = String.join(" ", Arrays.copyOfRange(splitter, 0, splitter.length - 1));
            if (isDigit) {
                int hargaMenu = Integer.parseInt(hargaStr);
                restoran.addMenu(new Menu(namaMenu, hargaMenu));
            } else {
                isMenuValid = false;
            }
        }
        if (!isMenuValid) {
            System.out.println("Harga menu harus bilangan bulat!");
            System.out.println();
        }

        return isMenuValid ? restoran : null;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    private void handleHapusRestoran() {
        System.out.println("--------------Hapus Restoran----------------");
        boolean isActionDeleteEnded = false;
        while (!isActionDeleteEnded) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream()
                    .anyMatch(restaurant -> restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase()));
            if (!isRestaurantExist) {
                System.out.println("Restoran tidak terdaftar pada sistem.");
                System.out.println();
            } else {
                restoList.removeIf(
                        restaurant -> restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase()));

                System.out.println("Restoran berhasil dihapus");
                isActionDeleteEnded = true;
            }
        }
    }

}