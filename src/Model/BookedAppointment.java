/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.SharedData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<BookedAppointment> getAllBookedAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();

        String sql = "SELECT id, appointment_id, user_id, status, doctor_comment FROM booked_appointments";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int appointmentId = rs.getInt("appointment_id");
            int userId = rs.getInt("user_id");
            String status = rs.getString("status");
            String doctorComment = rs.getString("doctor_comment");

            BookedAppointment bookedAppointment = new BookedAppointment(id, appointmentId, userId, status, doctorComment);
            bookedAppointments.add(bookedAppointment);
        }

        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        c.close();

        return bookedAppointments;
    }

    public void save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO booked_appointments (appointment_id, user_id, status, doctor_comment) VALUES (?, ?, ?, ?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getAppointmentId());
        ps.setInt(2, this.getUserId());
        ps.setString(3, this.getStatus());
        ps.setString(4, this.getDoctorComment());
        ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        c.close();
    }

    public static ArrayList<BookedAppointment> searchByPatientFirstName(String firstName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();

        try {
            Connection c = DB.getInstance().getConnection();
            String query = "SELECT * FROM booked_appointments INNER JOIN users ON booked_appointments.user_id = users.id WHERE users.firstname LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, firstName + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int appointmentId = rs.getInt("appointment_id");
                int userId = rs.getInt("user_id");
                String status = rs.getString("status");
                String doctorComment = rs.getString("doctor_comment");

                BookedAppointment bookedAppointment = new BookedAppointment(id, appointmentId, userId, status, doctorComment);
                bookedAppointments.add(bookedAppointment);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return bookedAppointments;
    }

    public static ArrayList<BookedAppointment> getAllWaitingAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
        int userI = SharedData.getInstance().getUserId(); 
        String sql = "SELECT id, appointment_id, user_id, status, doctor_comment FROM booked_appointments WHERE status = ? and user_id= ? ";
        ps = c.prepareStatement(sql);
        ps.setString(1, "waiting");
        ps.setInt(2, userI);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int appointmentId = rs.getInt("appointment_id");
            int userId = rs.getInt("user_id");
            String status = rs.getString("status");
            String doctorComment = rs.getString("doctor_comment");

            BookedAppointment bookedAppointment = new BookedAppointment(id, appointmentId, userId, status, doctorComment);
            bookedAppointments.add(bookedAppointment);
        }

        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        c.close();

        return bookedAppointments;
    }

    public static ArrayList<BookedAppointment> getAllFinishedAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
     int userI = SharedData.getInstance().getUserId(); 
        String sql = "SELECT id, appointment_id, user_id, status, doctor_comment FROM booked_appointments WHERE status = ? and user_id= ? ";
        ps = c.prepareStatement(sql);
        ps.setString(1, "finished");
        ps.setInt(2, userI);
        rs = ps.executeQuery();


        while (rs.next()) {
            int id = rs.getInt("id");
            int appointmentId = rs.getInt("appointment_id");
            int userId = rs.getInt("user_id");
            String status = rs.getString("status");
            String doctorComment = rs.getString("doctor_comment");

            BookedAppointment bookedAppointment = new BookedAppointment(id, appointmentId, userId, status, doctorComment);
            bookedAppointments.add(bookedAppointment);
        }

        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        c.close();

        return bookedAppointments;
    }

}
