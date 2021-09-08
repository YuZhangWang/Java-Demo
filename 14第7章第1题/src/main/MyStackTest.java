package main;

public class MyStackTest {
    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        System.out.println("是否为空:" + stack.isEmpty());
        stack.push("1");
        stack.push("2");
        System.out.println("是否为空:" + stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println("当前元素个数:" + stack.size());
        stack.clear();
        System.out.println("清空后 当前元素个数:" + stack.size());
    }
}
