package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String gender;
    private String role;

    public User(int age, String username, String password, String firstName, String lastName, int age1, String email, String phone, String gender, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO users (username, password, firstname, lastname, age, email, phone, gender, role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getFirstName());
        ps.setString(4, this.getLastName());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setString(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername() + " User was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<User> getAllPatients() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> patients = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'patient'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            int age = rs.getInt("age");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String gender = rs.getString("gender");
            String role = rs.getString("role");
            User patient = new User(age, username, password, firstname, lastname, age, email, phone, gender, role);
            patient.setId(id);
            patients.add(patient);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return patients;
    }

    public static ArrayList<User> searchByFirstName(String firstName) throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE firstname LIKE ?";
        ps = c.prepareStatement(sql);
        ps.setString(1, firstName);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            int age = rs.getInt("age");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String gender = rs.getString("gender");
            String role = rs.getString("role");
            User user = new User(age, username, password, firstname, lastname, age, email, phone, gender, role);
            user.setId(id);
            users.add(user);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return users;
    }

    public boolean delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM users WHERE id = ?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("User with ID " + this.getId() + " was deleted successfully!");
            return true;
        } else {
            System.out.println("Failed to delete user with ID " + this.getId());
            return false;
        }
    }

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE USERS SET username=?, password=?, firstname=?, lastname=?, age=?, email=?, phone=?, gender=?, role=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getFirstName());
        ps.setString(4, this.getLastName());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setString(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
        ps.setInt(10, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("User with id: " + this.getId() + " was updated successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }
    public static User getUserById(int userId) throws SQLException, ClassNotFoundException {
    Connection c = DB.getInstance().getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    User user = null;
    String sql = "SELECT * FROM users WHERE id = ?";
    ps = c.prepareStatement(sql);
    ps.setInt(1, userId);
    rs = ps.executeQuery();
    if (rs.next()) {
        String username = rs.getString("username");
        String password = rs.getString("password");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        int age = rs.getInt("age");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String gender = rs.getString("gender");
        String role = rs.getString("role");
        user = new User(age, username, password, firstname, lastname, age, email, phone, gender, role);
        user.setId(userId);
    }
    if (ps != null) {
        ps.close();
    }
    c.close();
    return user;
}


}
