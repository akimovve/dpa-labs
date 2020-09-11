package ru.mirea.n01pr9;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
		OwnPriorityQueue<Request> queue = new OwnPriorityQueue<>();

		queue.offer(new Request(EmployeePrinciple.P, 1, 15));
		queue.offer(new Request(EmployeePrinciple.K, 2, 5));
		queue.offer(new Request(EmployeePrinciple.M, 3, 10));
		queue.offer(new Request(EmployeePrinciple.K, 4, 10));
		queue.offer(new Request(EmployeePrinciple.P, 5, 5));
		queue.offer(new Request(EmployeePrinciple.P, 6, 15));
		queue.offer(new Request(EmployeePrinciple.M, 7, 5));
		queue.offer(new Request(EmployeePrinciple.M, 8, 5));
		queue.offer(new Request(EmployeePrinciple.P, 9, 10));
		queue.offer(new Request(EmployeePrinciple.K, 10, 4));

		for (Object request : queue.toArray()) {
			System.out.println(request);
		}

		Map<Integer, List<Request>> byTime =
				Arrays.stream(queue.toArray())
						.map(el -> (Request) el)
						.collect(Collectors.groupingBy(Request::getPriority));

		for (Map.Entry<Integer, List<Request>> el : byTime.entrySet()) {
			System.out.format("\n(%s)'%s' = %d",
					EmployeePrinciple.values()[el.getKey() - 1],
					el.getValue().get(0).getCategory().title(),
					el.getValue()
							.stream()
							.mapToInt(Request::getTimeMin)
							.sum());
		}
    }
}
