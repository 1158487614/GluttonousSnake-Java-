import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Snake_Frame extends JFrame {
	/**
	 * 
	 */
	private String[] flag = { "��ʼ", "��ͣ" };
	private int index = 0;
	private static final long serialVersionUID = 1L;
	public Snake_Panel snake_panel;
	private Snake_Thread snakethread;
	private boolean Control_Frame = true;

	JMenu game = new JMenu("��Ϸ");
	JMenu about = new JMenu("����");
	JMenuItem author=new JMenuItem("����");
	JMenuItem control = new JMenuItem(flag[0]);
	JMenuItem newGame = new JMenuItem("����Ϸ");
	JMenuItem exitGame = new JMenuItem("�˳�");

	public Snake_Frame(int Frame_Width, int Frame_Height) {
		super("̰����");
		setSize(Frame_Width, Frame_Height);
		setLayout(null);
		snake_panel = new Snake_Panel(600, 600);
		snakethread = new Snake_Thread(snake_panel);
		add(snake_panel);
		initMenu();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	// ��ʼ���˵�
	public void initMenu() {
		JMenuBar menubar = new JMenuBar();
		newGame.setText("����Ϸ");
		// �˳���Ϸ
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		control.addActionListener(new controlActionListener());
		newGame.addActionListener(new newgameActionListener());
		game.add(newGame);
		game.add(control);
		game.add(exitGame);
		about.add(author);
		menubar.add(game);
		menubar.add(about);
		this.setJMenuBar(menubar);
	}

	// ������Ϸ�Ŀ�ʼ����ͣ
	class controlActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (index == 0) {
				if (Control_Frame) {
					snakethread.start();
					Control_Frame = false;
				} else {
					snakethread.Control_Thread = true;
				}

			} else if (index == 1) {
				snakethread.Control_Thread = false;
			}
			index = (index + 1) % 2;
			control.setText(flag[index]);
		}

	}

	// ����Ϸ
	class newgameActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// ��ʼ�µ���Ϸ
			//ֹͣ�߳�
			snakethread.Control_Thread=false;
			//��ͼ���ʼ��
			snakethread.Control_Panel=true;
			index=0;
			control.setText(flag[0]);
		}
	}
}
