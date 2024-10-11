package Model;

public class model {
    double num;
    double precision;
    double root;
    double startNumber;

    public double getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(double startNumber) {
        this.startNumber = startNumber;
    }

    public double getRoot() {
        return root;
    }

    public void setRoot(double root) {
        this.root = root;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public static double calcRoot(double num, double precision, double startNum){
        double root = 0;
        if (startNum == 0) { throw new IllegalArgumentException("Dividing by zero");}

        while (Math.abs(Math.pow(startNum, 2) - num) > precision) {
            startNum = 0.5 * (startNum + num / startNum);
        }

        root = startNum;

        return root;
    }
}
