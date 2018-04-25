package dk.aau.sw2_18_a305.notation;

// "s" for Sharp
public enum PitchClass {
    C(0), Cs(1), D(2), Ds(3), E(4), F(5), Fs(6), G(7), Gs(8), A(9), As(10), B(11);

    public int number;

    PitchClass(int number) {
        this.number = number;
    }

    public PitchClass readPitchClass(int index) {
        switch (index % 12) {
            case 1: return C;
            case 2: return Cs;
            case 3: return D;
            case 4: return Ds;
            case 5: return E;
            case 6: return F;
            case 7: return Fs;
            case 8: return G;
            case 9: return Gs;
            case 10: return A;
            case 11: return As;
            case 0: return B;
            default: return C;
        }
    }
}
