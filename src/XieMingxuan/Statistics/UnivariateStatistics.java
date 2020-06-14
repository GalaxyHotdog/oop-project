package XieMingxuan.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-5
 * @描述 单变量统计类
 */
public class UnivariateStatistics extends StatBase
{
    List<Double> calcArea = new ArrayList<>();  //  计算用列表，只存所有数值

    @Override
    void getData() throws Exception
    {
       Scanner scanner = new Scanner(System.in);
       if (isFreqOpen)
       {
           System.out.println("请输入数据和对应的频率，输入 q 以结束：");
           while (true)
           {
               System.out.print(sizeOfData + 1 + ": ");
               try
               {
                   int freq = 1;
                   String index1, index2;
                   index1 = scanner.next();
                   if (index1.equals("q")) return;
                   double data = Double.parseDouble(index1);
                   while (true)
                   {
                       index2 = scanner.next();
                       if (!index2.equals("q"))
                       {
                           if (index2.matches("[-+]?[0-9]*"))
                           {
                               freq = Integer.parseInt(index2);
                               break;
                           }
                           else
                               System.out.print("频率不合法，请重新输入：");
                       }
                       else break;
                   }
                   sizeOfData++;
                   this.data.put(sizeOfData, new DataAndFreq(data, freq));
               }
               catch (Exception e)
               {
                   throw new Exception("输入错误！");
               }
           }
       }
       else
       {
           System.out.println("请输入数据，以 q 结束：");
           while (true)
           {
               System.out.print(sizeOfDataList + 1 + ": ");
               String index = scanner.next();
               if (index.equals("q")) return;
               if (index.matches("[-+]?[0-9](\\.[0-9]+)?"))
               {
                   sizeOfDataList++;
                   dataListX.put(sizeOfDataList, Double.parseDouble(index));
               }
               else System.out.println("输入错误！请重新输入：");
           }
       }
    }

    @Override
    void dataToCalcArea()
    {
       if (isFreqOpen)
       {
           for (int i = 1; i <= sizeOfData; i++)
           {
               DataAndFreq dataAndFreq = data.get(i);
               for (int j = 1; j <= dataAndFreq.frequency; j++)
                   calcArea.add(dataAndFreq.data);
           }
       }
       else
       {
           for (int i = 1; i <= sizeOfDataList; i++)
               calcArea.add(dataListX.get(i));
       }
       calcArea.sort(Double::compareTo);
    }

    @Override
    void calc()
    {
        System.out.println("∑(x) = " + XieMingxuan.Statistics.MathCalc.getSum(calcArea) + "\n" +
                "∑(x^2) = " + XieMingxuan.Statistics.MathCalc.getSum(calcArea, 2) + "\n" +
                "AVG(x) = " + XieMingxuan.Statistics.MathCalc.getAverage(calcArea) + "\n" +
                "σ^2(x) = " + XieMingxuan.Statistics.MathCalc.getStdDev("p", calcArea) + "\n" +
                "σ(x) = " + Math.sqrt(XieMingxuan.Statistics.MathCalc.getStdDev("p", calcArea)) + "\n" +
                "s^2(x) = " + XieMingxuan.Statistics.MathCalc.getStdDev("s", calcArea) + "\n" +
                "s(x) = " + Math.sqrt(XieMingxuan.Statistics.MathCalc.getStdDev("s", calcArea)) + "\n" +
                "n = " + XieMingxuan.Statistics.MathCalc.getNum(calcArea) + "\n" +
                "min(x) = " + XieMingxuan.Statistics.MathCalc.getMin(calcArea) + "\n" +
                "max(x) = " + XieMingxuan.Statistics.MathCalc.getMax(calcArea) + "\n" +
                "mid(x) = " + XieMingxuan.Statistics.MathCalc.getMid(calcArea)
        );
    }

   @Override
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

    /**
     * 适应图形界面的 run 方法
     * @param dataList String 类型的 data 列表
     * @return 计算后的结果
     */
    public static String rundata(String dataList) throws Exception
    {
        String[] list_Strings = dataList.split(" ");
        List<Double> calcArea = new ArrayList<>();
        for (String s : list_Strings)
            calcArea.add(Double.parseDouble(s));
        return "∑(x) = " + XieMingxuan.Statistics.MathCalc.getSum(calcArea) + "\n" +
                "∑(x^2) = " + XieMingxuan.Statistics.MathCalc.getSum(calcArea, 2) + "\n" +
                "AVG(x) = " + XieMingxuan.Statistics.MathCalc.getAverage(calcArea) + "\n" +
                "σ^2(x) = " + XieMingxuan.Statistics.MathCalc.getStdDev("p", calcArea) + "\n" +
                "σ(x) = " + Math.sqrt(XieMingxuan.Statistics.MathCalc.getStdDev("p", calcArea)) + "\n" +
                "s^2(x) = " + XieMingxuan.Statistics.MathCalc.getStdDev("s", calcArea) + "\n" +
                "s(x) = " + Math.sqrt(XieMingxuan.Statistics.MathCalc.getStdDev("s", calcArea)) + "\n" +
                "n = " + XieMingxuan.Statistics.MathCalc.getNum(calcArea) + "\n" +
                "min(x) = " + XieMingxuan.Statistics.MathCalc.getMin(calcArea) + "\n" +
                "max(x) = " + XieMingxuan.Statistics.MathCalc.getMax(calcArea) + "\n" +
                "mid(x) = " + XieMingxuan.Statistics.MathCalc.getMid(calcArea);
    }
}

