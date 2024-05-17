package assignments.assignment1;

import java.util.Scanner;
<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderGenerator {

    public static void showMenu() {
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
    private static final int ORDER_ID_LENGTH = 16;

    /*
     * Anda boleh membuat method baru sesuai kebutuhan Anda
     * Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method
     * yang sudah ada.
     */

    /*
     * Method ini untuk menampilkan DepeFood
     */
    public static void initMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.err.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
<<<<<<< HEAD
    }

    public static String generateOrderID(String namaRestoran, String tanggalPemesanan, String nomorTelpon) {
        String orderID = "";
        String cleanedRestoName = namaRestoran.replaceAll("\\s", "").toUpperCase();
        String cleanedDate = tanggalPemesanan.replaceAll("/", "");
        long nomorTelponLong = Long.parseLong(nomorTelpon);
        String nomorTelponString = String.valueOf(nomorTelponLong);
        int sum = 0;
        
        // Sum all digits in the phone number
        for (int i = 0; i < nomorTelponString.length(); i++) {
            sum += Character.getNumericValue(nomorTelponString.charAt(i));
        }
        int mod = sum % 100;
        String formattedMod = (mod < 10) ? "0" + mod : String.valueOf(mod);
        
        orderID = cleanedRestoName.substring(0, Math.min(cleanedRestoName.length(), 4)) +
                  cleanedDate +
                  formattedMod;
        
        return orderID + checkSum(orderID);
    }

    private static String checkSum(String num) {
        String codeCharacterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        int oddPosition = 0;
        int evenPosition = 0;

        for (int i = 0; i < num.length(); i++) {
            int numericalValue = codeCharacterSet.indexOf(num.charAt(i));
            if (i % 2 == 0) {
                evenPosition += numericalValue;
            } else {
                oddPosition += numericalValue;
            }
        }

        int evenPositionMod = evenPosition % 36;
        int oddPositionMod = oddPosition % 36;
        return String.valueOf(codeCharacterSet.charAt(evenPositionMod)) +
               String.valueOf(codeCharacterSet.charAt(oddPositionMod));
    }

    public static String generateBill(String orderID, String lokasiPengiriman) {
        String lokasiPengirimanUpper = lokasiPengiriman.toUpperCase();
        String[] locations = {"P", "U", "T", "S", "B"};
        int[] prices = {10000, 20000, 35000, 40000, 60000};

        for (int i = 0; i < locations.length; i++) {
            if (lokasiPengirimanUpper.equals(locations[i])) {
                return "Bill:\n" +
                       "Order ID: " + orderID + "\n" +
                       "Tanggal Pemesanan: " + orderID.substring(4, 6) + "/" + orderID.substring(6, 8) + "/" + orderID.substring(8, 12) + "\n" +
                       "Lokasi Pengiriman: " + lokasiPengirimanUpper + "\n" +
                       "Biaya Ongkos Kirim: Rp " + prices[i] + "\n";
            }
        }
        return "error";
    }

    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int chosenMenu = 0;
        String orderIDs = "";
        // Print banner
        showMenu();
        // While loop selama menu 3 belum diinput
        while (chosenMenu != 3) {
            System.out.println("-------------------------");
            System.out.println("Pilih menu: \n1. Generate Order ID\n2. Generate Bill\n3. Keluar");
            System.out.println("-------------------------");
            System.out.print("Pilihan Menu: ");
            chosenMenu = scanner.nextInt();
            scanner.nextLine();
            // If else sesuai menu yang dipilih
            if (chosenMenu == 3)
                break;
            else if (chosenMenu == 1) {
                String namaRestoran = "";
                boolean namaRestoranValidity = false;
                // While loop untuk ngevalidasi nama restoran
                while (namaRestoranValidity == false) {
                    System.out.print("Nama Restoran: ");
                    namaRestoran = scanner.nextLine();
                    if (namaRestoran.replace(" ", "").length() >= 4) {
                        namaRestoranValidity = true;
                    } else {
                        System.out.println(
                                "Nama restoran minimal 4 karakter (tidak termasuk white space). Silahkan masukkan ulang!");
                        continue;
                    }
                }
                String tanggalPemesanan = "";
                boolean tanggalPemesananValidity = false;
                // While loop untuk ngevalidasi tanggal pemesanan
                while (!tanggalPemesananValidity) {
                    System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
                    tanggalPemesanan = scanner.nextLine();
                    String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(tanggalPemesanan);
                    if (matcher.matches()) {
                        tanggalPemesananValidity = true;
                    } else {
                        System.out.println("Tanggal pemesanan dalam bentuk format DD/MM/YYYY");
                    }
                }
                System.out.print("Nomor Telpon: ");
                String nomorTelpon = scanner.nextLine();
                // Ubah nomor telpon jadi long agar bisa dijadiin parameter method
                // generateOrderID
                long nomorTelponLong = Long.parseLong(nomorTelpon);
                // Panggil method generateOrderId dan checkSum trus masukkin ke string orderID
                String orderID = generateOrderID(namaRestoran, tanggalPemesanan, nomorTelpon);
                String checkSum = checkSum(orderID);
                orderID += checkSum + " ";
                // Kumpulin semua orderID ke dalam string orderIDs
                orderIDs += " " + orderID;
                System.out.println(orderID + " berhasil ditambahkan");
            } else if (chosenMenu == 2) {
                String lokasiPengiriman = "";
                String inputtedOrderID = "";
                boolean orderIDValidity = false;
                // While loop untuk ngevalidasi checksum order id
                while (orderIDValidity == false) {
                    System.out.print("Order ID: ");
                    inputtedOrderID = scanner.nextLine();
                    if (inputtedOrderID.substring(14,16).equals(checkSum(inputtedOrderID.substring(0,14)))) {
                        orderIDValidity = true;
                    } else {
                        System.out.println(inputtedOrderID.substring(14,16));
                        System.out.println(checkSum(inputtedOrderID.substring(0,14)));
                        System.out.println("Order ID tidak tersedia. Silahkan masukkan ulang");
                        continue;
                    }
                }
                String bill = "error";
                // While loop untuk ngevalidasi ketersediaan lokasi pengiriman
                while (bill.equals("error")) {
                    System.out.print("Lokasi pengiriman: ");
                    lokasiPengiriman = scanner.nextLine();
                    bill = generateBill(inputtedOrderID, lokasiPengiriman);
                    if (bill.equals("error"))
                        continue;
                }
                System.out.println("Bill:\n" + bill);
                System.out.println("Order ID: " + inputtedOrderID);
                System.out.println("Tanggal Pemesanan: " + inputtedOrderID.substring(4, 6) + "/"
                        + inputtedOrderID.substring(6, 8) + "/" + inputtedOrderID.substring(8, 12));
                System.out.println("Lokasi Pengiriman: " + lokasiPengiriman.toUpperCase()) ;
            }
            System.out.println("");
        }
        System.out.println("Terima kasih telah menggunakan DePeFood!");
        scanner.close();
    }
}
=======
    }

    /*
     * Method ini untuk menampilkan menu
     */
    public static void showMenu() {
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     *
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {

        String restaurantCode = getRestaurantCode(namaRestoran);
        String formattedDate = formatDate(tanggalOrder);
        String phoneNumberChecksum = getPhoneNumberChecksum(noTelepon);

        String id = restaurantCode + formattedDate + phoneNumberChecksum;
        id = id.toUpperCase();
        String checksum = calculateChecksum(id);

        return id + checksum;
    }

    /*
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     *
     * @return String Bill dengan format sesuai di bawah:
     * Bill:
     * Order ID: [Order ID]
     * Tanggal Pemesanan: [Tanggal Pemesanan]
     * Lokasi Pengiriman: [Kode Lokasi]
     * Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
    public static String generateBill(String OrderID, String lokasi) {
        String formattedDate = OrderID.substring(4, 12);
        String tanggalPemesanan = formattedDate.substring(0, 2) + "/" + formattedDate.substring(2, 4) + "/"
                + formattedDate.substring(4, 8);

        return outputBill(OrderID, tanggalPemesanan, lokasi, calculateDeliveryCost(lokasi));
    }

    public static boolean validateRestaurantName(String restaurantName) {
        return restaurantName != null && !restaurantName.isEmpty() && getRestaurantCode(restaurantName).length() >= 4;
    }

    public static boolean validateDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return false;
        }

        for (String part : parts) {
            if (!part.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }

        return parts[0].length() == 2 && parts[1].length() == 2 && parts[2].length() == 4;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.chars().allMatch(Character::isDigit);
    }

    public static boolean validateLocation(String location) {
        char[] locationList = { 'P', 'U', 'T', 'S', 'B' };

        return location.length() == 1 && new String(locationList).contains(location);
    }

    public static boolean validateOrderID(String orderID) {
        if (orderID.length() != ORDER_ID_LENGTH) {
            System.out.println("Order ID minimal 16 karakter");
            return false;
        }

        if (!orderID.chars().allMatch(Character::isLetterOrDigit) || !checkIfChecksumValid(orderID)) {
            System.out.println("Silahkan masukkan Order ID yang valid!");
            return false;
        }

        return true;
    }

    public static boolean checkIfChecksumValid(String id) {
        String idWithoutChecksum = id.substring(0, id.length() - 2);
        String checksum = id.substring(id.length() - 2);

        return calculateChecksum(idWithoutChecksum).equals(checksum);
    }

    public static String getRestaurantCode(String restaurantName) {
        String[] words = restaurantName.split(" ");

        StringBuilder code = new StringBuilder();

        for (String word : words) {
            code.append(word);
        }

        return code.substring(0, Math.min(code.length(), 4));
    }

    public static String formatDate(String date) {
        String[] parts = date.split("/");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        return day + month + year;
    }

    public static String getPhoneNumberChecksum(String phoneNumber) {
        int sum = 0;
        for (char c : phoneNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        int checksum = sum % 100;
        return (checksum < 10) ? "0" + checksum : String.valueOf(checksum);
    }

    public static String calculateChecksum(String id) {
        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            int numericValue = getNumericValue(c);
            if (i % 2 == 0) {
                sumEven += numericValue;
            } else {
                sumOdd += numericValue;
            }
        }
        int remainderEven = sumEven % 36;
        int remainderOdd = sumOdd % 36;
        return reverseAssignment(remainderEven) + reverseAssignment(remainderOdd);
    }

    public static int getNumericValue(char c) {
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        } else {
            return c - 'A' + 10;
        }
    }

    public static String reverseAssignment(int remainder) {
        String code39CharacterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(code39CharacterSet.charAt(remainder));
    }

    public static int calculateDeliveryCost(String location) {
        switch (location) {
            case "P":
                return 10000;
            case "U":
                return 20000;
            case "T":
                return 35000;
            case "S":
                return 40000;
            case "B":
                return 60000;
            default:
                return 0;
        }
    }

    public static String outputBill(String orderID, String tanggalPemesanan, String lokasiPengiriman,
            int biayaOngkosKirim) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        decimalFormat.setDecimalFormatSymbols(symbols);

        return "Bill:\n" + "Order ID: " + orderID + "\n" + "Tanggal Pemesanan: " + tanggalPemesanan + "\n"
                + "Lokasi Pengiriman: " + lokasiPengiriman + "\n" + "Biaya Ongkos Kirim: Rp "
                + decimalFormat.format(biayaOngkosKirim) + "\n";
    }

    /*
     * Method ini untuk memproses ID Order
     */
    public static void processGenerateOrder() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine().toUpperCase();
            if (!validateRestaurantName(namaRestoran)) {
                System.out.println("Nama Restoran tidak valid!");
                continue;
            }

            System.out.print("Tanggal Pemesanan: ");
            String tanggalOrder = input.nextLine();

            if (!validateDate(tanggalOrder)) {
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
                continue;
            }

            System.out.print("No. Telpon: ");
            String noTelepon = input.nextLine();
            if (!validatePhoneNumber(noTelepon)) {
                System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
                continue;
            }

            System.out.println(
                    "Order ID " + generateOrderID(namaRestoran, tanggalOrder, noTelepon) + " diterima!");

            isInputValid = true;
        }
    }

    /*
     * Method ini untuk memproses generate bill
     */
    public static void processGenerateBill() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Order ID: ");
            String orderID = input.nextLine().toUpperCase();
            if (!validateOrderID(orderID)) {
                continue;
            }

            System.out.print("Lokasi Pengiriman: ");
            String lokasi = input.nextLine().toUpperCase();
            if (!validateLocation(lokasi)) {
                System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                continue;
            }

            System.out.println(generateBill(orderID, lokasi));
            isInputValid = true;
        }
    }

    public static void main(String[] args) {
        boolean isRunning = true;

        initMenu();
        while (isRunning) {
            showMenu();
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan Menu: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    processGenerateOrder();
                    break;
                case 2:
                    processGenerateBill();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println("--------------------------------------------");
        }
    }
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
