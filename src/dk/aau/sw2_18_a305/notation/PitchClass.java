package dk.aau.sw2_18_a305.notation;

/**
 * Represents the pitch of a note (C, D, E, F, G, A, B). The 's' represents #.
 */
public enum PitchClass {
    C(0), Cs(1), D(2), Ds(3), E(4), F(5), Fs(6), G(7), Gs(8), A(9), As(10), B(11);

    /**
     * Used to represent the numbering of the pitch class from the default constructor.
     * The number represents a pitch class' placement in relation to each other
     */
    public int number;

    /**
     * Constructs a pitch class with a number assigned
     * @param number The number of the pitch class
     */
    PitchClass(int number) {
        this.number = number;
    }

    /**
     * Used to identify a pitch class, by an index. Switches on index modulo 12,
     * and retrieves the pitch class with that number
     * @param index The index that is used to retrieve a specific pitch class
     * @return A pitch class based on the index provided. For example index = 0 or 13 returns C
     */
    public static PitchClass readPitchClass(int index) {
        switch (index % 12) {
            case 0: return C;
            case 1: return Cs;
            case 2: return D;
            case 3: return Ds;
            case 4: return E;
            case 5: return F;
            case 6: return Fs;
            case 7: return G;
            case 8: return Gs;
            case 9: return A;
            case 10: return As;
            default: return B; // Case 11
        }
    }
}
