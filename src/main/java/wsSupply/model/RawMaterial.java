package wsSupply.model;


public class RawMaterial {
    private int id;
    private String name;
    private String measureUnit;

    public RawMaterial(){}
    public RawMaterial(String name, String measureUnit){
        this.name = name;
        this.measureUnit = measureUnit;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getMeasureUnit(){
        return measureUnit;
    }
    public void setMeasureUnit(String measureUnit){
        this.measureUnit = measureUnit;
    }
}
