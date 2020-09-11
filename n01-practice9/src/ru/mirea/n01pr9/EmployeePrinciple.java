package ru.mirea.n01pr9;

public enum EmployeePrinciple {
	M("Manager", 1),
	K("Controller", 2),
	P("Worker", 3);

	final private String title;
	final private int priority;

	EmployeePrinciple(String title, Integer priority) {
		if (title == null || priority == null)
			throw new NullPointerException("title & priority");
		this.title = title;
		this.priority = priority;
	}

	public String title() {
		return title;
	}

	public int priority() {
		return priority;
	}
}
