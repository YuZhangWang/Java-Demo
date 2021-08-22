package main;

public class MyQueueTest
{
    public static void main(String[] args)
    {
        MyQueue list=new MyQueue();
        list.add("第一个元素");
        list.add("第二个元素");
        list.add("第三个元素");
        list.add("第四个元素");

        Object obj1=list.get(0);
        System.out.println("获取0位置的元素:"+obj1);
        Object obj2=list.set(3, "插入的 元素");
        System.out.println("插入的元素为:"+obj2);
        Object obj3=list.remove(3);
        System.out.println("移除3位置的元素:"+obj3);
        System.out.println("当前的队列长度:"+list.size());
        list.clear();
        System.out.println("当前的队列长度:"+list.size());
    }
}
