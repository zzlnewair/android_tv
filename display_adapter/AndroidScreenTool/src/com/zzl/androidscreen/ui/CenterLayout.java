package com.zzl.androidscreen.ui;

import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterLayout extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3503191094211761668L;

	JCheckBox item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13;
	CenterLayout(){
	
		this.add(new JLabel("选择想要生成的目标尺寸:"));
		
		item1 = new JCheckBox("1024x600");
		this.add(item1);
		item2 = new JCheckBox("1280x672");
		this.add(item2);
		
		item3 = new JCheckBox("1280x720");
		this.add(item3);
		item4 = new JCheckBox("1280x736");
		this.add(item4);
		item5 = new JCheckBox("1280x800");
		this.add(item5);
		item6 = new JCheckBox("1366x768");
		this.add(item6);
		//google 机型
		item7 = new JCheckBox("1920x1080");
		this.add(item7);
		
		item8 = new JCheckBox("2048x1440");
		this.add(item8);
		item9 = new JCheckBox("2048x1536");
		this.add(item9);
		item10 = new JCheckBox("2560x1440");
		this.add(item10);
		
		item11 = new JCheckBox("2560x1600");
		this.add(item11);
		
		item12 = new JCheckBox("3840x2160");
		this.add(item12);
		
		item13 = new JCheckBox("1920x1016");
		this.add(item13);
	}
	
	
	public LinkedList<String> getTargetValues(){
	
		LinkedList<String> lists = new LinkedList<String>();
		
		
		
		if(item1.isSelected()){
			String actionCommand = item1.getActionCommand();
			lists.add(actionCommand);
		}
		if(item2.isSelected()){
			String actionCommand = item2.getActionCommand();
			lists.add(actionCommand);
		}if(item3.isSelected()){
			String actionCommand = item3.getActionCommand();
			lists.add(actionCommand);
		}if(item4.isSelected()){
			String actionCommand = item4.getActionCommand();
			lists.add(actionCommand);
		}if(item5.isSelected()){
			String actionCommand = item5.getActionCommand();
			lists.add(actionCommand);
		}if(item6.isSelected()){
			String actionCommand = item6.getActionCommand();
			lists.add(actionCommand);
		}if(item7.isSelected()){
			String actionCommand = item7.getActionCommand();
			lists.add(actionCommand);
		}if(item8.isSelected()){
			String actionCommand = item8.getActionCommand();
			lists.add(actionCommand);
		}
		if(item9.isSelected()){
			String actionCommand = item9.getActionCommand();
			lists.add(actionCommand);
		}
		if(item10.isSelected()){
			String actionCommand = item10.getActionCommand();
			lists.add(actionCommand);
		}
		if(item11.isSelected()){
			String actionCommand = item11.getActionCommand();
			lists.add(actionCommand);
		}
		if(item12.isSelected()){
			String actionCommand = item12.getActionCommand();
			lists.add(actionCommand);
		}if(item13.isSelected()){
			String actionCommand = item13.getActionCommand();
			lists.add(actionCommand);
		}
	
		return lists;
	}
	
}
