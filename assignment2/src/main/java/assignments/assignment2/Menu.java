package assignments.assignment2;

public class Menu {
     // Attributes
    private String namaMakanan;
    private double harga;

    // Constructor
    public Menu(String namaMakanan, double harga) {
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    // Getter dan Setter
    public String getNamaMakanan() {
        return namaMakanan;
    }
    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }
    public double getHarga() {
        return this.harga;
    }
    public void setHarga(double harga) {
        this.harga = harga;
    }    
}
