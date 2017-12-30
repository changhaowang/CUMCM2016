import java.io.FileWriter;
import java.util.Vector;

/**
 * Created by john on 2016/9/10 0010.
 */
public class Community {
    int nodesNum;
    int inOutNodeNum;
    int iteNum;
    int iteMax;

    Vector<Integer> carsInput;
    Vector<Integer> carsOutput;
    Road[][] roads;
    Vector<Vector<Vector<Policy>>> policies;
    Vector<Vector<Vector<Double>>> policiProb;
    FileWriter f1;
    FileWriter f2;
    FileWriter f3;
    FileWriter f4;
    FileWriter f5;

    Vector<Vector<Double>> outputRatio;
    Community(int nodesNum0,int inNodeNum0,Vector<Integer>carsInput0
    , Road[][] roads0,Vector<Double> orginalWeight,String fileName1,
              String fileName2,String fileName3,String fileName4,String fileName5) throws Exception{
        nodesNum=nodesNum0;
        inOutNodeNum=inNodeNum0;
        carsInput=carsInput0;
        carsOutput=new Vector<Integer>();
        roads=roads0;
        policies=new Vector<Vector<Vector<Policy>>>();
        policiProb=new Vector<Vector<Vector<Double>>>();

        iteNum=0;
        iteMax=100;

        outputRatio=new Vector<>();
        for(int i=0;i<inOutNodeNum;i++){
        Vector<Double> ratio1=new Vector<Double>();
            double sum=0;
            for(int j=0;j<inOutNodeNum;j++)
                if(i!=j)
                    sum+=orginalWeight.get(j);
            for(int j=0;j<inOutNodeNum;j++)
                if(i!=j)
                ratio1.add(orginalWeight.get(j)/sum);
                 else{
                ratio1.add(0.0);}
            outputRatio.add(ratio1);
        }

        f1=new FileWriter(fileName1);
        f2=new FileWriter(fileName2);
        f3=new FileWriter(fileName3);
        f4=new FileWriter(fileName4);
        f5=new FileWriter(fileName5);
    }

    public Road findRoad(int src,int des){return roads[src][des];}
    public void generatePolicy(){
        for(int i=0;i<inOutNodeNum;i++){
            Vector<Vector<Policy>> inPolicy=new Vector<Vector<Policy>>();
            for(int j=0;j<inOutNodeNum;j++)
            {
                if(i!=j){
                Vector<Policy> outPolicy=dps(i,j);
                inPolicy.add(outPolicy);
                }
                else
                {
                    Vector<Policy> outPolicy=new Vector<Policy>();
                    inPolicy.add(outPolicy);
                }
            }
            policies.add(inPolicy);
        }
    }
    public void generateProb(){
        for(int i=0;i<policies.size();i++){
            policiProb.add(new Vector<Vector<Double>>());
            for(int j=0;j<policies.get(i).size();j++)
            {
                policiProb.get(i).add(new Vector<Double>());
                if(i!=j){
                double sum=0;
                for(int k=0;k<policies.get(i).get(j).size();k++){
                    double time=policies.get(i).get(j).get(k).calTime();
                    sum+=Math.exp(-0.5*time);
                }
                for(int k=0;k<policies.get(i).get(j).size();k++){
                    double time=policies.get(i).get(j).get(k).calTime();
                    policiProb.get(i).get(j).add(Math.exp(-0.5*time)/sum);
                }
            }
            }
        }
    }
    public void iterate(){
        generatePolicy();
        Vector<Vector<Vector<Double>>> oldnew=new Vector<>();
        Vector<Vector<Vector<Double>>> leaveCars=new Vector<>();
        Vector<Vector<Vector<Double>>> newCars=new Vector<>();

        while(iteNum<iteMax){
        generateProb();
            iteratePrint();
        leaveCars=calLeftCars();
            if(iteNum>0){
                for(int i=0;i<leaveCars.size();i++)
                {
                    for(int j=0;j<leaveCars.get(i).size();j++)
                        for(int k=0;k<leaveCars.get(i).get(j).size();k++)
                        {
                            if(oldnew.get(i).get(j).get(k)<
                                    leaveCars.get(i).get(j).get(k))
                                leaveCars.get(i).get(j).set(k,
                                        oldnew.get(i).get(j).get(k));
                        }
                }
            }
           /* System.out.println("leave cars:");
            for(int i=0;i<leaveCars.size();i++)
                for(int j=0;j<leaveCars.get(i).size();j++)
                    for(int k=0;k<leaveCars.get(i).get(j).size();k++)
                    {
                        Vector<Integer> v=policies.get(i).get(j).get(k).expectNodes;
                        for(int ii=0;ii<v.size();ii++)
                            System.out.print(" "+v.get(ii)+" ");
                        System.out.println(":"+leaveCars.get(i).get(j).get(k));
                        //policies.get(i).get(j).get(k).print();
                    }
     */
       newCars=calNewCars();
            oldnew=newCars;

        renewRoad(leaveCars,newCars);
        iteNum++;
        }
    }

    private Vector<Vector<Vector<Double>>> calLeftCars(){
        Vector<Vector<Vector<Double>>> result=new Vector<>();
        for(int i=0;i<policies.size();i++) {
            result.add(new Vector<>());
            for (int j = 0; j < policies.get(i).size(); j++)
            { result.get(i).add(new Vector<>());
                for (int k = 0; k < policies.get(i).get(j).size(); k++) {
                    Policy policy=policies.get(i).get(j).get(k);
                    double item=0;
                    double kmax=policy.calKmax();
                    if(policy.calK()<=0.5*kmax)
                        item=policy.calK()*policy.calLength();
                    else
                        item=Math.exp(-(policy.calK()-0.5*kmax)/kmax)*policy.calK()*policy.calLength();
                    result.get(i).get(j).add(item);
                }
            }
        }

        /*System.out.println("leave cars:");
        for(int i=0;i<result.size();i++)
            for(int j=0;j<result.get(i).size();j++)
                for(int k=0;k<result.get(i).get(j).size();k++)
                {
                    Vector<Integer> v=policies.get(i).get(j).get(k).expectNodes;
                    for(int ii=0;ii<v.size();ii++)
                  System.out.print(" "+v.get(ii)+" ");
                    System.out.println(":"+result.get(i).get(j).get(k));
                    //policies.get(i).get(j).get(k).print();
                }*/
        return result;
    }
    private Vector<Vector<Vector<Double>>> calNewCars(){
        Vector<Vector<Vector<Double>>> result=new Vector<>();
        for(int i=0;i<policies.size();i++) {
            result.add(new Vector<>());
            for (int j = 0; j < policies.get(i).size(); j++)
            {   result.get(i).add(new Vector<>());
                if(i!=j){
                double nCar=carsInput.get(i)*outputRatio.get(i).get(j);
                for(int k=0;k<policies.get(i).get(j).size();k++)
                  result.get(i).get(j).add(nCar*policiProb.get(i).get(j).get(k));
            }}
            }
       /*System.out.println("New cars:");
        for(int i=0;i<result.size();i++)
            for(int j=0;j<result.get(i).size();j++)
                for(int k=0;k<result.get(i).get(j).size();k++)
                {
                   Vector<Integer> v=policies.get(i).get(j).get(k).expectNodes;
                    for(int ii=0;ii<v.size();ii++)
                        System.out.print(" "+v.get(ii)+" ");
                    System.out.println(":"+result.get(i).get(j).get(k));
                    //policies.get(i).get(j).get(k).print();
                }*/
        return result;
    }
    private void renewRoad(Vector<Vector<Vector<Double>>> leaveC,
                           Vector<Vector<Vector<Double>>> newC){
        for(int i=0;i<policies.size();i++) {
            for (int j = 0; j < policies.get(i).size(); j++)
            {
                for (int k = 0; k < policies.get(i).get(j).size(); k++) {
                    double var=-leaveC.get(i).get(j).get(k)+
                            newC.get(i).get(j).get(k);
                    double uvar=var/policies.get(i).get(j).get(k).calLength();
                    policies.get(i).get(j).get(k).renewRoad(uvar);
                }
            }
        }
    }

    public void comunnityPrint(){
    System.out.println("Policies:");
    for(int i=0;i<inOutNodeNum;i++)
        for(int j=0;j<inOutNodeNum;j++)
        {
            if(i!=j){
            System.out.println("from "+i+" "+"to "+j+" num:"+policies.get(i).get(j).size());
            for(int k=0;k<policies.get(i).get(j).size();k++)
            {policies.get(i).get(j).get(k).print();
             //System.out.println("Pro:"+policiProb.get(i).get(j).get(k));
            }
            System.out.println("");
        }}

        System.out.println("Weights");
          for(int i=0;i<inOutNodeNum;i++)
              for(int j=0;j<inOutNodeNum;j++)
                  System.out.println(i+" "+j+" :"+outputRatio.get(i).get(j));
    }
    public void iteratePrint(){
        System.out.println("ite:"+iteNum);
        for(int i=0;i<nodesNum;i++)
            for(int j=0;j<nodesNum;j++)
            {
                if(roads[i][j]!=null)
                {
                    System.out.println(i+" " +j+" "+ "CarNum:"+roads[i][j].nCar);
                }
            }
        try {
            int count=1;
            for(int i=0;i<nodesNum;i++)
                for(int j=0;j<nodesNum;j++)
                {
                    if(roads[i][j]!=null&&!roads[i][j].inCommunnity)
                    {
                        f1.write(" "+roads[i][j].nCar);
                        f2.write(" "+roads[i][j].t);
                        f3.write(" "+roads[i][j].speed());
                        f4.write(" "+roads[i][j].saturation());
                        if(iteNum==0){
                        f5.write(count+": "+i+" "+j+" \n");
                        count++;}
                    }
                }

            f1.write("\n");
            f2.write("\n");
            f3.write("\n");
            f4.write("\n");
            f1.flush();
            f2.flush();
            f3.flush();
            f4.flush();
            f5.flush();
        }
        catch (Exception e)
        {
            System.out.println("File Write Error!");
            e.printStackTrace();
        }
       /* for(int i=0;i<policies.size();i++)
            for(int j=0;j<policies.get(i).size();j++)
                for(int k=0;k<policies.get(i).get(j).size();k++)
                {policies.get(i).get(j).get(k).print();
                    System.out.print(" "+policiProb.get(i).get(j).get(k));
                }*/
    }

    private Vector<Policy> dps(int in,int out){
         Vector<Policy> policies=new Vector<Policy>();
        Vector<Integer> currentNodes=new Vector<Integer>();
        dps_help(in,out,currentNodes,policies);
        return policies;
    }

    private void dps_help(int cur,int out,Vector<Integer>curNodes, Vector<Policy> re){

        if(curNodes.contains(cur))
            return;
        curNodes.add(cur);
        if(cur==out)
        {
            re.add(new Policy(curNodes,nodes2Roads(curNodes)));
            {
                if(curNodes.size()>0)
                    curNodes.remove(curNodes.size()-1);
                return;}
        }
        for(int i=0;i<nodesNum;i++){
            if(roads[cur][i]!=null&&i!=cur)
            {
                dps_help(i,out,curNodes,re);
            }
        }
        if(curNodes.size()>0)
            curNodes.remove(curNodes.size()-1);
    }

    private Vector<Road> nodes2Roads(Vector<Integer> curNodes){
        Vector<Road> road=new Vector<Road>();
        for(int i=1;i<curNodes.size();i++)
            road.add(roads[curNodes.get(i-1)][curNodes.get(i)]);
        return road;
    }
}
