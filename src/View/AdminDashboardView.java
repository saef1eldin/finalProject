package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminDashboardView extends Stage {
    public void showAdminDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminDashboardView.fxml"));
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
