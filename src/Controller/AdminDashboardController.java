/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Sword
 */
public class AdminDashboardController {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button RegisteredPatientsrBtn;
    @FXML
    private Button FreeAppointmentsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private Button BookedAppointmentsBtn1;
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
            ArrayList<User> users = User.getAllUsers();
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
    private void showFreeAppointments(ActionEvent event) {
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) {
        
        
        
        
    }

    @FXML
private void deleteSelectedAccount(ActionEvent event) {
    // احصل على الحساب المحدد في الجدول
    User selectedUser = tableView.getSelectionModel().getSelectedItem();
    
    if (selectedUser == null) {
        return;
    }
    
    try {
        boolean deleted = selectedUser.delete();

        if (deleted) {
            tableView.getItems().remove(selectedUser);
        } else {
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

    @FXML
private void searchForAnAccount(ActionEvent event) {
    String firstName = accontSearchTF.getText(); // افترض أن حقل النص لإدخال الاسم الأول هو accontSearchTF
    try {
        ArrayList<User> users = User.searchByFirstName(firstName);
        ObservableList<User> userList = FXCollections.observableArrayList(users);
        tableView.setItems(userList);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void showBookedAppointments(ActionEvent event) {
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

}
