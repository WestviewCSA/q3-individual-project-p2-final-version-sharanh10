import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class FileReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Queue q = getText("easyMap1");
		while(!q.isEmpty()) {
			System.out.println(q.poll());
		}
		System.out.println("");
		String[][] n = getTextCoords("easyMapCoords");
		for(int i = 0; i<n.length;i++) {
			for(int j = 0; j<n[0].length; j++) {
				System.out.print(n[i][j]);
			}
			System.out.println("");
		}

	}
	
	public static Queue<String> getText(String passedFile) {
		Queue<String> mapVals = new ArrayDeque<>();
		File file = new File(passedFile);
		Scanner scan;
		try {
			scan = new Scanner(file);
			String rows = scan.next();
			String cols = scan.next();
			String levels = scan.next();
			if(Integer.parseInt(rows)<=0 || Integer.parseInt(cols) <= 0 || Integer.parseInt(levels) <= 0) {
				System.out.println("Not valid row column combinattion");
				Queue<String> mapVals1 = new ArrayDeque<>();
			}
			while(scan.hasNext()) {
				String nextVal = scan.next();
				
				
				if (!nextVal.matches("[.$W@]+")) {
				    // nextVal contains characters other than . $ W @
					System.out.println("Contains an invalid character");
					Queue<String> mapVals1 = new ArrayDeque<>();
					return mapVals1;

				}
				else {
					mapVals.add(nextVal);
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapVals;

	}
	
	public static String[][] getTextCoords(String passedFile) {
		File file = new File(passedFile);
		
		Scanner scan;
		try {
			scan = new Scanner(file);
			String rows1 = scan.next();
			String cols1 = scan.next();
			String rows;
			String cols;
			String[][] coords = new String[Integer.parseInt(rows1)][Integer.parseInt(cols1)];
			
			
			String levels = scan.next();
			while(scan.hasNext()) {
				String num = scan.next();
				if (!num.matches("[.$W@]+")) {
				    // nextVal contains characters other than . $ W @
					System.out.println("Contains an invalid character");
					String[][] coords1 = new String[0][0];
					return coords1;

				}
				rows = scan.next();
				cols = scan.next();
				scan.next();
				//String nextval = scan.next();
				
				coords[Integer.parseInt(rows)][Integer.parseInt(cols)] = num;
			}
			for(int i = 0; i<coords.length;i++) {
				for(int j = 0; j<coords[0].length; j++) {
					if(coords[i][j]==null) {
						coords[i][j] = ".";
					}
				}
			}
			return coords;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}


}

