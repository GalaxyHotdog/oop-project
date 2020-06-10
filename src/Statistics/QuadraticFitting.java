package Statistics;

import static java.lang.Math.*;
import static Statistics.MathCalc.*;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-8
 * @描述 二次拟合实现类
 */
public class QuadraticFitting extends BivariateStatistics
{
    @Override
    void regressionCalculation()
    {
        double a, b, c, r;
        double[][] matrixA = {
                {getNum(X), getSum(X), getSum(X, 2)},
                {getSum(X), getSum(X, 2), getSum(X, 3)},
                {getSum(X, 2), getSum(X, 3), getSum(X, 4)}
        };
        double[][] matrixB = {
                {getSum(Y)},
                {getSumProduct(X, Y)},
                {getSumProduct(X, 2, Y)}
        };
        double[][] ans = Mat_mul(invert(matrixA), matrixB);
        a = ans[2][0];
        b = ans[1][0];
        c = ans[0][0];
        r = (getSum(Y, 2) - getSumOfSqrRes(a, b, c)) / getSum(Y, 2);

        System.out.println("y = Ax^2 + Bx + C:");
        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("C = " + c);
        System.out.println("R^2 = " + r);
    }

    /**
     * getSumOfSqrRes 方法，用于获取残差平方和
     *
     * @param a 二次项系数
     * @param b 一次项系数
     * @param c 常数项
     * @return 残差平方和
     */
    public double getSumOfSqrRes(double a, double b, double c)
    {
        double sum = 0.0;
        for (int i = 1; i <= dataListX.size(); i++)
            sum += pow((dataListY.get(i) - a * pow(dataListX.get(i), 2) - b * dataListX.get(i) - c), 2);
        return sum;
    }

    //  *************************************************************************************
    //  以下代码为 default 包中 MatrixCalculator 类的代码
    //  由于不能进行引用因此直接复制
    //  版权所有 @HengJie
    public double[][] Mat_mul(double[][] A, double[][] B)
    {
        double[][] ans = new double[A.length][B[1].length];
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < B[1].length; j++)
            {
                ans[i][j] = 0;
                for (int k = 0; k < A[1].length; k++)
                {
                    ans[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return ans;
    }

    public double[][] invert(double[][] a)
    {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        gaussian(a, index);

        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        for (int i = 0; i < n; ++i)
        {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k)
                {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public void gaussian(double[][] a, int[] index)
    {
        int n = index.length;
        double[] c = new double[n];

        for (int i = 0; i < n; ++i)
            index[i] = i;

        for (int i = 0; i < n; ++i)
        {
            double c1 = 0;
            for (int j = 0; j < n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j = 0; j < n - 1; ++j)
        {
            double pi1 = 0;
            for (int i = j; i < n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i)
            {
                double pj = a[index[i]][j] / a[index[j]][j];

                a[index[i]][j] = pj;

                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

}
