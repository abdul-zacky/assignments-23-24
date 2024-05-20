package assignments.assignment3.payment;

import assignments.assignment2.User;
import assignments.assignment2.Order;

public class DebitPayment implements DepeFoodPaymentSystem{
    private static final double MINIMUM_TOTAL_PRICE = 50000;

    // Method untuk get mininum total price
    public static double getMinimumTotalPrice() {
        return MINIMUM_TOTAL_PRICE;
    }

    // Method yang mengimplementasikan processPayment dari class DepeFoodPaymentSystem
    public static void processPayment(String orderID, User user) {
        // Ubah order ID ke dalam variabel bertipe Order agar nanti status pesanannya bisa di-set jadi true jika selesai dibayar
        Order order = assignments.assignment2.MainMenu.getOrderOrNull(orderID);
        double harga = order.getTotalHarga();
        // If else untuk ngecek jumlah saldo dan harga total yang mencukupi
        if (harga < DebitPayment.getMinimumTotalPrice()) {
            System.out.println("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
            return;
        } else if (user.saldo < harga) {
            System.out.println("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
            return;
        } else {
            user.saldo -= harga;
            // Set order jadi selesai
            order.setOrderFinished(true);
            System.out.println("Berhasil Membayar Bill sebesar Rp " + harga);
            return;
        }
    }

}
