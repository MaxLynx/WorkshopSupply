package wsSupply.view;

import wsSupply.controllers.DAO;
import wsSupply.controllers.RequirementsController;
import wsSupply.model.Model;
import wsSupply.model.ModelQuantity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CreatePlanDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    private DAO dao;
    private int rowCount;

    public CreatePlanDialog() {
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
        List<ModelQuantity> plan = new ArrayList<>();
        for(int i = 0; i < rowCount; i++){
            String modelName = table1.getModel().getValueAt(i, 0).toString();
            int modelQuantity = Integer.parseInt(table1.getModel().getValueAt(i, 1).toString());
            System.out.println(modelName);
            System.out.println(modelQuantity);
            plan.add(new ModelQuantity(modelName, modelQuantity));
        }
        new RequirementsController(plan);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        dao = new DAO();
        List<Model> models = dao.getModels();
        String[] columnNames = {"MODEL",
                "QUANTITY"};
        rowCount = models.size();
        Object[][] data = new Object [rowCount][2];
        int i = 0;
        for(Model model : models){
            data[i][1] = 0;
            data[i++][0] = model.getName();
        }
        table1 = new JTable(data, columnNames);
    }
}
