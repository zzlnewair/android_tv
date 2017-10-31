package com.zzl.androidscreen.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BottomLayout extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2566994302921751173L;

	private DuFrame frame;
	private JButton btnSure;
	
	String w_input;
	String h_input;

	BottomLayout(DuFrame frame) {

		this.frame = frame;
		btnSure = new JButton("生成");
		this.add(btnSure);
		// 设置事件监听
		btnSure.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSure) {

			String result = frame.getSrcSize();
			if(result.contains("x")){
				w_input = result.split("x")[0];
				h_input= result.split("x")[1];
			}
			
			// 屏蔽掉程序执行入口
			btnSure.setText("正在生成中");
			btnSure.setEnabled(false);

			// 判断文件是否存在
			File resFile = new File("./res");
			if (resFile.exists()) {
				System.out.println("当前目录存在同名文件夹，请处理");
//				System.out.println("当前目录存在同名文件夹，请处理");
				JOptionPane.showMessageDialog(null, "当前目录存在同名文件夹，请处理");
			} else {
				resFile.mkdir();
				System.out.println("创建成功");
			}

			// 获取相关参数
			LinkedList<String> lists = frame.getTargetValues();

			// 
			// 根文件创建成功，则创建其他文件夹
			File file;

			Iterator<String> iterator = lists.iterator();
			while (iterator.hasNext()) {
				String dirName = iterator.next();
				System.out.println("dirName   ==== " + dirName);
				file = new File("./res/values-" + dirName);

				if (!file.exists()) {
					file.mkdir();
					// 计算缩放比例
					String desValue = dirName;

					Pattern p = Pattern.compile("\\d+");
					Matcher m = p.matcher(desValue);

					String w_ValueOne = "";
					String h_ValueTwo = "";

					if (m.find()) {
						w_ValueOne = m.group(0);
						if (m.find()) {
							h_ValueTwo = m.group(0);
						}
					}

					System.out.println("w_ValueOne   ===  " + w_ValueOne);
					System.out.println("h_ValueTwo   ===  " + h_ValueTwo);
					// 获取最小值
					
					int w_intNum = Integer.parseInt(w_ValueOne);
					int h_intNum = Integer.parseInt(h_ValueTwo);
					
					
					float wScale = (float) ((w_intNum * 1.00) / Integer.parseInt(w_input));
					System.out.println("num---"+w_input+"scale----"+wScale);
					
					File dimensFile = new File(file.getAbsoluteFile()
							+ "/w_dimens.xml");

					try {
						com.zzl.androidscreen.utils.DimensUtils.outContent(
								dimensFile, "w",Integer.parseInt(w_input),wScale);
					} catch (IOException e2) {
						e2.printStackTrace();
						System.out.println("生成文件错误，请稍后重试");
						return;
					}
					
					float h_scale = (float) ((h_intNum * 1.00) / Integer.parseInt(h_input));
					System.out.println("num-h--"+h_input+"h_scale----"+h_scale);
					
					File dimensFile_h = new File(file.getAbsoluteFile()
							+ "/h_dimens.xml");

					try {
						com.zzl.androidscreen.utils.DimensUtils.outContent(
								dimensFile_h, "h",Integer.parseInt(h_input),h_scale);
					} catch (IOException e2) {
						e2.printStackTrace();
						System.out.println("生成文件错误，请稍后重试");
						return;
					}

					
				}
			}

			// 生成完毕，恢复按钮状态
			btnSure.setText("生成");
			btnSure.setEnabled(true);

		
		}

	}

}
