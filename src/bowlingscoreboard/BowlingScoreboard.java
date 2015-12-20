package bowlingscoreboard;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Kinsley
 */
public class BowlingScoreboard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ScoreBoard  s = new ScoreBoard();
        InputStream stream = new ByteArrayInputStream(String.join(" ", args).getBytes());
        InputStream temp = System.in;
        
        createFrame(s, stream);
        s.printBoard(); 
        createFrame(s, temp);
        
    }
    
    private static void createFrame(ScoreBoard s, InputStream stream) {
        Scanner input = new Scanner(stream);
        while(input.hasNextInt() && s.getBoardFramesSize() < 10) {
            int firstRoll = 0;
            int secondRoll = 0;
            Frame frame = null;
            
            firstRoll = input.nextInt();
            if(firstRoll != 10 || s.getBoardFramesSize() == 9)
                secondRoll = input.nextInt();
         
            if(s.getBoardFramesSize() < 9) {
                frame = new Frame(firstRoll, secondRoll);
            } else if (s.getBoardFramesSize()  == 9){
                int thirdRoll = input.nextInt();
                frame = new Frame(firstRoll, secondRoll, thirdRoll);
                
            } 
            s.addNewFrame(frame);    
        }
    }
    

}
