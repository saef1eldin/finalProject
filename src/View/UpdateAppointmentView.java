/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sword
 */
public class UpdateAppointmentView extends Stage {

    public void showUpdateAppointmentView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/UpdateAppointment.fxml"));
            Scene scene = new Scene(root);
            setScene(scene);
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        // Perform the necessary actions to close the admin dashboard view
        super.close();
    }
}
