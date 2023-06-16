/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Appointment;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sword
 */
public class AppointmentDashboardViewController implements Initializable {

    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, Integer> id;
    @FXML
    private TableColumn<Appointment, String> date;
    @FXML
    private TableColumn<Appointment, String> day;
    @FXML
    private TableColumn<Appointment, String> time;
    @FXML
    private TableColumn<Appointment, String> status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ShowRegisteredAppointment(ActionEvent event) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        day.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
        time.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load appointments into the table
        try {
            ArrayList<Appointment> appointments = Appointment.getAllAppointments();
            tableView.getItems().addAll(appointments);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately
        }
    }

    @FXML
    private void CreateAppointment(ActionEvent event) {
        ViewManager.openCreateAppointmentView();
    }

    @FXML
    private void deleteSelectedAppointments(ActionEvent event) {
        Appointment selectedAppointment = tableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment != null) {
            try {
                selectedAppointment.delete();
                tableView.getItems().remove(selectedAppointment);
                showAlert(AlertType.INFORMATION, "Success", "Appointment deleted successfully!");
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "Failed to delete the appointment.");
            }
        } else {
            showAlert(AlertType.WARNING, "Warning", "No appointment selected!");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void showAppointmentsPage(ActionEvent event) {
        ViewManager.openAppointmentDashboardView();

    }

    @FXML
    private void showMainPage(ActionEvent event) {
        ViewManager.openAdminDashboardView();
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    private void showBookedAppointmentPage(ActionEvent event) {
        ViewManager.openBookedAppointmentsView();
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    private void updateSelectedAppointment(ActionEvent event) {
        Appointment selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            return;
        }

        int selectedUserId = selectedUser.getId();  // احصل على معرّف المستخدم المحدد
        SharedData.getInstance().setUserId(selectedUserId);  // قم بتخزينه باستخدام SharedData
        ViewManager.openUpdateAppointmentView();

    }

    @FXML
    private void loginOut(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

}
