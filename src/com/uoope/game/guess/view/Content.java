package com.uoope.game.guess.view;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.uoope.game.guess.Program;

public class Content extends Panel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Program context;
	private Button replay;
	private Button login;
	private Button exit;

	public Button getReplay() {
		return replay;
	}

	public Content(Program context) {
		super();
		this.context = context;
		Panel scopeInfo = new Panel();
		scopeInfo.setLayout(new GridLayout(1, 8));
		scopeInfo.add(new Label("���ޣ�"));
		scopeInfo.add(context.getLimit1Comp());
		scopeInfo.add(new Label());
		scopeInfo.add(new Label());
		scopeInfo.add(new Label("���ޣ�"));
		scopeInfo.add(context.getLimit2Comp());
		scopeInfo.add(new Label());
		scopeInfo.add(new Label());

		Panel gradeInfo = new Panel();
		gradeInfo.setLayout(new GridLayout(1, 8));

		gradeInfo.add(new Label("���֣�"));
		gradeInfo.add(new Label(context.getScore() + ""));
		gradeInfo.add(new Label("��"));
		gradeInfo.add(new Label());
		gradeInfo.add(new Label("�ܷ�"));
		gradeInfo.add(context.getGradeComp());
		gradeInfo.add(new Label("��"));
		gradeInfo.add(new Label());

		this.setLayout(new GridLayout(8, 1));
		this.add(scopeInfo);
		this.add(gradeInfo);
		this.add(context.getpCommit());
		this.add(context.getTipComp());
		this.add(new Label());
		this.add(new Label());
		Panel meau = new Panel();
		meau.setLayout(new GridLayout(1, 3, 10, 0));
		replay = new Button("����һ��");
		exit = new Button("�˳�");
		login = new Button("ǩ��");
		
		meau.add(replay);
		meau.add(exit);
		meau.add(login);
		replay.addActionListener(this);
		replay.setVisible(false);
		login.addActionListener(this);
		login.setVisible(false);
		exit.addActionListener(this);
		exit.setVisible(false);

		this.add(meau);
		this.add(new Label());

	}

	public Button getLogin() {
		return login;
	}

	public Button getExit() {
		return exit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "����һ��":
			context.setVisible(false);
			new Program().start();
			break;
		case "�˳�":
			System.exit(0);
			break;
		case "ǩ��":
			context.doLogin();
			break;
		}

	}
}
