import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Optimal<T> {
	//same as queue except using priority queue and score method 
    int dollarRow;
    int dollarCol;
    int dollarLevel;

    public String getMaze(String[][][] maze) {
       //same as queue based but using a priority queue instead
        for (int l = 0; l < maze.length; l++)
            for (int r = 0; r < maze[l].length; r++)
                for (int c = 0; c < maze[l][r].length; c++)
                    if (maze[l][r][c].equals("$")) { 
                    	dollarRow = r; 
                    	dollarCol = c; 
                    	dollarLevel = l; 
                    }

        // priority queue - shorter path + closer to $ goes first
        //internally compares between each value	
        PriorityQueue<String> mapVals = new PriorityQueue<>((first, second) -> score(first) - score(second));
        Queue<String> visited = new ArrayDeque<>();

        int row = 0, col = 0;
        for (int r = 0; r < maze[0].length; r++)
            for (int c = 0; c < maze[0][0].length; c++)
                if (maze[0][r][c].equals("W")) { 
                	row = r; 
                	col = c; 
                	break;
                }

        String current = row + "," + col + "," + 0;
        mapVals.add(current);
        visited.add(current);

        while (!mapVals.isEmpty()) {
            current = mapVals.poll();
            String[] steps = current.split(" ");
            String lastStep = steps[steps.length - 1];
            String[] parts = lastStep.split(",");
            row = Integer.parseInt(parts[0]);
            col = Integer.parseInt(parts[1]);
            int level = Integer.parseInt(parts[2]);

            if (!(row-1 < 0) && !visited.contains((row-1)+","+col+","+level) && !maze[level][row-1][col].equals("@")) {
                String newOption = (row-1)+","+col+","+level;
                String newPath = current + " " + newOption;
                if (maze[level][row-1][col].equals("$")) 
                	return newPath;
                if (maze[level][row-1][col].equals("|")) { 
                	newOption = moveLevels(maze, level+1); newPath = current + " " + newOption; 
                }
                visited.add(newOption);
                mapVals.add(newPath);
            }
            if (!(row+1 > maze[0].length-1) && !visited.contains((row+1)+","+col+","+level) && !maze[level][row+1][col].equals("@")) {
                String newOption = (row+1)+","+col+","+level;
                String newPath = current + " " + newOption;
                if (maze[level][row+1][col].equals("$")) 
                	return newPath;
                if (maze[level][row+1][col].equals("|")) { 
                	newOption = moveLevels(maze, level+1); newPath = current + " " + newOption; 
                }
                visited.add(newOption);
                mapVals.add(newPath);
            }
            if (!(col+1 > maze[0][0].length-1) && !visited.contains(row+","+(col+1)+","+level) && !maze[level][row][col+1].equals("@")) {
                String newOption = row+","+(col+1)+","+level;
                String newPath = current + " " + newOption;
                if (maze[level][row][col+1].equals("$")) 
                	return newPath;
                if (maze[level][row][col+1].equals("|")) { 
                	newOption = moveLevels(maze, level+1); newPath = current + " " + newOption; 
                }
                visited.add(newOption);
                mapVals.add(newPath);
            }
            if (!(col-1 < 0) && !visited.contains(row+","+(col-1)+","+level) && !maze[level][row][col-1].equals("@")) {
                String newOption = row+","+(col-1)+","+level;
                String newPath = current + " " + newOption;
                if (maze[level][row][col-1].equals("$")) {
                	return newPath;
                }
                if (maze[level][row][col-1].equals("|")) { 
                	newOption = moveLevels(maze, level+1); newPath = current + " " + newOption; 
                }
                visited.add(newOption);
                mapVals.add(newPath);
            }
        }
        return "The Wolverine Store is closed.";
    }


    private int score(String path) {
        String[] steps = path.split(" ");
        String[] parts = steps[steps.length - 1].split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        int g = steps.length;
        int level = Integer.parseInt(parts[2]);
        int stepsTaken = steps.length;
        int h = Math.abs(row - dollarRow) + Math.abs(col - dollarCol) + Math.abs(level - dollarLevel);
        return g + h;
    }

    private String moveLevels(String[][][] maze, int nextLevel) {
        for (int r = 0; r < maze[nextLevel].length; r++)
            for (int c = 0; c < maze[nextLevel][0].length; c++)
                if (maze[nextLevel][r][c].equals("W"))
                    return r + "," + c + "," + nextLevel;
        return null;
    }
}
