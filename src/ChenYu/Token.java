import java.util.List;
import java.util.Timer;

public class Token
{
    public String token;//内容
    public int mark;//标记表明属性
    private Token left=null;//左子树
    private Token right=null;//右子树
    private Token operator=null;//操作符节点
    public Token ( String token, int mark)
    {//表达式
        this.token=token;
        this.mark=mark;
    }
    public Token(Token left,Token right,Token operator){//表达式树
        this.left=left;
        this.right=right;
        this.operator=operator;
        if(left!=null)
            this.token=left.token+operator.token+right.token;
        else
            this.token=operator.token+right.token;
        this.mark=-1;
    }
    public static void create(List tokens,boolean f){//表达式转化成表达式树
        if(f==false){//f标记为false，说明两边有()，为真，说明两边无()
            tokens.remove(0);
            tokens.remove(tokens.size()-1);
            }
        for (int i = 0; i < tokens.size(); i++) {
            Token x= (Token) tokens.get(i);
            if(x.mark==7){//函数
                Token left=null;
                Token right=null;
                if(x.token.equals("log")==false){//不是log,函数仅有一个参数，参数放在右子树
                    left=null;
                    right=(Token) tokens.get(i+1);
                }
                else{//是log，log(a)(b)，两个参数
                    left=(Token) tokens.get(i+1);
                    right=(Token) tokens.get(i+2);
                }
                tokens.set(i,new Token(left,right,x));
                tokens.remove(x);
                tokens.remove(left);
                tokens.remove(right);
                i--;
            }
        }
        for (int i = 0; i < tokens.size(); i++) {
            Token x= (Token) tokens.get(i);
            if(x.mark==6){//^
                Token left=(Token) tokens.get(i-1);
                Token right=(Token) tokens.get(i+1);
                tokens.set(i-1,new Token(left,right,x));
                tokens.remove(x);
                tokens.remove(left);
                tokens.remove(right);
                i--;
            }
        }
        for (int i = 0; i < tokens.size(); i++) {
            Token x= (Token) tokens.get(i);
            if(x.mark==5){//*//
                Token left=(Token) tokens.get(i-1);
                Token right=(Token) tokens.get(i+1);
                tokens.set(i-1,new Token(left,right,x));
                tokens.remove(x);
                tokens.remove(left);
                tokens.remove(right);
                i--;
            }
        }
        for (int i = 0; i < tokens.size(); i++) {
            Token x= (Token) tokens.get(i);
            if(x.mark==4){//+/-
                Token left=(Token) tokens.get(i-1);
                Token right=(Token) tokens.get(i+1);
                tokens.set(i-1,new Token(left,right,x));
                tokens.remove(x);
                tokens.remove(left);
                tokens.remove(right);
                i--;
            }
        }
    }
    public Token derivate(){//求导
        Token x=null;
        if(this.mark==2){//纯数字
            x=new Token("0",2);
        }
        else if(this.mark==3){//变量
            x=new Token("1",2);
        }
        else{
            if(this.operator.token.equals("sin")){
                x=new Token(new Token(null,this.right,new Token("cos",7)),this.right.derivate(),new Token("*",5));
            }
            else if(this.operator.token.equals("cos")){
                x=new Token(new Token(new Token("0",2),new Token(null,this.right,new Token("sin",7)),new Token("-",4)),this.right.derivate(),new Token("*",5));
            }
            else if(this.operator.token.equals("tan")){//tan(x)=sin(x)/cos(x)，在求导
                x=new Token(new Token(null,this.right,new Token("sin",7)),new Token(null,this.right,new Token("cos",7)),new Token("/",5)).derivate();
            }
            else if(this.operator.token.equals("ln")){
                x=new Token(new Token(new Token("1",2),right,new Token("/",5)),this.right.derivate(),new Token("*",5));
            }
            else if(this.operator.token.equals("log")){//log(a)(b)=ln(a)/ln(b)，再求导
                x=new Token(new Token(null,right,new Token("ln",7)),new Token(null,left,new Token("ln",7)),new Token("/",5)).derivate();
            }
            else if(this.operator.token.equals("*")){
                Token left=new Token(this.left.derivate(),this.right,new Token("*",5));
                Token right=new Token(this.left,this.right.derivate(),new Token("*",5));
                x=new Token(left,right,new Token("+",4));
            }
            else if(this.operator.token.equals("/")){
                Token left=new Token(this.left.derivate(),this.right,new Token("*",5));
                Token right=new Token(this.left,this.right.derivate(),new Token("*",5));
                Token top=new Token(left,right,new Token("-",4));
                Token bottom=new Token(this.right,this.right,new Token("*",5));
                x=new Token(top,bottom,new Token("/",5));
            }
            else if(this.operator.token.equals("+")||this.operator.token.equals("-")){
                x=new Token(this.left.derivate(),this.right.derivate(),this.operator);
            }
            else if(this.operator.token.equals("^")) {//a^b=e^(blna)，求导
                Token lna=new Token(null,left,new Token("ln",7));
                Token blna=new Token(right,lna,new Token("*",5));
                x=new Token(new Token(new Token("e",2),blna,new Token("^",6)),blna.derivate(),new Token("*",5));
            }
        }
        return x;
    }
    public double value(double x) throws Exception {//递归算表达式树
        double res=0;
        if(this.mark==2){
            if(this.token.equals("e"))
                res=Math.exp(1);
            else if(this.token.equals("pi"))
                res=Math.PI;
            else res=Double.valueOf(this.token);
        }
        else if(this.mark==3)
            res=x;
        else{
            double m=0,n=0;
            if(this.left!=null)m=left.value(x);
            n=right.value(x);
            if(this.operator.token.equals("*"))
                res=m*n;
            else if(this.operator.token.equals("/")){
                if(n==0)throw new Exception();
                res=m/n;
            }
            else if(this.operator.token.equals("+"))
                res=m+n;
            else if(this.operator.token.equals("-"))
                res=m-n;
            else if(this.operator.token.equals("^")){
                if(m==0&&n==0)throw new Exception();
                res=Math.pow(m,n);
            }
            else if(this.operator.token.equals("ln")){
                if(n==0)throw new Exception();
                res=Math.log(n);
            }
            else if(this.operator.token.equals("sin")){
                res=Math.sin(n);
            }
            else if(this.operator.token.equals("cos")){
                res=Math.cos(n);
            }

            else if(this.operator.token.equals("tan")){
                for (int i = (int)(n/(Math.PI/2)); i <  (int)(n/(Math.PI/2))+1; i++) {
                    if(i*Math.PI/2==n&&i%2==1)
                        throw new Exception();
                }
                if(n==Math.PI/2)throw new Exception();
                res=Math.tan(n);
            }
        }
        return res;
    }
    public String toString(){
        if(this.mark!=-1){
            return this.token;
        }
        if(this.left==null&&this.right!=null){
            return this.operator.token+"("+this.right+")";
        }
        else {
            String s="";
            if(this.left.operator!=null&&this.left.operator.mark<this.operator.mark){
                s+="("+this.left+")";
            }
            else
                s+=this.left;
            s+=this.operator.token;
            if(this.right.operator!=null&&this.right.operator.mark<this.operator.mark||this.operator.token.equals("-")){
                s+="("+this.right+")";
            }
            else
                s+=this.right;
            return s;
        }
    }
}