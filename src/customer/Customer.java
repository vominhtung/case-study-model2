package customer;

public class Customer {
    private String id;
    private String name;
    private String phone;

    public Customer() {
    }

    public Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "..............."+'\n'+" id khách hàng:" +
                  id + '\n' +
                " tên khách hàng:"  + name + '\n' +
                " số điện thoại khách hàng: " + phone+'\n'+"-------------" ;
    }

    public String toFile(){
        return id+","+name+","+phone;
    }
}
