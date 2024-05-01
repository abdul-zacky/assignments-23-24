package assignments.assignment3.payment;

import assignments.assignment2.User;
import assignments.assignment2.Order;

public class CreditCardPayment implements DepeFoodPaymentSystem{
    private static final double TRANSACTION_FEE_PERCENTAGE = 0.02;
    // Method untuk get persentase transaction fee
    public static double getTransactionFeePercentage() {
        return TRANSACTION_FEE_PERCENTAGE;
    }

    // Method yang mengimplementasikan processPayment dari class DepeFoodPaymentSystem
    public static void processPayment(String orderID, User user) {
        // Ubah order ID ke dalam variabel bertipe Order agar nanti status pesanannya bisa di-set jadi true jika selesai dibayar
        Order order = assignments.assignment2.MainMenu.getOrderOrNull(orderID);
        double harga = order.getTotalHarga();
        // Kalikan harga dengan persentase fee credit card
        double biayaTransaksi = harga * CreditCardPayment.getTransactionFeePercentage();
        user.saldo -= harga;
        user.saldo -= biayaTransaksi;
        // Set order jadi selesai
        order.setOrderFinished(true);
        System.out.println("Berhasil Membayar Bill sebesar Rp " + harga + " dengan biaya transaksi sebesar Rp " + biayaTransaksi);
        return;
    }
}