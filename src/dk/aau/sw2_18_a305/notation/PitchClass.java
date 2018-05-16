package dk.aau.sw2_18_a305.notation;

// "s" for Sharp
public enum PitchClass {
    C(0), Cs(1), D(2), Ds(3), E(4), F(5), Fs(6), G(7), Gs(8), A(9), As(10), B(11);

    public int number;

    PitchClass(int number) {
        this.number = number;
    }

    // We handle "invalid" index'es by % 12 in order to allow
    // the users of this enum to be able to fill in as high index as
    // they want, since it will just wrap around
    public static PitchClass readPitchClass(int index) {
        int i;

        // Check for invalid index
        if (index < 0 || index > 11){
            i = index % 12;
        } else i = index;

        switch (i) {
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
