# simple-hibernate-mapping-demo
This is a simple java application demonstrating the ManyToOne and OneToMany relationships in hibernate.

```java

	@OneToMany(mappedBy = "institue", cascade = CascadeType.ALL)
	private Set<Course> courses;
```

```java

	@ManyToOne
	@JoinColumn(name = "inst_id", nullable = false)
	private Institute institue;
	
```