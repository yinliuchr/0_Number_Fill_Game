 /**
 * Created by liuyin14 on 2016/10/7.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PlayNumbers extends JFrame implements ActionListener{
    JButton test1;
    JButton test2;
    JButton deep;
    JButton exit;
    private JPanel panel1;

    public static void main(String[] args) {
        PlayNumbers game = new PlayNumbers();
        game.go();
    }
    public void go(){
        setTitle("NUMBERPLATE");
        setSize(650,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置背景图片
        ImageIcon bg = new ImageIcon("a1.jpg");
        JLabel label = new JLabel(bg);
        label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
        getLayeredPane().add(label,new Integer(-30001));

        //background image at the bottom, < -30000(default mini)
        //设置ContentPane 透明
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setOpaque(false);

        // contentPane = topPanel + buttonPanel
        contentPane.setLayout(new BorderLayout(0,40));
        JPanel topPanel = getTopPanel();
        topPanel.setOpaque(false);
        contentPane.add(topPanel,"Center");
        JPanel buttonPanel = getButtonPanel();
        contentPane.add(buttonPanel,"South");

        setVisible(true);

    }

    private JPanel getTopPanel() {
        //topPanel = some JButtons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        JLabel caption = new JLabel("Number Plate!");
        caption.setFont(new Font("隶书",Font.ITALIC,40));
        panel.add(caption);

        test1 = new JButton("试炼1");
        test1.setFont(new Font("楷体",Font.ROMAN_BASELINE,30));
        panel.add(test1);
        test1.addActionListener(this);

        test2 = new JButton("试炼2");
        test2.setFont(new Font("楷体",Font.ROMAN_BASELINE,30));
        panel.add(test2);
        test2.addActionListener(this);

        deep = new JButton("更多难度");
        deep.setFont(new Font("楷体",Font.BOLD,30));
        panel.add(deep);
        deep.addActionListener(this);

        return panel;
    }

    private JPanel getButtonPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(50,0));

        exit = new JButton("我要退出！");
        exit.setFont(new Font("宋体",Font.HANGING_BASELINE,50));
        panel.add(exit,BorderLayout.CENTER);
        exit.addActionListener(this);

        return panel;
    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();

        if(obj == test1){

            int[] res = LargestNum.main();

            JFrame T1 = new JFrame("试炼1");
            T1.setSize(850, 300);

            JPanel P1 = (JPanel)T1.getContentPane();
            P1.setOpaque(false);

            //P1 = P10 + P11 + P12
            P1.setLayout(new BorderLayout());

            //P10
            JPanel P10 = new JPanel();
            JLabel L10 = new JLabel("请将1 2 3 4 5 6 这6个数字填入下面的方框，使其积最大：");
            L10.setFont(test1.getFont());
            P10.add(L10);
            P1.add(P10,BorderLayout.NORTH);

            //P11
            JPanel P11 = new JPanel();
            P11.setLayout(new FlowLayout());

            JTextField text11 = new JTextField(4);
            JTextField text12 = new JTextField(4);
            JTextField text13 = new JTextField(4);
            JLabel L20 = new JLabel("*");
            JTextField text14 = new JTextField(4);
            JTextField text15 = new JTextField(4);
            JTextField text16 = new JTextField(4);
            JLabel L21 = new JLabel("=");
            JLabel L22 = new JLabel("jieguo    ");
            JButton submit = new JButton("提交:");
            JLabel L23 = new JLabel("wrong");
            text11.setFont(test1.getFont());
            text12.setFont(test1.getFont());
            text13.setFont(test1.getFont());
            text14.setFont(test1.getFont());
            text15.setFont(test1.getFont());
            text16.setFont(test1.getFont());
            L20.setFont(test1.getFont());
            L21.setFont(test1.getFont());
            L22.setFont(test1.getFont());
            submit.setFont(test1.getFont());
            L23.setFont(test1.getFont());

            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    L23.setText("wrong");

                    int[] judge = {0,1,1,1,1,1,1};

                    String[] te1 = new String[7];

                    te1[1] = text11.getText();
                    te1[2] = text12.getText();
                    te1[3] = text13.getText();
                    te1[4] = text14.getText();
                    te1[5] = text15.getText();
                    te1[6] = text16.getText();

                    for(int i = 1; i < 7; ++ i){
                        if(te1[i].equals("1")) judge[1] = 0;
                        if(te1[i].equals("2")) judge[2] = 0;
                        if(te1[i].equals("3")) judge[3] = 0;
                        if(te1[i].equals("4")) judge[4] = 0;
                        if(te1[i].equals("5")) judge[5] = 0;
                        if(te1[i].equals("6")) judge[6] = 0;
                    }

                    int mark = 0;
                    for(int i = 1; i < 7; ++ i) mark += judge[i];
                    if(mark != 0){
                        L23.setText("输入不符合要求！请重新输入！");
                    }
                    else{
                        String fac1 = te1[1] + te1[2] + te1[3];
                        String fac2 = te1[4] + te1[5] + te1[6];
                        int te_1 = Integer.parseInt(fac1);
                        int te_2 = Integer.parseInt(fac2);
                        int te_re = te_1 * te_2;
                        L22.setText(String.valueOf(te_re));
                        if(te_re == res[2]) L23.setText("Right!");
                    }
                }
            });

            P11.add(text11);
            P11.add(text12);
            P11.add(text13);


            P11.add(L20);
            P11.add(text14);
            P11.add(text15);
            P11.add(text16);
            P11.add(L21);
            P11.add(L22);
            P11.add(submit);
            P11.add(L23);
            P1.add(P11,BorderLayout.CENTER);

            //P12
            JPanel P12 = new JPanel();
            P12.setLayout(new FlowLayout());
            JButton ai = new JButton("AI");
            JLabel ai_re = new JLabel("ai");
            JButton tui = new JButton("exit");
            ai.setFont(test1.getFont());
            ai_re.setFont(test1.getFont());
            tui.setFont(test1.getFont());

            ai.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ai_re.setText("应填的数： " + res[0] + " 与 " + res[1] + "  其结果为： "  + res[2] + " .");
                }
            });
            tui.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    T1.dispose();
                }
            });

            P12.add(ai);
            P12.add(ai_re);
            P12.add(tui);

            P1.add(P12,BorderLayout.SOUTH);

            T1.setVisible(true);

        }



        else if(obj == test2){
            int[] jieguo = NearestNum.main();

            JFrame T2 = new JFrame("试炼2");
            T2.setSize(1100, 300);
            JPanel P2 = (JPanel)T2.getContentPane();
            P2.setLayout(new BorderLayout());
            //P2 = P20 + P21 + P22

            //P20
            JPanel P20 = new JPanel();
            JLabel L201 = new JLabel("请将1 2 3 4 5 6 这六个数填入以下6个方框中，使其离400000最近：");
            L201.setFont(new Font("楷体",Font.BOLD,30));
            P20.add(L201);
            P2.add(P20,BorderLayout.NORTH);

            //P21
            JPanel P21 = new JPanel();
            JTextField text21 = new JTextField(2);
            JTextField text22 = new JTextField(2);
            JTextField text23 = new JTextField(2);
            JTextField text24 = new JTextField(2);
            JTextField text25 = new JTextField(2);
            JTextField text26 = new JTextField(2);

            text21.setFont(L201.getFont());
            text22.setFont(L201.getFont());
            text23.setFont(L201.getFont());
            text24.setFont(L201.getFont());
            text25.setFont(L201.getFont());
            text26.setFont(L201.getFont());



            JLabel L211 = new JLabel("jieguo");
            JButton submit = new JButton("提交:");
            JLabel L212 = new JLabel("与400000的距离:");
            JLabel L213 = new JLabel("差值");
            JLabel L214 = new JLabel("wrong");

            L211.setFont(L201.getFont());
            submit.setFont(L201.getFont());
            L212.setFont(L201.getFont());
            L213.setFont(L201.getFont());
            L214.setFont(L201.getFont());

            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    L214.setText("wrong");

                    int[] judge = {0,1,1,1,1,1,1};

                    String[] te2 = new String[7];

                    te2[1] = text21.getText();
                    te2[2] = text22.getText();
                    te2[3] = text23.getText();
                    te2[4] = text24.getText();
                    te2[5] = text25.getText();
                    te2[6] = text26.getText();

                    for(int i = 1; i < 7; ++ i){
                        if(te2[i].equals("1")) judge[1] = 0;
                        if(te2[i].equals("2")) judge[2] = 0;
                        if(te2[i].equals("3")) judge[3] = 0;
                        if(te2[i].equals("4")) judge[4] = 0;
                        if(te2[i].equals("5")) judge[5] = 0;
                        if(te2[i].equals("6")) judge[6] = 0;
                    }

                    int mark = 0;
                    for(int i = 1; i < 7; ++ i) mark += judge[i];
                    if(mark != 0){
                        L214.setText("输入不符合要求！请重新输入！");
                        L214.setFont(L201.getFont());
                    }
                    else{
                        String te = te2[1] + te2[2] + te2[3] + te2[4] + te2[5] + te2[6];
                        int tei = Integer.parseInt(te);
                        L211.setText(te);
                        int distance = Math.abs(tei-400000);
                        L213.setText(String.valueOf(distance));
                        if(distance == jieguo[1]) L214.setText("Right!");
                    }
                }
            });
            P21.add(text21);
            P21.add(text22);
            P21.add(text23);
            P21.add(text24);
            P21.add(text25);
            P21.add(text26);
            P21.add(L211);
            P21.add(submit);
            P21.add(L212);
            P21.add(L213);
            P21.add(L214);
            P2.add(P21,BorderLayout.CENTER);

            //P22
            JPanel P22 = new JPanel();
            P22.setLayout(new FlowLayout());
            JButton ai2 = new JButton("AI");
            JLabel ai_re = new JLabel("ai");
            JButton tui2 = new JButton("exit");

            ai2.setFont(L201.getFont());
            ai_re.setFont(L201.getFont());
            tui2.setFont(L201.getFont());

            ai2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String ai_1 = String.valueOf(jieguo[0]);
                    String ai_2 = String.valueOf(jieguo[1]);
                    ai_re.setText("应填的数: " + ai_1 + ",  " + "与400000的距离:" + ai_2 + " .");
                }
            });

            tui2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                T2.dispose();
                }
            });

            P22.add(ai2);
            P22.add(ai_re);
            P22.add(tui2);

            P2.add(P22,BorderLayout.SOUTH);



//            JLabel a = new JLabel(String.valueOf(jieguo[0]) + " " + String.valueOf(jieguo[1]));
//            T1.add(a);
//            LargestNum.main();
            T2.setVisible(true);
        }
        else if(obj == deep){
//            JOptionPane.showMessageDialog(null,"aindg");
            JFrame T3 = new JFrame("难度系数");
            T3.setSize(500,600);
            JPanel P3 = (JPanel)T3.getContentPane();
            P3.setLayout(new GridLayout(4,1));

            JButton B31 = new JButton("Simple");
            JButton B32 = new JButton("Normal");
            JButton B33 = new JButton("Hard");
            JButton B34 = new JButton("Extreme!");

            B31.setFont(new Font("楷体",Font.BOLD,40));
            B32.setFont(new Font("楷体",Font.BOLD,40));
            B33.setFont(new Font("楷体",Font.BOLD,40));
            B34.setFont(new Font("楷体",Font.BOLD,40));


            B31.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int res = SeveralMultipleBiggestNum.main(1);

                    JFrame T31 = new JFrame("One multiple_biggest");
                    T31.setSize(1200,300);
                    JPanel P31 = (JPanel)T31.getContentPane();
                    P31.setLayout(new BorderLayout());
                    //P31 = Pa + Pb + Pc

                    //Pa
                    JPanel Pa = new JPanel();
                    Pa.setLayout(new FlowLayout());
                    JLabel La = new JLabel("请在3 2 1 5 1 2 5这七个数中填入一个“*”号，使乘法算式结果最大:");
                    La.setFont(new Font("楷体",Font.BOLD,30));
                    //JTextField Lt = new JTextField(8);
                    Pa.add(La);
                    //Pa.add(Lt);

                    P31.add(Pa,BorderLayout.NORTH);

                    //Pb
                    JPanel Pb = new JPanel();
                    Pb.setLayout(new FlowLayout());

//                    JLabel[] L0 = new JLabel[8];

                    JLabel L01 = new JLabel("3");
                    JLabel L02 = new JLabel("2");
                    JLabel L03 = new JLabel("1");
                    JLabel L04 = new JLabel("5");
                    JLabel L05 = new JLabel("1");
                    JLabel L06 = new JLabel("2");
                    JLabel L07 = new JLabel("5");
                    L01.setFont(La.getFont());
                    L02.setFont(La.getFont());
                    L03.setFont(La.getFont());
                    L04.setFont(La.getFont());
                    L05.setFont(La.getFont());
                    L06.setFont(La.getFont());
                    L07.setFont(La.getFont());

                    JTextField text11 = new JTextField(2);
                    JTextField text12 = new JTextField(2);
                    JTextField text13 = new JTextField(2);
                    JTextField text14 = new JTextField(2);
                    JTextField text15 = new JTextField(2);
                    JTextField text16 = new JTextField(2);
                    text11.setFont(La.getFont());
                    text12.setFont(La.getFont());
                    text13.setFont(La.getFont());
                    text14.setFont(La.getFont());
                    text15.setFont(La.getFont());
                    text16.setFont(La.getFont());


                    JLabel L21 = new JLabel("=");
                    JLabel L22 = new JLabel("jieguo    ");
                    JButton submit = new JButton("提交:");
                    JLabel L23 = new JLabel("  wrong");
                    L21.setFont(La.getFont());
                    L22.setFont(La.getFont());
                    L23.setFont(La.getFont());
                    submit.setFont(La.getFont());

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            L23.setText("wrong");
                            String[] te1 = new String[7];
                            te1[1] = text11.getText();
                            te1[2] = text12.getText();
                            te1[3] = text13.getText();
                            te1[4] = text14.getText();
                            te1[5] = text15.getText();
                            te1[6] = text16.getText();
                            String sym = new String();
                            for(int i = 1; i < 7; ++ i){
                                sym += te1[i];
                            }
                            if(!sym.equals("*")) L23.setText("输入不符合要求！请重新填！");
                            else{
                                String zong = new String("3215125");
                                String fac1 = new String();
                                String fac2 = new String();
                                for(int i = 1; i < 7; ++ i){
                                    if(te1[i].equals("*")){
                                        fac1 = zong.substring(0,i);
                                        fac2 = zong.substring(i);
                                        break;
                                    }
                                }
                                int te_1 = Integer.parseInt(fac1);
                                int te_2 = Integer.parseInt(fac2);
                                int te_re = te_1 * te_2;
                                L22.setText(String.valueOf(te_re));
                                if(te_re == res) L23.setText("  Right!");
                            }

                        }
                    });

                    Pb.add(L01);
                    Pb.add(text11);
                    Pb.add(L02);
                    Pb.add(text12);
                    Pb.add(L03);
                    Pb.add(text13);
                    Pb.add(L04);
                    Pb.add(text14);
                    Pb.add(L05);
                    Pb.add(text15);
                    Pb.add(L06);
                    Pb.add(text16);
                    Pb.add(L07);

                    Pb.add(L21);
                    Pb.add(L22);
                    Pb.add(submit);
                    Pb.add(L23);
                    P31.add(Pb,BorderLayout.CENTER);

                    //Pc
                    JPanel Pc = new JPanel();
                    Pc.setLayout(new FlowLayout());
                    JButton ai = new JButton("AI");
                    JLabel ai_re = new JLabel("ai");
                    JButton tui = new JButton("exit");
                    ai.setFont(La.getFont());
                    ai_re.setFont(La.getFont());
                    tui.setFont(La.getFont());

                    ai.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                    ai_re.setText("应填的数： " + res[0] + " 与 " + res[1] + "  其结果为： "  + res[2] + " .");
                            ai_re.setText("最大的结果应该是： " + res);
                        }
                    });
                    tui.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            T31.dispose();
                        }
                    });

                    Pc.add(ai);
                    Pc.add(ai_re);
                    Pc.add(tui);

                    P31.add(Pc,BorderLayout.SOUTH);

                    T31.setVisible(true);
                }
            });

            B32.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int res = SeveralMultipleBiggestNum.main(2);            //ai 结果

                    JFrame T31 = new JFrame("Two_multiple_biggest");
                    T31.setSize(1200,300);
                    JPanel P31 = (JPanel)T31.getContentPane();
                    P31.setLayout(new BorderLayout());
                    //P31 = Pa + Pb + Pc

                    //Pa
                    JPanel Pa = new JPanel();
                    Pa.setLayout(new FlowLayout());
                    JLabel La = new JLabel("请在3 2 1 5 1 2 5这七个数中填入2个“*”号，使乘法算式结果最大:");
                    La.setFont(new Font("楷体",Font.BOLD,30));
                    //JTextField Lt = new JTextField(8);
                    Pa.add(La);
                    //Pa.add(Lt);

                    P31.add(Pa,BorderLayout.NORTH);

                    //Pb
                    JPanel Pb = new JPanel();
                    Pb.setLayout(new FlowLayout());

//                    JLabel[] L0 = new JLabel[8];

                    JLabel L01 = new JLabel("3");
                    JLabel L02 = new JLabel("2");
                    JLabel L03 = new JLabel("1");
                    JLabel L04 = new JLabel("5");
                    JLabel L05 = new JLabel("1");
                    JLabel L06 = new JLabel("2");
                    JLabel L07 = new JLabel("5");
                    L01.setFont(La.getFont());
                    L02.setFont(La.getFont());
                    L03.setFont(La.getFont());
                    L04.setFont(La.getFont());
                    L05.setFont(La.getFont());
                    L06.setFont(La.getFont());
                    L07.setFont(La.getFont());

                    JTextField text11 = new JTextField(2);
                    JTextField text12 = new JTextField(2);
                    JTextField text13 = new JTextField(2);
                    JTextField text14 = new JTextField(2);
                    JTextField text15 = new JTextField(2);
                    JTextField text16 = new JTextField(2);
                    text11.setFont(La.getFont());
                    text12.setFont(La.getFont());
                    text13.setFont(La.getFont());
                    text14.setFont(La.getFont());
                    text15.setFont(La.getFont());
                    text16.setFont(La.getFont());


                    JLabel L21 = new JLabel("=");
                    JLabel L22 = new JLabel("jieguo    ");
                    JButton submit = new JButton("提交:");
                    JLabel L23 = new JLabel("wrong");
                    L21.setFont(La.getFont());
                    L22.setFont(La.getFont());
                    L23.setFont(La.getFont());
                    submit.setFont(La.getFont());

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            L23.setText("wrong");
                            boolean closemulti = true;
                            String[] te1 = new String[7];
                            te1[1] = text11.getText();
                            te1[2] = text12.getText();
                            te1[3] = text13.getText();
                            te1[4] = text14.getText();
                            te1[5] = text15.getText();
                            te1[6] = text16.getText();
                            String sym = new String();
                            for(int i = 1; i < 7; ++ i){
                                if(te1[i].equals("**")) {
                                    closemulti = false;
                                    break;
                                }
                                sym += te1[i];
                            }
                            if((!sym.equals("**")) || !closemulti) L23.setText("输入不符合要求！请重新填！");
                            else{
                                String zong = new String("3215125");
                                String fac1 = new String();
                                String fac2 = new String();
                                String fac3 = new String();
                                int[] multind = new int[2];
                                int ind = 0;
                                for(int i = 1; i < 7; ++ i) {
                                    if (te1[i].equals("*")) {
                                        multind[ind] = i;
                                        ++ ind;
                                    }
                                    if(ind > 1) break;
                                }

                                fac1 = zong.substring(0,multind[0]);
                                fac2 = zong.substring(multind[0],multind[1]);
                                fac3 = zong.substring(multind[1]);


                                int te_1 = Integer.parseInt(fac1);
                                int te_2 = Integer.parseInt(fac2);
                                int te_3 = Integer.parseInt(fac3);
//                                System.out.println(fac1 + " " + fac2 + " " + fac3);
                                int te_re = te_1 * te_2 * te_3;
                                L22.setText(String.valueOf(te_re));
                                if(te_re == res) L23.setText("Right!");
                            }

                        }
                    });

                    Pb.add(L01);
                    Pb.add(text11);
                    Pb.add(L02);
                    Pb.add(text12);
                    Pb.add(L03);
                    Pb.add(text13);
                    Pb.add(L04);
                    Pb.add(text14);
                    Pb.add(L05);
                    Pb.add(text15);
                    Pb.add(L06);
                    Pb.add(text16);
                    Pb.add(L07);

                    Pb.add(L21);
                    Pb.add(L22);
                    Pb.add(submit);
                    Pb.add(L23);
                    P31.add(Pb,BorderLayout.CENTER);

                    //Pc
                    JPanel Pc = new JPanel();
                    Pc.setLayout(new FlowLayout());
                    JButton ai = new JButton("AI");
                    JLabel ai_re = new JLabel("ai");
                    JButton tui = new JButton("exit");
                    ai.setFont(La.getFont());
                    ai_re.setFont(La.getFont());
                    tui.setFont(La.getFont());

                    ai.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                    ai_re.setText("应填的数： " + res[0] + " 与 " + res[1] + "  其结果为： "  + res[2] + " .");
                            ai_re.setText("最大的结果应该是： " + res);
                        }
                    });
                    tui.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            T31.dispose();
                        }
                    });

                    Pc.add(ai);
                    Pc.add(ai_re);
                    Pc.add(tui);

                    P31.add(Pc,BorderLayout.SOUTH);

                    T31.setVisible(true);
                }
            });

            B33.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int res = SeveralMultipleBiggestNum.main(3);            //ai 结果

                    JFrame T31 = new JFrame("Three_multiple_biggest");
                    T31.setSize(1200,300);
                    JPanel P31 = (JPanel)T31.getContentPane();
                    P31.setLayout(new BorderLayout());
                    //P31 = Pa + Pb + Pc

                    //Pa
                    JPanel Pa = new JPanel();
                    Pa.setLayout(new FlowLayout());
                    JLabel La = new JLabel("请在3 2 1 5 1 2 5这七个数中填入3个“*”号，使乘法算式结果最大:");
                    La.setFont(new Font("楷体",Font.BOLD,30));
                    //JTextField Lt = new JTextField(8);
                    Pa.add(La);
                    //Pa.add(Lt);

                    P31.add(Pa,BorderLayout.NORTH);

                    //Pb
                    JPanel Pb = new JPanel();
                    Pb.setLayout(new FlowLayout());

//                    JLabel[] L0 = new JLabel[8];

                    JLabel L01 = new JLabel("3");
                    JLabel L02 = new JLabel("2");
                    JLabel L03 = new JLabel("1");
                    JLabel L04 = new JLabel("5");
                    JLabel L05 = new JLabel("1");
                    JLabel L06 = new JLabel("2");
                    JLabel L07 = new JLabel("5");
                    L01.setFont(La.getFont());
                    L02.setFont(La.getFont());
                    L03.setFont(La.getFont());
                    L04.setFont(La.getFont());
                    L05.setFont(La.getFont());
                    L06.setFont(La.getFont());
                    L07.setFont(La.getFont());

                    JTextField text11 = new JTextField(2);
                    JTextField text12 = new JTextField(2);
                    JTextField text13 = new JTextField(2);
                    JTextField text14 = new JTextField(2);
                    JTextField text15 = new JTextField(2);
                    JTextField text16 = new JTextField(2);
                    text11.setFont(La.getFont());
                    text12.setFont(La.getFont());
                    text13.setFont(La.getFont());
                    text14.setFont(La.getFont());
                    text15.setFont(La.getFont());
                    text16.setFont(La.getFont());


                    JLabel L21 = new JLabel("=");
                    JLabel L22 = new JLabel("jieguo    ");
                    JButton submit = new JButton("提交:");
                    JLabel L23 = new JLabel("wrong");
                    L21.setFont(La.getFont());
                    L22.setFont(La.getFont());
                    L23.setFont(La.getFont());
                    submit.setFont(La.getFont());

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            L23.setText("wrong");
                            boolean closemulti = true;
                            String[] te1 = new String[7];
                            te1[1] = text11.getText();
                            te1[2] = text12.getText();
                            te1[3] = text13.getText();
                            te1[4] = text14.getText();
                            te1[5] = text15.getText();
                            te1[6] = text16.getText();
                            String sym = new String();
                            for(int i = 1; i < 7; ++ i){
                                if(te1[i].equals("**") || te1[i].equals("***")) {
                                    closemulti = false;
                                    break;
                                }
                                sym += te1[i];
                            }
                            if((!sym.equals("***")) || !closemulti) L23.setText("输入不符合要求！请重新填！");
                            else{
                                String zong = new String("3215125");
                                String fac1 = new String();
                                String fac2 = new String();
                                String fac3 = new String();
                                String fac4 = new String();
                                int[] multind = new int[3];
                                int ind = 0;
                                for(int i = 1; i < 7; ++ i) {
                                    if (te1[i].equals("*")) {
                                        multind[ind] = i;
                                        ++ ind;
                                    }
                                    if(ind > 2) break;
                                }

                                fac1 = zong.substring(0,multind[0]);
                                fac2 = zong.substring(multind[0],multind[1]);
                                fac3 = zong.substring(multind[1],multind[2]);
                                fac4 = zong.substring(multind[2]);


                                int te_1 = Integer.parseInt(fac1);
                                int te_2 = Integer.parseInt(fac2);
                                int te_3 = Integer.parseInt(fac3);
                                int te_4 = Integer.parseInt(fac4);
//                                System.out.println(fac1 + " " + fac2 + " " + fac3);
                                int te_re = te_1 * te_2 * te_3 * te_4;
                                L22.setText(String.valueOf(te_re));
                                if(te_re == res) L23.setText("Right!");
                            }

                        }
                    });

                    Pb.add(L01);
                    Pb.add(text11);
                    Pb.add(L02);
                    Pb.add(text12);
                    Pb.add(L03);
                    Pb.add(text13);
                    Pb.add(L04);
                    Pb.add(text14);
                    Pb.add(L05);
                    Pb.add(text15);
                    Pb.add(L06);
                    Pb.add(text16);
                    Pb.add(L07);

                    Pb.add(L21);
                    Pb.add(L22);
                    Pb.add(submit);
                    Pb.add(L23);
                    P31.add(Pb,BorderLayout.CENTER);

                    //Pc
                    JPanel Pc = new JPanel();
                    Pc.setLayout(new FlowLayout());
                    JButton ai = new JButton("AI");
                    JLabel ai_re = new JLabel("ai");
                    JButton tui = new JButton("exit");
                    ai.setFont(La.getFont());
                    ai_re.setFont(La.getFont());
                    tui.setFont(La.getFont());

                    ai.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                    ai_re.setText("应填的数： " + res[0] + " 与 " + res[1] + "  其结果为： "  + res[2] + " .");
                            ai_re.setText("最大的结果应该是： " + res);
                        }
                    });
                    tui.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            T31.dispose();
                        }
                    });

                    Pc.add(ai);
                    Pc.add(ai_re);
                    Pc.add(tui);

                    P31.add(Pc,BorderLayout.SOUTH);

                    T31.setVisible(true);
                }
            });

            B34.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int[] res = NearestProduct.main();

                    JFrame T31 = new JFrame("近水楼台");
                    T31.setSize(1500,300);
                    JPanel P31 = (JPanel)T31.getContentPane();
                    P31.setLayout(new BorderLayout());
                    //P31 = Pa + Pb + Pc

                    //Pa
                    JPanel Pa = new JPanel();
                    Pa.setLayout(new FlowLayout());
                    JLabel La = new JLabel("请将1 2 3 4 5 6六个数分别填入下面方框中，使乘法算式结果最接近给定值：");
                    La.setFont(new Font("楷体",Font.BOLD,30));
                    JLabel Lt = new JLabel(String.valueOf(res[0]));
                    Lt.setFont(La.getFont());
                    Pa.add(La);
                    Pa.add(Lt);

                    P31.add(Pa,BorderLayout.NORTH);

                    //Pb
                    JPanel Pb = new JPanel();
                    Pb.setLayout(new FlowLayout());
                    JTextField text11 = new JTextField(2);
                    JTextField text12 = new JTextField(2);
                    JTextField text13 = new JTextField(2);
                    JLabel L20 = new JLabel("*");
                    JTextField text14 = new JTextField(2);
                    JTextField text15 = new JTextField(2);
                    JTextField text16 = new JTextField(2);
                    JLabel L21 = new JLabel("=");
                    JLabel L22 = new JLabel("jieguo    ");
                    JButton submit = new JButton("提交:");
                    JLabel L23 = new JLabel("wrong");

                    text11.setFont(La.getFont());
                    text12.setFont(La.getFont());
                    text13.setFont(La.getFont());
                    text14.setFont(La.getFont());
                    text15.setFont(La.getFont());
                    text16.setFont(La.getFont());
                    L20.setFont(La.getFont());
                    L21.setFont(La.getFont());
                    L22.setFont(La.getFont());
                    L23.setFont(La.getFont());
                    submit.setFont(La.getFont());

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            L23.setText("wrong");

                            int[] judge = {0,1,1,1,1,1,1};

                            String[] te1 = new String[7];

                            te1[1] = text11.getText();
                            te1[2] = text12.getText();
                            te1[3] = text13.getText();
                            te1[4] = text14.getText();
                            te1[5] = text15.getText();
                            te1[6] = text16.getText();

                            for(int i = 1; i < 7; ++ i){
                                if(te1[i].equals("1")) judge[1] = 0;
                                if(te1[i].equals("2")) judge[2] = 0;
                                if(te1[i].equals("3")) judge[3] = 0;
                                if(te1[i].equals("4")) judge[4] = 0;
                                if(te1[i].equals("5")) judge[5] = 0;
                                if(te1[i].equals("6")) judge[6] = 0;
                            }

                            int mark = 0;
                            for(int i = 1; i < 7; ++ i) mark += judge[i];
                            if(mark != 0){
                                L23.setText("输入不符合要求！请重新输入！");
                            }
                            else{
                                String fac1 = te1[1] + te1[2] + te1[3];
                                String fac2 = te1[4] + te1[5] + te1[6];
                                int te_1 = Integer.parseInt(fac1);
                                int te_2 = Integer.parseInt(fac2);
                                int te_re = te_1 * te_2;
                                L22.setText(String.valueOf(te_re));
                                if(te_re == res[3]) L23.setText("Right!");
                            }
                        }
                    });

                    Pb.add(text11);
                    Pb.add(text12);
                    Pb.add(text13);
                    Pb.add(L20);
                    Pb.add(text14);
                    Pb.add(text15);
                    Pb.add(text16);
                    Pb.add(L21);
                    Pb.add(L22);
                    Pb.add(submit);
                    Pb.add(L23);
                    P31.add(Pb,BorderLayout.CENTER);

                    //Pc
                    JPanel Pc = new JPanel();
                    Pc.setLayout(new FlowLayout());
                    JButton ai = new JButton("AI");
                    JLabel ai_re = new JLabel("ai");
                    JButton tui = new JButton("exit");
                    ai.setFont(La.getFont());
                    ai_re.setFont(La.getFont());
                    tui.setFont(La.getFont());

                    ai.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                    ai_re.setText("应填的数： " + res[1] + " 与 " + res[2] + "  其结果为： "  + res[3] + " .");
                        }
                    });
                    tui.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            T31.dispose();
                        }
                    });

                    Pc.add(ai);
                    Pc.add(ai_re);
                    Pc.add(tui);

                    P31.add(Pc,BorderLayout.SOUTH);
                    T31.setVisible(true);
                }
            });

            P3.add(B31);
            P3.add(B32);
            P3.add(B33);
            P3.add(B34);

            T3.setVisible(true);
        }


        else if(obj == exit){
            System.exit(0);
        }

    }

}
