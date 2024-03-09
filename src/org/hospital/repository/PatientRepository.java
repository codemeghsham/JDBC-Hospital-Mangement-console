package org.hospital.repository;
import java.sql.SQLException;
import java.util.*;
import org.hospital.model.PatientModel;

public class PatientRepository extends DBConfig {
	List<PatientModel> listPatient = new ArrayList<PatientModel>();
    PatientModel patientModel = new PatientModel();

    public boolean addPatient(PatientModel patientModel) {
        try {
            stmt = conn.prepareStatement("INSERT INTO Patient  VALUES ('0',?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, patientModel.getPtName());
            stmt.setInt(2, patientModel.getPtAge());
            stmt.setString(3, patientModel.getPtGender());
            stmt.setString(4, patientModel.getPtContact());
            stmt.setString(5, patientModel.getPtAddress());
            stmt.setDate(6, new java.sql.Date(patientModel.getPtOpdDate().getTime()));
            stmt.setInt(7, patientModel.getPtDocID());
            stmt.setInt(8, patientModel.getPtFees());
            stmt.setDate(9, new java.sql.Date(patientModel.getPtAppointmentDate().getTime()));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error adding patient: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    public List<PatientModel> getAllPatients() {
		try {
			stmt = conn.prepareStatement("SELECT * FROM patient");
			rs = stmt.executeQuery();
			while (rs.next()) {
				patientModel.setPtID(rs.getInt(1));
				patientModel.setPtName(rs.getNString(2));
				patientModel.setPtAge(rs.getInt(3));
				patientModel.setPtGender(rs.getString(4));
				patientModel.setPtContact(rs.getString(5));
				patientModel.setPtAddress(rs.getString(6));
				patientModel.setPtOpdDate(rs.getDate(7));
				patientModel.setPtDocID(rs.getInt(8));
				patientModel.setPtFees(rs.getInt(9));
				patientModel.setPtAppointmentDate(rs.getDate(10));
				listPatient.add(patientModel);
				}

			return listPatient.size() > 0 ? listPatient : null;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;

		}

	}
   
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }

    public List<PatientModel> searchPatientsByName(String name) {
        List<PatientModel> searchResults = new ArrayList<PatientModel>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM patient WHERE ptName=?");
            stmt.setString(1,name );
            rs = stmt.executeQuery();
            while (rs.next()) {
//                PatientModel patient = new PatientModel();
	            patientModel.setPtName(rs.getString("ptName"));
	            patientModel.setPtID(rs.getInt("ptID"));
	            patientModel.setPtAge(rs.getInt("ptAge"));
	            patientModel.setPtGender(rs.getString("ptGender"));
	            patientModel.setPtContact(rs.getString("ptContact"));
	            patientModel.setPtAddress(rs.getString("ptAddress"));
	            patientModel.setPtOpdDate(rs.getDate("ptOpdDate"));
	            patientModel.setPtDocID(rs.getInt("ptDocId"));
	            patientModel.setPtFees(rs.getInt("ptFess"));
	            patientModel.setPtAppointmentDate(rs.getDate("ptAppointmentDate"));
                searchResults.add(patientModel);
            }
        } catch (SQLException ex) {
            System.out.println("Error searching patients: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return searchResults;
    }
    public boolean deletePatientById(int patientId) {
        try {
            stmt = conn.prepareStatement("DELETE FROM patient WHERE ptID = ?");
            stmt.setInt(1, patientId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting patient: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }
}