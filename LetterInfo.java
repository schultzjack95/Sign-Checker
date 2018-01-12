package signcheck

public class LetterInfo {
    int letter_count;
    float width_cm;

    public LetterInfo() {
        letter_count = 0;
        width_cm = 1;
    }

    public LetterInfo(int cnt, float cm) {
        letter_count = cnt;
        width_cm = cm;
    }
    
    public int getCount() {
        return count;
    }

    public float getWidthInCm() {
        return width_cm;
    }
}
