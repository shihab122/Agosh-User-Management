package com.example.accountuser.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "users",
        indexes = {
                @Index(columnList = "email", name = "users_email"),
        }
)
public class User implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", insertable = false)
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email", unique = true)
        private String email;

        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @Column(name = "date_of_birth")
        private LocalDate dateOfBirth;

        @Column(name = "created_at", updatable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;

        @Column(name = "modified_at")
        @UpdateTimestamp
        private LocalDateTime modifiedAt;

        public User(String firstName, String lastName, String email, LocalDate dateOfBirth) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.dateOfBirth = dateOfBirth;
        }
}
