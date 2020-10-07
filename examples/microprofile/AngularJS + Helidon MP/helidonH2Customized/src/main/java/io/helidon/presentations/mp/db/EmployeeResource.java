
package io.helidon.presentations.mp.db;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.helidon.microprofile.cors.CrossOrigin;

/**
 * This class implements REST endpoints to interact with Employee. The following
 * operations are supported:
 *
 * <ul>
 * <li>GET /employees: Retrieve list of all employees</li>
 * <li>GET /employees/{id}: Retrieve single employee by ID</li>
 * <li>GET /employees/name/{name}: Retrieve single employee by name</li>
 * <li>DELETE /employee/{id}: Delete a employee by ID</li>
 * <li>POST /employee: Create a new employee</li>
 * </ul>
 *
 */
@Path("employees")
public class EmployeeResource {
    @PersistenceContext(unitName = "test")
    private EntityManager entityManager;

    @OPTIONS
    @CrossOrigin(
        allowMethods = {"PUT", "DELETE", "GET", "POST"},
        value = {"*"}
    	)
    public void optionsForGreeting() {}
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees() {
        return entityManager.createNamedQuery("getEmployees", Employee.class).getResultList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@PathParam("id") String id) {
        Employee pokemon = entityManager.find(Employee.class, Integer.valueOf(id));
        if (pokemon == null) {
            throw new NotFoundException("Unable to find employee with ID " + id);
        }
        return pokemon;
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public int getEmployeeCount() {
    	int count=0;
    	
    	List<Employee> employees = getEmployees();
    	Iterator<Employee> employeeIterator = employees.iterator();
    	
    	while(employeeIterator.hasNext())
    	{
    		employeeIterator.next();
    		count = count+1;
    	}
    	return count;
    } 

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteEmployee(@PathParam("id") String id) {
        Employee employee = getEmployeeById(id);
        entityManager.remove(employee);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeByName(@PathParam("name") String name) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("getEmployeeByName", Employee.class);
        List<Employee> list = query.setParameter("name", name).getResultList();
        if (list.isEmpty()) {
            throw new NotFoundException("Unable to find employee with name " + name);
        }
        return list.get(0);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public void createEmployee(Employee employee) {
        try {
            entityManager.persist(employee);
        } catch (Exception e) {
            throw new BadRequestException("Unable to create employee with ID " + employee.getId());
        }
    }
}
