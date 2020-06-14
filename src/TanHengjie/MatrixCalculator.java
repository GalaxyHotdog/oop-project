package TanHengjie;

import java.util.*;

public class MatrixCalculator {
    double[][] A;
    double[][] B;
    double[][] ans;
    int nA, mA, nB, mB, nans, mans;

    public void MatrixA(String n1) {
        String[] splited = n1.split("\\s+");
        nA = Integer.parseInt(splited[0]);
        mA = Integer.parseInt(splited[1]);
        A = new double[nA][mA];
    }

    public void MatrixB(String n1) {
        String[] splited = n1.split("\\s+");
        nB = Integer.parseInt(splited[0]);
        mB = Integer.parseInt(splited[1]);
        B = new double[nB][mB];
    }

    public void setA(double[][] a) {
        A = a;
    }

    public void setB(double[][] b) {
        B = b;
    }

    public double[][] getA() {
        return A;
    }

    public double[][] getB() {
        return B;
    }

    public String SetValueA(String input) {
        String[] splited = input.split("\\s+");
        if(splited.length != nA*mA)
            return "Error";
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mA; j++) {
                A[i][j] = Double.parseDouble(splited[i + j]);
            }
        }
        return "good";
    }

    public String SetValueB(String input) {
        String[] splited = input.split("\\s+");
        if(splited.length != nB*mB)
            return "Error";
        for (int i = 0; i < nB; i++) {
            for (int j = 0; j < mB; j++) {
                B[i][j] = Double.parseDouble(splited[i + j]);
            }
        }
        return "good";
    }

    public String Mat_mul(double[][] A, double[][] B) {
        if (A[1].length != B.length) {
            System.out.println("Can't be multiply.");
        } else {
            ans = new double[A.length][B[1].length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[1].length; j++) {
                    ans[i][j] = 0;
                    for (int k = 0; k < A[1].length; k++) {
                        ans[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
            return PrintMat(ans);
        }
        return "Invalid.";
    }

    public String Mat_add(double[][] A, double[][] B) {
        if (A.length != B.length || A[1].length != B[1].length) {
            System.out.println("Can't be add.");
        } else {
            ans = new double[A.length][A[1].length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[1].length; j++) {
                    ans[i][j] = A[i][j] + B[i][j];
                }
            }
            return PrintMat(ans);
        }
        return "Invalid.";
    }

    public String Mat_minus(double[][] A, double[][] B) {
        if (A.length != B.length || A[1].length != B[1].length) {
            System.out.println("Can't be minus.");
        } else {
            ans = new double[A.length][A[1].length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[1].length; j++) {
                    ans[i][j] = A[i][j] - B[i][j];
                }
            }
            return PrintMat(ans);
        }
        return "Invalid.";
    }

    public String Mat_tranverse() {
        double[][] des = new double[A[1].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[1].length; j++) {
                des[j][i] = A[i][j];
            }
        }
        this.setA(des);
        return PrintMat(A);
    }


    public String PrintMat(double[][] A) {
        String toPrint = "";
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[1].length; j++) {
                toPrint += (A[i][j] + " ");
            }
            toPrint += "\n";
        }
        return toPrint;
    }

    public String MatMulConst( double target_2) {
        for (int i = 0; i < this.A.length; i++) {
            for (int j = 0; j < this.A[1].length; i++) {
                this.A[i][j] *= target_2;
            }
        }
        return PrintMat(this.A);
    }

    public String MatDivConst(double target_2) {
        for (int i = 0; i < this.A.length; i++) {
            for (int j = 0; j < this.A[1].length; i++) {
                this.A[i][j] /= target_2;
            }
        }
        return PrintMat(this.A);
    }

    public double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        gaussian(a, index);

        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        for (int i = 0; i < n; ++i)
            index[i] = i;

        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                a[index[i]][j] = pj;

                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public double Mat_det(double[][] A) {
        double[][] tmp;
        double det = 0;

        if (A.length == 1) {
            det = A[0][0];
            return (det);
        } else if (A.length == 2) {
            det = ((A[0][0] * A[1][1]) - (A[0][1] * A[1][0]));
            return (det);
        } else {
            for (int i = 0; i < A[1].length; i++) {
                tmp = new double[A.length - 1][A[1].length - 1];
                for (int j = 1; j < A.length; j++) {
                    for (int k = 0; k < A[1].length; k++) {
                        if (k < i) {
                            tmp[j - 1][k] = A[j][k];
                        } else if (k > i) {
                            tmp[j - 1][k - 1] = A[j][k];
                        }
                    }
                }
                det += A[0][i] * Math.pow(-1, (double) i) * Mat_det(tmp);
            }
        }
        return (det);
    }
}