package co.edu.unbosque.Rutila.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Entity
@Getter
@Setter
@Table(name = "logs")
public class LogsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tables;


    private String operation;

    private String users;


    private String detail;


    private Timestamp dates;



}
