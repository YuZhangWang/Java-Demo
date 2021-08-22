import java.util.*;

public class HelloWorld{
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("java");
        list.add("aaa");
        list.add("java");
        list.add("java");
        list.add("bbb");

        for(int j = 0;j<list.size();j++){
            System.out.println(list.get(j));
        }

        for(int i = list.size() - 1 ; i >= 0 ; i--){
            if("java".equals(list.get(i))){
                list.remove(i);
            }
        }

        System.out.println();
        System.out.println();
        for(int j = 0;j<list.size();j++){
            System.out.println(list.get(j));
        }

        System.out.println(0x81>>5);
    }
}