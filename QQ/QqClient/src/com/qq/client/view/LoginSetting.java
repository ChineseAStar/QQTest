package com.qq.client.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginSetting extends JFrame implements ActionListener {

	JTextField jtf1,jtf2;
	JLabel jl1,jl2;
	JButton jb1,jb2;
	JPanel jp1,jp2;
	QqClientLogin qcl;
	
	public LoginSetting(QqClientLogin qcl)
	{
		this.qcl = qcl;		
		
		//jp1
		jp1 = new JPanel(new GridLayout(2,2));
		jl1 = new JLabel("IP地址",JLabel.CENTER);
		jl2 = new JLabel("端口号",JLabel.CENTER);
		jtf1 = new JTextField("127.0.0.1");
		jtf2 = new JTextField();
		jtf2.setText("9999");
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jl2);
		jp1.add(jtf2);
		
		//jp2
		jp2 = new JPanel();
		jb1 = new JButton("确定");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.addActionListener(this);
		jp2.add(jb1);
		jp2.add(jb2);
		
		
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(200,150);
		this.setLocation(700, 0);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		
		new OrderCheck(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1)
		{
			qcl.ip = jtf1.getText();
			qcl.port = Integer.parseInt(jtf2.getText());
			this.dispose();
		}else if(e.getSource() == jb2)
		{
			this.dispose();
		}
	}
	
}
