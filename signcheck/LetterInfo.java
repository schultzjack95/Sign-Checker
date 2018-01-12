package signcheck;

public class LetterInfo {
    int letter_count;
    float width_cm;

    public LetterInfo() {
        letter_count = 0;
        width_cm = 4;
    }

    public LetterInfo(int count) {
        letter_count = count;
        width_cm = 4;
    }

    public LetterInfo(int count, float cm) {
        letter_count = count;
        width_cm = cm;
    }
    
    public int getCount() {
        return letter_count;
    }

    public float getWidthInCm() {
        return width_cm;
    }
}
