import java.util.*;
public class Matrix {
    double[][] A;
    double[][] B;
    double[][] ans;
    int nA,mA,nB,mB,nans,mans;

    public void MatrixA(int n1,int m1) {
        nA=n1;
        mA=m1;
        A = new double[nA+1][mA+1];
    }
    public void MatrixB(int n1,int m1) {
        nB=n1;
        mB=m1;
        B = new double[nB+1][mB+1];
    }
    public void GetValue(char o){
        Scanner scn = new Scanner(System.in);
        if(o=='A') {
            for (int i = 1; i <= nA; i++) {
                for (int j = 1; j <= mA; j++) {
                    A[i][j] = scn.nextDouble();
                }
            }
        }
        else if(o=='B'){
            for (int i = 1; i <= nB; i++) {
                for (int j = 1; j <= mB; j++) {
                    B[i][j] = scn.nextDouble();
                }
            }
        }
    }
    public void Mat_mul(char first, char second){
        if(first=='A' && second == 'B'){

            if(mA!=nB){
                System.out.println("Can't be multiply.");
            }
            else{
                ans = new double[nA+1][mB+1];
                nans=nA;
                mans=mB;
                for(int i =1;i<=nA;i++){
                    for(int j=1;j<=mB;j++){
                        ans[i][j]=0;
                        for(int k=1;k<=mA;k++){
                            ans[i][j]+=A[i][k]*B[k][j];
                        }
                    }
                }
            }
        }
        else if(first=='B' && second == 'A'){
            if(mB!=nA){
                System.out.println("Can't be multiply.");
            }
            else{
                ans = new double[nB+1][mA+1];
                nans=nB;
                mans=mA;
                for(int i =1;i<=nB;i++){
                    for(int j=1;j<=mA;j++){
                        ans[i][j]=0;
                        for(int k=1;k<=mB;k++){
                            ans[i][j]+=B[i][k]*A[k][j];
                        }
                    }
                }
            }
        }
        for(int i=1;i<=nans;i++){
            for(int j=1;j<=mans;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Matrix m = new Matrix();
        m.MatrixA(3,4);
        m.MatrixB(4,3);
        m.GetValue('A');
        m.GetValue('B');
        m.Mat_mul('B','A');
    }
}
