/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sword
 */
public class BookedAppointment {
    private int id;
    private int appointmentId;
    private int userId;
    private String status;
    private String doctorComment;

    // Constructors
    public BookedAppointment() {
    }

    public BookedAppointment(int id, int appointmentId, int userId, String status, String doctorComment) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.status = status;
        this.doctorComment = doctorComment;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }
}
