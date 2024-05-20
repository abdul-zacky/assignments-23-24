package assignments.assignment4.components.form;

import assignments.assignment2.User;
import assignments.assignment3.systemCLI.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginForm {
    private Stage stage;
    private MainApp mainApp; // MainApp instance
    private TextField nameInput;
    private TextField phoneInput;

    public LoginForm(Stage stage, MainApp mainApp) { // Pass MainApp instance to constructor
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
    }

    private Scene createLoginForm() {
        GridPane grid = new GridPane();
        // Set semua item di tengah
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add Username Label
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        // Add Username Text Field
        TextField userTextField = new TextField();
        nameInput = userTextField;
        grid.add(userTextField, 1, 1);

        // Add Password Label
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        // Add Password Field
        TextField pwBox = new PasswordField();
        phoneInput = pwBox;
        grid.add(pwBox, 1, 2);

        // Add Login Button
        Button btn = new Button("Sign in");
        grid.add(btn, 1, 3);

        // Add functions to the button
        btn.setOnAction(e -> {
            System.out.println("Sign in button pressed");
            handleLogin();
            clearLoginForm();
        });
        return new Scene(grid, 400, 600);
    }

    private void handleLogin() {
        User userLoggedIn = DepeFood.getUser(nameInput.getText(), phoneInput.getText());
        // If else untuk ngecek validity user
        if (userLoggedIn != null) {
            System.out.println(userLoggedIn.role);
            switchSceneBasedOnRole(userLoggedIn.role, userLoggedIn);
        } else {
            // Show error/alert message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Error: User not found or wrong password.");
            alert.showAndWait();
        }
    }

    private void clearLoginForm() {
        // Clear the form fields
        nameInput.setText("");
        phoneInput.setText("");
    }

    // Method to get scene based on the role
    private void switchSceneBasedOnRole(String role, User userLoggedIn) {
        Scene newScene = null;
        switch (role) {
            case "Admin":
                newScene = new AdminMenu(stage, mainApp, userLoggedIn).getScene();
                break;
            case "Customer":
                newScene = new CustomerMenu(stage, mainApp, userLoggedIn).getScene();
                break;
            default:
                System.out.println("Unknown role: " + role);
                return;
        }
        stage.setScene(newScene);
        stage.show();
    }

    public Scene getScene() {
        return this.createLoginForm();
    }

}
