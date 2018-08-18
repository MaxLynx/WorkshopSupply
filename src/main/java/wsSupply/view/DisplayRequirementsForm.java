package wsSupply.view;

import wsSupply.controllers.DAO;
import wsSupply.model.Requirement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.List;


public class DisplayRequirementsForm extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private JButton ОТЧЁТButton;
    private JButton ВЫХОДButton;
    DAO dao;
    private String reportText;

    DisplayRequirementsForm(){
        setContentPane(panel1);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        ВЫХОДButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ОТЧЁТButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("D:\\REPORT.txt", "UTF-8");
                    writer.print(reportText);
                }
                catch(Exception exception){

                }
                finally{
                    writer.close();
                }
            }
        });
    }

    private void createUIComponents() {
        dao = new DAO();
        reportText = "";
        List<Requirement> requirements = dao.getRequirements();
        String[] columnNames = {"MATERIAL",
                "NEEDED",
                "DELIVERED"};
        reportText = "МАТЕРИАЛ / ПОТРЕБНОСТЬ / ПОСТАВЛЕНО\r\n";
        Object[][] data = new Object [requirements.size()][3];
        int i = 0;
        for(Requirement requirement : requirements){
            data[i][0] = requirement.getRawMaterial().getName();
            reportText += data[i][0] + "   ";
            data[i][1] = requirement.getNeeded() + " " + requirement.getRawMaterial().getMeasureUnit();
            reportText += data[i][1] + "   ";
            data[i][2] = requirement.getDelivered() + " " + requirement.getRawMaterial().getMeasureUnit();
            reportText += data[i][2] + "\r\n";
            i++;
        }
        table1 = new JTable(data, columnNames);
    }
}
