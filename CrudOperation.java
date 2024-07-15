package JDBC;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Scanner;

	public class CrudOperation {
	    static Scanner sc = new Scanner(System.in);

	    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	        // Load the MySQL JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish a connection to the database
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");

	        boolean b = true;

	        while (b) {
	            System.out.println("************************");
	            System.out.println("Select any one option:");
	            System.out.println("1. Insert 2. Retrieve All 3. Retrieve Based on ID 4. Delete record 5. Update record 6. Exit");

	            int val = sc.nextInt();

	            switch (val) {
	                case 1: {
	                    System.out.println("Enter Student ID, Name, address, age");

	                    PreparedStatement prep = con.prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?)");
	                    
	                    int id = sc.nextInt();
	                    String name = sc.next();
	                    String address=sc.next();
	                    int age = sc.nextInt();

	                    prep.setInt(1, id);
	                    prep.setString(2, name);
	                    prep.setString(3, address);
	                    prep.setInt(4, age);

	                    prep.execute();

	                    System.out.println("Values Inserted...");
	                    break;
	                }
	                case 2: {
	                    Statement st = con.createStatement();
	                    ResultSet rs = st.executeQuery("SELECT * FROM students");

	                    while (rs.next()) {
	                        int id = rs.getInt(1);
	                        String name = rs.getString(2);
	                        String address = rs.getString(3);
	                        int age = rs.getInt(4);

	                        System.out.println(id + " " + name + " " + address + " " + age);
	                    }
	                    break;
	                }
	                case 3: {
	                    PreparedStatement prep = con.prepareStatement("SELECT * FROM students WHERE id = ?");
	                    System.out.println("Enter student id");
	                    
	                    int a = sc.nextInt();
	                    prep.setInt(1, a);

	                    ResultSet rs = prep.executeQuery();
	                    while (rs.next()) {
	                        int id = rs.getInt(1);
	                        String name = rs.getString(2);
	                        String address = rs.getString(3);
	                        int age = rs.getInt(4);
	                        System.out.println("The details are:");
	                        System.out.println(id + " " + name + " " + address + " " + age);
	                    }
	                    break;
	                }
	                case 4: {
	                    PreparedStatement prep = con.prepareStatement("DELETE FROM students WHERE id = ?");
	                    System.out.println("Enter student id");
	                    
	                    int a = sc.nextInt();
	                    prep.setInt(1, a);

	                    prep.execute();

	                    System.out.println("Values Deleted...");
	                    break;
	                }
	                case 5: {
	                    PreparedStatement prep = con.prepareStatement("UPDATE students SET id = ?, name = ?, address = ?, age = ? WHERE id = ?");
	                    System.out.println("Enter Id of row to be updated");
	                    
	                    int a = sc.nextInt();

	                    System.out.println("Enter updated Student ID, Name, address, age");
	                    
	                    int id = sc.nextInt();
	                    String name = sc.next();
	                    String address = sc.next();
	                    int age = sc.nextInt();

	                    prep.setInt(1, id);
	                    prep.setString(2, name);
	                    prep.setString(3, address);
	                    prep.setInt(4, age);
	                    prep.setInt(5, a);

	                    prep.execute();

	                    System.out.println("Values Updated...");
	                    break;
	                }
	                case 6: {
	                    System.out.println("THANK YOU");
	                    b = false;
	                    break;
	                }
	                default: {
	                    System.out.println("Select proper Option...");
	                }
	            }
	        }

	        // Close the connection
	        con.close();
	    }
	}

