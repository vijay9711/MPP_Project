package business;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private checkoutRecord[] checkoutRecord;
//	private
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		this.checkoutRecord = new checkoutRecord[0];
	}
	
	public String getMemberId() {
		return memberId;
	}

	
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	public checkoutRecord[] getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(checkoutRecord checkRrd) {
		if(checkoutRecord == null){
			this.checkoutRecord = new checkoutRecord[0];
		}
		System.out.println(checkoutRecord.length + " checkoutRecord len ");
		checkoutRecord[] newRecordArr = new checkoutRecord[checkoutRecord.length + 1];
		System.arraycopy(checkoutRecord, 0, newRecordArr, 0, checkoutRecord.length);
		newRecordArr[checkoutRecord.length] = checkRrd;
		checkoutRecord = newRecordArr;
	}
	
	private static final long serialVersionUID = -2226197306790714013L;
}
