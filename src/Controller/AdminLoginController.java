/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DB;
import View.ViewManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Sword
 */
public class AdminLoginController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private void loginBtn(ActionEvent event) {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        try {
            DB db = DB.getInstance();
            Connection conn = db.getConnection();

            String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ? AND role = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);
            statement.setString(3, "Admin");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count > 0) {
                    ViewManager.openAdminDashboardView();
                    ViewManager.closeAdminLoginView();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Credentials");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid username or password.");
                    alert.showAndWait();
                }
            }

            conn.close();
        } catch (SQLException e) {
        }
    }

    
}
