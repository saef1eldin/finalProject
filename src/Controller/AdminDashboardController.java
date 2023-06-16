package Controller;

import Model.User;
import View.ViewManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> user;
    @FXML
    private TableColumn<User, String> fname;
    @FXML
    private TableColumn<User, String> lname;
    @FXML
    private TableColumn<User, Integer> age;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> gender;
    @FXML
    private TableColumn<User, String> role;


    @FXML

    private void showRegisteredPatientsr(ActionEvent event) {
        try {
            ArrayList<User> users = User.getAllPatients();
            ObservableList<User> userList = FXCollections.observableArrayList(users);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            user.setCellValueFactory(new PropertyValueFactory<>("username"));
            fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            age.setCellValueFactory(new PropertyValueFactory<>("age"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            role.setCellValueFactory(new PropertyValueFactory<>("role"));
            tableView.setItems(userList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            return;
        }

        int selectedUserId = selectedUser.getId();  // احصل على معرّف المستخدم المحدد
        SharedData.getInstance().setUserId(selectedUserId);  // قم بتخزينه باستخدام SharedData
        ViewManager.openUpdatePatientView();
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            return;
        }

        try {
            boolean deleted = selectedUser.delete();

            if (deleted) {
                tableView.getItems().remove(selectedUser);
            } else {
                // Show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete the selected account.");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
        String firstName = accontSearchTF.getText();
        try {
            ArrayList<User> users = User.searchByFirstName(firstName);
            ObservableList<User> userList = FXCollections.observableArrayList(users);
            tableView.setItems(userList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void CreatePatient2(ActionEvent event) {
        ViewManager.openPatientRegisterView();
    }

    public void refreshUserTableView() {
        try {
            ArrayList<User> users = User.getAllPatients();
            ObservableList<User> userList = FXCollections.observableArrayList(users);
            tableView.setItems(userList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showAppointmentsPage(ActionEvent event) {
        ViewManager.openAppointmentDashboardView();
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void showAppointmentsBookedPage(ActionEvent event) {
        ViewManager.openBookedAppointmentsView();
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    private void showMainPage(ActionEvent event) {
    }

    @FXML
    private void loginOut(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

}
