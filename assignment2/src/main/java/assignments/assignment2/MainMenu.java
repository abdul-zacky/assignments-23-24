package assignments.assignment2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// import assignments.assignment1.*;
=======
import assignments.assignment1.OrderGenerator;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList = new ArrayList<Restaurant>();
    private static ArrayList<User> userList;
<<<<<<< HEAD
    private static ArrayList<Order> orderList;
=======
    private static User userLoggedIn;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5

    public static void main(String[] args) {
        restoList = new ArrayList<>();
        initUser();
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
<<<<<<< HEAD
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
=======
                userLoggedIn = getUser(nama, noTelp);
                boolean isLoggedIn = true;
                if(userLoggedIn == null){
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    isLoggedIn = false;
                }
                else if(userLoggedIn.role == "Customer"){
                    System.out.println("Selamat Datang "+userLoggedIn.getNama()+"!");
                    while (isLoggedIn){
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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
<<<<<<< HEAD
                } else {
                    System.out.println("Selamat Datang Admin!");
                    // While loop selama case 3, atau keluar, belum diinput
                    while (isLoggedIn) {
=======
                }else{
                    System.out.println("Selamat Datang "+userLoggedIn.getNama()+"!");
                    while (isLoggedIn){
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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
<<<<<<< HEAD
            } else {
=======
            }
            else{
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

<<<<<<< HEAD
    // Method untuk mendapatkan info user untuk login
    public static User getUser(String nama, String nomorTelepon) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getNama().equalsIgnoreCase(nama)) {
                if (userList.get(i).getNomorTelepon().equalsIgnoreCase(nomorTelepon))
                    return userList.get(i);
=======
    public static User getUser(String nama, String nomorTelepon){

        for(User user: userList){
            if(user.getNama().equals(nama.trim()) && user.getNomorTelepon().equals(nomorTelepon.trim())){
                return user;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            }
        }
        return null;
    }

<<<<<<< HEAD
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
=======
    public static void handleBuatPesanan(){
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if(restaurant == null){
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
<<<<<<< HEAD
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
=======
            String tanggalPemesanan = input.nextLine().trim();
            if(!OrderGenerator.validateDate(tanggalPemesanan)){
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }
            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");
            List<String> listMenuPesananRequest = new ArrayList<>();
            for(int i=0; i < jumlahPesanan; i++){
                listMenuPesananRequest.add(input.nextLine().trim());
            }
            if(! validateRequestPesanan(restaurant, listMenuPesananRequest)){
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            };
            Order order = new Order(
                    OrderGenerator.generateOrderID(restaurantName, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                    tanggalPemesanan, 
                    OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()), 
                    restaurant, 
                    getMenuRequest(restaurant, listMenuPesananRequest));
            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
            return;
        }
    }

    public static boolean validateRequestPesanan(Restaurant restaurant, List<String> listMenuPesananRequest){
        return listMenuPesananRequest.stream().allMatch(pesanan -> 
            restaurant.getMenu().stream().anyMatch(menu -> menu.getNamaMakanan().equals(pesanan))
        );
    }

    public static Menu[] getMenuRequest(Restaurant restaurant, List<String> listMenuPesananRequest){
        Menu[] menu = new Menu[listMenuPesananRequest.size()];
        for(int i=0;i<menu.length;i++){
            for(Menu existMenu : restaurant.getMenu()){
                if(existMenu.getNamaMakanan().equals(listMenuPesananRequest.get(i))){
                    menu[i] = existMenu;
                }
            }
        }
        return menu;
    }

    public static String getMenuPesananOutput(Order order){
        StringBuilder pesananBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        for (Menu menu : order.getSortedMenu()) {
            pesananBuilder.append("- ").append(menu.getNamaMakanan()).append(" ").append(decimalFormat.format(menu.getHarga())).append("\n");
        }
        if (pesananBuilder.length() > 0) {
            pesananBuilder.deleteCharAt(pesananBuilder.length() - 1);
        }
        return pesananBuilder.toString();
    }

    public static String outputBillPesanan(Order order) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return String.format("Bill:%n" +
                         "Order ID: %s%n" +
                         "Tanggal Pemesanan: %s%n" +
                         "Lokasi Pengiriman: %s%n" +
                         "Status Pengiriman: %s%n"+
                         "Pesanan:%n%s%n"+
                         "Biaya Ongkos Kirim: Rp %s%n"+
                         "Total Biaya: Rp %s%n",
                         order.getOrderId(),
                         order.getTanggal(),
                         userLoggedIn.getLokasi(),
                         !order.getOrderFinished()? "Not Finished":"Finished",
                         getMenuPesananOutput(order),
                         decimalFormat.format(order.getOngkir()),
                         decimalFormat.format(order.getTotalHarga())
                         );
    }



    public static Restaurant getRestaurantByName(String name){
        Optional<Restaurant> restaurantMatched = restoList.stream().filter(restoran -> restoran.getNama().toLowerCase().equals(name.toLowerCase())).findFirst();
        if(restaurantMatched.isPresent()){
            return restaurantMatched.get();
        }
        return null;
    }

    public static void handleCetakBill(){
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if(order == null){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }
        
    }

    public static Order getOrderOrNull(String orderId) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
                }
            }
        }
        return null;
    }

    public static void handleLihatMenu(){
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if(restaurant == null){
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
    }

    public static void handleUpdateStatusPesanan(){
        System.out.println("--------------Update Status Pesanan---------------");
        while (true) {
            System.out.print("Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if(order == null){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.print("Status: ");
            String newStatus = input.nextLine().trim();
            if(newStatus.toLowerCase().equals("SELESAI".toLowerCase())){
                if(order.getOrderFinished() == true){
                    System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!", order.getOrderId());
                }
                else{
                    System.out.printf("Status pesanan dengan ID %s berhasil diupdate!", order.getOrderId());
                    order.setOrderFinished(true);
                }
            }
            return;
        }

    }

    public static void handleTambahRestoran(){
        System.out.println("--------------Tambah Restoran---------------");
        Restaurant restaurant = null;
        while (restaurant == null) {
            String namaRestaurant = getValidRestaurantName();
            restaurant = new Restaurant(namaRestaurant);
            restaurant = handleTambahMenuRestaurant(restaurant);
        }
        restoList.add(restaurant);
        System.out.print("Restaurant "+restaurant.getNama()+" Berhasil terdaftar." );
    }

    public static Restaurant handleTambahMenuRestaurant(Restaurant restoran){
        System.out.print("Jumlah Makanan: ");
        int  jumlahMenu = Integer.parseInt(input.nextLine().trim());
        boolean isMenuValid = true;
        for(int i = 0; i < jumlahMenu; i++){
            String inputValue = input.nextLine().trim();
            String[] splitter = inputValue.split(" ");
            String hargaStr = splitter[splitter.length-1];
            boolean isDigit = checkIsDigit(hargaStr);
            String namaMenu = String.join(" ", Arrays.copyOfRange(splitter, 0, splitter.length - 1));
            if(isDigit){
                int hargaMenu = Integer.parseInt(hargaStr);
                restoran.addMenu(new Menu(namaMenu, hargaMenu));
            }
            else{
                isMenuValid = false;
            }
        }
        if(!isMenuValid){
            System.out.println("Harga menu harus bilangan bulat!");
            System.out.println();
        }

        return isMenuValid? restoran : null; 
    }

    public static boolean checkIsDigit(String digits){
        return  digits.chars().allMatch(Character::isDigit);
    }
    
    public static String getValidRestaurantName() {
        String name = "";
        boolean isRestaurantNameValid = false;
    
        while (!isRestaurantNameValid) {
            System.out.print("Nama: ");
            String inputName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream()
                    .anyMatch(restoran -> restoran.getNama().toLowerCase().equals(inputName.toLowerCase()));
            boolean isRestaurantNameLengthValid = inputName.length() >= 4;
    
            if (isRestaurantExist) {
                System.out.printf("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!%n", inputName);
                System.out.println();
            } else if (!isRestaurantNameLengthValid) {
                System.out.println("Nama Restoran tidak valid! Minimal 4 karakter diperlukan.");
                System.out.println();
            } else {
                name = inputName;
                isRestaurantNameValid = true;
            }
        }
        return name;
    }
    

    public static void handleHapusRestoran(){
        System.out.println("--------------Hapus Restoran----------------");
        boolean isActionDeleteEnded = false;
        while (!isActionDeleteEnded) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream().anyMatch(restaurant -> restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase()));
            if(!isRestaurantExist){
                System.out.println("Restoran tidak terdaftar pada sistem.");
                System.out.println();
            }
            else{
                restoList = new ArrayList<>(restoList.stream().filter(restaurant-> !restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase())).toList());
                System.out.println("Restoran berhasil dihapus");
                isActionDeleteEnded = true;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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