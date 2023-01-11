package Project4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextBook extends JFrame{
    public JFrame jf;
    public TextBook(){
        //初始化界面
        initJFrame();
        //菜单设置
        initJMenuBarAndJTextArea();


        this.setVisible(true);
    }




    private void initJMenuBarAndJTextArea() {
        //创建整个菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建选项
        JMenu file = new JMenu("文件(F)");
        JMenu edit = new JMenu("编辑(E)");
        JMenu help = new JMenu("帮助(H)");
        //创建下拉
        JMenuItem creat = new JMenuItem("新建");
        JMenuItem open = new JMenuItem("打开");
        JMenuItem save = new JMenuItem("保存");
        JMenuItem savefo = new JMenuItem("另存为");
        JMenuItem exit = new JMenuItem("退出");
        //将下拉选项添加到条目中
        file.add(creat);
        file.add(open);
        file.add(save);
        file.add(savefo);
        file.add(exit);
        //将菜单里面的两个条目选项添加到整个大菜单
        jMenuBar.add(file);
        jMenuBar.add(edit);
        jMenuBar.add(help);
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);


        //进行文本域的设置
        JTextArea Text = new JTextArea();
        //将其设置为自动换行
        Text.setLineWrap(true);
        //添加垂直滚动条
        JScrollPane jScrollPane = new JScrollPane(Text);
        //输入框的可见
        Text.setVisible(true);
        //添加水平滚动条
        //JScrollBar jScrollBar = new JScrollBar();
        //把文本域放入可以下拉的框里
        //jScrollBar.add(Text);
        //---
        //调用JFrame显示文本域
        this.add(Text, BorderLayout.CENTER);


        //Lambda表达式 新建则再次调用新建一个笔记本
        creat.addActionListener(e -> {
            new TextBook();
        });
        open.addActionListener(new ActionListener() {  //打开一个文件写入记事本
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(jf, "打开文件", FileDialog.LOAD);
                fileDialog.setVisible(true);
                String absPath = fileDialog.getDirectory() + fileDialog.getFile();
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(absPath));
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        //不为空 写入文本框
                        Text.append(line + '\n');}
                    //读取完毕结束
                    bufferedReader.close();
                }catch (Exception ex){
                    ex.printStackTrace();//抓取栈异常
                };
            }
        });
        //保存
        save.addActionListener(new ActionListener() {
            File file;
            FileDialog fileDialog;
            @Override
            public void actionPerformed(ActionEvent e) {
                fileDialog = new FileDialog(fileDialog,"保存",FileDialog.SAVE);
                //fileDialog.setVisible(true);
                CheckSavet();
            }
            public void CheckSavet(){
                String directory = null;
                String fileName = null;
                if (file == null) {
                    fileDialog.setVisible(true);
                    directory = fileDialog.getDirectory();
                    fileName = fileDialog.getFile();
                    if (directory == null || fileName == null)
                        return;
                }
                    file=new File(directory,fileName);
                    try{
                        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
                        String t= Text.getText();
                        bufferedWriter.write(t);
                        bufferedWriter.close();
                        Text.setText("");
                        } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        //另存为
        savefo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e2) {
                //创建一个窗体并且设置窗体标题和保存功能
                FileDialog fileDialog = new FileDialog(jf, "另存文件至", FileDialog.SAVE);
                fileDialog.setVisible(true);
                //获取路径和设置的文件名
                String filePath = fileDialog.getDirectory() + fileDialog.getFile();
                try {//用来检测是否设置了保存路径
                    //设置输出文件名为保存的路径下面的文件名
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
                    //获取文本域字符串
                    String str = Text.getText();
                    //写入与关闭
                    bufferedWriter.write(str);
                    bufferedWriter.close();
                } catch (IOException IOex) {//获取IO流的异常
                    IOex.printStackTrace();
                }
            }
        });
        //直接关闭虚拟机
        exit.addActionListener(e -> System.exit(0));
    }
    private void initJFrame() {
        this.setSize(593,480);
        this.setTitle("记事本");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
