package wsSupply.model;


public class ModelQuantity {
    private String modelName;
    private int amount;

    public ModelQuantity(){}
    public ModelQuantity(String modelName, int amount){
        this.modelName = modelName;
        this.amount = amount;
    }

    public String getModel(){
        return modelName;
    }
    public void setModel(String modelName){
        this.modelName = modelName;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
}
