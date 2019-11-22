package start;


public class SalesOrder {

    Boolean contracted;
    private int quantity;
    private int salesOrder;
    private String model;
    private String description;
    private int code;
    private String date;
    private String custRef;


    public SalesOrder() {
    }

    public SalesOrder(int quantity, int salesOrder, String model, String description, Boolean contracted) {
        super();
        this.quantity = quantity;
        this.salesOrder = salesOrder;
        this.model = model;
        this.description = description;
        this.contracted = contracted;
    }

    public SalesOrder(int quantity, int salesOrder, String model, String description, Boolean contracted, String custRef, String date) {
        super();
        this.quantity = quantity;
        this.salesOrder = salesOrder;
        this.model = model;
        this.description = description;
        this.contracted = contracted;
        this.custRef = custRef;
        this.date = date;

    }

    public Boolean getContracted() {
        return contracted;
    }

    public void setContracted(Boolean contracted) {
        this.contracted = contracted;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(int salesOrder) {
        this.salesOrder = salesOrder;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustRef() {
        return custRef;
    }

    public void setCustRef(String custRef) {
        this.custRef = custRef;
    }
}
