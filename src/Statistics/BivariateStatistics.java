package Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static Statistics.MathCalc.*;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-6
 * @描述 多变量统计，实现了 getData 和 dataToCalcArea 方法
 */
public abstract class BivariateStatistics extends StatBase
{
    List<Double> listX = new ArrayList<>(); //  x 的计算集合
    List<Double> listY = new ArrayList<>(); //  y 的计算集合

    @Override
    void getData()
    {
        isFreqOpen = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入 X,Y 值,输入 q 以结束：");
        while (true)
        {
            System.out.print(sizeOfDataList + 1 + ": ");
            String index = scanner.next();
            if (index.equals("q")) return;
            double x = Double.parseDouble(index);
            double y = scanner.nextDouble();
            sizeOfDataList++;
            dataListX.put(sizeOfDataList, x);
            dataListY.put(sizeOfDataList, y);
        }
    }

    @Override
    void dataToCalcArea()
    {
        for (int i = 1; i <= sizeOfDataList; i++)
        {
            listX.add(dataListX.get(i));
            listY.add(dataListY.get(i));
        }
    }

    @Override
    void calc()
    {
        System.out.println("∑(x) = " + getSum(1, listX) +
                "∑(x^2) = " + getSum(2, listX) +
                "AVG(x) = " + getAverage(listX) +
                "σ^2(x) = " + getStdDev("p", listX) +
                "σ(x) = " + Math.sqrt(getStdDev("p", listX)) +
                "s^2(x) = " + getStdDev("s", listX) +
                "s(x) = " + Math.sqrt(getStdDev("s", listX)) +
                "n = " + getNum(listX) +
                "∑(y) = " + getSum(1, listY) +
                "∑(y^2) = " + getSum(2, listY) +
                "AVG(y) = " + getAverage(listY) +
                "σ^2(y) = " + getStdDev("p", listY) +
                "σ(y) = " + Math.sqrt(getStdDev("p", listY)) +
                "s^2(y) = " + getStdDev("s", listY) +
                "s(y) = " + Math.sqrt(getStdDev("s", listY)) +
                "min(x) = " + getMin(listX) +
                "max(x) = " + getMax(listX) +
                "min(y) = " + getMin(listY) +
                "max(y) = " + getMax(listY));
    }

    abstract void regressionCalculation();
}
