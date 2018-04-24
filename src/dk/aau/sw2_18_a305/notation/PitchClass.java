package dk.aau.sw2_18_a305.notation;

// "s" for Sharp
public enum PitchClass {
    C(0), Cs(1), D(2), Ds(3), E(4), F(5), Fs(6), G(7), Gs(8), A(9), As(10), B(11);

    public int number;

    PitchClass(int number) {
        this.number = number;
    }
}
