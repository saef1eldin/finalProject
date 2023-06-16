package Controller;

import Model.Appointment;
import View.ViewManager;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * 
 * This controller is responsible for creating new appointments.
 * It interacts with the Appointment model class and the CreateAppointmentView.
 * 
 * @author Sword
 */
public class CreateAppointmentViewController implements Initializable {

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
        List<String> daysOfWeek = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        day.getItems().addAll(daysOfWeek);
    }

    @FXML
    private void saveNewAppointment(ActionEvent event) {
        try {
            LocalDate appointmentDate = date.getValue();
            String appointmentDay = day.getValue();
            LocalTime appointmentTime = LocalTime.parse(time.getText());
            String status = "free";
            
            Appointment appointment = new Appointment(0, appointmentDate, appointmentDay, appointmentTime, status);
            int result = appointment.save();

            if (result > 0) {
                showAlert(AlertType.INFORMATION, "Appointment Saved", "Appointment was saved successfully!");
            } else {
                showAlert(AlertType.ERROR, "Save Failed", "Failed to save appointment.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Save Failed", "An error occurred while saving the appointment.");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void cancelUserCreation(ActionEvent event) {
        ViewManager.closeCreateAppointmentView();
    }

}
