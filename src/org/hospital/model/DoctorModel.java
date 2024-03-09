package org.hospital.model;

public class DoctorModel {
	private int docID;
	private String docName;
	private String docSpeciality;
	private String docDesignation;

	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocSpeciality() {
		return docSpeciality;
	}

	public void setDocSpeciality(String docSpeciality) {
		this.docSpeciality = docSpeciality;
	}

	public String getDocDesignation() {
		return docDesignation;
	}

	public void setDocDesignation(String docDesignation) {
		this.docDesignation = docDesignation;
	}
}
