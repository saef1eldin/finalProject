/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;


/**
 * Manages the views and their opening/closing operations.
 */
public class ViewManager {
    private static int selectedUserId;
    private static PatientLoginView patientLoginView;
    private static PatientRegisterView patientRegisterView;
    private static AdminLoginView adminLoginView;
    private static AdminDashboardView adminDashboardView;
    private static PatientDashboardView patientDashboardView;
    private static CreateAppointmentView createAppointmentView;
    private static AppointmentDashboardView appointmentDashboardView;
    private static UpdatePatientView updatePatientView;
    private static bookedAppointmentsPView bookedAppointmentsPView;
    private static UpdateAppointmentView UpdateAppointmentView;
    private static BookedAppointmentsView BookedAppointmentsView;

    public static int getSelectedUserId() {
    return selectedUserId;
    }

    private ViewManager() {
    }
    
    public static void openPatientLoginView() {
        if (patientLoginView == null) {
            patientLoginView = new PatientLoginView();
            patientLoginView.showPatientLogin();
        } else {
            patientLoginView.showPatientLogin();
        }
    }
    
    public static void closePatientLoginView() {
        if (patientLoginView != null) {
            patientLoginView.close();
        }
    }
    
    public static void openPatientRegisterView() {
        if (patientRegisterView == null) {
            patientRegisterView = new PatientRegisterView();
            patientRegisterView.showPatientRegister();
        } else {
            patientRegisterView.showPatientRegister();
        }
    }
    
    public static void closePatientRegisterView() {
        if (patientRegisterView != null) {
            patientRegisterView.close();
        }
    }
    
    public static void openAdminLoginView() {
        if (adminLoginView == null) {
            adminLoginView = new AdminLoginView();
            adminLoginView.showAdminLogin();
        } else {
            adminLoginView.showAdminLogin();
        }
    }
    
    public static void closeAdminLoginView() {
        if (adminLoginView != null) {
            adminLoginView.close();
        }
    }
    
    public static void openAdminDashboardView() {
        if (adminDashboardView == null) {
            adminDashboardView = new AdminDashboardView();
            adminDashboardView.showAdminDashboard();
        } else {
            adminDashboardView.showAdminDashboard();
        }
    }
    
    public static void closeAdminDashboardView() {
        if (adminDashboardView != null) {
            adminDashboardView.close();
        }
    }
    
    public static void openPatientDashboardView() {
        if (patientDashboardView == null) {
            patientDashboardView = new PatientDashboardView();
            patientDashboardView.showPatientDashboard();
        } else {
            patientDashboardView.showPatientDashboard();
        }
    }
    
    public static void closePatientDashboardView() {
        if (patientDashboardView != null) {
            patientDashboardView.close();
        }
    }
    
    public static void openCreateAppointmentView() {
        if (createAppointmentView == null) {
            createAppointmentView = new CreateAppointmentView();
            createAppointmentView.showCreateAppointment();
        } else {
            createAppointmentView.showCreateAppointment();
        }
    }
    
    public static void closeCreateAppointmentView() {
        if (createAppointmentView != null) {
            createAppointmentView.close();
        }
    }
    
    public static void openAppointmentDashboardView() {
        if (appointmentDashboardView == null) {
            appointmentDashboardView = new AppointmentDashboardView();
            appointmentDashboardView.showAppointmentDashboardView();
        } else {
            appointmentDashboardView.showAppointmentDashboardView();
        }
    }
    
    public static void closeAppointmentDashboardView() {
        if (appointmentDashboardView != null) {
            appointmentDashboardView.close();
        }
    }
    public static void openUpdatePatientView() {
    if (updatePatientView == null) {
        updatePatientView = new UpdatePatientView();
        updatePatientView.showUpdatePatientView();
    } else {
        updatePatientView.showUpdatePatientView();
    }
}


    
    public static void closeUpdatePatientView() {
        if (updatePatientView != null) {
            updatePatientView.close();
        }
    }


    public static void openbookedAppointmentsPView() {
    if (bookedAppointmentsPView == null) {
        bookedAppointmentsPView = new bookedAppointmentsPView();
        bookedAppointmentsPView.showBookedAppointmentsPView();
    } else {
        bookedAppointmentsPView.showBookedAppointmentsPView();
    }
}


    
    public static void closeBookedAppointmentsPView() {
        if (bookedAppointmentsPView != null) {
            bookedAppointmentsPView.close();
        }
    }

    public static void openUpdateAppointmentView() {
    if (UpdateAppointmentView == null) {
        UpdateAppointmentView = new UpdateAppointmentView();
        UpdateAppointmentView.showUpdateAppointmentView();
    } else {
        UpdateAppointmentView.showUpdateAppointmentView();
    }
}


    
    public static void closeUpdateAppointmentView() {
        if (UpdateAppointmentView != null) {
            UpdateAppointmentView.close();
        }
    }

    public static void openBookedAppointmentsView() {
    if (BookedAppointmentsView == null) {
        BookedAppointmentsView = new BookedAppointmentsView();
        BookedAppointmentsView.showBookedAppointmentsView();
    } else {
        BookedAppointmentsView.showBookedAppointmentsView();
    }
}


    
    public static void closeBookedAppointmentsView() {
        if (BookedAppointmentsView != null) {
            BookedAppointmentsView.close();
        }
    }

}
 