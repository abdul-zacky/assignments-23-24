package assignments.assignment3.systemCLI;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import assignments.assignment2.User;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment2.Restaurant;
import assignments.assignment2.OrderGenerator;
import assignments.assignment2.Order;

public class CustomerSystemCLI extends UserSystemCLI {

    // Method yang meng-override method handleMenu dari class UserSystemCLI
    @Override
    public boolean handleMenu(int choice, User userLoggedIn) {
        switch (choice) {
            case 1 -> handleBuatPesanan(userLoggedIn);
            case 2 -> handleCetakBill(userLoggedIn);
            case 3 -> handleLihatMenu();
            case 4 -> handleBayarBill(userLoggedIn);
            case 5 -> handleCekSaldo(userLoggedIn);
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    // Method yang meng-override method displayMenu dari class UserSystemCLI
    @Override
    public void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    // Method untuk buat pesanan baru (Method ini sebenarnya mirip dengan yang ada di TP 2, tapi ada penambahan parameter)
    public void handleBuatPesanan(User userLoggedIn) {
        System.out.println("--------------Buat Pesanan----------------");
        Scanner scanner = new Scanner(System.in);
        // While loop untuk mengecek ketersediaan restoran, validitas format tanggal pemesanan, dan ketersediaan menu
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = scanner.nextLine().trim();
            // Cek ketersediaan restauran, kalo gak ada, variabel restaurant akan jadi null dan mengulang while loop
            Restaurant restaurant = assignments.assignment2.MainMenu.getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = scanner.nextLine().trim();
            // Validasi tanggal pemesanan
            if (!OrderGenerator.validateDate(tanggalPemesanan)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }
            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Order: ");
            List<String> listMenuPesananRequest = new ArrayList<>();
            for (int i = 0; i < jumlahPesanan; i++) {
                listMenuPesananRequest.add(scanner.nextLine().trim());
            }
            if (!assignments.assignment2.MainMenu.validateRequestPesanan(restaurant, listMenuPesananRequest)) {
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            }
            ;
            Order order = new Order(
                    OrderGenerator.generateOrderID(restaurantName, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                    tanggalPemesanan,
                    OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()),
                    restaurant,
                    assignments.assignment2.MainMenu.getMenuRequest(restaurant, listMenuPesananRequest));
            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
            // scanner.close();
            return;
        }
    }

    public void handleCetakBill(User user) {
        // Meng-import method handleCetakBill yang sudah tersedia di TP 2
        assignments.assignment2.MainMenu.handleCetakBill(user);
    }

    public void handleLihatMenu() {
        // Meng-import method handleLihatMenu yang sudah tersedia di TP 2
        assignments.assignment2.MainMenu.handleLihatMenu();
    }

    // Method untuk membayar bill
    public void handleBayarBill(User user) {
        System.out.println("--------------Bayar Bill---------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bill: ");
        System.out.print("Order ID: ");
        String orderID = scanner.nextLine();
        // Ubah order ID ke dalam variabel bertipe Order agar lebih fleksibel
        Order order = assignments.assignment2.MainMenu.getOrderOrNull(orderID);
        // Cek ketersediaan orderID
        if (order == null) {
            System.out.println("Order ID tidak ditemukan! ");
            // scanner.close();
            return;
        }
        // Cek apakah pesanan dengan order id yang diberikan sudah lunas atau belum
        if (order.getOrderFinished()) {
            System.out.println("Pesanan dengan ID ini sudah lunas!");
            // scanner.close();
            return;
        } else {
            System.out.println("Opsi Pembayaran: ");
            System.out.print("1. Credit Card\n2. Debit\nPilihan Metode Pembayaran: ");
            int paymentMethod = Integer.parseInt(scanner.nextLine());
            // If else untuk tiap metode pembayaran
            if (paymentMethod == 1) {
                // If else untuk mengecek apakah user memiliki metode pembayaran yang diinginkan
                if (user.getPaymentSystem().getClass().toString().contains("CreditCardPayment")) {
                    // Panggil method processPayment dari class CreditCardPayment
                    assignments.assignment3.payment.CreditCardPayment.processPayment(orderID, user);
                } else {
                    System.out.println("User belum memiliki metode pembayaran ini!");
                }
            } else if (paymentMethod == 2) {
                // If else untuk mengecek apakah user memiliki metode pembayaran yang diinginkan
                if (user.getPaymentSystem().getClass().toString().contains("DebitPayment")) {
                    // Panggil method processPayment dari class DebitPayment
                    assignments.assignment3.payment.DebitPayment.processPayment(orderID, user);
                } else {
                    System.out.println("User belum memiliki metode pembayaran ini!");
                }
            }
        }
        // scanner.close();
    }

    // Method untuk cek saldo
    public void handleCekSaldo(User user) {
        System.out.println("Sisa saldo sebesar Rp " + user.getSaldo());
    }
}
