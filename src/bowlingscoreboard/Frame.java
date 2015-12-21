package bowlingscoreboard;

/**
 *
 * @author Kinsley
 */
public class Frame {
    public static final int MAX_PINS = 10;
    public static final int MAX_FRAMES = 10;
    
    private int firstRoll;
    private int secondRoll;
    private int thirdRoll;
    
    public Frame(int firstRoll, int secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
        this.thirdRoll = 0;
    }
    
    public Frame(int firstRoll, int secondRoll, int thirdRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
        this.thirdRoll = thirdRoll;
    }
    
    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }
    
    public int getThirdRoll() {
        return thirdRoll;
    }

    public int getFrameScore() {
        int frameScore = (isStrike() || isSpare()) ? 10 : (firstRoll + secondRoll);
        int frameTenScore = firstRoll + secondRoll + thirdRoll;
        return (thirdRoll > 0) ? frameTenScore : frameScore;
    }
    
    public boolean isStrike() {
        return (firstRoll == 10);
    }
    
    public boolean isSpare() {
        return (firstRoll + secondRoll == 10) && (firstRoll != 0);
    }
    
    @Override
    public String toString() {
        String roll1 = (firstRoll == 0) ? "" : (firstRoll + "");
        String roll2 = (secondRoll == 0) ? "" : (secondRoll + "");
        String roll3 = (thirdRoll == 0) ? "" : (thirdRoll + "");
        
        if(isStrike()) {
            roll1 = "X";
        } else if(isSpare()) {
            roll2 = "/";
        }
        
        return String.format("%3s|%3s|%3s", roll1, roll2, roll3);
    }
}
