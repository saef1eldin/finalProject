/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Appointment {

    private int id;
    private LocalDate appointmentDate;
    private String appointmentDay;
    private LocalTime appointmentTime;
    private String status;

    // Constructors
    public Appointment() {
    }

    public Appointment(int id, LocalDate appointmentDate, String appointmentDay, LocalTime appointmentTime, String status) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static ArrayList<Appointment> getAllAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT id, appointment_date, appointment_day, appointment_time, status FROM appointments";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            LocalDate appointmentDate = rs.getDate("appointment_date").toLocalDate();
            String appointmentDay = rs.getString("appointment_day");
            LocalTime appointmentTime = rs.getTime("appointment_time").toLocalTime();
            String status = rs.getString("status");

            Appointment appointment = new Appointment(id, appointmentDate, appointmentDay, appointmentTime, status);
            appointments.add(appointment);
        }

        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        c.close();

        return appointments;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO appointments (appointment_date, appointment_day, appointment_time, status) "
                + "VALUES (?, ?, ?, ?)";
        ps = c.prepareStatement(sql);
        ps.setObject(1, this.getAppointmentDate());
        ps.setString(2, this.getAppointmentDay());
        ps.setObject(3, this.getAppointmentTime());
        ps.setString(4, this.getStatus());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointment was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public void delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;

        String sql = "DELETE FROM appointments WHERE id = ?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());

        int recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            System.out.println("Appointment deleted successfully!");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();
    }

    public void updateStatus() throws SQLException, ClassNotFoundException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DB.getInstance().getConnection();

            String sql = "UPDATE appointments SET status = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, getStatus());
            statement.setInt(2, getId());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Appointment getAppointmentById(int appointmentId) throws SQLException, ClassNotFoundException {
    Connection c = DB.getInstance().getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Appointment appointment = null;

    String sql = "SELECT id, appointment_date, appointment_day, appointment_time, status FROM appointments WHERE id = ?";
    ps = c.prepareStatement(sql);
    ps.setInt(1, appointmentId);
    rs = ps.executeQuery();

    if (rs.next()) {
        int id = rs.getInt("id");
        LocalDate appointmentDate = rs.getDate("appointment_date").toLocalDate();
        String appointmentDay = rs.getString("appointment_day");
        LocalTime appointmentTime = rs.getTime("appointment_time").toLocalTime();
        String status = rs.getString("status");

        appointment = new Appointment(id, appointmentDate, appointmentDay, appointmentTime, status);
    }

    if (rs != null) {
        rs.close();
    }
    if (ps != null) {
        ps.close();
    }
    c.close();

    return appointment;
}

public int update() throws SQLException, ClassNotFoundException {
    Connection c = DB.getInstance().getConnection();
    PreparedStatement ps = null;
    int recordCounter = 0;
    String sql = "UPDATE appointments SET appointment_date = ?, appointment_day = ?, appointment_time = ?, status = ? WHERE id = ?";
    ps = c.prepareStatement(sql);
    ps.setObject(1, this.getAppointmentDate());
    ps.setString(2, this.getAppointmentDay());
    ps.setObject(3, this.getAppointmentTime());
    ps.setString(4, this.getStatus());
    ps.setInt(5, this.getId());
    recordCounter = ps.executeUpdate();
    if (recordCounter > 0) {
        System.out.println("Appointment was updated successfully!");
    }
    if (ps != null) {
        ps.close();
    }
    c.close();
    return recordCounter;
}

}
