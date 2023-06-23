package com.shanksgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javafx.scene.paint.Color.color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyGame extends JFrame implements ActionListener {
    
    JLabel heading;
    Font font = new Font("",Font.BOLD,40);
    
    JPanel mainpanel;
    
    JButton []btns = new JButton[9];
    
    int gameChances[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer =0;
    int wins[][]= {  
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6},
    };
    int winner=2;
    
    MyGame()
    {
        setTitle("My XO Game.....");
        setSize(850,850);
        ImageIcon icon = new ImageIcon("src/image/xo.png");
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }
    public void createGUI()
    {
        this.setLayout(new BorderLayout());
        heading = new JLabel("My XO...");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.black);
        
        this.add(heading, BorderLayout.NORTH);
        
        mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(3,3));
        
        for (int i=1;i<=9;i++)
        {
            JButton btn= new JButton();
            btn.setFont(font);
            btn.setBackground(Color.cyan);
            mainpanel.add(btn);
            btns[i-1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(mainpanel, BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton=(JButton) e.getSource();
        String namestr = currentButton.getName();
        
        int name = Integer.parseInt(namestr.trim());
        
        if(gameChances[name]==2)
        {
            if(activePlayer==1)
            {
                currentButton.setIcon(new ImageIcon("src/image/x.png"));
                gameChances[name]= activePlayer;
                activePlayer=0;
            }
            else
            {
                currentButton.setIcon(new ImageIcon("src/image/o.png"));
                gameChances[name]=activePlayer;
                activePlayer=1;
            }
            for(int []temp:wins)
            {
                if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]])&&gameChances[temp[2]]!=2)
                {
                    winner=gameChances[temp[0]];
                    JOptionPane.showMessageDialog(null,"Player "+winner+" Has Won The Game....Congrats");
                    break;
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Already Filled");
        }
        
    }
}
