package wsSupply.view;

import wsSupply.controllers.DAO;
import wsSupply.model.Model;
import wsSupply.model.RawMaterial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class DisplayModelsForm  extends JFrame{
    private JList list1;
    private JButton ПРОСМОТРЕТЬButton;
    private JButton УДАЛИТЬФАСОНButton;
    private JButton ВЫХОДButton;
    private JButton НОВЫЙФАСОНButton;
    private JPanel panel1;
    private JButton ОБНОВИТЬИНФОButton;
    private DAO dao;

    public DisplayModelsForm(){
        setContentPane(panel1);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
 /*       ПРОСМОТРЕТЬButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
    /*    УДАЛИТЬФАСОНButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
        ВЫХОДButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        НОВЫЙФАСОНButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddModelDialog();

            }
        });
        ОБНОВИТЬИНФОButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Model> models = dao.getModels();
                DefaultListModel listModel = new DefaultListModel();
                for (Model model:models) {
                    listModel.addElement(model.getName() + " (" + model.getType() + ")");
                }
                list1.setModel(listModel);
            }
        });
    }

    private void createUIComponents() {
        dao = new DAO();
        List<Model> models = dao.getModels();
        String[] listRows = new String[models.size()];
        int i = 0;
        for (Model model:models) {
            listRows[i++] = model.getName() + " (" + model.getType() + ")";
        }
        list1 = new JList(listRows);
    }
}
