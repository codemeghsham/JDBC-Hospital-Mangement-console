package org.hospital.model;

import java.util.Date;

public class PatientModel {
    private int ptID;
    private String ptName;
    private int ptAge;
    private String ptGender;
    private String ptContact;
    private String ptAddress;
    private Date ptOpdDate;
    private int ptDocID;
    private int ptFees;
    private Date ptAppointmentDate;

	public int getPtID() {
		return ptID;
	}

	public void setPtID(int ptID) {
		this.ptID = ptID;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public int getPtAge() {
		return ptAge;
	}

	public void setPtAge(int ptAge) {
		this.ptAge = ptAge;
	}

	public String getPtGender() {
		return ptGender;
	}

	public void setPtGender(String ptGender) {
		this.ptGender = ptGender;
	}

	public String getPtContact() {
		return ptContact;
	}

	public void setPtContact(String ptContact) {
		this.ptContact = ptContact;
	}

	public String getPtAddress() {
		return ptAddress;
	}

	public void setPtAddress(String ptAddress) {
		this.ptAddress = ptAddress;
	}

	public Date getPtOpdDate() {
		return ptOpdDate;
	}

	public void setPtOpdDate(Date ptOpdDate) {
		this.ptOpdDate = ptOpdDate;
	}

	public int getPtDocID() {
		return ptDocID;
	}

	public void setPtDocID(int ptDocID) {
		this.ptDocID = ptDocID;
	}

	public int getPtFees() {
		return ptFees;
	}

	public void setPtFees(int ptFees) {
		this.ptFees = ptFees;
	}

	public Date getPtAppointmentDate() {
		return ptAppointmentDate;
	}

	public void setPtAppointmentDate(Date ptAppointmentDate) {
		this.ptAppointmentDate = ptAppointmentDate;
	}
}

