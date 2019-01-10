package edu.mywork.ds;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class License
{
	String LicenseNo;
	
	License(){
		super();
	}
	License(String licenseno){
		LicenseNo=licenseno;
	}
	public String getLicense(){
		return LicenseNo;
	}

}

class DriverHash {
	
	HashMap<String, Integer> dHash ;
	
	DriverHash(){
		//Java default size is 16
		dHash = new HashMap<String, Integer>();
	}
	DriverHash(int size){
		dHash = new HashMap<String, Integer>(size);
	}
	/*
	 * This function creates an empty hash table that points to null
	 */
		void initializeHash(DriverHash dh) {
			dHash = new HashMap<String, Integer>();
	}
	/*
	 * This function inserts the license number lic to the hash table: if a driver’s 
	 * license number is already present, only the number of violations need to be 
	 * updated else a new entry has to be created.
	 * Initialized Hash
	 * @param dh
	 * @param drivingLicenseNumber
	 */
	void insertHash(DriverHash dh, License drivingLicenseNumber) {
		if(dh.dHash.get(drivingLicenseNumber.getLicense()) == null) {
			dh.dHash.put(drivingLicenseNumber.getLicense(), 1);
		}else {
			dh.dHash.put(drivingLicenseNumber.getLicense(), dh.dHash.get(drivingLicenseNumber.getLicense()).intValue()+ 1);
		}
	}
	/*
	 * This function prints the serious violators by looking through all hash 
	 * table entries and printing the license numbers of the drivers who have 
	 * more than 3 violations onto the file "violators.txt".
	 * This method create violator details in files
	 * @param dh
	 */
	void printViolators(DriverHash dh) {
		List<String> dlNumber = new ArrayList<String>();
		dh.dHash.forEach((k,v)->{
			if(v.intValue() >=3) {
				dlNumber.add(k);
			}
		});
		Path path;
		try {
			path = Paths.get("violators.txt");
			//System.out.println(" voilators file path " + path.toString());
			Files.write(path, dlNumber, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * This function destroys all the entries inside hash table. This is a 
	 * clean up code.This method destroy hash
	 * @param dh
	 */
		void destroyHash(DriverHash dh) {
		dh.dHash.clear();
	}
}