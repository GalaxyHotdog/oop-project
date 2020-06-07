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
        double a = (getNum(listX) * getSumProduct(listX, listY) - getSum(1, listX) * getSum(1, listY)) / (getNum(listX) * getSum(2, listX) - Math.pow(getSum(1, listX), 2));
        double b = (getSum(1, listY) - getNum(listX) * getSum(1, listX)) / getNum(listX);
        double numerator = 0.0; //  相关系数中的分子
        for (int i = 0; i < listX.size(); i++)
            numerator += (listX.get(i) - getAverage(listX)) * (listY.get(i) - getAverage(listY));
        double r = numerator / Math.sqrt(getStdDevSum(2, listX) * getStdDevSum(2, listY));

        System.out.println("Ax + B:");
        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("r = " + r);
    }
}
