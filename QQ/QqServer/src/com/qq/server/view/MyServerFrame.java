/**
 * 这是服务器的控制界面，可以完成启动服务器，关闭服务器
 * 可以管理和监控用户
 */
package com.qq.server.view;
import javax.swing.*;

import com.qq.server.bnmodel.*;
import com.qq.server.tools.ManageServer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class MyServerFrame extends JFrame implements ActionListener {

	JPanel jp1;
	JButton jb1,jb2;
	JTextField jtf;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame msf = new MyServerFrame();
	}

	public MyServerFrame()
	{
		jp1 = new JPanel();
		jb1 = new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2 = new JButton("关闭服务器");
		jb2.addActionListener(this);
		jtf = new JTextField("9999");
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jtf);
		
		
		this.add(jp1);
		this.setSize(500,400);
		this.setLocation(700, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jb1)
		{
			MyQqServer ms = new MyQqServer(jtf.getText());
			ms.start();
			ManageServer.addServer(jtf.getText(), ms);
		}else if(arg0.getSource() == jb2)
		{
			try {
				ManageServer.delServer(jtf.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
