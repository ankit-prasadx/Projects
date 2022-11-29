import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


class Calculator extends WindowAdapter implements ActionListener
{
    public JFrame mainframe;
    public JButton b1,b2,b3,b4,b5,b6;
    public JTextField t1;
    public JLabel lobj;
    Integer iNo;

    public Calculator(int width, int height, String Title)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        mainframe = new JFrame(Title);
        mainframe.setSize(width, height);

        mainframe.addWindowListener(this);

        b1 = new JButton("Check Prime");
        b1.setBorder(null);
        b2 = new JButton("Check Perfect");
        b2.setBorder(null);
        b3 = new JButton("Check Even/Odd");
        b3.setBorder(null);
        b4 = new JButton("Get Binary");
        b4.setBorder(null);
        b5 = new JButton("Get Octal");
        b5.setBorder(null);
        b6 = new JButton("Get Hexadecimal");
        b6.setBorder(null);

        t1 = new JTextField();
        t1.setHorizontalAlignment(JLabel.CENTER);

        lobj = new JLabel();
        lobj.setHorizontalAlignment(JLabel.CENTER);

        b1.setBounds(10,300,107,50);
        b2.setBounds(125,300,105,50);
        b3.setBounds(240,300,120,50);
        b4.setBounds(10,400,107,50);
        b5.setBounds(125,400,105,50);
        b6.setBounds(240,400,120,50);

        t1.setBounds(95,185,190,30);

        lobj.setBounds(95,85,190,30);

        mainframe.add(b1);
        mainframe.add(b2);
        mainframe.add(b3);
        mainframe.add(b4);
        mainframe.add(b5);
        mainframe.add(b6);

        mainframe.add(t1);
        mainframe.add(lobj);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        mainframe.setLayout(null);
        mainframe.setVisible(true);

    }

    public void windowClosing(WindowEvent obj)
    {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent obj)
    {
        try
        {
            iNo = Integer.parseInt(t1.getText());

            if(obj.getSource() == b1)
            {
                if(iNo == 1)
                {
                    lobj.setText("It is not a prime number");
                    return;
                }
                boolean flag = false;
                int iCnt = 0;
                for(iCnt = 2; iCnt <= (iNo/2); iCnt++)
                {
                    if((iNo % iCnt) == 0)
                    {
                        flag = true;
                        break;
                    }
                }
                if(flag == false)
                {
                    lobj.setText("It is a prime number");
                }
                else 
                {
                    lobj.setText("It is not a prime number");
                }
            }
            if(obj.getSource() == b2)
            {
                int temp = iNo;
                int iCnt = 0;
                int iSum = 0;
                
                for(iCnt = 1; iCnt <= (temp/2); iCnt++)
                {
                    if((temp % iCnt) == 0)
                    {
                        iSum = iSum + iCnt;
                    }
                }
                if(iSum == iNo)
                {
                    lobj.setText("It is a perfect number");
                }
                else 
                {
                    lobj.setText("It is not a perfect number");
                }
            }
            if(obj.getSource() == b3)
            {
                if((iNo % 2) == 0)
                {
                    lobj.setText("Its is Even Number");
                }
                else
                {
                    lobj.setText("Its is Odd Number");
                }
            }
            if(obj.getSource() == b4)
            {
                lobj.setText("Binary : "+Integer.toString(iNo,2));
            }
            if(obj.getSource() == b5)
            {
                lobj.setText("Octal : "+Integer.toString(iNo,8));
            }
            if(obj.getSource() == b6)
            {
                lobj.setText("Hexadecimal : "+Integer.toString(iNo,16));
            }
        }
        catch(Exception eobj)
        {

        }

    }
}

class BasicCalculator
{
    public static void main(String G[])
    {
        Calculator cobj = new Calculator(380,500, "Basic Calculator");
    }
    
}