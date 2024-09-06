
package com.example.Pos_System.models;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_users")
public class Users {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int user_id;
    private String fullname;
    private String username;
    private String password;
    private String role;
    private String createby;
    @Column(columnDefinition = "TEXT")
    private Date creDate;
    private  String imageFileName;
}
