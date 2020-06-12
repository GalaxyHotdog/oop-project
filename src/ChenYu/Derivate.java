import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class Derivate {
    private Token exper=null;
    public static void main(String[] args){
        String arg="tan(x)";
        try {
            Derivate x=new Derivate(arg);
            System.out.println(x.value(Math.PI*5/2,0));//对f(x)在x=1处求2阶导
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Derivate(String arg) throws Exception{
        Vector<Token> s=ToToken(arg);
        new Check(s);//检查表达式是否合法,不合法抛出异常
        Stack<Token> stack=new Stack();
        Stack<Integer> stack0=new Stack();
        for (int i = 0; i < s.size(); i++) {
            Token x = s.elementAt(i);
            if (x.mark == 0) {//寻找左括号
                stack.push(x);
                stack0.push(i);
            }
            else if (x.mark == 1) {//寻找右括号
                Token y = stack.pop();
                Integer m = stack0.pop();
                Token.create(s.subList(m, i + 1),false);//处理最近的左右括号间的字符串
                i = 0;
            }
        }
        Token.create(s.subList(0,s.size()),true);//处理余下的表达式
        exper=s.elementAt(0);//表达式树

    }
    public double value(double x,int n) throws Exception{
        if(n<0)throw new Exception();
        Token res=exper;
        for (int i = 0; i < n; i++) {
            res=res.derivate();
        }
        return (double)Math.round(res.value(x)*10000)/10000;//保留小数点后4位
    }
    private static Vector<Token> ToToken(String arg){
        Vector<Token> s=new Vector<>();
        boolean flag=true;
        for (int i = 0; i < arg.length(); i++) {
            Token x = null;
            char c=arg.charAt(i);
            if(c>='0'&&c<='9'){//输入数字
                int t=i;
                while(t<arg.length()&&arg.charAt(t)>='0'&&arg.charAt(t)<='9')
                    t++;
                x=new Token(arg.substring(i,t),2);

                i=t-1;flag=true;
            }
            else if(c=='x'){//输入变量
                if(s.size()!=0&&(s.lastElement().mark==2||s.lastElement().mark==3))//变量前有x或数字，加*
                s.add(new Token("*",5));
                x=new Token("x",3);
                flag=true;
            }

            else if(c=='e')//e也属于数字
                x=new Token("e",2);
            else if(c>='a'&&c<='z'){//输入函数
                int t=i;
                while(t<arg.length()&&arg.charAt(t)>='a'&&arg.charAt(t)<='z'&&arg.charAt(t)!='x')
                    t++;
                if(s.size()!=0&&(s.lastElement().mark==2||s.lastElement().mark==3))//函数前有x或数字，加*
                    s.add(new Token("*",5));
                x=new Token(arg.substring(i,t),7);
                i=t-1;flag=true;
            }
            else if(c=='+'||c=='-'){//输入运算符
                x=new Token(String.valueOf(c),4);
                if(s.lastElement().mark==0||s.lastElement().mark==4||s.lastElement().mark==5||s.lastElement().mark==6) {//  +/-前有(,+/-,*//,自动补0
                    s.add(new Token("0", 2));
                }
            }
            else if(c=='*'||c=='/')
                x=new Token(String.valueOf(c),5);
            else if(c=='^')
                x=new Token(String.valueOf(c),6);
            else if(c=='(')
                x=new Token("(",0);
            else if(c==')')
                x=new Token(")",1);
            if(x!=null){
                s.add(x);
            }
        }
        return s;
    }
    public String toString(){
        return exper.toString();
    }

}
/*
(              0
)              1
十进制数       2
变量           3
//O操作符
+|-            4
*|/            5
^              6
函数           7

 */
/*
            case c:return null;
            case xa:return "a*("+arg+")^(a-1)";
            case sin:return "cos("+arg+")";
            case cos:return "-sin("+arg+")";
            case tan:return "(sec("+arg+"))^2";
            case cot:return "-(csc("+arg+"))^2";
            case sec:return "(sec("+arg+"))*(tan("+arg+"))";
            case csc:return "-(csc("+arg+"))*(cot("+arg+"))";
            case e_x:return "e^("+arg+")";
            case ln:return "("+arg+")^-1";
            case arcsin:return "(1-("+arg+")^2)^-0.5";
            case arccos:return "-(1-("+arg+")^2)^-0.5";
            case arctan:return "(1+("+arg+")^2)^-1";
            case arccot:return "-(1+("+arg+")^2)^-1";
            default:return null;
*/
