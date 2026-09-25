package com.d1.usuarios;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SpringBootApplication
public class UsuariosApplication {
    public static void main(String[] args) { SpringApplication.run(UsuariosApplication.class, args); }
}

@Data
@Entity
class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
}

interface UsuarioRepository extends JpaRepository<Usuario, Long> {}

@RestController
@RequestMapping("/")
class UsuarioController {
    private final UsuarioRepository repo;
    public UsuarioController(UsuarioRepository repo) { this.repo = repo; }

    @PostMapping
    public Usuario crear(@RequestBody Usuario u) { return repo.save(u); }
    
    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }
}
