package assignments.assignment2;

import java.util.ArrayList;

public class User {
<<<<<<< HEAD
    // Attributes
    private String nama;
    private String nomorTelepon;
    private String email;
    private String lokasi;
    public String role;
    private static ArrayList<Order> orderHistory = new ArrayList<Order>();

    // Constructor
    public User(String nama, String nomorTelepon, String email, String lokasi, String role) {
=======
    
    private String nama;
    private String nomorTelepon;
    private String email;
    private ArrayList<Order> orderHistory;
    public String role;

    private String lokasi;
    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
<<<<<<< HEAD
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNomorTelepon() {
        return nomorTelepon;
    }
    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLokasi() {
        return lokasi;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    public static ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
    public void setOrderHistory(ArrayList<Order> orderHistory) {
        User.orderHistory = orderHistory;
    }

    // Method untuk buat pesanan baru
    public void buatPesanan(String orderID, String tanggalPemesanan, int biayaOngkosKirim, Restaurant restaurant, ArrayList<Menu> menuList, String nomorTelepon, String lokasi){
        Order pesananBaru = new Order(orderID, tanggalPemesanan, biayaOngkosKirim, restaurant, menuList, nomorTelepon, lokasi);
        orderHistory.add(pesananBaru);
    }
}
=======
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
    public String getNomorTelepon() {
        return nomorTelepon;
    }
    public void addOrderHistory(Order order){
        orderHistory.add(order);
    }
    public ArrayList<Order> getOrderHistory() {
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

}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
