package assignments.assignment2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderGenerator {

    public static void showMenu() {
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
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