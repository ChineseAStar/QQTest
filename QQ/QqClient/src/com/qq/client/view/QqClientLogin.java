/**
 * qq登录界面布局
 * 目前只有简单登录功能
 */
package com.qq.client.view;

import javax.swing.*;

import com.qq.client.model.*;
import com.qq.common.*;
import com.qq.client.tools.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class QqClientLogin extends JFrame implements ActionListener,MouseListener{

	//定义北部需要的组件
	
	JLabel jbl1;
	
	//定义中部需要的组件
	//中部有三个JPanel，有一个叫选项卡窗口管理
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//定义南部需要的组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	//信息
	String ip = "127.0.0.1";
	int port = 9999;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			QqClientLogin qq = new QqClientLogin();
	}
	
	public QqClientLogin()
	{
		//处理北部
		jbl1 = new JLabel(new ImageIcon("images/tou.gif"));
		
		//处理南部
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("images/denglu.gif"));
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb2.addActionListener(this);
		jp1_jb3 = new JButton(new ImageIcon("images/xiangdao.gif"));
		jp1_jb3.addActionListener(this);
		
		//把三个按钮放入到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		//处理中部
		jp2 = new JPanel(new GridLayout(3,3));
		
		jp2_jbl1 = new JLabel("QQ号码",JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ密码",JLabel.CENTER);
		jp2_jbl3 = new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.addMouseListener(this);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4 = new JLabel("高级设置",JLabel.CENTER);
		jp2_jbl4.addMouseListener(this);
		jp2_jb1 = new JButton(new ImageIcon("images/clear.gif"));
		jp2_jb1.addActionListener(this);
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("隐身登录");
		jp2_jcb2 = new JCheckBox("记住密码");
		
		//把控件按照顺序加入到jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		//
		jp3 = new JPanel();
		
		//
		jp4 = new JPanel();
		
		//创建选项卡窗口
		jtp = new JTabbedPane();
		jtp.add("QQ号码", jp2);
		jtp.add("手机号码", jp3);
		jtp.add("电子邮件", jp4);
		
		this.add(jbl1,"North");
		this.add(jtp, "Center");
		this.add(jp1, "South");
		this.setLocation(700, 0);
		this.setSize(350,240);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//如果用户点击登录
		if(arg0.getSource() == jp1_jb1)
		{			
			QqClientUser qqClientUser = new QqClientUser(ip,port);
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPassword(new String(jp2_jpf.getPassword()));
			
			if(u.getUserId().equals("") || u.getPassword().equals(""))
			{
				JOptionPane.showMessageDialog(this, "用户名、密码不能为空!");
			}else{
				if(qqClientUser.checkUser(u))
				{
					//发送一个要求返回在线好友的请求包
					try {
						//把创建好友列表的语句提前
						QqFriendList qqfriendlist= new QqFriendList(u.getUserId());
						ManageQqFriendList.addQqFriendList(u.getUserId(), qqfriendlist);
						
						
						//做一个好友列表Message包
						Message m1 = new Message();
						m1.setMessType(MessageType.message_req_Friend);
						m1.setGetter("0");
						m1.setSender(u.getUserId());
						ManageClientConServerThread.getClientConServerThread(u.getUserId()).sendInfo(m1);
						//做一个在线好友Message包
						Message m2 = new Message();
						m2.setMessType(MessageType.message_req_onlineFriend);
						m2.setGetter("0");
						m2.setSender(u.getUserId());
						ManageClientConServerThread.getClientConServerThread(u.getUserId()).sendInfo(m2);				
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					//同时关掉登录界面
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "用户名密码错误!");
				}
			}
		}else if(arg0.getSource() == jp1_jb2)
		{
			//退出
			this.dispose();
		}else if(arg0.getSource() == jp1_jb3)
		{
			//引入注册界面
			
		}else if(arg0.getSource() == jp2_jb1)
		{
			jp2_jtf.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jp2_jbl3)
		{
			JOptionPane.showMessageDialog(this, "功能尚未实现，敬请期待!");
		}else if(arg0.getSource() == jp2_jbl4)
		{
			new LoginSetting(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
