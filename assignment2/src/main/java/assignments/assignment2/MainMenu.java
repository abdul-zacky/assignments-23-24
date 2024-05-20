package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// import assignments.assignment1.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList = new ArrayList<Restaurant>();
    private static ArrayList<User> userList;
    private static ArrayList<Order> orderList;

    public static void main(String[] args) {
        boolean programRunning = true;
        // Initialize user yang udah tersedia
        initUser();
        // While loop selama menu keluar belum diinput
        while (programRunning) {
            printHeader();
            startMenu();
            int command = input.nextInt();
            input.nextLine();

            if (command == 1) {
                System.out.println("\nSilakan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine();
                User userLoggedIn = getUser(nama, noTelp);
                // Kalo userLoggedIn == null, berarti info user tidak tersedia
                if (userLoggedIn == null) {
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    continue;
                }
                boolean isLoggedIn = true;

                // If else sesuai role
                if (userLoggedIn.role == "Customer") {
                    System.out.println("Selamat Datang " + userLoggedIn.getNama() + "!");
                    // While loop selama case 5, atau keluar, belum diinput
                    while (isLoggedIn) {
                        menuCustomer();
                        int commandCust = input.nextInt();
                        input.nextLine();

                        // Panggil method sesuai menu yang diinput
                        switch (commandCust) {
                            case 1 -> handleBuatPesanan(userLoggedIn);
                            case 2 -> handleCetakBill();
                            case 3 -> handleLihatMenu();
                            case 4 -> handleUpdateStatusPesanan();
                            case 5 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                } else {
                    System.out.println("Selamat Datang Admin!");
                    // While loop selama case 3, atau keluar, belum diinput
                    while (isLoggedIn) {
                        menuAdmin();
                        int commandAdmin = input.nextInt();
                        input.nextLine();

                        // Panggil method sesuai menu yang diinput
                        switch (commandAdmin) {
                            case 1 -> handleTambahRestoran();
                            case 2 -> handleHapusRestoran();
                            case 3 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                }
            } else if (command == 2) {
                programRunning = false;
            } else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    // Method untuk mendapatkan info user untuk login
    public static User getUser(String nama, String nomorTelepon) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getNama().equalsIgnoreCase(nama)) {
                if (userList.get(i).getNomorTelepon().equalsIgnoreCase(nomorTelepon))
                    return userList.get(i);
            }
        }
        return null;
    }

    // Method untuk buat pesanan
    public static void handleBuatPesanan(User customer) {
        System.out.println("--------------Buat Pesanan----------------");
        Restaurant restor = null;
        boolean tanggalPemesananValidity = false;
        boolean menuValidity = false;
        // While loop untuk ngecek apakah restorannya tersedia, tanggal pemesanannya valid, dan menunya tersedia
        while (restor == null || !tanggalPemesananValidity || !menuValidity) {
            System.out.print("Nama Restoran: ");
            String namaRestor = input.nextLine();
            // For loop untuk nyari nama restoran yang cocok
            for (int i = 0; i < restoList.size(); i++) {
                if (restoList.get(i).getNama().equalsIgnoreCase(namaRestor)) {
                    restor = restoList.get(i);
                }
            }
            if (restor == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine();
            System.out.print("Jumlah Pesanan: ");
            String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(tanggalPemesanan);
            if (matcher.matches()) {
                tanggalPemesananValidity = true;
            } else {
                System.out.println("Tanggal pemesanan dalam bentuk format DD/MM/YYYY\n");
                continue;
            }
            String jumlahPesanan = input.nextLine();
            System.out.println("Order:");
            ArrayList<String> itemList = new ArrayList<String>();
            // For loop untuk nambahin item sebanyak jumlah pesanan
            for (int i = 0; i < Integer.parseInt(jumlahPesanan); i++) {
                String item = input.nextLine();
                itemList.add(item);
            }
            ArrayList<Menu> menuList = new ArrayList<Menu>();
            // For loop untuk nambahin item ke menuList sesuai itemList untuk nanti dicocokin apakah jumlah menu nya sesuai antara kedua list
            for (int i = 0; i < restor.getMenu().size(); i++) {
                for (int j = 0; j < itemList.size(); j++) {
                    if (itemList.get(j).equalsIgnoreCase(restor.getMenu().get(i).getNamaMakanan())) {
                        menuList.add(restor.getMenu().get(i));
                        break;
                    }
                }
            }
            // Kalo jumlah menu kedua nya berbeda, berarti ada menu yang tidak tersedia, maka menunya tidak valid
            if (menuList.size() != itemList.size()) {
                System.out.println("Mohon memesan menu yang tersedia di restoran!\n");
                continue;
            } else
                menuValidity = true;
            Restaurant restaurant = new Restaurant(namaRestor, menuList);
            String nomorTelepon = customer.getNomorTelepon();
            String lokasiPengiriman = customer.getLokasi();
            int ongkir = 0;
            String[] locations = { "P", "U", "T", "S", "B" };
            int[] ongkirs = { 10000, 20000, 35000, 40000, 60000 };
            // For loop untuk nentuin ongkir yang tepat sesuai lokasi pengiriman
            for (int i = 0; i < locations.length; i++) {
                if (lokasiPengiriman.equalsIgnoreCase(locations[i])) {
                    ongkir = ongkirs[i];
                }
            }
            String orderID = OrderGenerator.generateOrderID(namaRestor, tanggalPemesanan, nomorTelepon);
            customer.buatPesanan(orderID, tanggalPemesanan, ongkir, restaurant, menuList, nomorTelepon,
                    lokasiPengiriman);
            System.out.println("Pesanan dengan ID " + orderID + " diterima!");
        }
    }

    // Method untuk nyetak bill
    public static void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");
        orderList = User.getOrderHistory();
        boolean orderIDValidity = false;
        // While loop untuk ngecek apakah orderID nya tersedia
        while (!orderIDValidity) {
            System.out.print("Masukkan Order ID: ");
            String inputtedOrderID = input.nextLine();
            // For loop untuk cari orderID yang sesuai
            for (Order order : orderList) {
                if (order.getOrderID().equalsIgnoreCase(inputtedOrderID)) {
                    orderIDValidity = true;
                    System.out.println("Bill: ");
                    System.out.println("Order ID: " + inputtedOrderID);
                    System.out.println("Tanggal Pemesanan: " + order.getTanggalPemesanan());
                    System.out.println("Restaurant: " + order.getRestaurant());
                    System.out.println("Lokasi Pengiriman: " + order.getLokasi());
                    System.out.println("Status Pengiriman: " + order.getOrderFinished());
                    System.out.println("Pesanan: " + order.getMenuList());
                    System.out.println("Biaya Ongkos Kirim: Rp " + order.getOngkir());
                    System.out.println("Total Biaya: Rp " + order.getTotalBiaya());
                    break;
                }
            }
            if (!orderIDValidity) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
        }
    }

    // Method untuk nampilin menu
    public static void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");
        boolean namaRestoranValidity = false;
        // While loop untuk ngecek apakah nama restoran nya valid
        while (!namaRestoranValidity) {
            System.out.print("Nama Restoran: ");
            String inputtedRestaurant = input.nextLine();
            // For loop untuk mencari nama restoran yang sesuai
            for (Restaurant resto : restoList) {
                if (inputtedRestaurant.equalsIgnoreCase(resto.getNama())) {
                    namaRestoranValidity = true;
                    System.out.println(resto.lihatMenu());
                    break;
                }
            }
            if (!namaRestoranValidity) {
                System.out.println("Restoran tidak terdaftar pada sistem\n");
                continue;
            }
        }
    }

    // Method untuk update status pesanan
    public static void handleUpdateStatusPesanan() {
        System.out.println("--------------Update Status Pesanan----------------");
        String status;
        orderList = User.getOrderHistory();
        boolean orderIDValidity = false;
        boolean orderFinished = true;
        // While loop untuk ngecek apakah orderID nya valid dan status pesanan nya masih bisa diupdate
        while (!orderIDValidity || orderFinished) {
            System.out.print("Order ID: ");
            String orderID = input.nextLine();
            // For loop untuk nyari orderID yang sesuai
            for (Order order : orderList) {
                if (order.getOrderID().equalsIgnoreCase(orderID)) {
                    orderIDValidity = true;
                    System.out.print("Status: ");
                    status = input.nextLine();
                    if (status.equalsIgnoreCase("selesai")) {
                        if (!order.getOrderFinished()) {
                            System.out.println("Status pesanan dengan ID " + orderID + " berhasil diupdate!");
                            order.setOrderFinished();
                            orderFinished = false;
                        } else {
                            System.out.println("Status pesanan dengan ID " + orderID + " tidak berhasil diupdate!\n");
                            continue;
                        }
                    }
                    break;
                }
            }
            if (!orderIDValidity) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
        }
    }

    // Method untuk tambah restoran
    public static void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran----------------");
        String inputtedNamaResto = "";
        boolean namaRestoranValidity = false;
        boolean hargaMenuValidity = false;
        ArrayList<Menu> listMenu = new ArrayList<Menu>();
        // While loop untuk ngecek apakah nama restoran tersedia dan harga menu nya valid
        while (!namaRestoranValidity || !hargaMenuValidity) {
            System.out.print("Nama: ");
            namaRestoranValidity = true;
            inputtedNamaResto = input.nextLine();
            if (inputtedNamaResto.length() < 4) {
                System.out.println("Nama restoran tidak valid!");
                namaRestoranValidity = false;
            }
            // For loop untuk menemukan nama restoran yang sama
            for (Restaurant resto : restoList) {
                if (resto.getNama().equalsIgnoreCase(inputtedNamaResto)) {
                    System.out.println("Restoran dengan nama " + inputtedNamaResto
                            + "sudah pernah terdaftar. Mohon masukkan nama yang berbeda!");
                    namaRestoranValidity = false;
                }
            }
            // Kalo nama restorannya udah valid, lanjutin kode nya, kalo belom, ulang while loop
            if (namaRestoranValidity) {
                hargaMenuValidity = true;
                System.out.print("Jumlah Makanan: ");
                int jumlahMakanan = Integer.parseInt(input.nextLine());
                // For loop untuk input menu sesuai jumlah yang udah ditentukan
                for (int i = 0; i < jumlahMakanan; i++) {
                    String menu = input.nextLine();
                    String namaMenu = "";
                    String hargaMenuString = "";
                    int hargaMenu = 0;
                    // For loop untuk misahin nama menu dan harga, dan ngecek apakah harga nya merupakan integer
                    for (int j = 0; j < menu.length(); j++) {
                        if (menu.charAt(j) == ' ') {
                            namaMenu = menu.substring(0, j);
                            hargaMenuString = menu.substring(j + 1);
                            if (!isNumeric(hargaMenuString)) {
                                System.out.println("Harga menu harus bilangan bulat!");
                                System.out.println("");
                                hargaMenuValidity = false;
                            } else {
                                hargaMenu = Integer.parseInt(hargaMenuString);
                                listMenu.add(new Menu(namaMenu, hargaMenu));
                            }
                        }
                    }
                }
                if (hargaMenuValidity) {
                    System.out.println("Restaurant " + inputtedNamaResto + " berhasil terdaftar.");
                    restoList.add(new Restaurant(inputtedNamaResto, listMenu));
                    break;
                }
            }
            if (!hargaMenuValidity)
                continue;
        }
    }

    // Method untuk hapus restoran
    public static void handleHapusRestoran() {
        System.out.println("--------------Hapus Restoran----------------");
        boolean namaRestoranValidity = false;
        // While loop untuk mengecek apakah restoran yang ingin dihapus tersedia
        while (!namaRestoranValidity) {
            System.out.print("Nama Restoran: ");
            String inputtedRestaurant = input.nextLine();
            // For loop untuk mencari nama restoran yang cocok
            for (int i = 0; i < restoList.size(); i++) {
                if (inputtedRestaurant.equalsIgnoreCase(restoList.get(i).getNama())) {
                    restoList.remove(i);
                    namaRestoranValidity = true;
                    System.out.println("Restoran berhasil dihapus.");
                }
            }
            if (!namaRestoranValidity) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
        }
    }

    // Method untuk ngecek apakah sebuah string merupakan digit
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void initUser() {
        userList = new ArrayList<User>();
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    public static void printHeader() {
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu() {
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuCustomer() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }
}