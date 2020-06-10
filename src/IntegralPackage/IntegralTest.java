package IntegralPackage;

import java.util.Scanner;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-3
 * @描述 积分测试类，用于测试积分的所有功能
 */
public class IntegralTest
{
    public static void main(String[] args)
    {
        while (true)
        {
            try
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入要进行的操作：" +
                        "1.计算定积分\n" +
                        "2.查看不定积分表\n" +
                        "q.结束程序");
                int option = scanner.nextInt();
                if (option == 1)
                    DefiniteIntegral.run();
                else if (option == 2)
                    IndefiniteIntegral.run();
                else return;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
