package lab.three.structures;

public class LUData {
    private final ProfileMatrix matrix;
    private final double[] b;
    private final double[] x;
    private final int n;

    public LUData(ProfileMatrix matrix, double[] b, double[] x, int n) {
        this.matrix = matrix;
        this.b = b;
        this.x = x;
        this.n = n;
    }

    public ProfileMatrix getMatrix() {
        return matrix;
    }

    public double[] getB() {
        return b;
    }

    public double[] getX() {
        return x;
    }

    public int getN() {
        return n;
    }
}
