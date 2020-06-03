import java.util.*;

public class MatrixCalculator {
    double[][] A;
    double[][] B;
    double[][] ans;
    int nA, mA, nB, mB, nans, mans;

    public void MatrixA(int n1, int m1) {
        nA = n1;
        mA = m1;
        A = new double[nA][mA];
    }

    public void MatrixB(int n1, int m1) {
        nB = n1;
        mB = m1;
        B = new double[nB][mB];
    }

    public void setA(double[][] a) {
        A = a;
    }

    public void setB(double[][] b) {
        B = b;
    }

    public void GetValue(char o) {
        Scanner scn = new Scanner(System.in);
        if (o == 'A') {
            for (int i = 0; i < nA; i++) {
                for (int j = 0; j < mA; j++) {
                    A[i][j] = Double.parseDouble(scn.next());
                }
            }
        } else if (o == 'B') {
            for (int i = 0; i < nB; i++) {
                for (int j = 0; j < mB; j++) {
                    B[i][j] = Double.parseDouble(scn.next());
                }
            }
        }
    }

    public void Mat_mul(double[][] A, double[][] B) {
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
        }
        PrintMat(ans);
    }

    public void Mat_add(double[][] A, double[][] B) {
        if (A.length != B.length || A[1].length != B[1].length) {
            System.out.println("Can't be add.");
        } else {
            ans = new double[A.length][A[1].length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[1].length; j++) {
                    ans[i][j] = A[i][j] + B[i][j];
                }
            }
            PrintMat(ans);
        }
    }

    public void Mat_minus(double[][] A, double[][] B) {
        if (A.length != B.length || A[1].length != B[1].length) {
            System.out.println("Can't be add.");
        } else {
            ans = new double[A.length][A[1].length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[1].length; j++) {
                    ans[i][j] = A[i][j] - B[i][j];
                }
            }
            PrintMat(ans);
        }
    }

    public void Mat_tranverse(char mat, double[][] target) {
        double[][] des = new double[target[1].length][target.length];
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target[1].length; j++) {
                des[j][i] = target[i][j];
            }
        }
        if (mat == 'A') {
            this.setA(des);
        } else if (mat == 'B') {
            this.setB(des);
        } else {
            System.out.println("Not such matrix found.");
        }
    }

    public void PrintMat(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[1].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void MatMulConst(char target, double target_2) {
        if (target == 'A') {
            for (int i = 0; i < this.A.length; i++) {
                for (int j = 0; j < this.A[1].length; i++) {
                    this.A[i][j] *= target_2;
                }
            }
        }
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

    public static void main(String[] args) {
        MatrixCalculator m = new MatrixCalculator();
        m.MatrixA(3, 3);
        m.MatrixB(4, 4);
        m.GetValue('A');
        //m.GetValue('B');
        System.out.println(m.Mat_det(m.A));
    }
}
