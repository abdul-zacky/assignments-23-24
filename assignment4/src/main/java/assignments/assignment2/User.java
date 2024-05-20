package assignments.assignment2;

import java.util.ArrayList;

import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class User {

    private String nama;
    private String nomorTelepon;
    private String email;
    public static ArrayList<assignments.assignment2.Order> orderHistory = new ArrayList<>();
    public String role;
    private String lokasi;
    public DepeFoodPaymentSystem payment;
    public long saldo;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        orderHistory = new ArrayList<>();
    }

    public User(String nama, String nomorTelepon, String email, String lokasi, String role,
            DepeFoodPaymentSystem payment, int saldo) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.payment = payment;
        this.saldo = saldo;
        orderHistory = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getRole() {
        return role;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public void addOrderHistory(Order order) {
        orderHistory.add(order);
    }

    public static ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public boolean isOrderBelongsToUser(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
    }

    public long getSaldo() {
        return this.saldo;
    }

    public DepeFoodPaymentSystem getPaymentSystem() {
        return this.payment;
    }

    public void payWithDebit(String orderID) {
        Order order = assignments.assignment2.MainMenu.getOrderOrNull(orderID);
        double harga = order.getTotalHarga();
        if (harga < DebitPayment.getMinimumTotalPrice()) {
            System.out.println("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
            return;
        } else if (this.saldo < harga) {
            System.out.println("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
            return;
        } else {
            this.saldo -= harga;
            order.setOrderFinished(true);
            System.out.println("Berhasil Membayar Bill sebesar Rp " + harga);
            return;
        }
    }

    public void payWithCredit(String orderID) {
        Order order = assignments.assignment2.MainMenu.getOrderOrNull(orderID);
        double harga = order.getTotalHarga();
        double biayaTransaksi = harga * CreditCardPayment.getTransactionFeePercentage();
        this.saldo -= harga;
        this.saldo -= biayaTransaksi;
        order.setOrderFinished(true);
        System.out.println("Berhasil Membayar Bill sebesar Rp " + harga + " dengan biaya transaksi sebesar Rp " + biayaTransaksi);
        return;
    }
    
}
