package nono.com.taskbuddybe.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://taskbuddy-git-main-ishimwemoses-projects.vercel.app",
    "https://taskbuddy.vercel.app"
})
public class UserController {

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login(
        @RequestParam String email,
        @RequestParam String password
    ) {
        return ResponseEntity.ok(Map.of(
            "message", "Login endpoint reached",
            "email", email
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
        @RequestBody Map<String, String> body
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of(
                "message", "Register endpoint reached",
                "email", body.getOrDefault("email", "")
            ));
    }
}
