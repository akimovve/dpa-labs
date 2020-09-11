package ru.mirea.n01pr9;

public class Request implements Comparable<Request> {
	final private EmployeePrinciple category;
	final private int employeeId;
	final private int timeMin;

	public Request(EmployeePrinciple category, int employeeId, int timeMin) {
		this.category = category;
		this.employeeId = employeeId;
		this.timeMin = timeMin;
	}

	@Override
	public int compareTo(Request o) {
		return Integer.compare(o.getPriority(), getPriority());
	}

	public EmployeePrinciple getCategory() {
		return category;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public int getTimeMin() {
		return timeMin;
	}

	public int getPriority() {
		return category.priority();
	}

	@Override
	public String toString() {
		return "Request {" +
				"category = " + category +
				", employeeId = " + employeeId +
				", timeMin = " + timeMin +
				'}';
	}
}
