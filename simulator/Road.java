/**
 * Created by john on 2016/9/10 0010.
 */
public class Road {
    public static double LCar=5;
    public static double z=21.0/11;

    final double vf; //Maximum speed of a road.
    final double vm; //Expected speed of a road.
    final double LRoad; // Length of a road.
    final double WRoad; // Width of a road.
    final double pedestr;


    double Qm; //Maximum number of running cars on a road. Qm= vm/LCar
    double kmax; // kmax=1/LCar
    double kj; //kj=0.5*kmax
    double nCar; //Number of running cars on a road.
    double k; //k=nCar/LRoad

    double t0; // t0=LRoad/vf
    double t;// t=to*(1+alpha*(1-(1-k/kj)^z)^beta)
    double beta;// beta=vf/(2*vm-vf)
    double alpha;// alpha= beta*sqrt((vf-vm)/(vf*vm))/Qm

    boolean inCommunnity;

    Road(double vf0,double vm0,double LRoad0,double WRoad0, double iniCarNum
      ,boolean inCommunity0,double pedestrian0){
        vf=vf0;vm=vm0;LRoad=LRoad0;WRoad=WRoad0;nCar=iniCarNum;
        inCommunnity=inCommunity0;
        pedestr=pedestrian0;
        renewPara();
    }
    public void renewPara(){setParaAboutCar();setParaAboutTime();}
    private void setParaAboutCar(){
        Qm=vm/LCar*WRoad;
        kmax=WRoad/LCar;
        kj=0.5*kmax;
        k=nCar/LRoad/WRoad;
    }
    private void setParaAboutTime(){
        t0=LRoad/vf;
        beta=vf/(2*vm-vf);
        alpha= beta*Math.sqrt((vf-vm)/(vf*vm))/Qm;
        t=t0*(1+alpha*Math.pow(1-calPow(1-k/kj,z),beta));
        t=t*pedestr;
    }

    public void setnCar(double nCar0){
        nCar=nCar0;
        renewPara();
    }

    public void printInfo(boolean detail){
        System.out.println("nCar:"+nCar+" k:"+k+" to:"+t0+" t:"+t);
        if(detail)
         System.out.println("vf:"+vf+" vm:"+vm+" LRoad:"+LRoad+" Qm:"+Qm);
    }
    public double calPow(double base, double coe){
        if(base==0)
            return 0;
        else if(base>0)
            return Math.pow(base,coe);
        else
            return -Math.pow(-base,coe);
    }

    public double saturation(){
        return k/kmax;
    }
    public double speed(){
        return LRoad/t;
    }
}
