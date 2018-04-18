package dk.aau.sw2_18_a305.notation;

// "s" for Sharp
public enum PitchClass {
    C(1), Cs(2), D(3), Ds(4), E(5), F(6), Fs(7), G(8), Gs(9), A(10), As(11), B(12);

    public int number;

    PitchClass(int number) {
        this.number = number;
    }
}
