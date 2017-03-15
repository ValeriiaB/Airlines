package com.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight implements Serializable {
    @Id
    public String id;
    public String date;
    public String directionFrom;
    public String directionTo;
    public Long capacity;
}

