package XieMingxuan.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static XieMingxuan.Statistics.MathCalc.*;
/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-6
 * @描述 多变量统计，实现了 getData 和 dataToCalcArea 方法
 */
public abstract class BivariateStatistics extends StatBase
{
    List<Double> X = new ArrayList<>(); //  x 的计算集合
    List<Double> Y = new ArrayList<>(); //  y 的计算集合

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
            X.add(dataListX.get(i));
            Y.add(dataListY.get(i));
        }
    }

    @Override
    void calc()
    {
        System.out.println("∑(x) = " + getSum(X, 1) + "\n" +
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
                "max(y) = " + getMax(Y));
    }

   // @Override
    public void run()
    {
        {
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                System.out.println("BBBBBBB请输入要进行的操作：\n" +
                        "1.输入数据\n" +
                        "2.查看数据\n" +
                        "3.修改数据\n" +
                        "4.计算变量值\n" +
                        "5.计算回归方程\n" +
                        "q.退出");
                String index = scanner.next();
                switch (index)
                {
                    case "q":
                        return;
                    case "1":
                        getData();
                        dataToCalcArea();
                        break;
                    case "2":
                        showData();
                        break;
                    case "3":
                        changeData();
                        dataToCalcArea();
                        break;
                    case "4":
                        calc();
                        break;
                    case "5":
                        regressionCalculation();
                        break;
                        /*
                    case "6":
                        if (isFreqOpen)
                            System.out.println("已关闭频率开关");
                        else
                            System.out.println("已打开频率开关");
                        changeFreqState();
                        break;
                        */
                    default:
                }
            }
        }
    }

    /**
     * regressionCalculation 方法，用于实现回归计算
     */
    abstract void regressionCalculation();
}

