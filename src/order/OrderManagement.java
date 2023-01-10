package order;

import customer.Customer;
import customer.CustomerManagement;
import product.Product;
import product.ProductManagement;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderManagement {
    private List<Order>orderList;
    private static final String FILE_PATH = "order.csv";

    private OrderManagement(){
        orderList = new ArrayList<>();
    }
    private static OrderManagement orderManagement = new OrderManagement();
    private static CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    private static ProductManagement productManagement = ProductManagement.getProductManagement();
    public static OrderManagement getOrderManagement(){
        return orderManagement;
    }
    public void add(Order order){
//        Customer customer = customerManagement.searchById(order.getCustomerID());
//        order.setCustomerName(customer.getName());
////        Product product = productManagement.searchById(order.getProductName());
////        order.setProductName(product.getId());
//        Product product = productManagement.searchById(order.getProductId());
//        order.setProductId(product.getId());

        orderList.add(order);
        saveFile();
    }
    public Order searchById(String id){
        for (Order order : orderList){
            if (order.getOrderId().equals(id)){
                return order;
            }
        }
        return null;
    }
    public boolean checkIdOrder(String id){
        for (Order order : orderList){
            if (order.getOrderId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public boolean remove(String id){
        for (Order order : orderList){
            if (order.getOrderId().equals(id)){
                orderList.remove(order);
                saveFile();
                return true;
            }
        }
        return false;
    }
    public List<Order>displayOrder(){
        return orderList;
    }
    public void saveFile(){
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
            for (Order o :orderList){
                bufferedWriter.write(o.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    public void  readFromFile(){
        orderList.clear();
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = ",";
            while ((line = bufferedReader.readLine()) != null){
                Order order = handleLine (line);
                orderList.add(order);
                System.out.println(order);
            }
            bufferedReader.close();
            fileReader.close();
        }catch (IOException | ParseException e){
            throw new RuntimeException(e);
        }
    }
    public Order handleLine(String line) throws ParseException {
        String[] strings = line.split(",");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
         Order order = new Order(strings[0],simpleDateFormat.parse(
                 strings[1]),strings[2],strings[3],strings[4],strings[5],Integer.parseInt(strings[6]),
                 Double.parseDouble(strings[7]),Double.parseDouble(strings[8])
         );
        return order;

    }

    @Override
    public String toString() {
        return "OrderManagement{" +
                "orderList=" + orderList +
                '}';
    }
}
