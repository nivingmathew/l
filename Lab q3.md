# Spring Boot Project: Login + Student CRUD (Complete Ordered Guide)

This guide is organized in the exact order you should execute steps, from project creation to a working Login → Dashboard → Add/View/Delete Student app.

---

## PART 1 — Create the Project

### Step 1: Open Eclipse
```
File → New → Spring Starter Project
```

### Step 2: Project Details
- **Name**: `demo`
- **Type**: Maven
- **Packaging**: Jar
- **Java Version**: 17 (or whatever you have installed)

### Step 3: Group & Package
```
Group: com.example
Artifact: demo
Package: com.example.demo
```
Keep the package name consistent throughout the project.

### Step 4: Add Dependencies
Search for and add:
- Spring Web
- Spring Data JPA
- Thymeleaf
- MySQL Driver

### Step 5: Click Finish

---

## PART 2 — What You Get Automatically

```
demo
 ├── src/main/java/com/example/demo
 │    └── DemoApplication.java   (main class)
 │
 ├── src/main/resources
 │    ├── application.properties
 │    ├── templates
 │    └── static
```
You don't need to create any of this manually.

---

## PART 3 — First Run (No Database Yet)

Right-click the project:
```
Run As → Spring Boot App
```

Open a browser at:
```
http://localhost:8080
```
If you see no startup error in the console, the project is ready (a 404/whitelabel page in the browser is fine at this stage — that just means there's no controller mapped yet).

**If errors appear:** check your Java version, and ignore any MySQL/datasource errors for now since the DB isn't configured yet.

---

## PART 4 — Set Up Packages

Right-click `com.example.demo` → New → Package, and create:
```
controller
model
repository
```

You'll build things in this order: Model (Entity) → Repository → Controller → HTML templates.

---

## PART 5 — Connect MySQL

### Step 1: Create the database
```sql
CREATE DATABASE examdb;
```

### Step 2: Configure `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/examdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Step 3: Verify
- MySQL server is running
- Database name matches (`examdb`)
- Username/password are correct

Run the app again — it should start without datasource errors.

---

## PART 6 — Build the Login Feature

**Flow:** Login Page → Check DB → If correct → Dashboard

### 6.1 — User Entity
`model/User.java`
```java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;

    // getters & setters
}
```

### 6.2 — Repository
`repository/UserRepository.java`
```java
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);
}
```

### 6.3 — Controller
`controller/LoginController.java`
```java
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;

@Controller
public class LoginController {

    @Autowired
    UserRepository repo;

    // show login page
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    // handle login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        User user = repo.findByUsernameAndPassword(username, password);

        if (user != null) {
            return "dashboard";   // success
        }

        model.addAttribute("error", "Invalid Credentials");
        return "login";
    }
}
```

### 6.4 — HTML Templates

`templates/login.html`
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Login</title>
</head>
<body>

<h2>Login Page</h2>

<form action="/login" method="post">
    Username:
    <input type="text" name="username"><br><br>

    Password:
    <input type="password" name="password"><br><br>

    <button type="submit">Login</button>
</form>

<p style="color:red" th:text="${error}"></p>

</body>
</html>
```

`templates/dashboard.html`
```html
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

<h2>Welcome! Login Successful</h2>

<a href="/add">Add Student</a><br>
<a href="/students">View Students</a>

</body>
</html>
```

### 6.5 — Insert Test User
```sql
INSERT INTO user(username, password)
VALUES ('admin', '123');
```

### 6.6 — Test It
Run the app, open `http://localhost:8080/`, log in with:
- Username: `admin`
- Password: `123`

Correct login → goes to dashboard. Wrong login → shows error message.

### Troubleshooting
| Problem | Check |
|---|---|
| Login always fails | Table name is `user`, data exists, column names match |
| 404 after login | `dashboard.html` is missing from `templates/` |
| White/blank page | `templates` folder is in the correct location |

At this point you have: Spring Boot running, MySQL connected, login working, full MVC flow complete.

---

## PART 7 — Build the Student CRUD Feature

**Flow:** Login → Dashboard → Add Student → Save → View Students → Delete Student

### 7.1 — Student Entity
`model/Student.java`
```java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String email;

    // getters & setters
}
```

### 7.2 — Repository
`repository/StudentRepository.java`
```java
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
```

### 7.3 — Controller (includes Add, Save, View, and Delete)
`controller/StudentController.java`
```java
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Controller
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // Show form
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    // Save data
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        repo.save(student);
        return "redirect:/students";
    }

    // View all students
    @GetMapping("/students")
    public String getStudents(Model model) {
        model.addAttribute("list", repo.findAll());
        return "viewStudents";
    }

    // Delete a student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/students";
    }
}
```

### 7.4 — HTML Templates

`templates/addStudent.html`
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<h2>Add Student</h2>

<form action="/save" method="post">
    Name: <input type="text" name="name"><br><br>
    Email: <input type="text" name="email"><br><br>

    <button type="submit">Save</button>
</form>

</body>
</html>
```

`templates/viewStudents.html` (includes the Delete column)
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

<h2>Student List</h2>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Action</th>
</tr>

<tr th:each="s : ${list}">
    <td th:text="${s.id}"></td>
    <td th:text="${s.name}"></td>
    <td th:text="${s.email}"></td>
    <td>
        <a th:href="@{/delete/{id}(id=${s.id})}">Delete</a>
    </td>
</tr>

</table>

<br>
<a href="/add">Add New Student</a>

</body>
</html>
```

The dashboard already links to `/add` and `/students` from Part 6.4, so no further changes are needed there.

### Troubleshooting
| Problem | Check |
|---|---|
| Delete not working | `repo.deleteById(id);` exists, URL is `/delete/{id}`, `@PathVariable` is present |
| 404 on delete | Mapping must be `@GetMapping("/delete/{id}")`, not `@GetMapping("/delete")` |

---

## PART 8 — Final Project Structure

```
demo
├── src/main/java/com/example/demo
│   ├── controller
│   │   ├── LoginController.java
│   │   └── StudentController.java
│   │
│   ├── model
│   │   ├── User.java
│   │   └── Student.java
│   │
│   ├── repository
│   │   ├── UserRepository.java
│   │   └── StudentRepository.java
│   │
│   └── DemoApplication.java   (main class)
│
├── src/main/resources
│   ├── templates
│   │   ├── login.html
│   │   ├── dashboard.html
│   │   ├── addStudent.html
│   │   └── viewStudents.html
│   │
│   ├── static
│   └── application.properties
```

---

## Quick Execution Checklist

1. Create Spring Starter project with the 4 dependencies
2. Run once with no DB configured — confirm it starts
3. Create the `examdb` database and configure `application.properties`
4. Run again — confirm DB connects with no errors
5. Build `User` → `UserRepository` → `LoginController` → `login.html` / `dashboard.html`
6. Insert a test user row, then test login at `/`
7. Build `Student` → `StudentRepository` → `StudentController` → `addStudent.html` / `viewStudents.html`
8. Test the full flow: login → add student → view list → delete student
