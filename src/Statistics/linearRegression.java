package Statistics;

import static Statistics.MathCalc.*;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-7
 * @描述 一元线性回归类，可以实现线性回归计算
 */
public class linearRegression extends BivariateStatistics
{
    @Override
    void regressionCalculation()
    {
        double a =
                (getNum(X) * getSumProduct(X, Y) - getSum(X) * getSum(Y)) / (getNum(X) * getSum(X, 2) - Math.pow(getSum(X), 2));
        double b = getAverage(Y) - a * getAverage(X);
        double numerator = 0.0; //  相关系数中的分子
        for (int i = 0; i < X.size(); i++)
            numerator += (X.get(i) - getAverage(X)) * (Y.get(i) - getAverage(Y));
        double r = numerator / Math.sqrt(getStdDevSum(X, 2) * getStdDevSum(Y, 2));

        System.out.println("Y = Ax + B:");
        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("r = " + r);
    }
}
