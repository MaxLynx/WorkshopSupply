package wsSupply.view;

import wsSupply.controllers.DAO;
import wsSupply.model.RawMaterial;
import wsSupply.model.Requirement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RegisterMaterialDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JList list1;
    private JTextField textField2;
    private DAO dao;
    private java.util.List<RawMaterial> materials;

    public RegisterMaterialDialog() {
        setContentPane(contentPane);
        setVisible(true);
        getRootPane().setDefaultButton(buttonOK);
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        for(Requirement requirement : dao.getRequirements()){
            if(requirement.getRawMaterial().getName().equals(materials.get(list1.getSelectedIndex()).getName())){
                double delivered = Double.parseDouble(textField1.getText());
                double difference = requirement.getNeeded() - requirement.getDelivered();
                if(delivered > difference){
                    textField2.setText("ДОСТАВЛЕНО НА " + (delivered + difference) + " "
                            + requirement.getRawMaterial().getMeasureUnit() + " БОЛЬШЕ НЕОБХОДИМОГО");
                    requirement.setDelivered(requirement.getNeeded());
                }
                else{
                    requirement.setDelivered(requirement.getDelivered() + delivered);
                }
                System.out.println(requirement.getDelivered());
                dao.updateRequirement(requirement);
                break;
            }
        }
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        dao = new DAO();
        materials = dao.getRawMaterials();
        String[] listRows = new String[materials.size()];
        int i = 0;
        for (RawMaterial material:materials) {
            listRows[i++] = material.getName() + " " + material.getMeasureUnit();
        }
        list1 = new JList(listRows);
    }
}
