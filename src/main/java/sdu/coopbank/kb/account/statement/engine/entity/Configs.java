package sdu.coopbank.kb.account.statement.engine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "CONFIGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "PARAM", nullable = false)
    private String param;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "VALTYPE")
    private String valueType;

    @Column(name = "status")
    private int status;
}

