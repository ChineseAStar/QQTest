/**
 * 我的好友列表（包括陌生人和黑名单）
 * 目前只能固定显示一定数量的好友，还没有实现和数据库的链接
 * 当启动时自动开启与服务器的链接线程
 */
package com.qq.client.view;

import javax.swing.*;

import com.qq.client.tools.*;
import com.qq.common.*;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;

public class QqFriendList extends JFrame implements WindowListener,ActionListener,MouseListener {

	//处理第一章卡片
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	
	//处理第一章卡片
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	
	//
	String ownerId;
	JLabel[] jbls;
	
	//整个JFrame放入卡片布局
	CardLayout cl;
	
	//更新在线好友情况
	public void updateFriend(Message m)
	{
		String onLineFriend[] = m.getCon().split(" ");
		for(int i = 0;i<onLineFriend.length;i++)
		{
			for(int j = 0;j<jbls.length;j++)
			{
				if(jbls[j].getText().equals(onLineFriend[i]))
				{
					jbls[j].setEnabled(true);
				}
			}
			
		}
	}
	
	//好友信息列表
	public void cFriendList(Message m)
	{
		String Friend[] = m.getCon().split(" ");
		jphy2 = new JPanel(new GridLayout(Friend.length+1,1,4,4));
		jbls = new JLabel[Friend.length+1];
		jbls[0] = new JLabel(ownerId,new ImageIcon("images/mm.jpg"),JLabel.LEFT);
		jphy2.add(jbls[0]);
		jbls[0].addMouseListener(this);
		for(int i = 1;i<=Friend.length;i++)
		{
			jbls[i] = new JLabel(Friend[i-1],new ImageIcon("images/mm.jpg"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		jsp1 = new JScrollPane(jphy2);
		jphy1.removeAll();
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		this.validate();
		this.repaint();
		
	}
	
	//构造器
	public QqFriendList(String ownerId)
	{
		this.ownerId = ownerId;
		//处理第一张卡片
		jphy_jb1 = new JButton("我的好友");
		jphy_jb2 = new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("黑名单");
		jphy1 = new JPanel(new BorderLayout());
		//假定有50个好友
		jphy2 = new JPanel(new GridLayout(50,1,4,4));
		
		//给jphy2,初始化50个好友
		jbls = new JLabel[50];
//		for(int i = 0;i<jbls.length;i++)
//		{
//			jbls[i] = new JLabel(i+1+"",new ImageIcon("images/mm.jpg"),JLabel.LEFT);
//			jbls[i].setEnabled(false);
//			if(jbls[i].getText().equals(ownerId))
//			{
//				jbls[i].setEnabled(true);
//			}
//			jbls[i].addMouseListener(this);
//			jphy2.add(jbls[i]);
//		}
		
		jphy3 = new JPanel(new GridLayout(2,1));
		//吧两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		jsp1 = new JScrollPane(jphy2);
		
		//
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//处理第二章卡片
		jpmsr_jb1 = new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("陌生人");
		jpmsr_jb3 = new JButton("黑名单");
		jpmsr1 = new JPanel(new BorderLayout());
		//假定有50个好友
		jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
		
		//给jpmsr2,初始化20个陌生人
		JLabel[] jbls2 = new JLabel[20];
		for(int i = 0;i<jbls2.length;i++)
		{
			jbls2[i] = new JLabel(i+1+"",new ImageIcon("images/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3 = new JPanel(new GridLayout(2,1));
		//吧两个按钮加入到jpmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		jsp2 = new JScrollPane(jpmsr2);
		
		//
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		
		//在窗口显示自己的编号
		this.setTitle(ownerId);
		this.setLocation(700, 0);
		this.setSize(200,450);
		this.setVisible(true);
		this.addWindowListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jphy_jb2)
		{
			JOptionPane.showMessageDialog(this, "友情提示该功能没有写好，使用可能导致不嫩那个愉快的聊天了!");
			cl.show(this.getContentPane(), "2");
		}else if(e.getSource() == jpmsr_jb1)
		{
			cl.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//相应用户双击的事件，并得到好友的编号
		if(arg0.getClickCount() == 2)
		{
			//得到该好友的编号
			String friendNo = ((JLabel)arg0.getSource()).getText();
			QqChat qqchat = new QqChat(ownerId,friendNo);
			
			//把聊天界面加入到管理类中
			ManageQqChat.addQqChat(this.ownerId+" "+friendNo, qqchat);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		Message m = new Message();
		m.setMessType("0");
		m.setSender(ownerId);
		m.setGetter("0");
		//发送客户端退出信息
		ManageClientConServerThread.getClientConServerThread(ownerId).sendInfo(m);
		ManageClientConServerThread.delClientConServerThread(ownerId);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
