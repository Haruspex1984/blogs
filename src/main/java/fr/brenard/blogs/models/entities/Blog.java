package fr.brenard.blogs.models.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToOne(mappedBy = "blog", orphanRemoval = true)
    private User user;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Article> articles = new LinkedHashSet<>();

}
