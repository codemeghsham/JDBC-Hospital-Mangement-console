package org.hospital.clientApp;
import java.util.*;
import java.sql.*;
import org.hospital.model.DoctorModel;
import org.hospital.service.DoctorService;
import org.hospital.repository.DoctorRepository;
import org.hospital.model.PatientModel;
import org.hospital.service.PatientService;
public class ClientDashboard {
	public static void main(String[] args) throws Exception {
		// Creating instances of required services and repositories
		DoctorService doctorService = new DoctorService();
		DoctorRepository doctorRepository = new DoctorRepository();
		PatientService patientService = new PatientService();
		PatientModel patientModel = new PatientModel();

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Username for admin  ==> ");
		String uName = sc.nextLine();

		System.out.print("Enter Password for admin  ==> ");
		String uPass = sc.nextLine();

		if (uName.equals("a") && uPass.equals("p")) {
			// Main menu loop
			do {
				System.out.println("\tcase 1: Doctor Submenu  ");
				System.out.println("\tcase 2: Patient Submenu ");

				System.out.print("Enter your choice ==> ");
				int choice = sc.nextInt();
				switch (choice) {
					case 1: // Doctor Submenu
						boolean doctorSubMenu = true;
						while (doctorSubMenu) {
							System.out.println("\tcase 1: Add new Doctor");
							System.out.println("\tcase 2: View All Doctor List");
							System.out.println("\tCase 3: Search Doctor");
							System.out.println("\tcase 4: Delete Doctor By ID");
							System.out.println("\tPress 0 to return to Main Menu");
							System.out.print("Enter your choice  ==> ");
							choice = sc.nextInt();
							switch (choice) {
								case 1: // Add new Doctor
									sc.nextLine();
									System.out.println("Enter Doctor details:");
									System.out.print("Doctor Name: ");
									String doctorName = sc.nextLine();
									System.out.print("Specialty: ");
									String specialty = sc.nextLine();
									System.out.print("Designation: ");
									String designation = sc.nextLine();

									DoctorModel doctorModel = new DoctorModel();
									doctorModel.setDocName(doctorName);
									doctorModel.setDocSpeciality(specialty);
									doctorModel.setDocDesignation(designation);

									int result = doctorService.addDoctor(doctorModel);
									System.out.println((result == 1) ? "Doctor added successfully."
											: (result == -1) ? "Doctor already present" : "Some problem occurred.");
									break;
								case 2: // View All Doctor List
									System.out.println("All Doctor List:");
									List<DoctorModel> doctorList = doctorService.getAllDoctor();
									Set<Integer> seenIds = new HashSet<>(); // To keep track of seen doctor IDs
									System.out.printf("%-8s %-20s %-20s %-20s\n",
											"DocID",
											"DocName",
											"DocSpeciality",
											"DocDesignation");
									for (DoctorModel doctor : doctorList) {
										if (!seenIds.contains(doctor.getDocID())) {
											System.out.printf("%-8s %-20s %-20s %-20s\n",
													doctor.getDocID(),
													doctor.getDocName(),
													doctor.getDocSpeciality(),
													doctor.getDocDesignation());
											seenIds.add(doctor.getDocID());
										}
									}
									break;
								case 3: // Search Doctor
									sc.nextLine();
									System.out.println("Enter the name of the doctor to search:");
									String searchDoctorName = sc.nextLine();
									DoctorModel searchedDoctor = doctorRepository.searchDoctor(searchDoctorName);
									if (searchedDoctor != null) {
										System.out.println("Doctor found:");
										System.out.println("Name: " + searchedDoctor.getDocName());
										System.out.println("ID: " + searchedDoctor.getDocID());
										System.out.println("Specialty: " + searchedDoctor.getDocSpeciality());
										System.out.println("Designation: " + searchedDoctor.getDocDesignation());
									} else {
										System.out.println("Doctor not found.");
									}
									break;
								case 4: // Delete Doctor By ID
									System.out.println("Enter the ID of the doctor to delete:");
									int deleteDoctorId = sc.nextInt();
									boolean isDeleted = doctorService.deleteDoctor(deleteDoctorId);
									if (isDeleted) {
										System.out.println("Doctor deleted successfully.");
									} else {
										System.out.println("Failed to delete doctor. Doctor may not exist.");
									}
									break;
								case 0: // Return to Main Menu
									doctorSubMenu = false;
									break;
								default:
									System.out.println("Wrong choice");
							}
						}
						break;

					case 2: // Patient Submenu
						boolean patientSubMenu = true;
						while (patientSubMenu) {
							System.out.println("case 1: ADD new patient");
							System.out.println("case 2: View All patient List");
							System.out.println("Case 3: Search patient by name");
							System.out.println("case 4: Delete patient By ID");
							System.out.println("case 0: Exit");

							System.out.println("Enter your choice");
							choice = sc.nextInt();

							switch (choice) {
								case 1: // Add new Patient
									sc.nextLine();
									System.out.println("Enter patient details:");

									System.out.print("Patient Name: ");
									String ptName = sc.nextLine();

									System.out.print("Age: ");
									int ptAge = sc.nextInt();
									sc.nextLine(); // Consume newline

									System.out.print("Gender: ");
									String ptGender = sc.nextLine();

									System.out.print("Contact: ");
									String ptContact = sc.nextLine();

									System.out.print("Address: ");
									String ptAddress = sc.nextLine();

									System.out.print("OPD Date (YYYY-MM-DD): ");
									String ptOpdDate = sc.nextLine();
									java.sql.Date opdDate = java.sql.Date.valueOf(ptOpdDate);

									System.out.print("Doctor ID: ");
									int ptDocID = sc.nextInt();

									System.out.print("Fees: ");
									int ptFees = sc.nextInt();
									sc.nextLine();

									System.out.print("Appointment Date (YYYY-MM-DD): ");
									String appointmentDateStr = sc.nextLine();
									java.sql.Date ptAppointmentDate = java.sql.Date.valueOf(appointmentDateStr);


									patientModel.setPtName(ptName);
									patientModel.setPtAge(ptAge);
									patientModel.setPtGender(ptGender);
									patientModel.setPtContact(ptContact);
									patientModel.setPtAddress(ptAddress);
									patientModel.setPtOpdDate(ptAppointmentDate);
									patientModel.setPtID(ptDocID);
									patientModel.setPtFees(ptFees);
									patientModel.setPtAppointmentDate(ptAppointmentDate);

									boolean isSuccess = patientService.addPatient(patientModel);
									if (isSuccess) {
										System.out.println("Patient added successfully.");
									} else {
										System.out.println("Failed to add patient.");
									}
									break;

								case 2: // View All Patient List
									System.out.println("All Patients List:");
									List<PatientModel> patient = patientService.getAllPatients();
									if (patient != null && !patient.isEmpty()) {
										for (PatientModel pt : patient) {
											System.out.println("Patient ID: " + pt.getPtID());
											System.out.println("Name: " + pt.getPtName());
											System.out.println("Age: " + pt.getPtAge());
											System.out.println("Gender: " + pt.getPtGender());
											System.out.println("Contact: " + pt.getPtContact());
											System.out.println("Address: " + pt.getPtAddress());
											System.out.println("OPD Date: " + pt.getPtOpdDate());
											System.out.println("Doctor ID: " + pt.getPtDocID());
											System.out.println("Fees: " + pt.getPtFees());
											System.out.println("Appointment Date: " + pt.getPtAppointmentDate());
											System.out.println("------------------------------------");
										}
									} else {
										System.out.println("No patients found.");
									}
									break;
								case 3: // Search Patient by name
									sc.nextLine(); // Consume newline
									System.out.println("Enter the name of the patient to search:");
									String searchName = sc.nextLine(); // Read the name of the patient to search for
									List<PatientModel> searchResults = patientService.searchPatientsByName(searchName); // Call the service method to search for patients by name
									if (searchResults != null && !searchResults.isEmpty()) { // If search results are not empty
										System.out.println("Search Results:");
										for (PatientModel pt : searchResults) { // Iterate over each patient in the search results
											// Print details of each patient
											System.out.println("Patient ID: " + pt.getPtID());
											System.out.println("Name: " + pt.getPtName());
											System.out.println("Age: " + pt.getPtAge());
											System.out.println("Gender: " + pt.getPtGender());
											System.out.println("Contact: " + pt.getPtContact());
											System.out.println("Address: " + pt.getPtAddress());
											System.out.println("OPD Date: " + pt.getPtOpdDate());
											System.out.println("Doctor ID: " + pt.getPtDocID());
											System.out.println("Fees: " + pt.getPtFees());
											System.out.println("Appointment Date: " + pt.getPtAppointmentDate());
											System.out.println("------------------------------------");

											System.out.println("patients found ");
										}
									} else {
										System.out.println("No patients found with the given name."); // Print message if no patients found
									}
									break;
								case 4: // Delete Patient By ID
									sc.nextLine();

									System.out.print("Enter patient ID to delete: ");
									int deleteId = sc.nextInt();
									boolean isDeleted = patientService.deletePatientById(deleteId);
									if (isDeleted) {
										System.out.println("Patient with ID " + deleteId + " deleted successfully.");
									} else {
										System.out.println("Failed to delete patient with ID " + deleteId + ".");
									}
									break;

								case 0: // Exit
									patientSubMenu = false;
									break;

								default:
									System.out.println("Wrong choice");
							}
						}
						break;
					default:
						System.out.println("Wrong choice");
				}
			} while (true); // Infinite loop for main menu
		} else {
			System.out.println("Credential do not match !!! ");
		}
	}
}
