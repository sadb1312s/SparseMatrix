package sample;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static Random random = new Random();
    static int size;
    static int N;

    static public ArrayList<Integer> MainD = new ArrayList<>();//main diagonal
    static public ArrayList<Integer> TopVars = new ArrayList<>();//not 0 element
    static public ArrayList<Integer> BotVars = new ArrayList<>();//not 0 element
    static public ArrayList<Integer> LI = new ArrayList<>();//string
    static public ArrayList<Integer> LJ = new ArrayList<>();//ctolbci

    static public ArrayList<Integer> DopD = new ArrayList<>();//Dop diagonal
    //temp
    static public ArrayList<Integer> LIt = new ArrayList<>();//string
    static public ArrayList<Integer> LJt = new ArrayList<>();//ctolbci
    static public ArrayList<Integer> TopVarst = new ArrayList<>();//not 0 element
    static public ArrayList<Integer> BotVarst = new ArrayList<>();//not 0 element


    static int tempLI=1;
    static int tempLIt=1;
    static int LItf=0;
    static int ArraySize=0;

    public static void main(String[] args) {
        System.out.println("sparse sym matrix");


        int MatrixArray[][]=GenSymMatrix(5,2,5);
        //сделать чтение из файла
		/*int MatrixArray[][] = {
			{0,1,0,5,1},
			{1,0,2,0,0},
			{0,2,6,0,0},
			{5,0,0,0,0},
			{5,0,0,0,1}

		};*/
        ArraySize=MatrixArray.length;
        LI.add(1);
        //преобразование в структуру из 5 массивов
        for(int i=0;i<ArraySize;i++){
            for(int j=0;j<ArraySize;j++){
                //maind
                if(i==j){
                    MainD.add(MatrixArray[i][j]);
                }
                //top
                if(i<j){
                    if(MatrixArray[i][j]!=0){
                        System.out.println(">"+MatrixArray[i][j]+" "+(j+1));
                        TopVars.add(MatrixArray[i][j]);
                        LJ.add((j+1));
                        tempLI++;
                    }
                }
            }
            if(LI.size()<ArraySize){LI.add(tempLI);}
        }
        //bot
        for(int j=0;j<ArraySize;j++){
            for(int i=0;i<ArraySize;i++){
                if(i>j){
                    if(MatrixArray[i][j]!=0){
                        BotVars.add(MatrixArray[i][j]);
                    }
                }
            }
        }

        //вывод массивов
        PrintArrayMatrix();

        printMatrixMainD();

        DopDiagonalReflected();
        printMatrixDopD();
        PrintArrayMatrix();
        System.out.println("FINISH");

    }

    private static void	printMatrixMainD(){
        System.out.println();
        System.out.println();
        for(int i=0;i<ArraySize;i++){
            for(int j=0;j<ArraySize;j++){
                if(i==j){
                    System.out.print(MainD.get(i)+" ");
                    //System.out.print(0);
                }
                if(i<j){
                    System.out.print(getVarTop(i,j)+" ");
                    //System.out.print(1);
                }
                if(i>j){
                    System.out.print(getVarBot(j,i)+" ");
                    //System.out.print(2);
                }
            }
            System.out.println();
        }
    }
    //отрисовать зеркальную матрицу относительно доп диагонали, вызывается только после метода DopDiagonalReflected!
    private static void	printMatrixDopD(){
        System.out.println();
        System.out.println();
        for(int i=0;i<ArraySize;i++){
            for(int j=0;j<ArraySize;j++){
                if(j==ArraySize-i-1){

                    System.out.print(DopD.get(i)+" ");
                }
                if(j<ArraySize-i-1){
                    System.out.print(getVarTop(i,j)+" ");
                }
                if(j>ArraySize-i-1){
                    System.out.print(getVarTop(ArraySize-j-1,ArraySize-i-1)+" ");
                }
            }
            System.out.println();
        }
    }
    //доступ к элементам передаём индексы i j как у обычного массива
    private static int getVarTop(int i, int j){
        int tempVar=0;
        int N1=LI.get(i)-1;
        int N2=LI.get(i+1)-1;

        for(int k=N1;k<N2;k++){
            //System.out.println("sravnenie "+LJ.get(k)+" "+j);
            if(LJ.get(k)==j+1){

                tempVar=TopVars.get(k);
            }
        }
        //System.out.println(i+" "+j+" "+N1+" "+N2);
        return tempVar;
    }
    private static int getVarBot(int i, int j){
        int tempVar=0;
        int N1=LI.get(i)-1;
        int N2=LI.get(i+1)-1;

        for(int k=N1;k<N2;k++){
            //System.out.println("sravnenie "+LJ.get(k)+" "+j);
            if(LJ.get(k)==j+1){

                tempVar=BotVars.get(k);
            }
        }
        //System.out.println(">"+i+" "+j+" "+N1+" "+N2);
        return tempVar;
    }
    //формирование дополниьельной диагонали
    private static void CreateDopD(){

        for(int i=0;i<ArraySize;i++){
            for(int j=0;j<ArraySize;j++){
                if(ArraySize-i-1==j){
                    if(i==j){
                        DopD.add(MainD.get(i));
                    }
                    if(i>j){
                        DopD.add(getVarBot(j,i));
                    }
                    if(i<j){
                        DopD.add(getVarTop(i,j));
                    }

                }else{
                    //System.out.print(0);
                }
            }

            //System.out.println();




        }
    }
    //вывод 5 массивов для проверки
    private static void PrintArrayMatrix(){

        System.out.println("---------");
        System.out.println("-5-array-");
        System.out.println("Main");
        for(Integer i:MainD){
            System.out.print(i);
        }
        if(DopD.size()!=0){
            System.out.println();
            System.out.println("Dop");
            for(Integer i:DopD){
                System.out.print(i);
            }
        }
        System.out.println();
        System.out.println("TopVars");
        for(Integer i:TopVars){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("LJ");
        for(Integer i:LJ){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("LI");
        for(Integer i:LI){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("BotVars");
        for(Integer i:BotVars){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("---------");
    }
    //temp
    static void PrintArrayMatrix2(){
        System.out.println("---------");
        System.out.println("-5-array-");


        System.out.println();
        System.out.println("Dop");
        for(Integer i:DopD){
            System.out.print(i);
        }

        System.out.println();
        System.out.println("TopVarst");
        for(Integer i:TopVarst){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("LJt");
        for(Integer i:LJt){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("LIt");
        for(Integer i:LIt){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("BotVarst");
        for(Integer i:BotVarst){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("---------");
    }
    //функция для отражения относительно доп диагонали
    private static void DopDiagonalReflected(){
        //доп диагональ
        CreateDopD();
        tempLIt=0;
        int temp4;
        LIt.add(1);
        //всё что выше доп диагонали в масси topvarst
        for(int i=0;i<ArraySize;i++){
            int tempvar;
            //LItf=0;
            for(int j=0;j<ArraySize-i-1;j++){
                if(i==j){
                    //System.out.print(MainD.get(i));
                    if(MainD.get(i)!=0){
                        TopVarst.add(MainD.get(i));
                        LJt.add(j+1);
                        LItf++;
                        tempLIt++;
                    }
                }
                if(i<j){
                    //System.out.print(getVarTop(i,j));
                    if(getVarTop(i,j)!=0){
                        TopVarst.add(getVarTop(i,j));
                        LJt.add(j+1);
                        LItf++;
                        tempLIt++;
                    }
                }
                if(i>j){
                    //System.out.print(getVarBot(j,i));

                    if(getVarBot(j,i)!=0){
                        TopVarst.add(getVarBot(j,i));
                        LJt.add(j+1);
                        LItf++;
                        tempLIt++;
                    }
                }
            }

            if(LIt.size()<ArraySize){LIt.add(tempLIt+1);}

        }

        //вывод массивов
        //PrintArrayMatrix2();

        //перезапись из temp
        TopVars.clear();
        TopVars.addAll(TopVarst);
        BotVars.clear();
        BotVars.addAll(TopVars);
        LJ.clear();
        LI.clear();
        LJ.addAll(LJt);
        LI.addAll(LIt);





        //System.out.print(">"+BotVars.size()+" "+TopVars.size());
    }
    //рандомная генерация (структурно)семетричной матрицы в виде двухмерного массива
    private static int[][] GenSymMatrix(int size, int SymOrStrSym, int zapolnenost){
        //size - размер матрицы
        //SymOrStrSym какую матрицу генерируем
        //1-семетричная матрица
        //2-структурно семетричная
        //zapolnenost- шанс генерации не 0
        //заполненость не может быть больше 3(может получится не разреженная матрица)!
        int MatrixArray[][] = new int[size][size];
        int c;
        int N=0;//для контроля разреженная матрица или нет
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                c = random.nextInt(9);
                //System.out.print(c);

                if(i<=j){
                    if(c<zapolnenost){
                        MatrixArray[i][j]=(1+random.nextInt(9));
                        N++;
                        if(i!=j){
                            if(SymOrStrSym==1){
                                MatrixArray[j][i]=MatrixArray[i][j];
                                N++;
                            }
                            if(SymOrStrSym==2){
                                MatrixArray[j][i]=(1+random.nextInt(9));
                                N++;
                            }
                        }

                    }

                }
                //System.out.print(MatrixArray[i][j]);
            }
            //System.out.println();
        }
        System.out.println(N);
        return MatrixArray;

    }
}
