package Controller;

import Model.User;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class UpdatePatientController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField fNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField phoneTF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int selectedUserId = SharedData.getInstance().getUserId();
        try {
            User selectedUser = User.getUserById(selectedUserId);
            if (selectedUser != null) {
                usernameTF.setText(selectedUser.getUsername());
                passwordTF.setText(selectedUser.getPassword());
                emailTF.setText(selectedUser.getEmail());
                fNameTF.setText(selectedUser.getFirstName());
                lastNameTF.setText(selectedUser.getLastName());
                ageTF.setText(String.valueOf(selectedUser.getAge()));
                phoneTF.setText(selectedUser.getPhone());
                if (selectedUser.getGender().equalsIgnoreCase("Male")) {
                    maleRadio.setSelected(true);
                } else if (selectedUser.getGender().equalsIgnoreCase("Female")) {
                    femaleRadio.setSelected(true);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void updateUser() {
        try {
            // استرداد القيم المحدثة من حقول الإدخال
            String username = usernameTF.getText();
            String password = passwordTF.getText();
            String firstName = fNameTF.getText();
            String lastName = lastNameTF.getText();
            int age = Integer.parseInt(ageTF.getText());
            String email = emailTF.getText();
            String phone = phoneTF.getText();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String role = "patient"; 

            int selectedUserId = SharedData.getInstance().getUserId();
            User updatedUser = new User(age, username, password, firstName, lastName, age, email, phone, gender, role);
            updatedUser.setId(selectedUserId);
            int rowsAffected = updatedUser.update();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Update");
                alert.setContentText("User Updated");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Update");
                alert.setContentText("User not Updated");
                alert.showAndWait();
            }

            // قم بما تريده بعد تحديث البيانات مثل إغلاق النافذة أو تحديث واجهة المستخدم
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelCreation() {
        ViewManager.closeUpdatePatientView();
    }

    private void clearInputFields() {
        usernameTF.clear();
        passwordTF.clear();
        emailTF.clear();
        fNameTF.clear();
        lastNameTF.clear();
        ageTF.clear();
        phoneTF.clear();
        maleRadio.setSelected(true);
    }

}
