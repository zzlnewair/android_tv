package com.zzl.androidscreen.ui;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TopLayout extends JPanel{

	private String strSelect = "选择效果图尺寸:";
	
	
	private ButtonGroup group;
	//private JRadioButton jrb480x800;
	private JRadioButton jrb1280x720;
	
	private JRadioButton jrb1920x1080;
	
	/**
	 * 构造容器
	 */
	TopLayout(){
		
		
		JLabel jlShow = new JLabel(strSelect);
		group = new ButtonGroup();
		
		this.add( jlShow);
//		RadioButtonBorder border = new RadioButtonBorder(shadow, darkShadow, highlight, lightHighlight)
		jrb1920x1080 = new JRadioButton("1920x1080");
		jrb1920x1080.setSelected(true);
		group.add(jrb1920x1080);
//		JCheckBox jcb480x800 = new JCheckBox("");
		this.add( jrb1920x1080);
		jrb1280x720 = new JRadioButton("1280x720");
		group.add(jrb1280x720);
		this.add( jrb1280x720);
		
	}
	
	
	public String getSrcSize(){
		
		
		if(jrb1920x1080.isSelected()){
			return "1920x1080";
		}else if(jrb1280x720.isSelected()){
			return "1280x720";
		}
//		String result = group.getSelection().getSelectedObjects(1) + "";
//		System.out.println("result  ====" + result);
//		return result;
		
		return null;
	}
	
	
	
}
