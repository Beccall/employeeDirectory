package employeeDirectory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final String MANAGER_ERROR_MESSAGE = "This role must have an assigned Manager";
    private static final String ROLE_ERROR_MESSAGE = "Only these roles are supported: Manager, Developer, QA Tester";

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

        if(!isManagerValid(employee)) {
            return new ErrorMessage(MANAGER_ERROR_MESSAGE);
        }
        if(!isRoleValid(employee)) {
            return new ErrorMessage(ROLE_ERROR_MESSAGE);
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
    public Object updateEmployee(Employee employee, Long employeeId) {
        employee.setId(employeeId);

        if(!isManagerValid(employee)) {
            return new ErrorMessage(MANAGER_ERROR_MESSAGE);
        }
        if(!isRoleValid(employee)) {
            return new ErrorMessage(ROLE_ERROR_MESSAGE);
        }
        return employeeRepository.save(employee);
    }

    /**
     * Checks if Manager is required
     * @param employee Employee object that is being created
     * @return Returns false if Manager is required but set as null
     */
    private boolean isManagerValid(Employee employee) {

        if (!employee.getRole().equalsIgnoreCase("manager") && employee.getManagerId() == null) {
            return false;
        }
        return true;
    }

    /**
     * Checks if Employee Role is valid
     * @param employee Employee that is being created
     * @return false if emoloyee Role is not developer, manager or qa tester
     */
    private boolean isRoleValid(Employee employee) {

        if (!employee.getRole().equalsIgnoreCase("manager")
                && !employee.getRole().equalsIgnoreCase("developer")
                && !employee.getRole().equalsIgnoreCase("qa tester")) {
            return false;
        }
        return true;
    }


}
