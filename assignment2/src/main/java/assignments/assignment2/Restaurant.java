package assignments.assignment2;

<<<<<<< HEAD
import java.util.ArrayList;

public class Restaurant {
    // Attributes
    private String nama;
    private ArrayList<Menu> menu;

    // Constructor
    public Restaurant(String nama, ArrayList<Menu> menu) {
        this.nama = nama;
        this.menu = menu;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
    private String nama;
    private ArrayList<Menu> menu;
    public Restaurant(String nama){
        this.nama = nama;
        this.menu = new ArrayList<>();
    }
    
    public String getNama() {
        return nama;
    }
    public void addMenu(Menu newMenu){
        menu.add(newMenu);
    }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public ArrayList<Menu> getMenu() {
        return menu;
    }

<<<<<<< HEAD
    public void setMenu(ArrayList<Menu> menu) {
        this.menu = menu;
    }

    static void bubbleSort(int arr[], int n) {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    // Method untuk sorting menu
    public static void bubbleSort(ArrayList<String> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    // Swap arr[j] dan arr[j+1]
                    String temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    // Method untuk sort berdasarkan harga
    public static void sortPrice(ArrayList<Menu> listMenu) {
        for (int i = 0; i < listMenu.size() - 1; i++) {
            for (int j = 0; j < listMenu.size() - i - 1; j++) {
                if (listMenu.get(j).getHarga() < listMenu.get(j + 1).getHarga()) {
                    // Swap elements if they are in the wrong order
                    Menu temp = listMenu.get(j);
                    listMenu.set(j, listMenu.get(j + 1));
                    listMenu.set(j + 1, temp);
                }
            }
        }
    }

    // Method untuk sort menu dengan harga yang sama berdasarkan alfabet
    public static void sortAlphabet(ArrayList<Menu> listMenu) {
        for (int i = 0; i < listMenu.size() - 1; i++) {
            for (int j = 0; j < listMenu.size() - i - 1; j++) {
                if (listMenu.get(j).getHarga() == listMenu.get(j + 1).getHarga()) {
                    if (listMenu.get(j).getNamaMakanan().compareTo(listMenu.get(j+1).getNamaMakanan()) > 0) {
                        Menu temp = listMenu.get(j);
                        listMenu.set(j, listMenu.get(j + 1));
                        listMenu.set(j + 1, temp);
                    }
                }
            } 
        }
    }

    // Method untuk melihat menu
    public String lihatMenu() {
        ArrayList<String> namaNamaMakanan = new ArrayList<String>();
        sortPrice(getMenu());
        sortAlphabet(getMenu());
        for (Menu menu : getMenu()) {
            namaNamaMakanan.add(menu.getNamaMakanan() + " " + (int) menu.getHarga() + "\n");
        }
        String output = "Menu: \n";
        for (int i = 0; i < namaNamaMakanan.size(); i++) {
            output += (i + 1) + ". " + namaNamaMakanan.get(i);
        }
        return output;
    }
}
=======
    private ArrayList<Menu> sortMenu(){
        Menu[] menuArr = new Menu[menu.size()];
        for(int i=0; i < menu.size();i++){
            menuArr[i] = menu.get(i);
        }
        int n = menuArr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (menuArr[j].getHarga() > menuArr[j+1].getHarga()) {
                    
                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j+1];
                    menuArr[j+1] = temp;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(menuArr));
    }
    public String printMenu() {
        StringBuilder menuString = new StringBuilder("Menu:\n");
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        int menuNumber = 1;
        for (Menu menuItem : sortMenu()) {
            menuString.append(menuNumber).append(". ").append(menuItem.getNamaMakanan()).append(" ").append(decimalFormat.format(menuItem.getHarga())).append("\n");
            menuNumber++;
        }
        if (menuString.length() > 0) {
            menuString.deleteCharAt(menuString.length() - 1);
        }
        return menuString.toString();
    }
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
