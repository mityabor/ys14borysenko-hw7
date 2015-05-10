package ua.yandex.pizza.domain;

public class Pizza {
    private Integer id;
    private String name;
    private Double price;
    private PizzaTypes type;

    public Pizza() {
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setType(PizzaTypes type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public PizzaTypes getType() {
        return type;
    }    

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + '}';
    }
    
}
