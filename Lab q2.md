Great — let’s build a **complete Spring Boot Movie Management System (CRUD)** from scratch in a **simple, exam-ready format**.

This will include:

- Add Movie
- View Movies
- Delete Movie
- (Optional: Update later)

---

# ✅ 1. Project Structure

```
src/main/java/com/example/demo
│
├── controller
│     └── MovieController.java
│
├── model
│     └── Movie.java
│
├── repository
│     └── MovieRepository.java
│
└── DemoApplication.java

src/main/resources
│
├── templates
│     ├── add-movie.html
│     └── view-movies.html
│
└── application.properties
```

---

# ✅ 2. Entity Class (Movie.java)

```java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String genre;
    private double rating;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
```

---

# ✅ 3. Repository (MovieRepository.java)

```java
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
```

👉 **Why JpaRepository?**

- No need to write SQL
- Built-in methods: `save()`, `findAll()`, `deleteById()`

---

# ✅ 4. Controller (MovieController.java)

```java
package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository repo;

    // Home → redirect to add page
    @GetMapping("/")
    public String home() {
        return "redirect:/add";
    }

    // Show Add Form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    // Save Movie
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie) {
        repo.save(movie);
        return "redirect:/view";
    }

    // View Movies
    @GetMapping("/view")
    public String viewMovies(Model model) {
        model.addAttribute("movies", repo.findAll());
        return "view-movies";
    }

    // Delete Movie
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/view";
    }
}
```

---

# ✅ 5. Add Movie Page (add-movie.html)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Add Movie</title>
</head>
<body>

<h2>Add Movie</h2>

<form action="/save" method="post">
    Name: <input type="text" name="name"><br><br>
    Genre: <input type="text" name="genre"><br><br>
    Rating: <input type="number" step="0.1" name="rating"><br><br>

    <button type="submit">Save</button>
</form>

<br>
<a href="/view">View Movies</a>

</body>
</html>
```

---

# ✅ 6. View Movies Page (view-movies.html)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>View Movies</title>
</head>
<body>

<h2>Movie List</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Genre</th>
        <th>Rating</th>
        <th>Action</th>
    </tr>

    <tr th:each="m : ${movies}">
        <td th:text="${m.id}"></td>
        <td th:text="${m.name}"></td>
        <td th:text="${m.genre}"></td>
        <td th:text="${m.rating}"></td>
        <td>
            <a th:href="@{'/delete/' + ${m.id}}">Delete</a>
        </td>
    </tr>
</table>

<br>
<a href="/add">Add New Movie</a>

</body>
</html>
```

---

# ✅ 7. application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/moviedb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.thymeleaf.cache=false
```

---

# ✅ 8. Database (MySQL)

```sql
CREATE DATABASE moviedb;
```

👉 Table will be auto-created.

---

# ✅ 9. Common Errors (VERY IMPORTANT ⚠️)

### ❌ Whitelabel Error (404)

✔ Fix:

- Check URL → `/add` or `/view`
- Check controller mappings

---

### ❌ Template Not Found

✔ Fix:

- HTML must be inside:

```
src/main/resources/templates
```

---

### ❌ Package Error

✔ Fix:

- Base package must be:

```
com.example.demo
```

Everything inside it.

---

# ✅ 10. Flow of Program (VIVA GOLD ⭐)

1. User opens `/add`
2. Form submitted → `/save`
3. Controller → `repo.save()`
4. Data stored in DB
5. Redirect → `/view`
6. Show all movies
7. Click delete → `/delete/{id}`

---

# ✅ 11. Viva Questions (Must Prepare)

- What is `@Entity`?
- What is `@Id`?
- What is `JpaRepository`?
- Difference: `@GetMapping` vs `@PostMapping`
- What is Thymeleaf?
- What is `@Controller`?
- What is Dependency Injection (`@Autowired`)?
- What is MVC flow?

---

# ✅ If you want next:

I can also give:

- ✏️
- 🔐
- 📄

Just say: **“add update feature”** 👍