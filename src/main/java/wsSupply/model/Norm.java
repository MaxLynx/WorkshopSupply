package wsSupply.model;


public class Norm {
    private int id;
    private Model model;
    private RawMaterial rawMaterial;
    private double amount;

    public Norm(){}
    public Norm(Model model, RawMaterial rawMaterial, double amount){
        this.model = model;
        this.rawMaterial = rawMaterial;
        this.amount = amount;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public Model getModel(){
        return model;
    }
    public void setModel(Model model){
        this.model = model;
    }
    public RawMaterial getRawMaterial(){
        return rawMaterial;
    }
    public void setRawMaterial(RawMaterial rawMaterial){
        this.rawMaterial = rawMaterial;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
}
