package employees.service;

import employees.repository.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-05T14:54:41+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto map(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setName( employee.getName() );

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> map(List<Employee> employee) {
        if ( employee == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employee.size() );
        for ( Employee employee1 : employee ) {
            list.add( map( employee1 ) );
        }

        return list;
    }
}
