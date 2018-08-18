package wsSupply.view;

import wsSupply.controllers.DAO;
import wsSupply.model.RawMaterial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AddRawMaterialDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;
    private JButton УДАЛИТЬButton;
    private DAO dao;

    public AddRawMaterialDialog() {
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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        УДАЛИТЬButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list1.getSelectedIndex();
                List<RawMaterial>materials = dao.getRawMaterials();
                dao.deleteRawMaterial(materials.get(selectedIndex).getID());
                materials = dao.getRawMaterials();
                DefaultListModel model = new DefaultListModel();
                for (RawMaterial material:materials) {
                    model.addElement(material.getName() + " " + material.getMeasureUnit());
                }
                list1.setModel(model);
            }
        });
    }

    private void onOK() {
        dao.addRawMaterial(textField1.getText(), textField2.getText());
        List<RawMaterial>materials = dao.getRawMaterials();
        DefaultListModel model = new DefaultListModel();
        for (RawMaterial material:materials) {
            model.addElement(material.getName() + " " + material.getMeasureUnit());
        }
        list1.setModel(model);
    }

    private void onCancel() {
        dispose();
    }


    private void createUIComponents() {
        dao = new DAO();
        List<RawMaterial>materials = dao.getRawMaterials();
        String[] listRows = new String[materials.size()];
        int i = 0;
        for (RawMaterial material:materials) {
            listRows[i++] = material.getName() + " " + material.getMeasureUnit();
        }
        list1 = new JList(listRows);
    }
}
