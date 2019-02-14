package lombok;

@Data
public class GetterSetter {

    private int age = 10;
    private String name;
    private String phone;
    private int salary;

    public static void main(String[] args) {
        GetterSetter object = new GetterSetter();

        object.setName("Daniel");
        object.setSalary(500);
        System.out.println(object);
    }
}
