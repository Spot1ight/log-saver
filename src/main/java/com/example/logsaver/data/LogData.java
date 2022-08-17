package com.example.logsaver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "log_data")
@Getter
@Setter
@NoArgsConstructor
public class LogData implements Serializable {

    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String command;
    @Column
    private String content;
    @Column
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    public LogData(String command, String content) {
        this.command = command;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogData logData = (LogData) o;
        return id.equals(logData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
