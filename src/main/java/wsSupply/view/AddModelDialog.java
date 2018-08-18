package wsSupply.view;

import wsSupply.controllers.DAO;
import wsSupply.model.Model;
import wsSupply.model.Norm;
import wsSupply.model.RawMaterial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class AddModelDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JList list1;
    private JTable table1;
    private JList list2;
    private JButton ДОБАВИТЬButton;
    private JTextField textField2;
    private DAO dao;
    private java.util.List<RawMaterial> materials;
    private int rowCount = 0;

    public AddModelDialog() {
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
        ДОБАВИТЬButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.addRow(new Object[]{list2.getSelectedValue().toString().split(" ")[0], textField2.getText()});
                rowCount++;
            }
        });
    }

    private void onOK() {
        Model model = dao.addModel(textField1.getText(), list1.getSelectedValue().toString());
        List<Norm> norms = new ArrayList<Norm>();
        for(int i = 0; i < rowCount; i++){
            RawMaterial material = materials.get(i);
            double amount = Double.parseDouble(table1.getModel().getValueAt(i, 1).toString());
            norms.add(new Norm(model, material, amount));
        }
        dao.addNormList(norms);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        dao = new DAO();
        String[] listRows = {
                "каблуки", "подошва", "набойки"
        };
        list1 = new JList(listRows);
        table1 = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"Материал", "Количество"}));
        materials = dao.getRawMaterials();
        String[] listRows2 = new String[materials.size()];
        int i = 0;
        for (RawMaterial material:materials) {
            listRows2[i++] = material.getName() + " " + material.getMeasureUnit();
        }
        list2 = new JList(listRows2);
    }
}
