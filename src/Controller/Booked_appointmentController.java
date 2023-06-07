/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.BookedAppointment;
import Model.DB;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Booked_appointmentController   {

    @FXML
    private TableView<BookedAppointment> tView;
    @FXML
    private TableColumn<BookedAppointment, Integer> tid;
    @FXML
    private TableColumn<BookedAppointment, Integer> taid;
    @FXML
    private TableColumn<BookedAppointment, Integer> tuserid;
    @FXML
    private TableColumn<BookedAppointment, Integer> tstatus;
    @FXML
    private TableColumn<BookedAppointment, Integer> tdc;

    /**
     * Initializes the controller class.
     */
  public void DisplayBlookedAppointment() {
    try {
        Connection conn = DB.getInstance().getConnection();
        if (conn == null) {
            return;
        }

        String query = "SELECT * FROM booked_appointments";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<BookedAppointment> bookedAppointmentList = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("id");
            int appointmentId = rs.getInt("appointment_id");
            int userId = rs.getInt("user_id");
            String status = rs.getString("status");
            String doctorComment = rs.getString("doctor_comment");

            BookedAppointment bookedAppointment = new BookedAppointment(id, appointmentId, userId, status, doctorComment);
            bookedAppointmentList.add(bookedAppointment);
        }

        tView.setItems(bookedAppointmentList);

        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        taid.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        tuserid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        tstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tdc.setCellValueFactory(new PropertyValueFactory<>("doctorComment"));

        tView.refresh();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

