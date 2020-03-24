package com.jm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.dubbo.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@ToString(of={"name"},exclude = {"id"})
class User {
    private int id;
    private String name;
}

public class Test {

    public static void t1() {
        try {
            System.out.println("Test");

            String v = "VALUE";
            Object obj = (Object) v;
            CompletableFuture<Object> future0 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            });
            CompletableFuture<String> future1 = future0.handle((o, t) -> {
                return "Handle:" + o.toString();
            });
            CompletableFuture<String> future = future1.thenApply(Function.identity());
            future = future.thenApply(item -> item + ":themApply");
//            CompletableFuture<?> f = future.thenRun(()->System.out.println("OK"));
            future.whenComplete((res, t) -> {
                if (t == null) {
                    System.out.println("WC:" + res);
                } else {
                    System.out.println(StringUtils.toString(t));
                }
            }).whenComplete((res, t) -> {
                System.out.println("WC2:" + res);
                System.out.println(System.currentTimeMillis());
            });
            System.out.println("End");
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(1200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            future0.complete((Object) "VVV");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> genre = new ArrayList<>(Arrays.asList("rock", "pop", "jazz", "reggae"));
        long a = genre.stream().filter(s -> s.startsWith("r")).count();
        System.out.println(a);

        boolean b = genre.stream().noneMatch(String::isEmpty);
        System.out.println(b);

        System.out.println(genre.stream().peek(System.out::println).anyMatch(s -> s.indexOf("r") == 0));

        List<User> users = Arrays.asList(new User(1, "Tomcat"), new User(2, "Apache"), new User(3, "Nginx"));

        Map<Integer,User> map = users.stream().collect(Collectors.toMap(User::getId,obj->obj));
        System.out.println(map);

        String queryString = "type=1&from=APP&source=homePage";

        Map<String,String> map2 = Stream.of(queryString.split("&")).map(obj->obj.split("="))
                .collect(Collectors.toMap(e->e[0],e->e[1]));
        System.out.println(map2);

        Stream.of(queryString.split("&")).flatMap(obj->Stream.of(obj.split("="))).collect(Collectors.toList())
                .forEach(System.out::println);

        t1();
    }
}
