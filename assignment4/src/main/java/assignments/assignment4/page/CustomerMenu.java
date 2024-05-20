package assignments.assignment4.page;

import java.util.List;
import java.util.stream.Collectors;

import assignments.assignment2.Menu;
import assignments.assignment2.Order;
import assignments.assignment2.OrderGenerator;
import assignments.assignment2.Restaurant;
import assignments.assignment2.User;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.BillPrinter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerMenu extends MemberMenu {
    private Stage stage;
    private Scene scene;
    private Scene addOrderScene;
    private Scene baseMenuScene;
    private Scene printBillScene;
    private Scene payBillScene;
    private Scene cekSaldoScene;
    private BillPrinter billPrinter; // Instance of BillPrinter
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private static Label label = new Label();
    private Label balanceLabel;
    private MainApp mainApp;
    // private List<Restaurant> restoList = new ArrayList<>();
    private User user;

    // Constructor
    public CustomerMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addOrderScene = createTambahPesananForm();
        this.billPrinter = new BillPrinter(stage, mainApp, this.user, scene); // Pass user to BillPrinter constructor
        this.printBillScene = createBillPrinter();
        this.payBillScene = createBayarBillForm();
        this.cekSaldoScene = createCekSaldoScene();
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public Scene createBaseMenu() {
        VBox menuLayout = new VBox(10);
        // Set the layout to be centered
        menuLayout.setAlignment(Pos.CENTER);
        // Set all the buttons
        Button addOrder = new Button("Make Order");
        Button printBill = new Button("Print Bill");
        Button payBill = new Button("Pay Bill");
        Button checkBalance = new Button("Check Balance");
        Button logoutButton = new Button("Log Out");
        // Set functions to each button
        addOrder.setOnAction(e -> {
            createTambahPesananForm();
            mainApp.setScene(addOrderScene);
        });
        printBill.setOnAction(e -> {
            createBillPrinter();
            mainApp.setScene(printBillScene);
        });
        payBill.setOnAction(e -> {
            createBayarBillForm();
            mainApp.setScene(payBillScene);
        });
        checkBalance.setOnAction(e -> {
            createCekSaldoScene();
            mainApp.setScene(cekSaldoScene);
        });
        logoutButton.setOnAction(e -> mainApp.logout());
        // Add all buttons to the vbox
        menuLayout.getChildren().addAll(addOrder, printBill, payBill, checkBalance, logoutButton);
        // Create a new Scene object to be set and returned later
        baseMenuScene = new Scene(menuLayout, 400, 600);
        setScene(baseMenuScene);
        return baseMenuScene;
    }

    private Scene createTambahPesananForm() {
        VBox menuLayout = new VBox(10);
        // Set the layout to be centered
        menuLayout.setAlignment(Pos.CENTER);
        // Set labels, combobox, listviews, and textfields
        Label labelRestaurant = new Label("Restaurant");
        ComboBox<String> comboBox = new ComboBox<>();
        for (Restaurant resto : AdminMenu.restoList) {
            comboBox.getItems().add(resto.getNama());
        }
        comboBox.setPromptText("Select a Restaurant");
        Label labelDate = new Label("Date (DD/MM/YYYY):");
        TextField date = new TextField();
        Button menuButton = new Button("Menu");
        Label menuItemsLabel = new Label("Menu Items:");

        ListView<String> menuItemsListView = new ListView<>();
        ObservableList<String> clickedItems = FXCollections.observableArrayList();
        menuItemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        menuItemsListView.setPrefHeight(200);

        Button addButton = new Button("Add Order");
        Button backButton = new Button("Back");

        // Set functions to each button
        menuButton.setOnAction(e -> {
            // Clear the listview
            menuItemsListView.getItems().clear();
            Restaurant restaurant = AdminMenu.getRestaurantByName(comboBox.getValue());
            // If else to check the availability of the restaurant
            if (restaurant != null) {
                restaurant.getMenu().forEach(item -> {
                    menuItemsListView.getItems().add(item.getNamaMakanan());
                });
            } else {
                menuItemsListView.getItems().add("Restaurant not found.");
            }
        });
        menuItemsListView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) { // Single click
                String selectedItem = menuItemsListView.getSelectionModel().getSelectedItem();
                // If to add the selected item to the clickedItems list
                if (selectedItem != null && !clickedItems.contains(selectedItem)) {
                    clickedItems.add(selectedItem);
                    System.out.println("Added to clicked list: " + selectedItem);
                }
            }
        });
        addButton.setOnAction(e -> {
            // If else to check the validity of the date
            if (!validateDate(date.getText())) {
                AdminMenu.showAlert("Error", "Invalid Date", "Invalid Date, use DD/MM/YYYY",
                        "error");
            } else {
                // Create a new Order object
                Order order = new Order(
                        OrderGenerator.generateOrderID(comboBox.getValue().toUpperCase(), date.getText(),
                                user.getNomorTelepon()),
                        date.getText(),
                        OrderGenerator.calculateDeliveryCost(user.getLokasi()),
                        AdminMenu.getRestaurantByName(comboBox.getValue()),
                        getMenuRequest(AdminMenu.getRestaurantByName(comboBox.getValue()),
                                clickedItems.stream().collect(Collectors.toList())));
                // Add the order to the user object
                user.addOrderHistory(order);
                for (Order order2 : User.getOrderHistory()) {
                    System.out.println(order2.getOrderId());
                }
                // Show success message
                AdminMenu
                        .showAlert(
                                "Success", "Success",
                                "Order " + OrderGenerator.generateOrderID(comboBox.getValue().toUpperCase(),
                                        date.getText(), user.getNomorTelepon()) + " has succeccfully been added",
                                "success");
            }
        });
        backButton.setOnAction(e -> {
            back();
            date.clear();
        });
        // Add labels, combobox, listviews, and textfields to the vbox
        menuLayout.getChildren().addAll(labelRestaurant, comboBox, labelDate, date, menuButton, menuItemsLabel,
                menuItemsListView,
                addButton, backButton);
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createBillPrinter() {
        Scene billScene = new BillPrinter(stage, mainApp, user, scene).getScene();
        return billScene;
    }

    private Scene createBayarBillForm() {
        VBox menuLayout = new VBox(10);
        // Set the leyout to be centered
        menuLayout.setAlignment(Pos.CENTER);
        // Set labels, combobox, and textfields
        Label orderIDLabel = new Label("Order ID:");
        TextField orderID = new TextField();
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Select Payment");
        comboBox.getItems().add("Credit Card");
        comboBox.getItems().add("Debit");
        Button payButton = new Button("Pay");
        Button backButton = new Button("Back");
        // Add functions to each button
        payButton.setOnAction(e -> {
            handleBayarBill(orderID.getText(), comboBox.getValue());
            orderID.setText("");
            comboBox.setPromptText("Select Payment");
            balanceLabel.setText("Saldo: Rp " + user.getSaldo()); // Refresh the balance
        });
        backButton.setOnAction(e -> {
            back();
        });
        // Add labels, combobox, and textfields to the vbox
        menuLayout.getChildren().addAll(orderIDLabel, orderID, comboBox, payButton, backButton);
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createCekSaldoScene() {
        VBox menuLayout = new VBox(10);
        // Set the layout to be centered
        menuLayout.setAlignment(Pos.CENTER);
        // Set labels and buttons
        Label name = new Label(user.getNama());
        balanceLabel = new Label("Saldo: Rp " + user.getSaldo());
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> back());
        // Add labels and buttons to the vbox
        menuLayout.getChildren().addAll(name, balanceLabel, backButton);
        return new Scene(menuLayout, 400, 600);
    }

    public void handleBayarBill(String orderId, String paymentOption) {
        Order order = assignments.assignment2.MainMenu.getOrderOrNull(orderId);

        // If to check if the order id is available
        if (order == null) {
            showAlert("Error", "Not found", "This order ID was not found", "error");
            return;
        }
        // If to check if the order is already paid
        if (order.getOrderFinished()) {
            showAlert("Paid", "Has been Paid", "This order has been Paid", "information");
            return;
        }

        // Get the available payments for the user
        DepeFoodPaymentSystem paymentSystem = user.getPaymentSystem();
        boolean isCreditCard = paymentSystem instanceof CreditCardPayment;

        // If to check the valid payment
        if ((isCreditCard && paymentOption.equals("Debit"))
                || (!isCreditCard && paymentOption.equals("Credit Card"))) {
            showAlert("Error", "Not supported", "You don't have this type of payment", "error");
            return;
        }

        // If else for each payment method
        if (paymentOption.equals("Debit")) {
            double harga = order.getTotalHarga();
            // If else to check the minimmum spend and balance
            if (harga < 50000) {
                showAlert("Error", "Not enough", "The spend should not be less than 50000", "error");
                return;
            } else if (user.saldo < harga) {
                showAlert("Error", "Not enough balance", "You don't have enough balance to pay", "error");
                return;
            } else {
                user.saldo -= harga;
                // Set the order to be finished
                order.setOrderFinished(true);
                showAlert("Success", "Payment Success", "Your Payment is successful", "info");
            }
        } else if (paymentOption.equals("Credit Card")) {
            // Ubah order ID ke dalam variabel bertipe Order agar nanti status pesanannya
            // bisa di-set jadi true jika selesai dibayar
            double harga = order.getTotalHarga();
            // Kalikan harga dengan persentase fee credit card
            double biayaTransaksi = harga * CreditCardPayment.getTransactionFeePercentage();
            user.saldo -= harga;
            user.saldo -= biayaTransaksi;
            // Set order jadi selesai
            order.setOrderFinished(true);
            showAlert("Success", "Payment Success", "Your Payment is successful", "info");
            return;
        }
    }

    // Method to set the order to be finished
    public static void handleUpdateStatusPesanan(Order order) {
        order.setOrderFinished(true);
    }

    // Method to show alert/error message
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

    // Method from previous TP
    public static Menu[] getMenuRequest(Restaurant restaurant, List<String> listMenuPesananRequest) {
        Menu[] menu = new Menu[listMenuPesananRequest.size()];
        for (int i = 0; i < menu.length; i++) {
            for (Menu existMenu : restaurant.getMenu()) {
                if (existMenu.getNamaMakanan().equals(listMenuPesananRequest.get(i))) {
                    menu[i] = existMenu;
                }
            }
        }
        return menu;
    }

    // Method from previous TP
    public static boolean validateDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return false;
        }
        for (String part : parts) {
            if (!part.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }
        return parts[0].length() == 2 && parts[1].length() == 2 && parts[2].length() == 4;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
        // currentScene = scene;
    }

    public void back() {
        setScene(baseMenuScene);
    }
}