package Project1;
public class BookBody {
    private int bno;
    private String name;
    private double price;
    private int num;
    public BookBody() {
    }
    public BookBody(String name, int bno, double price, int num) {
        this.name = name;
        this.bno = bno;
        this.price = price;
        this.num = num;
    }
    public String toString() {
        return  bno + "   "+ name + "   "+ price + "   "+ num ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
