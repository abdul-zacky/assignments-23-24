package assignments.assignment3.systemCLI;

<<<<<<< HEAD
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
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import assignments.assignment1.OrderGenerator;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class CustomerSystemCLI extends UserSystemCLI {

    @Override
    boolean handleMenu(int choice) {
        switch (choice) {
            case 1 -> handleBuatPesanan();
            case 2 -> handleCetakBill();
            case 3 -> handleLihatMenu();
            case 4 -> handleBayarBill();
            case 5 -> handleCekSaldo();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            case 6 -> {
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
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

<<<<<<< HEAD
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
=======
    private void handleBuatPesanan() {
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
<<<<<<< HEAD
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = scanner.nextLine().trim();
            // Validasi tanggal pemesanan
=======

            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine().trim();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            if (!OrderGenerator.validateDate(tanggalPemesanan)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }
<<<<<<< HEAD
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
=======

            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");

            List<String> listMenuPesananRequest = new ArrayList<>();

            for (int i = 0; i < jumlahPesanan; i++) {
                listMenuPesananRequest.add(input.nextLine().trim());
            }

            if (!validateRequestPesanan(restaurant, listMenuPesananRequest)) {
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            Order order = new Order(
                    OrderGenerator.generateOrderID(restaurantName, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                    tanggalPemesanan,
                    OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()),
                    restaurant,
<<<<<<< HEAD
                    assignments.assignment2.MainMenu.getMenuRequest(restaurant, listMenuPesananRequest));
            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
            // scanner.close();
=======
                    getMenuRequest(restaurant, listMenuPesananRequest));

            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            return;
        }
    }

<<<<<<< HEAD
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
                if (user.getPayment().getClass().toString().contains("CreditCardPayment")) {
                    // Panggil method processPayment dari class CreditCardPayment
                    assignments.assignment3.payment.CreditCardPayment.processPayment(orderID, user);
                } else {
                    System.out.println("User belum memiliki metode pembayaran ini!");
                }
            } else if (paymentMethod == 2) {
                // If else untuk mengecek apakah user memiliki metode pembayaran yang diinginkan
                if (user.getPayment().getClass().toString().contains("DebitPayment")) {
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
=======
    private void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }

    }

    void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
    }

    void handleUpdateStatusPesanan(Order order) {
        order.setOrderFinished(true);
    }

    void handleBayarBill() {
        System.out.println("--------------Bayar Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();

            Order order = getOrderOrNull(orderId);

            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }

            if (order.getOrderFinished()) {
                System.out.println("Pesanan dengan ID ini sudah lunas!\n");
                return;
            }

            System.out.println(outputBillPesanan(order));

            System.out.println("Opsi Pembayaran:");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit");

            System.out.print("Pilihan Metode Pembayaran: ");
            String paymentOption = input.nextLine().trim();

            if (!paymentOption.equals("1") && !paymentOption.equals("2")) {
                System.out.println("Pilihan tidak valid, silakan coba kembali\n");
                continue;
            }

            DepeFoodPaymentSystem paymentSystem = userLoggedIn.getPaymentSystem();

            boolean isCreditCard = paymentSystem instanceof CreditCardPayment;

            if ((isCreditCard && paymentOption.equals("2")) || (!isCreditCard && paymentOption.equals("1"))) {
                System.out.println("User belum memiliki metode pembayaran ini!\n");
                continue;
            }

            long amountToPay = 0;

            try {
                amountToPay = paymentSystem.processPayment(userLoggedIn.getSaldo(), (long) order.getTotalHarga());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }

            long saldoLeft = userLoggedIn.getSaldo() - amountToPay;

            userLoggedIn.setSaldo(saldoLeft);
            handleUpdateStatusPesanan(order);

            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            decimalFormat.setDecimalFormatSymbols(symbols);

            System.out.printf("Berhasil Membayar Bill sebesar Rp %s", decimalFormat.format(amountToPay));

            return;
        }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    void handleCekSaldo() {
        System.out.println("--------------Cek Saldo----------------");

        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);

        System.out.printf("Sisa saldo sebesar Rp %s", decimalFormat.format(userLoggedIn.getSaldo()));
    }

}