package assignments.assignment2;

import java.util.ArrayList;

public class Order {
     // Attributes
    private String orderID;
    private String tanggalPemesanan;
    private int biayaOngkosKirim;
    private Restaurant restaurant;
    private ArrayList<Menu> item;
    private boolean orderFinished = false;
    private String nomorTelpon;
    private String lokasi;

    // Constructor
    public Order(String orderID, String tanggalPemesanan, int biayaOngkosKirim, Restaurant restaurant,
            ArrayList<Menu> item, String nomorTelpon, String lokasi) {
        this.orderID = orderID;
        this.tanggalPemesanan = tanggalPemesanan;
        this.biayaOngkosKirim = biayaOngkosKirim;
        this.restaurant = restaurant;
        this.item = item;
        this.nomorTelpon = nomorTelpon;
        this.lokasi = lokasi;
    }

    public String getOrderID() {
        return this.orderID;
    }
    public void setOrderID(Restaurant restaurant, String tanggalPemesanan, String nomorTelpon) {
        String namaRestoran = restaurant.getNama();
        this.orderID = OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, nomorTelpon);
    }

    // Getter dan Setter
    public boolean getOrderFinished() {
        return this.orderFinished;
    }
    public void setOrderFinished() {
        this.orderFinished = true;
    }
    public String getTanggalPemesanan() {
        return this.tanggalPemesanan;
    }
    public String getRestaurant() {
        return this.restaurant.getNama();
    }
    public String getLokasi() {
        return this.lokasi;
    }
    public int getOngkir() {
        return this.biayaOngkosKirim;
    }
    public String getMenuList() {
        String output = "\n";
        for (Menu menu : this.item) {
            output += "- " + menu.getNamaMakanan() + " " + (int) menu.getHarga() + "\n";
        }
        return output;
    }
    public int getTotalBiaya() {
        int totalBiaya = getOngkir();
        for (Menu menu: this.item) {
            totalBiaya += menu.getHarga();
        }
        return totalBiaya;
    }
}
