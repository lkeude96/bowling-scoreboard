package bowlingscoreboard;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Kinsley
 */
public class ScoreBoard {
    
    private ArrayList<Frame> boardFrames = new ArrayList<Frame>();
    
    public void addNewFrame(Frame newFrame) {
        boardFrames.add(newFrame);
    }
    
    public int getBoardFramesSize() {
        return boardFrames.size();
    }
    public int getCurrentTotalScoreForFrame(int index) {
        int currentScore = 0;
        
        for(int i = 0; i <= index; i++) {
            currentScore += getFrameScoreWithBonus(i);
        }
        
        return currentScore;
    }
    
    public int getFrameScoreWithBonus(int index) {
        Frame currentFrame = boardFrames.get(index);
        
        int currentFrameScore = currentFrame.getFrameScore();
        
        if(index < boardFrames.size() - 1) {
            if (currentFrame.isStrike()) {
                Frame nextFrame = boardFrames.get(index + 1);
                
                if(nextFrame.isStrike()) {
                    currentFrameScore += nextFrame.getFrameScore();
                    
                    if (index + 2 < boardFrames.size())
                        currentFrameScore += boardFrames.get(index + 2).getFirstRoll();
                } else {
                    currentFrameScore += nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
                } 
            } else if(currentFrame.isSpare()) {
                currentFrameScore += boardFrames.get(index + 1).getFirstRoll();
            } 
        }
        
        return currentFrameScore;
    }
    
    public void printFrame(int index) throws IOException {
        System.out.println("+---+---+---+---+------+");
        System.out.printf("|%3s|%3s|%3s|%3s|%6s|%n", "FR", "R1", "R2", "R3", "Score");
            
        for(int i = 0; i < boardFrames.size(); i++) {
            System.out.println("+---+---+---+---+------+");
            System.out.printf("|%3d|%s|%6d|%n", i+1, boardFrames.get(i).toString(), getCurrentTotalScoreForFrame(i));
        }
        System.out.println("+---+---+---+---+------+");
    }
}
