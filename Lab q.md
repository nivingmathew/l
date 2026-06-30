Perfect 👍 — here are **exam-ready viva questions + crisp answers** for your **Spring Boot Movie CRUD (Add/View/Delete)** project.

---

# 🎓 🔥 MOST IMPORTANT VIVA QUESTIONS

## 1. What is Spring Boot?

👉 Spring Boot is a framework used to build **Java web applications quickly** with:

- Auto configuration
- Embedded server (Tomcat)
- Minimal setup

---

## 2. What is MVC Architecture?

👉 MVC = Model + View + Controller

- **Model** → Data (Movie class)
- **View** → UI (HTML / Thymeleaf)
- **Controller** → Handles request (MovieController)

---

## 3. What is @Entity?

👉 `@Entity` marks a class as a **database table**

```java
@Entity
public class Movie { }
```

---

## 4. What is @Id?

👉 It defines the **primary key**

```java
@Id
@GeneratedValue
private int id;
```

---

## 5. What is JpaRepository?

👉 Interface used for **database operations without SQL**

✔ Provides:

- `save()`
- `findAll()`
- `deleteById()`

---

## 6. Why are we using JpaRepository?

👉 To avoid writing SQL queries and reduce code.

---

## 7. What is @Controller?

👉 Marks class as **Spring MVC Controller**

Handles HTTP requests.

---

## 8. What is @Autowired?

👉 Used for **Dependency Injection**

Spring automatically creates object.

```java
@Autowired
MovieRepository repo;
```

---

## 9. Why constructor injection is recommended?

👉 Because:

- More secure
- Easier testing
- Avoids null errors

---

## 10. What is @GetMapping?

👉 Used to **fetch data / open page**

```java
@GetMapping("/view")
```

---

## 11. What is @PostMapping?

👉 Used to **send/save data**

```java
@PostMapping("/save")
```

---

## 12. Difference: GET vs POST

|GET|POST|
|---|---|
|Fetch data|Send data|
|Visible in URL|Hidden|
|Less secure|More secure|

---

## 13. What is Thymeleaf?

👉 Template engine to connect **Java + HTML**

Example:

```html
<tr th:each="m : ${movies}">
```

---

## 14. Why `xmlns:th="http://www.thymeleaf.org"`?

👉 Enables Thymeleaf syntax (`th:` attributes)

---

## 15. What is @ModelAttribute?

👉 Binds form data to Java object

```java
public String save(@ModelAttribute Movie movie)
```

---

## 16. What is @PathVariable?

👉 Gets value from URL

```java
/delete/{id}
```

---

## 17. What is application.properties?

👉 Configuration file for:

- DB connection
- Hibernate
- Server

---

## 18. What is Hibernate?

👉 ORM tool → converts Java object ↔ Database table

---

## 19. What is ddl-auto=update?

👉 Automatically creates/updates table

---

## 20. What is Dependency Injection?

👉 Spring creates and injects objects instead of manual creation.

---

# 🔁 FLOW QUESTION (VERY IMPORTANT ⭐)

## 21. Explain program flow

👉 Answer like this:

1. User opens `/add`
2. Form is displayed
3. User submits form → `/save`
4. Controller receives data
5. Data saved using `repo.save()`
6. Redirect to `/view`
7. Movies displayed
8. Delete button → `/delete/{id}`

---

# ⚠️ ERROR-BASED QUESTIONS

## 22. What is Whitelabel Error?

👉 Occurs when:

- No mapping found
- Wrong URL

---

## 23. Template not found error?

👉 HTML not inside:

```
resources/templates
```

---

## 24. Why 404 error occurs?

👉 URL mismatch or mapping not defined

---

# 🧠 SMALL TRICK QUESTIONS

## 25. Why use redirect?

👉 To avoid duplicate form submission

```java
return "redirect:/view";
```

---

## 26. What is Embedded Server?

👉 Built-in server (Tomcat), no need external setup

---

## 27. Can we use SQL instead of JpaRepository?

👉 Yes, using `@Query` or JDBC

---

# 🎯 LAST MINUTE REVISION (SUPER SHORT)

👉 Remember this line for viva:

> “This is a Spring Boot MVC application where controller handles request, model stores data, repository interacts with database using JPA, and Thymeleaf is used for view rendering.”

---

# ✅ If examiner asks:

👉 “What next can you add?”

Say:

- Update feature
- Login system
- Validation
- REST API

---

If you want, I can give: ✅  
✅  
✅

Just say 👍