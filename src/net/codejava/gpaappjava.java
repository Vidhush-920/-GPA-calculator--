package net.codejava;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class gpaappjava {
	
	public static void menu(Connection connection, Scanner scanner, Statement statement) throws SQLException {
		
		int e;
		String name;
		System.out.println("----GPA Calculator----");			
		
		ResultSet resa = statement.executeQuery("SELECT * FROM result");
		
		if (resa.next() == false) {	
			System.out.print("Enter your name: ");
			name = scanner.next();
			System.out.print("Course duration(in years): ");
			int dur = scanner.nextInt();
			PreparedStatement pstatement = connection.prepareStatement("INSERT INTO result (Full_Name, Course_Duration, No_of_Subjects, Total_Credits, GPT) VALUES (?, ?, 0, 0, 0.0)");
			pstatement.setString(1, name);
			pstatement.setInt(2, dur);
			
			int rows = pstatement.executeUpdate();
			if (rows > 0) {
				System.out.println("\nCongratulations! Registeration was completed.");
			}
			pstatement.close();
			
		}
		
		else {
			ResultSet resb = statement.executeQuery("SELECT Name FROM result");
			System.out.println("\nHello " + resb.getString("Name") + ", welcome back!");
			System.out.println("\n----Menu----");
			System.out.println("1. View results.");
			System.out.println("2. Add results.");
			System.out.println("3. Update results.");
			System.out.println("4. Export data.");
			System.out.println("5. Clear data.");
			System.out.println("0. View results.\n");
			
			System.out.print("Enter your choice: ");
			e = scanner.nextInt();
			
			if(e==0) {
				System.out.println("\nExit...");
				System.exit(0);
			}
			
			else if (e==2) {
				System.out.println("----Add Result----");
				System.out.print("Year(1, 2, 3, 4): ");
				int ye = scanner.nextInt();
				System.out.print("Course code: ");
				String cc = scanner.next();
				System.out.print("Grade: ");
				String gr = scanner.next();
				System.out.print("Credits: ");
				int cred = scanner.nextInt();
				
				System.out.print("\nConfirm (y/n): ");
				char co = scanner.next().charAt(0);
				
				if (co == 'y') {
					PreparedStatement pstatement = connection.prepareStatement("INSERT INTO course_record (Course, Year, Credits, Grade) VALUES (?, ?, ?, ?)");
					pstatement.setString(1, cc);
					pstatement.setInt(2, ye);
					pstatement.setInt(3,  cred);
					pstatement.setString(4, gr);
					
					statement.executeUpdate("UPDATE result SET No_of_Subjects=No_of_Subjects+1, Total_Credits=Total_Credits+1, GPT=GPT+(");					
					
					statement.executeUpdate("UPDATE result_y (No_of_Subjects_y, Total_Credits_y, GPT_y) WHERE Year=" + ye);
					
					
					int rows = pstatement.executeUpdate();
					if (rows > 0) {
						System.out.println("\nRecord was added.");
					}
					pstatement.close();
				}
			}
			
		/*	else if(e==4) {
				public class exportt();
			}*/
			
		}
	}
	
	public static void main(String[] args) {
		
		String db = "gpa_calculate";
		String url = "jdbc:mysql://localhost:8888/" + db;
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			Connection connection = DriverManager.getConnection(url, "root", "Vidhush20!");
			System.out.println("Connected to the Database " + db);
			
			Statement statement = connection.createStatement();
			
			menu(connection, scanner, statement);
			
			
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Ooops..! An Error occured.");
			e.printStackTrace();
		}
	}
}