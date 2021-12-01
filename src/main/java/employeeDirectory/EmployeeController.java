package employeeDirectory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    /**
     * Creates an EmployeeController Object
     *
     * @param employeeService the employeeService object
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Saves a new employee to the employee directory
     *
     * @param employee the Employee object that needs saving.
     * @return the successfully saved Employee object or an error message object
     */
    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {

        Object response = employeeService.createNewEmployee(employee);

        if (response instanceof ErrorMessage) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok().body(response);
    }

    /**
     * Gets a list of employees whose salary falls between a specific range
     *
     * @param lowRange  the low range of salary for the list of employees
     * @param highRange the high range of salary for the list of employees
     * @return A list of Employees whose salary falls between a specific range
     */
    @GetMapping("/employees")
    public List<Employee> getEmployeesBySalary(@RequestParam Integer lowRange, @RequestParam Integer highRange) {
        return employeeService.findEmployeesBySalary(lowRange, highRange);
    }

    /**
     * Deletes an employee by id
     *
     * @param employeeId id for the employee that needs to be deleted
     */
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    /**
     * Updates employee details
     *
     * @param employeeId the id for the employee that should be updated
     * @param employee   the new employee with update details
     * @return the newly updated Employee object
     */
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        Object response = employeeService.updateEmployee(employee, employeeId);

        if (response instanceof ErrorMessage) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok().body(response);
    }
}
