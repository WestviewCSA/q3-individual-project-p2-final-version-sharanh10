import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class StackBased<T> {
	private Stack<T> myStack;
	int row;
	int col;
	String current;
	public StackBased() {
		myStack= new Stack<>();
	}
	public void getMaze(String[][] maze) {
		/*Queue based approach: 
	Same as stack based, but now each pop will be the last added element
	We will essentially be completing one full path and checking for the $
		 */
		Stack<String> valsMap = new Stack<>();
		Stack<String> visited = new Stack<>();
		int row = 0;
		int col = 0;
		String current = row + "," + col;
		valsMap.add(current);
		while(!valsMap.isEmpty()) {
			row = Integer.parseInt(current.split(",")[0]);
			col = Integer.parseInt(current.split(",")[1]);

			visited.add(current);
			//if not already added then add it
			if(!(col-1 < 0)) {
				valsMap.add(row + "," + (col-1));
				if(maze[row][col-1].equals("$")) {
					System.out.println((row) + "," + (col-1));

				}
			}
			if (row-1 >= 0) {
	        }
			col++;
		}
		
	}
	
	public static String[][][] fillMaze(String[][][] maze, String path) {
		String[][][] newMaze = maze;
		String[] steps = path.split(" ");
		for(int i = 0; i<steps.length; i++) {
			String[] curr = steps[i].split(",");
			newMaze[Integer.parseInt(curr[0])][Integer.parseInt(curr[0])][Integer.parseInt(curr[0])] = "+";
		}
		return newMaze;
      
        
	}
	
}

