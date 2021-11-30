package employeeDirectory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public Object createEmployee(@RequestBody Employee employee) {
        return employeeService.createNewEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeesBySalary(@RequestParam Integer lowRange, @RequestParam Integer highRange) {
        return employeeService.findEmployeesBySalary(lowRange, highRange);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employee, employeeId);
    }
}
