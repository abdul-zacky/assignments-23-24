package assignments.assignment3.payment;

import assignments.assignment2.User;

public interface DepeFoodPaymentSystem {
<<<<<<< HEAD
    // Set default saldo jadi 0
    public long saldo = 0;
    // Method untuk melakukan payment
    // Method ini tidak menggunakan amount sebagai parameter, tapi orderID dan user agar lebih fleksibel
    public static void processPayment(String orderID, User user) {}
=======

    public long processPayment(long saldo, long amount) throws Exception;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
}