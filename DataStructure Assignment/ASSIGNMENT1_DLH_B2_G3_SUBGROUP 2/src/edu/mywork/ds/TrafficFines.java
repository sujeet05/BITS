package edu.mywork.ds;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class TrafficFines {
	
private static final String inputfile = "input.txt";
	/**
	 * Read the input file and return file data as list of array string
	 * @return
	 */
	private static List<String[]> getInputFileDataAsList() {
		List<String[]> inputDataList = new ArrayList<String[]>();
		
		try (Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(inputfile).toURI())))
		{
		    for (String line : (Iterable<String>) lines::iterator)
		    {
		       String str [] = line.split(",");
		       inputDataList.add(str);
		    }
		} catch(NullPointerException e) {
			inputDataList = null;
			System.out.println("IO Exception occured while reading file "+ e.getMessage());
		} 
		catch (IOException e) {
			inputDataList = null;
			e.printStackTrace();
			System.out.println("IO Exception occured while reading file "+ e.getMessage());
		} catch (URISyntaxException e) {
			inputDataList = null;
			System.out.println("URI Syntax Exception occured while reading file "+ e.getMessage());
		}
		return inputDataList;
	}
	
	public static void main(String[] arg) {
		
			List<String[]> inputDataList = getInputFileDataAsList();
			if(inputDataList !=null){

				DriverHash dHash = new DriverHash();
				PoliceTree policeTree = new PoliceTree();
				policeTree.root = null;
			
				for(String[] str : inputDataList) {
				License license = new License(str[1]);
				dHash.insertHash(dHash, license);
				policeTree = policeTree.insertByPoliceId(policeTree, Integer.valueOf(str[0].trim()), Integer.valueOf(str[2].trim()));
				}
			
				/*Function call for violator.txt file generation which will have all 
				 * the violators ,violated >=3* Question :1*/
				
				dHash.printViolators(dHash);
				
				/*Function call for Bonus file generation which will have all 
				 * the police ID eligible for bonus Question :2*/
				
				policeTree.printBonusPolicemen(policeTree);
			}
			return;
	}
	
}
