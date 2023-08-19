package forum.main.entity;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class User {
    private long id;
    private String username;
    private List<String> keywordList;
}
