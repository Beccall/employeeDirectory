package employeeDirectory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Gets all employees from the employee directory
     * @return a List of all employees
     */
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    /**
     * Adds a new Employee to the directory
     * @param employee that was created
     * @return Employee that was created
     */
    @PostMapping("/employees")
    public String createEmployee(@RequestBody Employee employee) {

        if (!employee.getRole().equalsIgnoreCase("manager" ) && employee.getManagerId() == null){
            return "ManagerId cannot be null for this role. " + employee.getName() + " was not added.";
        }
        log.info("Adding new employee {}", employee);
        employeeRepository.save(employee);
        return employee.getName() + " was added successfully";
    }
}
