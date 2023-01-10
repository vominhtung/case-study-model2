package order;
import customer.CustomerManagement;
import product.Product;
import product.ProductManagement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String orderId;
    private Date  orderDate;
    private String customerID;
    private String customerName;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private double total;

    private Map<String,Integer> mapOrders;
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    ProductManagement productManagement = ProductManagement.getProductManagement();

    public Order() {
    }

    public Order(Date orderDate, String orderId, String customerID){
        this.orderId = orderId;
        this.customerID = customerID;
        this.orderDate =new Date() ;
        this.mapOrders=new HashMap<>();
    }

    public Order(String orderId, Date orderDate, String customerID, String customerName,String productId,
                 String productName,int quantity, double price, double total) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.customerName = customerName;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.mapOrders = new HashMap<>();

    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getProductId (){
        return productId;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public  int getQuantity(){return  quantity;}
    public void  setQuantity(int quantity){this.quantity = quantity;}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<String, Integer> getMapOrders() {
        return mapOrders;
    }

    public void setMapOrders(Map<String, Integer> mapOrders) {
        this.mapOrders = mapOrders;
    }

    public void addProduct(String productID, String name, int  quantity) {
        getMapOrders().put(productID, quantity);
    }
    public double getSubTotal(String productID, int quantity) {
        Product product = productManagement.searchById(productID);
        double result;
        double p = product.getPrice();
        result = p * quantity;
        return result;
    }
    public double getTotal() {
        double total = 0;
        for (String key : getMapOrders().keySet()) {
            total += getSubTotal(key, getMapOrders().get(key));
        }
        return total;
    }
    public String stringCreatedDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(getOrderDate());
    }
    @Override
    public String toString() {
        return "*id hóa đơn: " + orderId + "\n" +
                "*ngày mua: " + stringCreatedDay() + "\n" +
                "*Id khách hàng:" + customerID + "\n"+
                "*tên khách hàng: " +getCustomerName()+"\n"+
                "*số điện thoại khách hàng: " + customerManagement.searchById(getCustomerID()).getPhone() + "\n" +
                "*id sản phẩm :"+ getProductId()+"\n"+
                "*tên sản phẩm:"+getProductName()+"\n"+
                "*so luong mua:"+quantity+"\n"+
                "*giá sản phẩm:"+getPrice()+"\n"+
                "       tổng tiền: " + total+'\n'+"-------------"+'\n';
    }

    public String toFile() {
        return orderId+","+stringCreatedDay() + "," + customerID + "," + getCustomerName() + ","+ getProductId()+
                ","+ getProductName() + "," +quantity+","+ getPrice() +"," + getTotal();
    }

}
