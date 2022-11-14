package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String sequence;

    public ReversedSequence(String sequence) {
        this.sequence = new StringBuilder(sequence).reverse().toString();
    }

    @Override
    public int length() {
        return this.sequence.length();
    }

    @Override
    public char charAt(int i) {
        if (i >= this.length()) {
            throw new RuntimeException("Character does not exist");
        }
        return this.sequence.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.sequence.subSequence(i, i1);
    }

    public String toString() {
        return this.sequence;
    }
}

// END
