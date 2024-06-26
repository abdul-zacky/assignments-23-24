package assignments.assignment4.page;

import java.util.ArrayList;
import java.util.List;

import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;
import assignments.assignment2.User;
import assignments.assignment4.MainApp;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu {
    private Stage stage;
    private Scene scene;
    private Scene baseMenuScene;
    private User user;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    public static List<Restaurant> restoList = new ArrayList<>();
    private MainApp mainApp; // Reference to MainApp instance
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private ListView<String> menuItemsListView = new ListView<>();

    // Constructor
    public AdminMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addRestaurantScene = createAddRestaurantForm();
        this.addMenuScene = createAddMenuForm();
        this.viewRestaurantsScene = createViewRestaurantsForm();
    }

    @Override
    public Scene createBaseMenu() {
        VBox menuLayout = new VBox(10);
        // Set the layout to be centered
        menuLayout.setAlignment(Pos.CENTER);

        // Set all buttons
        Button addRestaurantButton = new Button("Tambah Restoran");
        Button addMenuButton = new Button("Tambah Menu Restoran");
        Button viewRestaurantsButton = new Button("Lihat Daftar Restoran");
        Button logoutButton = new Button("Log Out");

        // Set the correct functions to each button
        addRestaurantButton.setOnAction(e -> {
            Scene addRestaurantScene = createAddRestaurantForm();
            mainApp.setScene(addRestaurantScene);
        });
        addMenuButton.setOnAction(e -> {
            createAddMenuForm();
            mainApp.setScene(addMenuScene);
        });
        viewRestaurantsButton.setOnAction(e -> {
            createViewRestaurantsForm();
            mainApp.setScene(viewRestaurantsScene);
        });
        logoutButton.setOnAction(e -> mainApp.logout());

        // Add buttons to the VBox
        menuLayout.getChildren().addAll(addRestaurantButton, addMenuButton, viewRestaurantsButton, logoutButton);

        // Return the scene
        baseMenuScene = new Scene(menuLayout, 400, 600);
        setScene(baseMenuScene);
        return baseMenuScene;
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    private Scene createAddRestaurantForm() {
        VBox layout = new VBox(10);
        // Set the layout to be centered
        layout.setAlignment(Pos.CENTER);
        // Set labels, textfield, and buttons
        Label labelRestaurant = new Label("Restaurant Name:");
        TextField restaurantName = new TextField();
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");
        // Set functions to the button
        submitButton.setOnAction(e -> {
            handleTambahRestoran(restaurantName.getText());
            restaurantName.setText("");
        });
        backButton.setOnAction(e -> back());
        // Add labels, textfield, and buttons to the vbox
        layout.getChildren().addAll(labelRestaurant, restaurantName, submitButton, backButton);
        return new Scene(layout, 400, 600);
    }

    private Scene createAddMenuForm() {
        VBox layout = new VBox(10);
        // Set the layout to be centered
        layout.setAlignment(Pos.CENTER);
        // Set labels, textfield, and buttons
        Label labelRestaurant = new Label("Restaurant Name:");
        TextField restaurantName = new TextField();
        Label labelMenuItem = new Label("Menu Item Name:");
        TextField menuItemName = new TextField();
        Label labelPrice = new Label("Price:");
        TextField price = new TextField();
        Button addButton = new Button("Add Menu Item");
        Button backButton = new Button("Back");
        // Set functions to the button
        addButton.setOnAction(e -> {
            // If else to check the availability of the restaurant
            if (price.getText().equals("") || menuItemName.getText().equals("")) {
                showAlert("Error", "Restaurant Not Found", "Restaurant was not found", "error");
            } else {
                handleTambahMenuRestoran(getRestaurantByName(restaurantName.getText()), menuItemName.getText(),
                        Double.parseDouble(price.getText()));
            }
            // Clear all the textfields
            restaurantName.setText("");
            menuItemName.setText("");
            price.setText("");
        });
        backButton.setOnAction(e -> back());
        // Add labels, textfield, and buttons to the vbox
        layout.getChildren().addAll(labelRestaurant, restaurantName, labelMenuItem, menuItemName, labelPrice, price,
                addButton, backButton);
        return new Scene(layout, 400, 600);
    }

    private Scene createViewRestaurantsForm() {
        VBox layout = new VBox(10);
        // Set the layout to be centered
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(15, 20, 10, 10)); // Add padding around the layout
        // Set labels, textfield, buttons, and listviews
        Label labelRestaurant = new Label("Restaurant Name:");
        TextField restaurantName = new TextField();
        ListView<String> menuItemsListView = new ListView<>();
        menuItemsListView.setPrefHeight(200);
        Button searchButton = new Button("Search");
        // Set functions to the buttons
        searchButton.setOnAction(e -> {
            menuItemsListView.getItems().clear();
            Restaurant restaurant = getRestaurantByName(restaurantName.getText());
            // If else to check if the restaurant is available
            if (restaurant != null) {
                restaurant.getMenu().forEach(item -> {
                    menuItemsListView.getItems().add(item.getNamaMakanan() + " - Rp" + item.getHarga());
                });
            } else {
                showAlert("Error", "Restaurant not found", "Restaurant not found", "error");
                restaurantName.setText("");
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            back();
            restaurantName.setText("");
            menuItemsListView.getItems().clear();
        });
        // Add labels, textfield, buttons, and listviews to the vbox
        layout.getChildren().addAll(labelRestaurant, restaurantName, searchButton, menuItemsListView, backButton);
        return new Scene(layout, 400, 600);
    }

    private void handleTambahRestoran(String inputtedName) {
        // Booleans to check the existence of the restaurant and the validity of its name
        boolean isRestaurantExist = restoList.stream()
                .anyMatch(restoran -> restoran.getNama().toLowerCase()
                        .equals(inputtedName.replace(" ", "").toLowerCase()));
        boolean isRestaurantNameLengthValid = inputtedName.replace(" ", "").length() >= 4;
        // If else to check each condition
        if (isRestaurantExist) {
            showAlert("Error", "Restaurant Exist", inputtedName + " already exists!", "error");
        } else if (!isRestaurantNameLengthValid) {
            System.out.println(inputtedName);
            showAlert("Error", "Invalid Length", "Restaurant name must be at least 4 characters long!", "error");
        } else {
            // Create a new restaurant object and add it to the list
            Restaurant newRestaurant = new Restaurant(inputtedName.replace(" ", ""));
            restoList.add(newRestaurant);
            showAlert("Success", "Success", inputtedName + " was successfully added", "success");
        }
    }

    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, double price) {
        // If else to check if the restaurant is available
        if (restaurant == null) {
            showAlert("Error", "Restaurant Not Found", "Restaurant was not found", "error");
        } else {
            Menu newMenu = new Menu(itemName, price);
            restaurant.addMenu(newMenu);
            showAlert("Success", "Success", newMenu.getNamaMakanan() + " was successfully added", "success");
        }
    }

    // Method from previous TP
    public static Restaurant getRestaurantByName(String name) {
        for (Restaurant resto : restoList) {
            if (resto.getNama().equals(name)) {
                return resto;
            }
        }
        return null;
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

    public void setScene(Scene scene) {
        stage.setScene(scene);
        // currentScene = scene;
    }

    public void back() {
        setScene(baseMenuScene);
    }
}
