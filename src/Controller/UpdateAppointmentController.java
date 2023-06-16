/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Appointment;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sword
 */
public class UpdateAppointmentController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private TextField time;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> day;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int selectedAppointmentId = SharedData.getInstance().getUserId();
        List<String> daysOfWeek = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        day.getItems().addAll(daysOfWeek);

        try {
            Appointment selectedAppointment = Appointment.getAppointmentById(selectedAppointmentId);
            if (selectedAppointment != null) {
                date.setValue(selectedAppointment.getAppointmentDate());
                day.setValue(selectedAppointment.getAppointmentDay());
                time.setText(selectedAppointment.getAppointmentTime().toString());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelUserCreation(ActionEvent event) {
    }

    @FXML

private void saveAppointment(ActionEvent event) {
    try {
        int selectedAppointmentId = SharedData.getInstance().getUserId();
        Appointment selectedAppointment = Appointment.getAppointmentById(selectedAppointmentId);

        LocalDate appointmentDate = date.getValue();
        String appointmentDay = day.getValue();
        LocalTime appointmentTime = LocalTime.parse(time.getText());
        String status = "free";

        selectedAppointment.setAppointmentDate(appointmentDate);
        selectedAppointment.setAppointmentDay(appointmentDay);
        selectedAppointment.setAppointmentTime(appointmentTime);
        selectedAppointment.setStatus(status);

        int result = selectedAppointment.update();

        if (result > 0) {
            showAlert(Alert.AlertType.INFORMATION, "Appointment Updated", "Appointment was updated successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update appointment.");
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Update Failed", "An error occurred while updating the appointment.");
    }
}
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
