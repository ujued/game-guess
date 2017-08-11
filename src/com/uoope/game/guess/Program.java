package com.uoope.game.guess;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

import com.uoope.game.guess.view.Content;
import com.uoope.game.guess.view.KeyBoard;

public class Program extends JFrame {
	public static void main(String[] args) throws IOException {
		Program program = new Program();
		program.start();
	}

	private static final long serialVersionUID = -1051524347397796439L;

	private Label pCommit;
	private Label tipComp;
	private Label gradeComp;
	private Label limit1Comp;
	private Label limit2Comp;
	private Content content;

	private int limit1;
	private int limit2;
	private int target;
	private int score;
	private int count;
	private Grade grade;
	private Data data;

	public Program() {
		limit1 = getRandom(0, 1000);
		limit2 = getRandom(100, 2000);
		while (true) {
			if (limit2 - limit1 < 100) {
				limit2 = getRandom(200, 2200);
				continue;
			}
			break;
		}
		this.target = getRandom(limit1, limit2);
		this.score = (limit2 - limit1) / 50 * 3;
		this.data = new Data();
		data.setGrade(Grade.get());
		this.grade = data.getGrade();
	}

	private void updateGradeComp() {
		gradeComp.setText(grade.toString());
	}

	private void showMeau() {
		content.getReplay().setVisible(true);
		content.getExit().setVisible(true);
	}

	private void showLogin() {
		content.getLogin().setVisible(true);
	}

	public void doLogin() {
		grade.add(40);
		updateGradeComp();
		data.setDate(System.currentTimeMillis());
		tipComp.setText("ǩ���ɹ�����ȡ40����");
		content.getLogin().setVisible(false);
	}

	public void start() {
		// Context设置
		Program context = this;
		context.setTitle("������С��Ϸ");
		context.setSize(500, 300);
		context.setLayout(new GridLayout(1, 2, 10, 0));
		context.setResizable(false);
		context.setLocationRelativeTo(null);// JFrame����

		Label text = new Label();
		text.setBackground(Color.gray);
		text.setForeground(Color.white);
		Label gradeComp = new Label(context.getGrade() + "");
		Label limit1Comp = new Label(context.getLimit1() + "");
		Label limit2Comp = new Label(context.getLimit2() + "");
		context.setpCommit(text);
		context.setTipComp(new Label());
		context.setGradeComp(gradeComp);
		context.setLimit1Comp(limit1Comp);
		context.setLimit2Comp(limit2Comp);

		content = new Content(context);
		context.add(content);
		context.add(new KeyBoard(context));
		context.setVisible(true);
		context.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ǩ����ť��ʾ�߼�
		String lDate = data.getProperty(1);
		if (System.currentTimeMillis() - Long.parseLong(lDate) >= 86400000) {
			showLogin();
		}

		// �˳���Ϸʱ���ݳ־û���data.uf
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				data.persist();
			}
		});
		
		// ���ֺķ�
		if (Integer.parseInt(grade.toString()) > 4) {
			int randomSocre = getRandom(1, 6);
			grade.sub(randomSocre);
			updateGradeComp();
			tipComp.setText("�����볡��" + randomSocre + "����");
		}
	}

	public void guess() {
		if (Integer.parseInt(grade.toString()) < 2) {
			tipComp.setText("���ֲ����ˣ�����ǩ�����ټ�����!");
			return;
		}
		int user_target = 0;
		try {
			user_target = Integer.parseInt(pCommit.getText().trim());
		} catch (Exception e) {
			tipComp.setText("�������ˣ���δ�������µ�����");
			return;
		}

		if (user_target <= limit1) {
			tipComp.setText("�������ˣ�" + user_target + " ��" + limit1 + "��Сѽ����10�֣�");
			grade.sub(10);
			updateGradeComp();
			clearPCommit();
			return;
		} else if (user_target >= limit2) {
			tipComp.setText("�������ˣ�" + user_target + " ��" + limit2 + "����ѽ����10�֣�");
			grade.sub(10);
			updateGradeComp();
			clearPCommit();
			return;
		}
		if (user_target > target) {
			count++;
			if (count < 5) {
				grade.sub(2);
			} else if (count < 8) {
				grade.sub(4);
			} else {
				grade.sub(5);
			}

			tipComp.setText(String.format("%s����", user_target));
			updateGradeComp();
			limit2Comp.setText(user_target + "");
			limit2 = user_target;
			clearPCommit();
		} else if (user_target < target) {
			count++;
			if (count < 5) {
				grade.sub(2);
			} else if (count < 8) {
				grade.sub(4);
			} else {
				grade.sub(5);
			}
			tipComp.setText(String.format("%sС��", user_target));
			updateGradeComp();
			limit1Comp.setText(user_target + "");
			limit1 = user_target;
			clearPCommit();
		} else {
			tipComp.setText("��ϲ�㣬�¶��ˣ�");
			grade.add(score);
			updateGradeComp();
			showMeau();
		}
	}

	// some tools
	public void clearTip() {
		tipComp.setText("");
	}

	public void clearPCommit() {
		pCommit.setText("");
	}

	public int getRandom(int a, int bound) {
		return new Random().nextInt(bound - a) + a;
	}

	// the getters and setters
	public Label getLimit1Comp() {
		return limit1Comp;
	}

	public void setLimit1Comp(Label limit1Comp) {
		this.limit1Comp = limit1Comp;
	}

	public Label getLimit2Comp() {
		return limit2Comp;
	}

	public void setLimit2Comp(Label limit2Comp) {
		this.limit2Comp = limit2Comp;
	}

	public Label getGradeComp() {
		return gradeComp;
	}

	public void setGradeComp(Label gradeComp) {
		this.gradeComp = gradeComp;
	}

	public Label getTipComp() {
		return tipComp;
	}

	public void setTipComp(Label tip) {
		this.tipComp = tip;
	}

	public Label getpCommit() {
		return pCommit;
	}

	public void setpCommit(Label pCommit) {
		this.pCommit = pCommit;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public int getLimit1() {
		return limit1;
	}

	public int getLimit2() {
		return limit2;
	}

	public int getTarget() {
		return target;
	}

	public int getScore() {
		return score;
	}
}
