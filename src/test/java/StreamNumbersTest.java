import group.stream_numbers_generate_task.Order;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamNumbersTest {
    @Test
    public void testStreamNumbers() {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, List<Order>> collect = orders.stream().collect(Collectors.groupingBy(Order::getProduct));

        System.out.println("Заказы сгрупированные по продуктам:");
        for (Map.Entry<String, List<Order>> entry : collect.entrySet()) {
            System.out.println(entry.getKey());
        }

        System.out.println("\nОбщая стоимость заказов по продуктам:");
        Map<String, Double> summingByProducts = new HashMap<>();
        for(Map.Entry<String, List<Order>> entry : collect.entrySet()) {
            Optional<Double> sumByOrder = entry.getValue().stream().map(Order::getCost).reduce(Double::sum);
            sumByOrder.ifPresent(s -> {
                System.out.println(entry.getKey() + ": " + s);
                summingByProducts.put(entry.getKey(), s);
            });
        }
        System.out.println("\nСортировка по стоимости заказа:");
        List<Map.Entry<String, Double>> list = summingByProducts.entrySet().stream()
                .sorted((entry1, entry2)-> entry2.getValue().compareTo(entry1.getValue())).toList();

        for(Map.Entry<String, Double> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nТри самых дорогих продукта:");
        List<Order> sortedList = orders.stream().sorted(Comparator.comparingDouble(Order::getCost).reversed()).toList();
        sortedList.subList(0, 3).forEach(order -> System.out.println(order.getProduct()+" : "+order.getCost()));

        sortedList.subList(0, 3).stream().map(Order::getCost).reduce(Double::sum)
                .ifPresent(s->System.out.println("Общая сумма трёх продуктов: "+s));
    }


}
