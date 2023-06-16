/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sword
 */
public class UpdatePatientView extends Stage {

    public UpdatePatientView() {
    }
    public void showUpdatePatientView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/UpdatePatient.fxml"));
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