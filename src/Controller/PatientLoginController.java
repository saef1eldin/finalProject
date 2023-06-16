package Controller;

import Model.DB;
import View.ViewManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientLoginController {

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

            String query = "SELECT id, COUNT(*) FROM users WHERE username = ? AND password = ? AND role = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);
            statement.setString(3, "Patient");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(2);

                if (count > 0) {
                    int userId = resultSet.getInt("id");
                    SharedData.getInstance().setUserId(userId); 
                    ViewManager.openPatientDashboardView();
                    ViewManager.closePatientLoginView();
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
            e.printStackTrace();
        }

    }

    @FXML
    private void registerBtn(ActionEvent event) {
        ViewManager.openPatientRegisterView();
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

}
