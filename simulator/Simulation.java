import java.util.Vector;

/**
 * Created by john on 2016/9/10 0010.
 */
public class Simulation {
    public static void main(String arg[]) throws  Exception{
        //Road a=new Road(100,60,1000,200,250);
        //a.printInfo(true);
        //System.out.println(a.calPow(-233,0.3));
        testGeneratePolicy();
    }

    public static void testGeneratePolicy() throws  Exception{
        Vector<Integer> carsInput=new Vector<Integer>();
        carsInput.add(180);
        carsInput.add(180);
        carsInput.add(180);
        carsInput.add(180);
        Road[][] roads=new Road[18][18];
        for(int i=0;i<18;i++)
            for(int j=0;j<18;j++)
                roads[i][j]=null;
        roads[0][4]=new Road(40000/3,30000/3,163,2,0,false,1);
        roads[4][5]=new Road(40000/3,30000/3,80,2,0,false,1);
        roads[5][2]=new Road(40000/3,30000/3,40,2,0,false,1);
        roads[2][8]=new Road(40000/3,30000/3,30,2,0,false,1);
        roads[8][12]=new Road(40000/3,30000/3,30,2,0,false,1);
        roads[12][16]=new Road(40000/3,30000/3,40,2,0,false,1);
        roads[16][3]=new Road(40000/3,30000/3,20,2,0,false,1);
        roads[3][17]=new Road(60000/3,50000/3,120,6,0,false,1);
        roads[17][1]=new Road(60000/3,50000/3,163,6,0,false,1);
        roads[1][13]=new Road(50000/3,40000/3,20,4,0,false,1);
        roads[13][9]=new Road(50000/3,40000/3,40,4,0,false,1);
        roads[9][0]=new Road(50000/3,40000/3,60,4,0,false,1);

        roads[4][0]=new Road(40000/3,30000/3,163,2,0,false,1);
        roads[5][4]=new Road(40000/3,30000/3,80,2,0,false,1);
        roads[2][5]=new Road(40000/3,30000/3,40,2,0,false,1);
        roads[8][2]=new Road(40000/3,30000/3,30,2,0,false,1);
        roads[12][8]=new Road(40000/3,30000/3,30,2,0,false,1);
        roads[16][12]=new Road(40000/3,30000/3,40,2,0,false,1);
        roads[3][16]=new Road(40000/3,30000/3,20,2,0,false,1);
        roads[17][3]=new Road(60000/3,50000/3,120,6,0,false,1);
        roads[1][17]=new Road(60000/3,50000/3,163,6,0,false,1);
        roads[13][1]=new Road(50000/3,40000/3,20,4,0,false,1);
        roads[9][13]=new Road(50000/3,40000/3,40,4,0,false,1);
        roads[0][9]=new Road(50000/3,40000/3,60,4,0,false,1);


        /*
        roads[4][6]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[5][7]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[6][10]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[7][11]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[10][14]=new Road(40000/3,30000/3,40,2,0,true,1.02);
        roads[11][15]=new Road(40000/3,30000/3,40,2,0,true,1.02);
        roads[14][17]=new Road(40000/3,30000/3,20,2,0,true,1.02);
        roads[6][7]=new Road(40000/3,30000/3,80,2,0,true,1.02);
        roads[10][11]=new Road(40000/3,30000/3,80,2,0,true,1.02);
        roads[11][12]=new Road(40000/3,30000/3,40,2,0,true,1.02);
        roads[9][10]=new Road(40000/3,30000/3,163,2,0,true,1.02);
        roads[13][14]=new Road(40000/3,30000/3,163,2,0,true,1.02);
        roads[14][15]=new Road(40000/3,30000/3,80,2,0,true,1.02);
        roads[15][16]=new Road(40000/3,30000/3,40,2,0,true,1.02);

        roads[6][4]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[7][5]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[10][6]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[11][7]=new Road(40000/3,30000/3,30,2,0,true,1.02);
        roads[14][10]=new Road(40000/3,30000/3,40,2,0,true,1.02);
        roads[15][11]=new Road(40000/3,30000/3,40,2,0,true,1.02);
        roads[17][14]=new Road(40000/3,30000/3,20,2,0,true,1.02);
        roads[7][6]=new Road(40000/3,30000/3,80,2,0,true,1.02);
        roads[11][10]=new Road(40000/3,30000/3,80,2,0,true,1.02);
        roads[12][11]=new Road(40000/3,30000/3,40,2,0,true,1.02);
        roads[10][9]=new Road(40000/3,30000/3,163,2,0,true,1.02);
        roads[14][13]=new Road(40000/3,30000/3,163,2,0,true,1.02);
        roads[15][14]=new Road(40000/3,30000/3,80,2,0,true,1.02);
        roads[16][15]=new Road(40000/3,30000/3,40,2,0,true,1.02);
*/
        Vector<Double> outWeights=new Vector<Double>();
        outWeights.add(0.25);
        outWeights.add(0.25);
        outWeights.add(0.25);
        outWeights.add(0.25);

        String fileName1="1.txt";
        String fileName2="2.txt";
        String fileName3="3.txt";
        String fileName4="4.txt";
        String fileName5="5.txt";
       Community co=new Community(18,4,carsInput,roads,outWeights,fileName1,
               fileName2,fileName3,fileName4,fileName5);
        //co.generatePolicy();
        //co.generateProb();
        //co.comunnityPrint();
        co.iterate();
    }}
    /*public static Road[][] readRoads(String fileName,int nodeNum) throws Exception{
        /*Road[][] roads=new Road[][];
        File file=new File(fileName);
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),"GBK");
        int count=0;
        while(true){
            double vf=read.read();
            double vm=read.read();
            double Lroad=read.read();
            roads[count/nodeNum][count%nodeNum].vf=read.read();

        }
    }
        Road[][] roads=new Road[][];
        return null;
}*/
