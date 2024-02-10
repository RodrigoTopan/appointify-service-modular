package usecase;

import usecase.employee.contract.command.CreateEmployee;
import usecase.employee.contract.command.CreatedEmployee;
import usecase.employee.contract.query.FoundEmployee;
import usecase.employee.mapper.EmployeeMapper;
import ports.input.EmployeeInputPort;
import ports.output.repository.CompanyRepository;
import ports.output.repository.EmployeeRepository;
import ports.output.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManagerUseCase implements EmployeeInputPort {
    private final EmployeeMapper employeeMapper;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    public EmployeeManagerUseCase(EmployeeMapper employeeMapper, CompanyRepository companyRepository, EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeMapper = employeeMapper;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreatedEmployee create(CreateEmployee command) {
        var company = companyRepository.findById(command.getCompanyId());
        var user = userRepository.findById(command.getUserId());
        var employee = company.createEmployee(user);
        var savedEmployeeEntity = employeeRepository.save(employee);
        return employeeMapper.employeeToCreateEmployeeCommandResponse(savedEmployeeEntity);
    }

    @Override
    public List<FoundEmployee> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeToFindEmployeeQueryResponse)
                .collect(Collectors.toList());
    }
}