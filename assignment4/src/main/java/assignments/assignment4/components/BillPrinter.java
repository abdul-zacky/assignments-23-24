package assignments.assignment4.components;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import assignments.assignment2.Order;
import assignments.assignment2.User;
import assignments.assignment4.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BillPrinter {
    private Stage stage;
    private MainApp mainApp;
    private User user;
    private Scene backScene;

    // Constructor
    public BillPrinter(Stage stage, MainApp mainApp, User user, Scene backScene) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user;
        this.backScene = backScene;
    }

    // Bill printer form
    private Scene createBillPrinterForm() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        // Set label, textfield, and buttons
        Label labelOrderID = new Label("Order ID:");
        TextField orderID = new TextField();
        Button printButton = new Button("Print Bill");
        Button backButton = new Button("Back");
        // Add functions to the button
        printButton.setOnAction(e -> {
            // If else to check if the order id is available
            if (getOrderOrNull(orderID.getText()) == null) {
                showAlert("Error", "Not Found", "Order ID not found", "error");
                // Clear orderID textfield
                orderID.setText("");
            } else {
                // Make a new Label object for the bill
                Label printedBill = new Label(outputBillPesanan(getOrderOrNull(orderID.getText()), user));
                // Clear the layout, and add the bill label to the layout
                layout.getChildren().clear();
                layout.getChildren().addAll(printedBill, backButton);
            }
        });
        // Set functions to the back button
        backButton.setOnAction(e -> {
            // Clear the current layout and add the previous label, textfield, and buttons
            layout.getChildren().clear();
            layout.getChildren().addAll(labelOrderID, orderID, printButton, backButton);
            orderID.setText("");
            stage.setScene(backScene);
        });
        layout.getChildren().addAll(labelOrderID, orderID, printButton, backButton);
        return new Scene(layout, 400, 600);
    }

    // Method from TP 2
    public static Order getOrderOrNull(String orderId) {
        for (Order order : User.getOrderHistory()) {
            System.out.println(order.getOrderId());
            System.out.println(orderId);
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }
        return null;
    }

    // Method from TP 2
    public static String outputBillPesanan(Order order, User userLoggedIn2) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return String.format("Bill:%n" +
                "Order ID: %s%n" +
                "Tanggal Pemesanan: %s%n" +
                "Lokasi Pengiriman: %s%n" +
                "Status Pengiriman: %s%n" +
                "Pesanan:%n%s%n" +
                "Biaya Ongkos Kirim: Rp %s%n" +
                "Total Biaya: Rp %s%n",
                order.getOrderId(),
                order.getTanggal(),
                userLoggedIn2.getLokasi(),
                !order.getOrderFinished() ? "Not Finished" : "Finished",
                assignments.assignment2.MainMenu.getMenuPesananOutput(order),
                decimalFormat.format(order.getOngkir()),
                decimalFormat.format(order.getTotalHarga()));
    }

    // Method to show error/alert message
    public static void showAlert(String title, String header, String content, String type) {
        Alert alert;
        if (type.equalsIgnoreCase("error")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Scene getScene() {
        return this.createBillPrinterForm();
    }

    // Class ini opsional
    public class MenuItem {
        private final StringProperty itemName;
        private final StringProperty price;

        public MenuItem(String itemName, String price) {
            this.itemName = new SimpleStringProperty(itemName);
            this.price = new SimpleStringProperty(price);
        }

        public StringProperty itemNameProperty() {
            return itemName;
        }

        public StringProperty priceProperty() {
            return price;
        }

        public String getItemName() {
            return itemName.get();
        }

        public String getPrice() {
            return price.get();
        }
    }
}
