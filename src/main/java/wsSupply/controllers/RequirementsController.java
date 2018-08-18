package wsSupply.controllers;


import wsSupply.model.*;

import java.util.ArrayList;
import java.util.List;

public class RequirementsController {
    private List<Requirement> requirements;
    public RequirementsController(List<ModelQuantity> plan){
        DAO dao = new DAO();
        List<Norm> norms = dao.getNorms();
        List<RawMaterial> materials = dao.getRawMaterials();
        requirements = new ArrayList<Requirement>();
        for(RawMaterial material : materials) {
            double neededQuantity = 0.0;
            for (ModelQuantity modelQuantity : plan) {
                int count = modelQuantity.getAmount();
                String modelName = modelQuantity.getModel();
                for(Norm norm : norms){
                    if(norm.getModel().getName().equals(modelName) &&
                            norm.getRawMaterial().getName().equals(material.getName())){
                        neededQuantity += norm.getAmount()*count;
                        break;
                    }
                }
            }
            if(neededQuantity > 0.0)
                requirements.add(new Requirement(material, Math.round(neededQuantity*100)*1.0/100, 0.0));
        }
        dao.clearRequirements();
        dao.addRequirementsList(requirements);
    }
}
