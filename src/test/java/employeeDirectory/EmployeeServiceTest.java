package employeeDirectory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
    public void before() {
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setRole("developer");
        employee.setManagerId(1L);

        BDDMockito.given(employeeRepository.save(ArgumentMatchers.any())).willReturn(new Employee());
        Object result = employeeService.createNewEmployee(employee);

        Assertions.assertEquals(Employee.class, result.getClass());
    }

    @Test
    public void testCreateEmployeeWithoutManager() {
        Employee employee = new Employee();
        employee.setRole("developer");

        ErrorMessage result = (ErrorMessage) employeeService.createNewEmployee(employee);

        Assertions.assertEquals("This role must have an assigned Manager", result.getErrorMessage());
    }

    @Test
    public void testCreateEmployeeWithIncorrectRole() {
        Employee employee = new Employee();
        employee.setRole("scrum master");
        employee.setManagerId(1L);

        ErrorMessage result = (ErrorMessage) employeeService.createNewEmployee(employee);

        Assertions.assertEquals("Only these roles are supported: Manager, Developer, QA Tester", result.getErrorMessage());
    }
}
