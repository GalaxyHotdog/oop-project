package XieMingxuan.BaseCalc;

import java.util.Scanner;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-12
 * @描述 进制转化类，用于实现进制转化
 */
public class BaseConversion
{
    /**
     * DecToBin 方法，用于实现十进制向二进制转化
     *
     * @param string 十进制字符串
     * @return 二进制字符串
     */
    public static String DecToBin(String string)
    {
        return Integer.toBinaryString(Integer.parseInt(string));
    }

    /**
     * DecToOct 方法，用于实现十进制向八进制转化
     *
     * @param string 十进制字符串
     * @return 八进制字符串
     */
    public static String DecToOct(String string)
    {
        return Integer.toOctalString(Integer.parseInt(string));
    }

    /**
     * DecToDec 方法，用于实现十进制向十进制转化，防止错误发生
     *
     * @param string 十进制字符串
     * @return 十进制字符串
     */
    public static String DecToDec(String string)
    {
        return string;
    }

    /**
     * DecToHex 方法，用于实现十进制向十六进制转化
     *
     * @param string 十进制字符串
     * @return 十六进制字符串
     */
    public static String DecToHex(String string)
    {
        return Integer.toHexString(Integer.parseInt(string));
    }

    /**
     * BinToDec 方法，用于实现二进制向十进制转化
     *
     * @param string 二进制字符串
     * @return 十进制字符串
     */
    public static String BinToDec(String string)
    {
        return Integer.toString(Integer.parseInt(string, 2));
    }

    /**
     * OctToDec 方法，用于实现八进制向十进制转化
     *
     * @param string 八进制字符串
     * @return 十进制字符串
     */
    public static String OctToDec(String string)
    {
        return Integer.toString(Integer.parseInt(string, 8));
    }

    /**
     * HexToDec 方法，用于实现十六进制向十进制转化
     *
     * @param string 十六进制字符串
     * @return 十进制字符串
     */
    public static String HexToDec(String string)
    {
        return Integer.toString(Integer.parseInt(string, 16));
    }

    /**
     * run 方法，用于实现进制转换
     */
    public static void run()
    {
        Scanner scanner = new Scanner(System.in);
        int index1 = 3;
        int index2 = 3;
        while (true)
        {
            System.out.println("请输入要进行的操作，输入 q 以结束：\n" +
                    "1.修改转化类型\n" +
                    "2.转化数据");
            printType(index1, index2);
            String option = scanner.next();
            if (option.equals("q")) return;
            if (option.equals("1"))
            {
                System.out.println("请输入要转化的类型，输入 q 以结束：\n" +
                        "1.二进制\n" +
                        "2.八进制\n" +
                        "3.十进制\n" +
                        "4.十六进制");
                String option1 = scanner.next();
                if (option1.equals("q")) return;
                index1 = Integer.parseInt(option1);

                System.out.println("请输入转化的第二个类型：\n" +
                        "1.二进制\n" +
                        "2.八进制\n" +
                        "3.十进制\n" +
                        "4.十六进制");
                index2 = scanner.nextInt();
                printType(index1, index2);
            }

            System.out.println("请输入要转化的数，输入 q 以退出");
            while (true)
            {
                String target = scanner.next();
                if (target.equals("q")) break;
                if (index1 == 3)
                {
                    switch (index2)
                    {
                        case 1:
                            System.out.println(DecToBin(target));
                            break;
                        case 2:
                            System.out.println(DecToOct(target));
                            break;
                        case 3:
                            System.out.println(DecToDec(target));
                            break;
                        case 4:
                            System.out.println(DecToHex(target));
                    }
                }
                else
                {
                    String temp;
                    switch (index1)
                    {
                        case 1:
                            temp = BinToDec(target);
                            break;
                        case 2:
                            temp = OctToDec(target);
                            break;
                        case 4:
                            temp = HexToDec(target);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + index1);
                    }
                    switch (index2)
                    {
                        case 1:
                            System.out.println(DecToBin(temp));
                            break;
                        case 2:
                            System.out.println(DecToOct(temp));
                            break;
                        case 3:
                            System.out.println(DecToDec(temp));
                            break;
                        case 4:
                            System.out.println(DecToHex(temp));
                    }
                }
            }
        }
    }

    /**
     * printType 方法，用于显示当前从几进制转化为几进制
     *
     * @param index1 被转化
     * @param index2 转化
     */
    private static void printType(int index1, int index2)
    {
        System.out.print("当前为 ");
        switch (index1)
        {
            case 1:
                System.out.print("二进制");
                break;
            case 2:
                System.out.print("八进制");
                break;
            case 3:
                System.out.print("十进制");
                break;
            case 4:
                System.out.print("十六进制");
        }
        System.out.print(" 转化为 ");
        switch (index2)
        {
            case 1:
                System.out.println("二进制");
                break;
            case 2:
                System.out.println("八进制");
                break;
            case 3:
                System.out.println("十进制");
                break;
            case 4:
                System.out.println("十六进制");
        }
    }

    /**
     * 适应图形界面的 run 方法
     *
     * @param number  要转换的数值
     * @param oldType 被转化时的进制
     * @param newType 转化后的进制
     * @return 转化后的值
     */
    public static String run(String number, int oldType, int newType)
    {
        String temp = "";
        switch (oldType)
        {
            case 2:
                temp = BinToDec(number);
                break;
            case 8:
                temp = OctToDec(number);
                break;
            case 10:
                temp = number;
                break;
            case 16:
                temp = HexToDec(number);
        }
        switch (newType)
        {
            case 2:
                return DecToBin(temp);
            case 8:
                return DecToOct(temp);
            case 10:
                return DecToDec(temp);
            case 16:
                return DecToHex(temp);
        }
        return null;
    }
}
