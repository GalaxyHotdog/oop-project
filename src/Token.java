import java.util.List;
import java.util.Timer;

public class Token
{
    public String token;
    public int mark;
    private Token left=null;
    private Token right=null;
    private Token operator=null;
    public Token ( String token, int mark)
    {
        this.token=token;
        this.mark=mark;
    }
    public Token(Token left,Token right,Token operator){
        this.left=left;
        this.right=right;
        this.operator=operator;
        if(left!=null)
            this.token=left.token+operator.token+right.token;
        else
            this.token=operator.token+right.token;
        this.mark=-1;
    }
    public static void create(List tokens,boolean f){
        if(f==false){
            tokens.remove(0);
            tokens.remove(tokens.size()-1);
            }
        for (int i = 0; i < tokens.size(); i++) {
            Token x= (Token) tokens.get(i);
            if(x.mark==7){
                Token left=null;
                Token right=null;
                if(x.token.equals("log")==false){
                    left=null;
                    right=(Token) tokens.get(i+1);
                }
                else{
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
            if(x.mark==6){
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
            if(x.mark==5){
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
            if(x.mark==4){
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
    public Token derivate(){
        Token x=null;
        if(this.mark==2){
            x=new Token("0",2);
        }
        else if(this.mark==3){
            x=new Token("1",2);
        }
        else{
            if(this.operator.token.equals("sin")){
                x=new Token(new Token(null,this.right,new Token("cos",7)),this.right.derivate(),new Token("*",5));
            }
            else if(this.operator.token.equals("cos")){
                x=new Token(new Token(new Token("0",2),new Token(null,this.right,new Token("sin",7)),new Token("-",4)),this.right.derivate(),new Token("*",5));
            }
            else if(this.operator.token.equals("tan")){
                x=new Token(new Token(null,this.right,new Token("sin",7)),new Token(null,this.right,new Token("cos",7)),new Token("/",5)).derivate();
            }
            else if(this.operator.token.equals("ln")){
                x=new Token(new Token(new Token("1",2),right,new Token("/",5)),this.right.derivate(),new Token("*",5));
            }
            else if(this.operator.token.equals("log")){
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
            else if(this.operator.token.equals("^")) {
                Token lna=new Token(null,left,new Token("ln",7));
                Token blna=new Token(right,lna,new Token("*",5));
                x=new Token(new Token(new Token("e",2),blna,new Token("^",6)),blna.derivate(),new Token("*",5));
            }
        }
        return x;
    }
    public double value(double x) throws Exception {
        double res=0;
        if(this.mark==2){
            if(this.token.equals("e"))
                res=Math.exp(1);
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