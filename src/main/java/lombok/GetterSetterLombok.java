package lombok;

@Getter
@Setter
public class GetterSetterLombok {

    private int age = 10;
    private String name;

    public static void main(String[] args) {
         GetterSetter object = new GetterSetter();

         object.setName("Daniel");
         System.out.println(object.getName());
     }
}
