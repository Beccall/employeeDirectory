package employeeDirectory;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;
    private String dateOfBirth;
    private String emailAddress;
    private Integer salary;
    private Long managerId;
}
