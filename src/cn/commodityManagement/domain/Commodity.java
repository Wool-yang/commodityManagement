package cn.commodityManagement.domain;

public class Commodity {
    private int id;
    private String name;
    private double price;
    private String describe;
    private String keyWord;
    private int categoryid;

    public Commodity() {
    }

    public Commodity(int id, String name, double price, String describe, String keyWord, int categoryid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.keyWord = keyWord;
        this.categoryid = categoryid;
    }

    public Commodity(int id) {
        this.id = id;
    }

    public Commodity(String name, double price, String describe, String keyWord, int categoryid) {
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.keyWord = keyWord;
        this.categoryid = categoryid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }
}
