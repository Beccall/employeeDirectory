package employeeDirectory;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public Object createNewEmployee(Employee employee) {
        if (!employee.getRole().equalsIgnoreCase("manager") && employee.getManagerId() == null)
        {
            ErrorMessage errorMessage = new ErrorMessage();
            return errorMessage;
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesBySalary(Integer lowRange, Integer highRange) {
        return employeeRepository.findBySalaryBetween(lowRange, highRange);
    }

    public Employee updateEmployee(Employee employee, Long employeeId) {
        employee.setId(employeeId);
        return employeeRepository.save(employee);
    }
}
