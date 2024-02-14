package CRUDApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class StudentClass {
	private int studId;
	private String studName;
	private String studEmailID;
	private char studGrade;
	private long studNumber;

	static HashMap<Integer, StudentClass> studentData = new HashMap<>();
	static int countStudent = 1;

	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStudEmailID() {
		return studEmailID;

	}

	public void setStudEmailID(String studEmailID) {
		this.studEmailID = studEmailID;
	}

	public char getStudGrade() {
		return studGrade;
	}

	public void setStudGrade(char c) {
		this.studGrade = c;
	}

	public long getStudNumber() {
		return studNumber;
	}

	public void setStudNumber(long l) {
		this.studNumber = l;
	}

}

public class Main {
	public static void printMenu() {

		System.out.println("1. To create a new student");
		System.out.println("2. To remove a  student");
		System.out.println("3. To update a student");
		System.out.println("4. To display all a  student dat");
		System.out.println("5. To search a  student");
		System.out.println("6. To exit application");
	}

	public static void toCreateANewStudent() {
		System.out.println("Welcome - Creating a new student");
		Scanner s = new Scanner(System.in);
		StudentClass obj = new StudentClass();
//        obj.setStudId(StudentClass.countStudent);
//        System.out.println("Student Id is " + obj.getStudId());

		System.out.print("Enter student name: ");
		obj.setStudName(s.next());

		System.out.print("Enter student email: ");
		obj.setStudEmailID(s.next());

		System.out.print("Enter student grade: ");
		obj.setStudGrade(s.next().charAt(0));

		System.out.print("Enter student number: ");
		obj.setStudNumber(s.nextLong());
 
		DBOperations.toAddStudentData(obj);

//        StudentClass.studentData.put(obj.getStudId(), obj);
//        StudentClass.countStudent++;
//        toDisplayAStudent();
//		s.close();
	}

	public static void toRemoveAStudent() {
		System.out.println("Welcome Remove a student");
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the ID of the Student to remove");
		int id = input.nextInt();
		if (StudentClass.studentData.containsKey(id)) {
			StudentClass.studentData.remove(id);
			System.out.println("Student is Removed Successfully ");
		} else {
			System.out.println("Student does not exist with this id !!!!!!!");
		}
//		input.close();
	}

	public static void toUpdateAStudent() {
		System.out.println("Welcome Update a student");
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the ID of the Student to update:");
		int id = input.nextInt();
		if(DBOperations.studExists(id)) {
			System.out.println("1. Update name");
			System.out.println("2. Update email");
			System.out.println("3. Update Grade");
			System.out.println("4. Update Number");
			System.out.println("Enter your choice");
			int choice = input.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter the update name: ");
				String name = input.next();
				DBOperations.toUpdateStudName(name, id);
				
				break;
				
			case 2:
				System.out.println("Enter the update email: ");
				String email = input.next();
				DBOperations.toUpdateStudEmail(email, id);	

				break;
				
			case 3:
			System.out.println("Enter the update Grade: ");
			char grade = input.next().charAt(0);
			DBOperations.toUpdateStudGrade(grade, id);	
			
			break;
			
			case 4:
				System.out.println("Enter the update Grade: ");
				long number = input.nextLong();
				DBOperations.toUpdateStudNumber(number, id);	
				
				break;
				

			}
			
		}
		else {
			System.out.println("Student does not exist with this id !!!!!!!\n");
		}
		toDisplayAStudent();
//		input.close();
	}

	public static void toDisplayAStudent() {
		HashMap<Integer, StudentClass> studentData1 = DBOperations.toDisplayStudent();
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		System.out.println("Student ID \t\t Student Name \t\t Student Mail \t\t Student Grade \t\t Student Number");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		for (Map.Entry<Integer, StudentClass> studentData : studentData1.entrySet()) {
			System.out.print(studentData.getKey() + "\t\t");
			System.out.print(studentData.getValue().getStudName() + "\t\t");
			System.out.print(studentData.getValue().getStudEmailID() + "\t\t");
			System.out.print(studentData.getValue().getStudGrade() + "\t\t");
			System.out.println(studentData.getValue().getStudNumber());
		}

		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		System.out.println("Printed Student Data Successfully");
	}

	public static void toSearchAStudent() {
		System.out.println("Welcome Search a student");
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the ID of the Student to search");
		int id = input.nextInt();
		if (StudentClass.studentData.containsKey(id)) {
			System.out.println("Student name :" + StudentClass.studentData.get(id).getStudName());
			System.out.println("Student Email :" + StudentClass.studentData.get(id).getStudEmailID());
			System.out.println("Student Grade :" + StudentClass.studentData.get(id).getStudGrade());
			System.out.println("Student Number :" + StudentClass.studentData.get(id).getStudNumber());
			System.out.println("Student is Displayed Successfully \n");
		} else {
			System.out.println("Student does not exist with this id !!!!!!!\n");
		}
		toDisplayAStudent();
//		input.close();

	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("\t\t\t\tStudent Database Application\n");
		boolean flag = true;
		while (flag) {
			printMenu();
			System.out.println("Enter your choice");
			int choice = s.nextInt();
			switch (choice) {
			case 1:
				toCreateANewStudent();
				break;
			case 2:
				toRemoveAStudent();
				break;
			case 3:
				toUpdateAStudent();
				break;
			case 4:
				toDisplayAStudent();
				break;
			case 5:
				toSearchAStudent();
				break;
			case 6:
				flag = false;
				break;

			default:
				System.err.println("Invalid choice");
				break;
			}
		}
		// s.close();
		System.out.println("Have a good day");
	}
}