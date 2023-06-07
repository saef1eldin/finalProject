/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Appointment;
import Model.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppointmentController {
    @FXML
    private TableView<Appointment> tView;
    @FXML
    private TableColumn<Appointment, Integer> tid;
    @FXML
    private TableColumn<Appointment, LocalDate> tadata;
    @FXML
    private TableColumn<Appointment, String> tday;
    @FXML
    private TableColumn<Appointment, LocalTime> ttime;
    @FXML
    private TableColumn<Appointment, String> tstatus;

    public void initialize() {
        // Assuming you have defined the TableColumn properties correctly
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tadata.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        tday.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
        ttime.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        tstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void displayAppointments() {
        try {
            Connection conn = DB.getInstance().getConnection();
            if (conn == null) {
                return;
            }

            String query = "SELECT * FROM appointments";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate appointmentDate = rs.getDate("appointment_date").toLocalDate();
                String appointmentDay = rs.getString("appointment_day");
                LocalTime appointmentTime = rs.getTime("appointment_time").toLocalTime();
                String status = rs.getString("status");

                Appointment appointment = new Appointment(id, appointmentDate, appointmentDay, appointmentTime, status);
                appointmentList.add(appointment);
            }

            tView.setItems(appointmentList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
