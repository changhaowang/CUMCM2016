import java.util.Vector;

/**
 * Created by john on 2016/9/10 0010.
 */
public class Policy{

    Vector<Integer> expectNodes;
    Vector<Road> expectRoads;


    Policy(Vector<Integer> nodes,Vector<Road> roads){
        expectNodes=new Vector<Integer>();
        expectRoads=new Vector<Road>();
        for(int i=0;i<nodes.size();i++)
            expectNodes.add(nodes.get(i));
        for(int i=0;i<roads.size();i++)
            expectRoads.add(roads.get(i));
    }

    double calTime(){
        double totalTime=0;
        for(int i=0;i<expectRoads.size();i++)
            totalTime+=expectRoads.get(i).t;
        return totalTime;
    }

    double calLength(){
        double totalLength=0;
        for(int i=0;i<expectRoads.size();i++)
            totalLength+=expectRoads.get(i).LRoad;
        return totalLength;
    }
    double calK(){
        double totalCar=0;
        double totalLength=0;
        for(int i=0;i<expectRoads.size();i++)
        {totalLength+=expectRoads.get(i).LRoad*expectRoads.get(i).WRoad;
         totalCar+=expectRoads.get(i).nCar;

        }
        return totalCar/totalLength;
    }

    double calKmax(){
        double totalLength=0;
        for(int i=0;i<expectRoads.size();i++)
        {totalLength+=expectRoads.get(i).LRoad*expectRoads.get(i).WRoad;
        }
        return totalLength/calLength()/Road.LCar;
    }

    void renewRoad(double uvar){
        for(int i=0;i<expectRoads.size();i++)
        {
            double fvar=uvar*expectRoads.get(i).LRoad;
            expectRoads.get(i).nCar+=fvar;
            if(expectRoads.get(i).nCar<0)
                expectRoads.get(i).nCar=0;
            expectRoads.get(i).renewPara();
        }
    }

    double roadNum(){return expectRoads.size();}

    void print(){
        System.out.println("policy:");
        for(int i=0;i<expectNodes.size();i++)
            System.out.print(" "+expectNodes.get(i));
        System.out.println();
        System.out.println("time: "+calTime()+" K: "+calK()+" length: "+calLength());
    }

}
