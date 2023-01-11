package Project6;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SelectWord extends JFrame {
    //public void Select() /*throws ClassNotFoundException*/{
    public SelectWord() throws ClassNotFoundException, SQLException {
        //基本方法
        initJFrame();
        //GUI界面与SQL
        initJGuIandSQL();
        this.setVisible(true);
    }

        private void initJGuIandSQL() throws ClassNotFoundException, SQLException {
            JLabel jLabel = new JLabel("输入要查询的单词:");
            //指定位置
            jLabel.setBounds(30,10,120,30);
            JTextField jTextField = new JTextField();
            jTextField.setBounds(150,10,230,30);
            JButton jButton = new JButton("查询");
            jButton.setBounds(420,10,80,30);
            JTextArea jTextArea = new JTextArea();
            jTextArea.setBounds(30,45,470,200);
            jTextArea.setEditable(false);//不允许输入
            this.add(jLabel);
            this.add(jTextField);
            this.add(jButton);
            this.add(jTextArea);

            //启用MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/数据库名称";
            String user = "root";
            String pwd = "******";
            Connection connection = DriverManager.getConnection(url,user,pwd);

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String word = jTextField.getText();
                    int flag = 0;
                    int target = 0;
                    String Eng = null,Chi = null;
                    char[] toCharArray = word.toCharArray();
                    byte[] toByteArray = new byte[toCharArray.length];
                    int n=0;
                    for(int i = 0; i < word.length(); i++) {
                        n = word.charAt(i);
                        if (!(19968 <= n && n < 40869)) {
                            target = 3;
                        } else {
                            target = 1;
                        }
                    }
                    for (int i = 0; i < toCharArray.length; i++) {
                        toByteArray[i] = (byte) toCharArray[i];
                        // 拆分字符串对每一个字符进行判断 看是否是英文字母
                        if ((toByteArray[i] >= 97 && toByteArray[i] <= 122) ||
                                (toByteArray[i] >= 65 && toByteArray[i] <= 90)) {
                            flag++;
                            target = 0;
                        }else{
                            break;
                        }
                    }
                    if (flag == toCharArray.length) {
                        Eng = word;
                    }else {
                        target = 1;
                        Chi = word;
                    }
                    if (target>=3){
                            System.out.println("非纯字符组合");
                            jTextArea.setText("输入的字符不合法！");
                            System.exit(0);
                    }
                    if (connection == null){
                        System.out.println("连接数据库失败，暂时无法查询");
                        return;
                    }else{
                        System.out.println("连接成功");
                    }
                    String sql = null;
                    if (word != null){
                        try {
                            assert connection != null;
                            Statement statement = connection.createStatement();
                            if (target == 0){
                                sql = "select * from word where Eng = "+ "'"+ Eng +"'";
                            }else{
                                sql = "select * from word where Chi = "+ "'"+ Chi +"'";
                            }
                            ResultSet resultSet = statement.executeQuery(sql);
                            while (resultSet.next()){
                                if (target == 0){
                                    System.out.println(resultSet.getString("Chi"));
                                    jTextArea.setText(resultSet.getString("Chi"));
                                }else {
                                    System.out.println(resultSet.getString("Eng"));
                                    jTextArea.setText(resultSet.getString("Eng"));
                                }
                            }
                            //System.out.println(result);
                            //jTextArea.setText(result);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });{

            }
    }

    private void initJFrame() {
        this.setSize(540,300);
        this.setTitle("英语释义查询");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

}
