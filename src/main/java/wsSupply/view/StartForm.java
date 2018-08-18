package wsSupply.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartForm extends JFrame{
    private JButton ДОБАВИТЬВИДСЫРЬЯButton;
    private JPanel rootPanel;
    private JButton ВЫХОДButton;
    private JButton РЕДАКТИРОВАТЬФАСОНЫButton;
    private JButton СОЗДАТЬПЛАННАМЕСЯЦButton;
    private JButton ТЕКУЩАЯПОТРЕБНОСТЬВСЫРЬЕButton;
    private JButton ОФОРМИТЬДОСТАВКУСЫРЬЯButton;

    public StartForm() {
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        ДОБАВИТЬВИДСЫРЬЯButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRawMaterialDialog();
            }
        });
        ВЫХОДButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        СОЗДАТЬПЛАННАМЕСЯЦButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreatePlanDialog();
            }
        });
        ОФОРМИТЬДОСТАВКУСЫРЬЯButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterMaterialDialog();
            }
        });
        ТЕКУЩАЯПОТРЕБНОСТЬВСЫРЬЕButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayRequirementsForm();
            }
        });
        РЕДАКТИРОВАТЬФАСОНЫButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayModelsForm();
            }
        });
    }
}
