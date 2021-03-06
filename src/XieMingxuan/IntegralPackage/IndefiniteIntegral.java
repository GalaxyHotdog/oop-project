package XieMingxuan.IntegralPackage;

import java.util.Scanner;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-2
 * @描述 不定积分类，用于显示已知的不定积分
 */
public class IndefiniteIntegral
{
    /**
     * getkindOfIndefInt 方法，用于获取用户指令
     *
     * @return 用户指令
     */
    private static int getkindOfIndefInt()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查寻的基本函数类型：");
        System.out.println("1.幂函数");
        System.out.println("2.指数函数");
        System.out.println("3.对数函数");
        System.out.println("4.三角函数");
        System.out.println("5.反三角函数");
        System.out.println("6.常数函数");
        return scanner.nextInt();
    }

    /**
     * showIndefiniteIntegral 方法，用于输出指令对应的不定积分形式
     *
     * @param option 指令编号
     */
    private static void showIndefiniteIntegral(int option)
    {
        switch (option)
        {
            case 1:
                System.out.println("∫(x^a)dx=1/(a+1)*x^(a+1)+C,a!=-1");
                System.out.println("∫(x^a)dx=ln|x|+C,a=-1");
                break;
            case 2:
                System.out.println("∫(a^x)dx=1/ln(a)*a^x+C");
                System.out.println("∫(e^x)dx=e^x+C");
                break;
            case 3:
                System.out.println("∫log(a)(x)dx=xlog(a)(x)-xlog(a)(e)+C");
                System.out.println("∫ln(x)dx=xln(x)-x+C");
                break;
            case 4:
                System.out.println("∫sin(x)dx=-cos(x)+C");
                System.out.println("∫cos(x)dx=six(x)+C");
                System.out.println("∫tan(x)dx=-ln|cos(x)|+C");
                System.out.println("∫cot(x)dx=ln|sin(x)|+C");
                System.out.println("∫sec(x)dx=ln|sec(x)+tan(x)|+C=1/2*ln|(1+sin(x))/(1-sin(x))|+C");
                System.out.println("∫csc(x)dx=ln|csc(x)-cot(x)|+C=ln|tan(x/2)|+C");
                break;
            case 5:
                System.out.println("∫arcsin(x)dx=xarcsin(x)+sqrt(1-x^2)+C");
                System.out.println("∫arccos(x)dx=xarccos(x)-sqrt(1-x^2)+C");
                System.out.println("∫arctan(x)dx=xarctan(x)-1/2*ln(1+x^2)+C");
                System.out.println("∫arccot(x)dx=xarccot(x)+1/2*ln(1+x^2)+C");
                System.out.println("∫arcsec(x)dx=xarcsec(x)-ln(x+sqrt(x^2-1))+C");
                System.out.println("∫arccsc(x)dx=xarccsc(x)+ln(x+sqrt(x^2-1))+C");
                break;
            case 6:
                System.out.println("∫Rdx=Rx+C");
                break;
            default:
        }
    }

    /**
     * run 静态方法，用于执行不定积分操作
     */
    public static void run()
    {
        showIndefiniteIntegral(getkindOfIndefInt());
    }

    /**
     * 适应图形界面的 run 方法,用于查询对应的不定积分
     *
     * @param option 需要查询的类型
     *               option==1 表示查询幂函数
     *               option==2 表示查询指数函数
     *               option==3 表示查询对数函数
     *               option==4 表示查询三角函数
     *               option==5 表示查询反三角函数
     *               option==6 表示查询常数函数
     * @return 不定积分字符串表达
     */
    public static String run(int option)
    {
        switch (option)
        {
            case 1:
                return "∫(x^a)dx=1/(a+1)*x^(a+1)+C,a!=-1\n" +
                        "∫(x^a)dx=ln|x|+C,a=-1";
            case 2:
                return "∫(a^x)dx=1/ln(a)*a^x+C\n" +
                        "∫(e^x)dx=e^x+C";
            case 3:
                return "∫log(a)(x)dx=xlog(a)(x)-xlog(a)(e)+C\n" +
                        "∫ln(x)dx=xln(x)-x+C";
            case 4:
                return "∫sin(x)dx=-cos(x)+C\n" +
                        "∫cos(x)dx=six(x)+C\n" +
                        "∫tan(x)dx=-ln|cos(x)|+C\n" +
                        "∫cot(x)dx=ln|sin(x)|+C\n" +
                        "∫sec(x)dx=ln|sec(x)+tan(x)|+C=1/2*ln|(1+sin(x))/(1-sin(x))|+C\n" +
                        "∫csc(x)dx=ln|csc(x)-cot(x)|+C=ln|tan(x/2)|+C";
            case 5:
                return "∫arcsin(x)dx=xarcsin(x)+sqrt(1-x^2)+C\n" +
                        "∫arccos(x)dx=xarccos(x)-sqrt(1-x^2)+C\n" +
                        "∫arctan(x)dx=xarctan(x)-1/2*ln(1+x^2)+C\n" +
                        "∫arccot(x)dx=xarccot(x)+1/2*ln(1+x^2)+C\n" +
                        "∫arcsec(x)dx=xarcsec(x)-ln(x+sqrt(x^2-1))+C\n" +
                        "∫arccsc(x)dx=xarccsc(x)+ln(x+sqrt(x^2-1))+C";
            case 6:
                return "∫Rdx=Rx+C";
            default:
        }
        return null;
    }
}

