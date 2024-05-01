package assignments.assignment3.payment;

import assignments.assignment2.User;

public interface DepeFoodPaymentSystem {
    // Set default saldo jadi 0
    public long saldo = 0;
    // Method untuk melakukan payment
    // Method ini tidak menggunakan amount sebagai parameter, tapi orderID dan user agar lebih fleksibel
    public static void processPayment(String orderID, User user) {}
}