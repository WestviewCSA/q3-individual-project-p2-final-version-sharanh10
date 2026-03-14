import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class FileReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//call the StackBased class to get stack solution
		
		Queue q = getText("easyMap1");
		while(!q.isEmpty()) {
			System.out.println(q.poll());
		}
		System.out.println("");
		String[][] n = getTextCoords("easyMap2");
		for(int i = 0; i<n.length;i++) {
			for(int j = 0; j<n[0].length; j++) {
				System.out.print(n[i][j]);
			}
			System.out.println("");
		}
		System.out.println(queueBased(n));

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
			String levels = scan.next();
			String rows;
			String cols;
			String[][] coords = new String[Integer.parseInt(rows1)*Integer.parseInt(levels)][Integer.parseInt(cols1)];
			
			System.out.println(Integer.parseInt(rows1)*Integer.parseInt(levels));
			
			while(scan.hasNext()) {
				String num = scan.next();
				if (!num.matches("[.$W@|]+")) {
				    // nextVal contains characters other than . $ W @
					System.out.println("Contains an invalid character");
					String[][] coords1 = new String[0][0];
					return coords1;

				}
				rows = scan.next();
				cols = scan.next();
				
				levels = scan.next();
				
				int newRow = Integer.parseInt(levels)*Integer.parseInt(rows1)+ Integer.parseInt(rows);
				//String nextval = scan.next();
				
				if(Integer.parseInt(rows1) <= Integer.parseInt(rows) || Integer.parseInt(cols1)<=Integer.parseInt(cols)) {
					System.out.println("Map specs isn't accurate");
					String[][] coords1 = new String[0][0];
					return coords1;

				}
				coords[newRow][Integer.parseInt(cols)] = num;
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
	
	public static boolean queueBased(String[][] maze) {
		Queue<String> mapVals = new ArrayDeque<>();
		Queue<String> visited = new ArrayDeque<>();
		int row = 1;
		int col = 0;
		//assume that starting position is at 1,0 for now - we can fix later
		String current = row + "," + col;
		mapVals.add(current);
		visited.add(current);
		while(!mapVals.isEmpty()) {
			current = mapVals.poll(); 
			String lastStep = current.split(" ")[current.split(" ").length - 1];
			row = Integer.parseInt(lastStep.split(",")[0]);
			col = Integer.parseInt(lastStep.split(",")[1]);

			visited.add(current);
			//if not already added then add it
			if(!(col-1 < 0)) {
				String newPath = current + " " + row + "," + (col-1);
				visited.add(row + "," + (col-1));
				mapVals.add(newPath);
				if(maze[row][col-1].equals("$")) {
					System.out.println((row) + "," + (col+1));
					return true;
				}
			}
			if(!(col+1 > maze[0].length-1)) {
				String newPath = current + " " + row + "," + (col+1);
				visited.add(row + "," + (col+1));
				mapVals.add(newPath);
				if(maze[row][col+1].equals("$")) {
					System.out.println((row) + "," + (col+1));
					return true;
				}
			}
			if(!(row-1 < 0)) {
				String newPath = current + " " + (row-1) + "," + col;
				visited.add((row-1) + "," + col);
				mapVals.add(newPath);
				if(maze[row-1][col].equals("$")) {
					System.out.println(newPath);
					return true;
				}
			}
			if(!(row+1 > maze.length-1)) {
				String newPath = current + " " + (row+1) + "," + col;
				visited.add((row+1) + "," + col);
				mapVals.add(newPath);
				if(maze[row+1][col].equals("$")) {
					System.out.println(newPath);
					return true;
				}
			}
			//check if any of them is the $ sign
			
			
	
		}
		
		return false;
	}
	//stac based

}

