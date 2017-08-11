package com.uoope.game.guess.view;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.uoope.game.guess.Program;

public class KeyBoard extends Panel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Program program;
	private Label text;

	public KeyBoard(Program program) {
		super();
		this.program = program;
		this.text = program.getpCommit();
		this.setLayout(new GridLayout(4, 3));
		Button btn = null;
		for (int i = 1; i < 11; i++) {
			if (i == 10)
				btn = new Button("0");
			else
				btn = new Button(i + "");
			btn.addActionListener(this);
			this.add(btn);
		}
		Button guess = new Button("²Â");
		Button clear = new Button("ÖØÖÃ");
		guess.addActionListener(this);
		clear.addActionListener(this);
		this.add(guess);
		this.add(clear);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "0":
			text.setText(text.getText() + 0);
			break;
		case "1":
			text.setText(text.getText() + 1);
			break;
		case "2":
			text.setText(text.getText() + 2);
			break;
		case "3":
			text.setText(text.getText() + 3);
			break;
		case "4":
			text.setText(text.getText() + 4);
			break;
		case "5":
			text.setText(text.getText() + 5);
			break;
		case "6":
			text.setText(text.getText() + 6);
			break;
		case "7":
			text.setText(text.getText() + 7);
			break;
		case "8":
			text.setText(text.getText() + 8);
			break;
		case "9":
			text.setText(text.getText() + 9);
			break;
		case "²Â":
			program.guess();
			break;
		case "ÖØÖÃ":
			program.clearPCommit();
			program.clearTip();
			break;
		}
	}
}
