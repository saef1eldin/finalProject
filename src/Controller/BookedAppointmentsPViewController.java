/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.BookedAppointment;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sword
 */
public class BookedAppointmentsPViewController implements Initializable {

    @FXML
    private TableView<BookedAppointment> tableView;
    @FXML
    private TableColumn<BookedAppointment, Integer> id;
    @FXML
    private TableColumn<BookedAppointment, Integer> appointment_id;
    @FXML
    private TableColumn<BookedAppointment, Integer> user_id;
    @FXML
    private TableColumn<BookedAppointment, String> status;
    @FXML
    private TableColumn<BookedAppointment, String> doctor_comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ShowWaitingApointments(ActionEvent event) {
        try {
            ArrayList<BookedAppointment> bookedAppointments = BookedAppointment.getAllWaitingAppointments();

            ObservableList<BookedAppointment> data = FXCollections.observableArrayList(bookedAppointments);
            tableView.setItems(data);

            id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            appointment_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAppointmentId()).asObject());
            user_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUserId()).asObject());
            status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
            doctor_comment.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoctorComment()));
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void finishedAppointments(ActionEvent event) {
        try {
            ArrayList<BookedAppointment> bookedAppointments = BookedAppointment.getAllFinishedAppointments();

            ObservableList<BookedAppointment> data = FXCollections.observableArrayList(bookedAppointments);
            tableView.setItems(data);

            id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            appointment_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAppointmentId()).asObject());
            user_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUserId()).asObject());
            status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
            doctor_comment.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoctorComment()));
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void showAppointmentsPage(ActionEvent event) {
        ViewManager.openPatientDashboardView();
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    private void showBookedAppointmentsPage(ActionEvent event) {

    }

    @FXML
    private void loginOut(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

    }

}
