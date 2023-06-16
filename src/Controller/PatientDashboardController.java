package Controller;

import Model.Appointment;
import Model.BookedAppointment;
import View.ViewManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientDashboardController {

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


    @FXML
    private void ShowRegisteredAppointment(ActionEvent event) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        day.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
        time.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            ArrayList<Appointment> appointments = Appointment.getAllAppointments();
            tableView.getItems().addAll(appointments);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void BookedAppointment(ActionEvent event) {
        Appointment selectedAppointment = tableView.getSelectionModel().getSelectedItem();
        selectedAppointment.setStatus("booked");

        try {
            selectedAppointment.updateStatus();
            BookedAppointment bookedAppointment = new BookedAppointment();
            bookedAppointment.setAppointmentId(selectedAppointment.getId());
            bookedAppointment.setUserId(SharedData.getInstance().getUserId());
            bookedAppointment.setDoctorComment("");
            bookedAppointment.setStatus("waiting");
            bookedAppointment.save();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Appointment booked");
            alert.setHeaderText(null);
            alert.setContentText("The appointment has been booked successfully!");
            alert.showAndWait();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void showAppointmentsPage(ActionEvent event) {

    }

    @FXML
    private void showBookedAppointmentPage(ActionEvent event) {
                ViewManager.openbookedAppointmentsPView();
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    private void loginOut(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }
}
