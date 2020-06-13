package XieMingxuan.Statistics;

import java.util.ArrayList;
import java.util.List;

import static XieMingxuan.Statistics.MathCalc.*;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-7
 * @描述 一元线性回归类，可以实现线性回归计算
 */
public class LinearRegression extends BivariateStatistics
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

    /**
     * 适应图形界面的 run 方法
     *
     * @param dataList 所有参与运算的数据
     * @param option   表示要进行的计算类型
     *                 option==1 表示计算变量
     *                 option==2 表示回归计算
     * @return 计算值
     */
    public static String run(String dataList, int option)
    {
        String[] list_Strings = dataList.split(" ");
        List<Double> X = new ArrayList<>();
        List<Double> Y = new ArrayList<>();
        for (int i = 0; i < list_Strings.length; )
        {
            X.add(Double.parseDouble(list_Strings[i]));
            i++;
            Y.add(Double.parseDouble(list_Strings[i]));
            i++;
        }
        if (option == 1)
            return "∑(x) = " + getSum(X, 1) + "\n" +
                    "∑(x^2) = " + getSum(X, 2) + "\n" +
                    "AVG(x) = " + getAverage(X) + "\n" +
                    "σ^2(x) = " + getStdDev("p", X) + "\n" +
                    "σ(x) = " + Math.sqrt(getStdDev("p", X)) + "\n" +
                    "s^2(x) = " + getStdDev("s", X) + "\n" +
                    "s(x) = " + Math.sqrt(getStdDev("s", X)) + "\n" +
                    "n = " + getNum(X) + "\n" +
                    "∑(y) = " + getSum(Y, 1) + "\n" +
                    "∑(y^2) = " + getSum(Y, 2) + "\n" +
                    "AVG(y) = " + getAverage(Y) + "\n" +
                    "σ^2(y) = " + getStdDev("p", Y) + "\n" +
                    "σ(y) = " + Math.sqrt(getStdDev("p", Y)) + "\n" +
                    "s^2(y) = " + getStdDev("s", Y) + "\n" +
                    "s(y) = " + Math.sqrt(getStdDev("s", Y)) + "\n" +
                    "min(x) = " + getMin(X) + "\n" +
                    "max(x) = " + getMax(X) + "\n" +
                    "min(y) = " + getMin(Y) + "\n" +
                    "max(y) = " + getMax(Y);
        if (option == 2)
        {
            double a =
                    (getNum(X) * getSumProduct(X, Y) - getSum(X) * getSum(Y)) / (getNum(X) * getSum(X, 2) - Math.pow(getSum(X), 2));
            double b = getAverage(Y) - a * getAverage(X);
            double numerator = 0.0; //  相关系数中的分子
            for (int i = 0; i < X.size(); i++)
                numerator += (X.get(i) - getAverage(X)) * (Y.get(i) - getAverage(Y));
            double r = numerator / Math.sqrt(getStdDevSum(X, 2) * getStdDevSum(Y, 2));

            return "Y = Ax + B:\n" +
                    "A = " + a + "\n" +
                    "B = " + b + "\n" +
                    "r = " + r;
        }
        return null;
    }
}
