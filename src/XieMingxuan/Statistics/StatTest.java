package XieMingxuan.Statistics;

import java.util.Scanner;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-9
 * @描述 统计功能的测试类
 */
public class StatTest
{
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("输入对应操作的序号进行该操作：\n" +
                    "1.单变量统计\n" +
                    "2.一元线性回归分析\n" +
                    "3.一元二次回归分析\n" +
                    "q.退出");
            String option = scanner.next();
            if (option.equals("q")) return;
            if (option.equals("1")) new UnivariateStatistics().run();
            if (option.equals("2")) new LinearRegression().run();
            if (option.equals("3")) new QuadraticFitting().run();
        }
    }
}
