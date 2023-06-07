/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import View.ViewManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Sword
 */
public class PatientRegisterController {

    @FXML
    private Button saveNewUserBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private TextField fNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField phoneTF;
    @FXML

    private void saveNewUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        // get data from all text fields 
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String email = emailTF.getText();
        String firstName = fNameTF.getText();
        String lastName = lastNameTF.getText();
        int age = Integer.parseInt(ageTF.getText());
        String phone = phoneTF.getText();
        String gender = ((RadioButton)genderGroup.getSelectedToggle()).getText();
        User user = new User(username, password, firstName, lastName, age, email, phone, gender,"Patient");
        int recordCounter = user.save();
        if (recordCounter > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User inserted");
            alert.setContentText("User inserted");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelUserCreation(ActionEvent event) {
       ViewManager.closePatientRegisterView();
    }

}
