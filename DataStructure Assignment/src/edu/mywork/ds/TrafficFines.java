package edu.mywork.ds;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TrafficFines {

	/**
	 * Read the input file and return file data as list of array string
	 * @return
	 */
	private static List<String[]> getInputFileDataAsList() {
		List<String[]> inputDataList = new ArrayList<String[]>();
		try (Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource("input.txt").toURI())))
		{
		    for (String line : (Iterable<String>) lines::iterator)
		    {
		       String str [] = line.split(",");
		       inputDataList.add(str);
		    }
		} catch (IOException e) {
			System.out.println("IO Exception occured while reading file "+ e.getMessage());
		} catch (URISyntaxException e) {
			System.out.println("URI Syntax Exception occured while reading file "+ e.getMessage());
		}
		return inputDataList;
	}
	
	public static void main(String[] arg) {
		
			System.out.println("Traffic application start running");
			
			System.out.println("Reading application input files..");
			
			List<String[]> inputDataList = getInputFileDataAsList();
			
			DriverHash dHash = new DriverHash();
			PoliceTree policeTree = new PoliceTree();
			policeTree.root = null;
			
			for(String[] str : inputDataList) {
				//System.out.println( str[0] + " "+ str[1]+" "+str[2] );
				License license = new License(str[1]);
				dHash.insertHash(dHash, license);
				policeTree = policeTree.insertByPoliceId(policeTree, Integer.valueOf(str[0].trim()), Integer.valueOf(str[2].trim()));
			}
			dHash.printViolators(dHash);
			policeTree.printPoliceTree(policeTree);
			System.out.println("\n");
		    policeTree.printBonusPolicemen(policeTree);
			System.out.println("Traffic application end running");
	}
	
}
