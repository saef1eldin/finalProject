/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Sword
 */
public class ViewManager {
    private static PatientLoginView patientLoginView;
    private static PatientRegisterView patientRegisterView;
    private static AdminLoginView adminLoginView;
    private static AdminDashboardView adminDashboardView;
    private static PatientDashboardView patientDashboardView;
    
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
}
