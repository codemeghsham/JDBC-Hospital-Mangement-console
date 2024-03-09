package org.hospital.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hospital.model.DoctorModel;

public class DoctorRepository extends DBConfig {
	List<DoctorModel> listDoc = new ArrayList<DoctorModel>();

	// Method to check if a doctor with given name exists
	public boolean isDoctorPresent(String doctorName) {
		try {
			stmt = conn.prepareStatement("SELECT * FROM Doctor WHERE docName = ?");
			stmt.setString(1, doctorName);
			rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException ex) {
			return false;
		}
	}

	// Method to add a new doctor
	public boolean isAddDoctor(DoctorModel model) {
		try {
			stmt = conn.prepareStatement("INSERT INTO doctor VALUES('0',?,?,?)");
			stmt.setString(1, model.getDocName());
			stmt.setString(2, model.getDocSpeciality());
			stmt.setString(3, model.getDocDesignation());
			int value = stmt.executeUpdate();
			return value > 0;
		} catch (SQLException ex) {
			System.out.println("Error adding doctor: " + ex.getMessage());
			return false;
		}
	}

	// Method to retrieve all doctors
	public List<DoctorModel> getAllDoctor() {
		try {
			DoctorModel model = null;
			stmt = conn.prepareStatement("SELECT * FROM Doctor");
			rs = stmt.executeQuery();
			while (rs.next()) {
				model = new DoctorModel();
				model.setDocID(rs.getInt(1));
				model.setDocName(rs.getString(2));
				model.setDocSpeciality(rs.getString(3));
				model.setDocDesignation(rs.getString(4));
				listDoc.add(model);
			}
			return listDoc.size() > 0 ? listDoc : null;
		} catch (SQLException ex) {
			System.out.println("Error retrieving doctors: " + ex.getMessage());
			return null;
		}
	}

	// Method to search for a doctor by name
	public DoctorModel searchDoctor(String doctorName) {
		try {
			stmt = conn.prepareStatement("SELECT * FROM Doctor WHERE docName = ?");
			stmt.setString(1, doctorName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				DoctorModel model = new DoctorModel();
				model.setDocID(rs.getInt("docID"));
				model.setDocName(rs.getString("docName"));
				model.setDocSpeciality(rs.getString("docSpeciality"));
				model.setDocDesignation(rs.getString("docDesignation"));
				return model;
			} else {
				return null; // Doctor not found
			}
		} catch (SQLException ex) {
			System.out.println("Error searching for doctor: " + ex.getMessage());
			return null;
		} finally {
			// Close resources
			closeResources();
		}
	}

	// Method to delete a doctor by ID
	public boolean deleteDoctor(int doctorId) {
		try {
			stmt = conn.prepareStatement("DELETE FROM Doctor WHERE docID = ?");
			stmt.setInt(1, doctorId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException ex) {
			System.out.println("Error deleting doctor: " + ex.getMessage());
			return false;
		} finally {
			// Close resources
			closeResources();
		}
	}

	// Method to close resources
	private void closeResources() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
		} catch (SQLException ex) {
			System.out.println("Error closing resources: " + ex.getMessage());
		}
	}
}
