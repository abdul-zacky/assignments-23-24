package assignments.assignment2;

public class User {
    // Attributes
    private String nama;
    private String nomorTelepon;
    private String email;
    private String lokasi;
    public String role;
    private static ArrayList<Order> orderHistory = new ArrayList<Order>();

    // Constructor
    public User(String nama, String nomorTelepon, String email, String lokasi, String role) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
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