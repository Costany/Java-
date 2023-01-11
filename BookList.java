package Project1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class BookList {
    Scanner sc = new Scanner(System.in);
    public void useBookList(){
        BookBody []books = new BookBody[20];
        BookList BList = new BookList();
        //Scanner sc = new Scanner(System.in);
        int  bookNum;
        books[0] = new BookBody("Java教程",1,30.6,30);
        books[1] = new BookBody("JSP教程",2,42.1,46);
        books[2] = new BookBody("SSH架构",3,47.3,15);
        BList.PrintBook(books);
        int Bnosum = 0;
        int Tprice = 0;
        int [] noCount = new int[3];
        boolean next1 = true;
        while (next1){
            int lno = BList.SelectBooks()-1;
            int Pricecout = BList.BuyBooks();
            Tprice += Pricecout;
            noCount[lno] = Pricecout;
            System.out.println("是否继续购买？");
            String tf = sc.nextLine();
            if (tf.equals("否")){
                next1 = false;
            } else if (tf.equals("是")) {
                next1 = true;
            }else {
                System.out.println("字符不合法");
                System.exit(0);
            }
        }
        System.out.println("     图书订单");
        System.out.println("图书名称   购买数量   图书单价");
        System.out.println("---------------------------------------------------");
        double sumprice = 0;
        DecimalFormat df =new DecimalFormat("0.00 ");
        for (int i = 0; i < noCount.length; i++) {
            if (noCount[i] != 0){
                System.out.println(books[i].getName()+"   "+noCount[i]+"   "+
                        books[i].getPrice());
                sumprice = sumprice + books[i].getPrice()*noCount[i];
            } else if ("null".equals(String.valueOf(noCount[i]))) {
                continue;
            }
        }
        System.out.println("---------------------------------------------------");
        System.out.println("订单总额：     "+df.format(sumprice));


    }
    private void PrintBook(BookBody[] books) {
        System.out.println("---------------------------------------------------");
        int bookNum = 3;
        for (int i = 0; i < bookNum; i++) {
            System.out.println(books[i].getBno()+"   "+books[i].getName()+"   "+
                    books[i].getPrice()+"    "+books[i].getNum());
        }
        System.out.println("---------------------------------------------------");
    }
    private int SelectBooks() {
        System.out.println("请输入图书编号选择图书：");
        return sc.nextInt();
    }
    private  int BuyBooks(){
        System.out.println("请输入购买图书数量：");
        return sc.nextInt();
    }
  }
