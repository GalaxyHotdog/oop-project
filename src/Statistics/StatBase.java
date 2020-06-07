package Statistics;

import java.util.*;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-3
 * @描述 是所有统计类的父类
 */
class DataAndFreq
{
    double data;
    int frequency;

    public DataAndFreq(double data, int frequency)
    {
        this.data = data;
        this.frequency = frequency;
    }
}

public abstract class StatBase
{
    protected static boolean isFreqOpen = false;                    // 标志频率开关开启或关闭
    protected Map<Integer, Double> dataListX = new HashMap<>();     // 不包含频率的数据集合 x
    protected Map<Integer, Double> dataListY = new HashMap<>();     // 不包含频率的数据集合 y
    protected int sizeOfDataList = dataListX.size();                // 不包含频率的数据集 x 的元素个数
    protected Map<Integer, DataAndFreq> data = new HashMap<>();     // 包含频率的数据集合
    protected int sizeOfData = data.size();                         // 包含频率的数据集的元素个数

    /**
     * changeFreqState 方法，用于修改频率开关
     */
    public static void changeFreqState()
    {
        isFreqOpen = !isFreqOpen;
    }

    /**
     * showFreqState 方法，用于显示频率开关状态
     */
    public static void showFreqState()
    {
        if (isFreqOpen) System.out.println("频率开关已打开");
        else System.out.println("频率开关已关闭");
    }

    /**
     * getData 方法，用于从键盘中获取数据，存入数据存储列表
     *
     * @throws Exception 输入错误抛出异常
     */
    abstract void getData() throws Exception;

    /**
     * showData 方法，用于打印所有数据信息
     */
    private void showData()
    {
        if (this instanceof UnivariateStatistics)
        {
            if (isFreqOpen) System.out.println(data.toString());
            else System.out.println(dataListX.toString());
        }
        else
            for (int i = 1; i < sizeOfDataList; i++)
                System.out.println(i + ". X:" + dataListX.get(i) + " Y:" + dataListY.get(i));
    }

    /**
     * changeData 方法，用于实现修改某个数据的功能
     */
    private void changeData()
    {
        Scanner scanner = new Scanner(System.in);
        int key;
        while (true)
        {
            System.out.print("请输入要修改的数据编号，输入 q 结束");
            String option = scanner.next();
            if (option.equals("q"))
                return;
            key = Integer.parseInt(option);
            if (this instanceof UnivariateStatistics)
            {
                if (isFreqOpen)
                {
                    if (key > sizeOfData)
                    {
                        System.out.println("找不到该数据");
                        continue;
                    }
                    System.out.print("请输入修改后的数值：");
                    double x = scanner.nextDouble();
                    System.out.print("请输入修改后的频率：");
                    int freq = scanner.nextInt();
                    DataAndFreq data = new DataAndFreq(x, freq);
                    this.data.replace(key, data);
                }
                else
                {
                    if (key > sizeOfDataList)
                    {
                        System.out.println("找不到该数据");
                        continue;
                    }
                    System.out.print("请输入修改后的数值：");
                    double x = scanner.nextDouble();
                    dataListX.replace(key, x);
                }
                System.out.println("修改成功");
            }
            if (this instanceof BivariateStatistics)
            {
                if (key > sizeOfDataList)
                {
                    System.out.println("找不到该数据");
                    continue;
                }
                System.out.print("请输入修改后的 X 值：");
                double x = scanner.nextDouble();
                System.out.print("请输入修改后的 Y 值：");
                double y = scanner.nextDouble();
                dataListX.replace(key, x);
                dataListX.replace(key, y);
                System.out.println("修改成功");
            }
        }
    }

    /**
     * dataToCalcArea 方法，用于将数据从集合中转移到计算集合中
     */
    abstract void dataToCalcArea();

    /**
     * calc 方法，用于实现统计中所有的计算方法
     */
    abstract void calc();

    /**
     * run 方法，用于实现统计功能
     *
     * @throws Exception 可能抛出获取数据的输入异常
     */
    public void run() throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("请输入要进行的操作：\n" +
                    "1.输入数据\n" +
                    "2.查看数据\n" +
                    "3.修改数据\n" +
                    "4.计算对应值\n" +
                    "5.打开/关闭频率开关\n" +
                    "q.退出");
            String index = scanner.next();
            switch (index)
            {
                case "q":
                    return;
                case "1":
                    getData();
                    break;
                case "2":
                    showData();
                    break;
                case "3":
                    changeData();
                    break;
                case "4":
                    calc();
                    break;
                case "5":
                    if (isFreqOpen)
                        System.out.println("已关闭频率开关");
                    else
                        System.out.println("已打开频率开关");
                    changeFreqState();
                    break;
                default:
            }
        }
    }
}
