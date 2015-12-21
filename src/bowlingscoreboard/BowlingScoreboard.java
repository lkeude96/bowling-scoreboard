package bowlingscoreboard;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.IOException;
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
    public static void main(String[] args) throws IOException {
        ScoreBoard s = new ScoreBoard();
        InputStream stream = new ByteArrayInputStream(String.join(" ", args).getBytes());
        InputStream temp = System.in;

        createFrame(s, stream);
        createFrame(s, temp);
    }

    private static void createFrame(ScoreBoard s, InputStream stream) throws IOException {
        try (Scanner input = new Scanner(stream)) {
            while (input.hasNextInt()) {
                boolean valid = true;
                int firstRoll = 0;
                int secondRoll = 0;
                int thirdRoll = 0;

                firstRoll = getValidInput(input, firstRoll, secondRoll);
                if (validate(firstRoll)) {
                    if (firstRoll < Frame.MAX_PINS || s.getBoardFramesSize() - 1 == Frame.MAX_FRAMES) {
                        secondRoll =  getValidInput(input, firstRoll, secondRoll);
                        if(!validate(secondRoll)) {
                            valid = false;
                        }

                    }

                    if (s.getBoardFramesSize() < Frame.MAX_FRAMES - 1 && valid) {
                        s.addNewFrame(new Frame(firstRoll, secondRoll));
                        s.printFrame(s.getBoardFramesSize() - 1);
                        
                    } else if (s.getBoardFramesSize() == Frame.MAX_FRAMES - 1) {
                        if (firstRoll == Frame.MAX_PINS)
                            thirdRoll =  getValidInput(input, firstRoll, secondRoll);
                        
                        s.addNewFrame(new Frame(firstRoll, secondRoll, thirdRoll));
                        s.printFrame(s.getBoardFramesSize() - 1);
                        
                        input.close();
                        stream.close();
                        System.exit(0);
                    }
                }
            }
        }
    }

    private static boolean validate(int roll) {
        return roll >= 0 && roll <= 10;
    }
    
    private static int getValidInput(Scanner input,int firstRoll, int secondRoll) {
        int value = input.nextInt();
        while (!validate(value)) {
            System.out.println("bad");
            value = input.nextInt();
        }
        return value;
    }
}
