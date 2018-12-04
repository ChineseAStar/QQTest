/**
 * qq��¼���沼��
 * Ŀǰֻ�м򵥵�¼����
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

	//���山����Ҫ�����
	
	JLabel jbl1;
	
	//�����в���Ҫ�����
	//�в�������JPanel����һ����ѡ����ڹ���
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//�����ϲ���Ҫ�����
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	//��Ϣ
	String ip = "127.0.0.1";
	int port = 9999;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			QqClientLogin qq = new QqClientLogin();
	}
	
	public QqClientLogin()
	{
		//������
		jbl1 = new JLabel(new ImageIcon("images/tou.gif"));
		
		//�����ϲ�
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("images/denglu.gif"));
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb2.addActionListener(this);
		jp1_jb3 = new JButton(new ImageIcon("images/xiangdao.gif"));
		jp1_jb3.addActionListener(this);
		
		//��������ť���뵽jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		//�����в�
		jp2 = new JPanel(new GridLayout(3,3));
		
		jp2_jbl1 = new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl3 = new JLabel("��������",JLabel.CENTER);
		jp2_jbl3.addMouseListener(this);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4 = new JLabel("�߼�����",JLabel.CENTER);
		jp2_jbl4.addMouseListener(this);
		jp2_jb1 = new JButton(new ImageIcon("images/clear.gif"));
		jp2_jb1.addActionListener(this);
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("�����¼");
		jp2_jcb2 = new JCheckBox("��ס����");
		
		//�ѿؼ�����˳����뵽jp2
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
		
		//����ѡ�����
		jtp = new JTabbedPane();
		jtp.add("QQ����", jp2);
		jtp.add("�ֻ�����", jp3);
		jtp.add("�����ʼ�", jp4);
		
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
		//����û������¼
		if(arg0.getSource() == jp1_jb1)
		{			
			QqClientUser qqClientUser = new QqClientUser(ip,port);
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPassword(new String(jp2_jpf.getPassword()));
			
			if(u.getUserId().equals("") || u.getPassword().equals(""))
			{
				JOptionPane.showMessageDialog(this, "�û��������벻��Ϊ��!");
			}else{
				if(qqClientUser.checkUser(u))
				{
					//����һ��Ҫ�󷵻����ߺ��ѵ������
					try {
						//�Ѵ��������б�������ǰ
						QqFriendList qqfriendlist= new QqFriendList(u.getUserId());
						ManageQqFriendList.addQqFriendList(u.getUserId(), qqfriendlist);
						
						
						//��һ�������б�Message��
						Message m1 = new Message();
						m1.setMessType(MessageType.message_req_Friend);
						m1.setGetter("0");
						m1.setSender(u.getUserId());
						ManageClientConServerThread.getClientConServerThread(u.getUserId()).sendInfo(m1);
						//��һ�����ߺ���Message��
						Message m2 = new Message();
						m2.setMessType(MessageType.message_req_onlineFriend);
						m2.setGetter("0");
						m2.setSender(u.getUserId());
						ManageClientConServerThread.getClientConServerThread(u.getUserId()).sendInfo(m2);				
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					//ͬʱ�ص���¼����
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "�û����������!");
				}
			}
		}else if(arg0.getSource() == jp1_jb2)
		{
			//�˳�
			this.dispose();
		}else if(arg0.getSource() == jp1_jb3)
		{
			//����ע�����
			
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
			JOptionPane.showMessageDialog(this, "������δʵ�֣������ڴ�!");
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
