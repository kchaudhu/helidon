
package io.helidon.presentations.mp.db;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity(name = "Employee")
@Table(name = "EMPLOYEE")
@Access(AccessType.PROPERTY)
@NamedQueries({
        @NamedQuery(name = "getEmployees",
                    query = "SELECT e FROM Employee e"),
        @NamedQuery(name = "getEmployeeByName",
                    query = "SELECT e FROM Employee e WHERE e.name = :name")
})
public class Employee {

    private int id;

    private String name;
    
    private String role;


    public Employee() {
    }

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic(optional = false)
    @Column(name = "ROLE", nullable = false)
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}    
}
