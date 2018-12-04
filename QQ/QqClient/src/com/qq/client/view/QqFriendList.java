/**
 * �ҵĺ����б�����İ���˺ͺ�������
 * Ŀǰֻ�̶ܹ���ʾһ�������ĺ��ѣ���û��ʵ�ֺ����ݿ������
 * ������ʱ�Զ�������������������߳�
 */
package com.qq.client.view;

import javax.swing.*;

import com.qq.client.tools.*;
import com.qq.common.*;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;

public class QqFriendList extends JFrame implements WindowListener,ActionListener,MouseListener {

	//�����һ�¿�Ƭ
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	
	//�����һ�¿�Ƭ
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	
	//
	String ownerId;
	JLabel[] jbls;
	
	//����JFrame���뿨Ƭ����
	CardLayout cl;
	
	//�������ߺ������
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
	
	//������Ϣ�б�
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
	
	//������
	public QqFriendList(String ownerId)
	{
		this.ownerId = ownerId;
		//�����һ�ſ�Ƭ
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb2 = new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("������");
		jphy1 = new JPanel(new BorderLayout());
		//�ٶ���50������
		jphy2 = new JPanel(new GridLayout(50,1,4,4));
		
		//��jphy2,��ʼ��50������
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
		//��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		jsp1 = new JScrollPane(jphy2);
		
		//
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//����ڶ��¿�Ƭ
		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("������");
		jpmsr1 = new JPanel(new BorderLayout());
		//�ٶ���50������
		jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
		
		//��jpmsr2,��ʼ��20��İ����
		JLabel[] jbls2 = new JLabel[20];
		for(int i = 0;i<jbls2.length;i++)
		{
			jbls2[i] = new JLabel(i+1+"",new ImageIcon("images/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3 = new JPanel(new GridLayout(2,1));
		//��������ť���뵽jpmsr3
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
		
		//�ڴ�����ʾ�Լ��ı��
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
			JOptionPane.showMessageDialog(this, "������ʾ�ù���û��д�ã�ʹ�ÿ��ܵ��²����Ǹ�����������!");
			cl.show(this.getContentPane(), "2");
		}else if(e.getSource() == jpmsr_jb1)
		{
			cl.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//��Ӧ�û�˫�����¼������õ����ѵı��
		if(arg0.getClickCount() == 2)
		{
			//�õ��ú��ѵı��
			String friendNo = ((JLabel)arg0.getSource()).getText();
			QqChat qqchat = new QqChat(ownerId,friendNo);
			
			//�����������뵽��������
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
		//���Ϳͻ����˳���Ϣ
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
