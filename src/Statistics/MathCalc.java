package Statistics;

import java.util.List;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-5
 * @描述 各种统计功能的实现类
 */
public class MathCalc
{
    /**
     * getSum 方法，用于求一个 Number类型的数组中所有元素的 n 次方和
     *
     * @param times 次数
     * @param list  要求和数组
     * @return 和
     */
    public static double getSum(int times, List<? extends Number> list)
    {
        double sum = 0.0;
        for (Number x : list)
            sum += Math.pow((double) x, times);
        return sum;
    }

    /**
     * getSumProduct 方法，用于获取两个列表中的对应元素乘积的和
     *
     * @param list1 目标列表 1
     * @param list2 目标列表 2
     * @return 和
     */
    public static double getSumProduct(List<Double> list1, List<Double> list2)
    {
        double sum = 0.0;
        for (int i = 0; i < list1.size(); i++)
            sum += (list1.get(i) * list2.get(i));
        return sum;
    }

    /**
     * getNum 方法，返回一个列表的元素个数
     *
     * @param list 所求列表
     * @return 列表元素个数
     */
    public static int getNum(List<? extends Number> list)
    {
        return list.size();
    }

    /**
     * getAverage 函数，用于求一个 Number 类型的数组的平均数
     *
     * @param list 目标数组
     * @return 平均数
     */
    public static double getAverage(List<? extends Number> list)
    {
        return getSum(1, list) / getNum(list);
    }

    /**
     * getStdDevSum 方法，用于求标准化 n 阶矩的分子
     *
     * @param times 阶数
     * @param list  目标列表
     * @return n 阶矩分子
     */
    public static double getStdDevSum(int times, List<? extends Number> list)
    {
        double sum = 0.0;
        double average = getAverage(list);
        for (Number number : list)
            sum += Math.pow(sum - average, times);
        return sum;
    }

    /**
     * getStdDev 方法，用于获取总体/样本方差
     *
     * @param option "p"表示总体方差，"s"表示总体标准差
     * @param list   目标列表
     * @return 方差
     */
    public static double getStdDev(String option, List<? extends Number> list)
    {
        double stdDevSum = getStdDevSum(2, list);
        if (option.equals("p"))
            return stdDevSum / getNum(list);
        if (option.equals("s"))
            return stdDevSum / (getNum(list) - 1);
        return 0.0;
    }

    /**
     * getMin 方法，用于获取有序列表的最小值
     *
     * @param list 目标列表
     * @return 最小值
     */
    public static double getMin(List<Double> list)
    {
        return list.get(0);
    }

    /**
     * getMax 方法，用于获取有序列表的最大值
     *
     * @param list 目标列表
     * @return 最大值
     */
    public static double getMax(List<Double> list)
    {
        return list.get(getNum(list) - 1);
    }

    /**
     * getMid 方法， 用于获取有序列表的中位数
     *
     * @param list 目标列表
     * @return 中位数
     */
    public static double getMid(List<Double> list)
    {
        int size = getNum(list);
        if (size % 2 == 0)
            return (list.get(size / 2 - 1) + list.get(size / 2)) / 2;
        else
            return list.get((size - 1) / 2);
    }
}
