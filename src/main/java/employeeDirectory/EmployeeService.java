package employeeDirectory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    /**
     * Creates the Employee Service Object.
     *
     * @param employeeRepository the employeeRepository object
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Deletes an employee by id
     *
     * @param employeeId the id for the employee that needs to be deleted
     */
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Saves a new employee to the employee directory
     *
     * @param employee the Employee object that needs to be saved.
     * @return the successfully saved Employee object or an error message object
     */
    public Object createNewEmployee(Employee employee) {
        if (!employee.getRole().equalsIgnoreCase("manager") && employee.getManagerId() == null) {
            return new ErrorMessage("This role must have an assigned Manager");
        }
        if (!employee.getRole().equalsIgnoreCase("manager")
                && !employee.getRole().equalsIgnoreCase("developer")
                && !employee.getRole().equalsIgnoreCase("qa tester")) {
            return new ErrorMessage("Only these roles are supported: Manager, Developer, QA Tester");
        }
        return employeeRepository.save(employee);
    }

    /**
     * Gets a list of employees whose salary falls between a specific range
     *
     * @param lowRange  the low range of salary for the list of employees
     * @param highRange the high range of salary for the list of employees
     * @return A list of Employees whose salary falls between a specific range
     */
    public List<Employee> findEmployeesBySalary(Integer lowRange, Integer highRange) {
        return employeeRepository.findBySalaryBetween(lowRange, highRange);
    }

    /**
     * Updates employee details
     *
     * @param employeeId the id for the employee that should be updated
     * @param employee   the new employee with update details
     * @return the newly updated Employee object
     */
    public Employee updateEmployee(Employee employee, Long employeeId) {
        employee.setId(employeeId);
        return employeeRepository.save(employee);
    }
}
