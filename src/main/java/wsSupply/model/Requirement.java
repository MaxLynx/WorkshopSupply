package wsSupply.model;


public class Requirement {
    private int id;
    private RawMaterial rawMaterial;
    private double needed;
    private double delivered;

    public Requirement(){}
    public Requirement(RawMaterial rawMaterial, double needed, double delivered){
        this.rawMaterial = rawMaterial;
        this.needed = needed;
        this.delivered = delivered;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public RawMaterial getRawMaterial(){
        return rawMaterial;
    }
    public void setRawMaterial(RawMaterial rawMaterial){
        this.rawMaterial = rawMaterial;
    }
    public double getNeeded(){
        return needed;
    }
    public void setNeeded(double needed){
        this.needed = needed;
    }
    public double getDelivered(){
        return delivered;
    }
    public void setDelivered(double delivered){
        this.delivered = delivered;
    }
}
