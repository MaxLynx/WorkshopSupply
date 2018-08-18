package wsSupply.model;


public class Model {
    private int id;
    private String name;
    private String type;

    public Model(){}
    public Model(String name, String type){
        this.name = name;
        this.type = type;
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
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
}
