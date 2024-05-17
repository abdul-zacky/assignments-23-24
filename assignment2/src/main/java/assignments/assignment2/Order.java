package assignments.assignment2;

import java.util.ArrayList;

public class Order {
<<<<<<< HEAD
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
=======

    private String orderId;
    private String tanggal;
    private int ongkir;
    private Restaurant restaurant;
    private boolean orderFinished;
    private Menu[] items;

    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items) {
        this.orderId = orderId;
        this.tanggal = tanggal;
        this.ongkir = ongkir;
        this.restaurant = resto;
        this.orderFinished = false;
        this.items = items;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean getOrderFinished() {
        return this.orderFinished;
    }

    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getOngkir() {
        return ongkir;
    }

    public Menu[] getItems() {
        return items;
    }

    public Menu[] getSortedMenu() {
        Menu[] menuArr = new Menu[getItems().length];
        for (int i = 0; i < getItems().length; i++) {
            menuArr[i] = getItems()[i];
        }
        int n = menuArr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (menuArr[j].getHarga() > menuArr[j + 1].getHarga()) {

                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j + 1];
                    menuArr[j + 1] = temp;
                }
            }
        }
        return menuArr;
    }

    public double getTotalHarga() {
        double sum = 0;
        for (Menu menu : getItems()) {
            sum += menu.getHarga();
        }
        return sum += getOngkir();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }
}
