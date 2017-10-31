package easy.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author limengyu
 * @create 2017/10/30
 */
public class TranslationForm {

    public JRadioButton google;
    public JRadioButton baidu;
    public JRadioButton jinshan;
    public JRadioButton tencent;
    public JRadioButton omi;
    public JPanel mainPanel;
    private JLabel desc;
    private String selectValue;
    private JRadioButton currentSelectButton;


    public TranslationForm() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton temp = (JRadioButton)e.getSource();
                if(temp.isSelected()){
                    selectValue = temp.getText();
                    currentSelectButton = temp;
                    System.out.println("当前选中: "+temp.getText());
                }
            }
        };
        baidu.addActionListener(listener);
        jinshan.addActionListener(listener);
        tencent.addActionListener(listener);
        omi.addActionListener(listener);
        google.addActionListener(listener);
    }
    public String getSelectValue() {
        return selectValue;
    }

    public JRadioButton getCurrentSelectButton(){
        return currentSelectButton;
    }
}
