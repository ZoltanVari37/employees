package employees.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "emp_name1")
    private String name1;

    @Column(name = "emp_name2")
    private String name2;

    @Column(name = "test3")
    private String test3;

    public Employee(String name) {
        this.name = name;
    }
}
