package pl.sternik.as.projekt.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

@Entity
@XmlRootElement
public class Tank {

	@Id
	@NotNull
    private Long id;
	@NotEmpty
    private String name;
	@NotEmpty
    private String country;
	@Size(min = 2, max = 30, message = "Description should be in the range [{min}...{max}]")
    private String description;
	@NotNull
    private Type type;
	@NotNull
    private Date dateOfRelease;
	@NotNull
    private double price;
	@NotNull
	private Status status;


	public static Tank produceTank(Long id, String name, String country, String description, Type type, Date dateOfRelease, double price, Status status) {
		Tank p = new Tank();
		p.id = id;
		p.name = name;
		p.country = country;
		p.description = description;
		p.type = type;
		p.dateOfRelease = dateOfRelease;
		p.price = price;
		p.status = status;
		return p;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Date getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tank tank = (Tank) o;
		return Double.compare(tank.price, price) == 0 &&
				Objects.equals(id, tank.id) &&
				Objects.equals(name, tank.name) &&
				Objects.equals(country, tank.country) &&
				Objects.equals(description, tank.description) &&
				Objects.equals(type, tank.type) &&
				Objects.equals(dateOfRelease, tank.dateOfRelease) &&
				status == tank.status;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, country, description, type, dateOfRelease, price, status);
	}

	@Override
	public String toString() {
		return "Tank{" +
				"id=" + id +
				", name='" + name + '\'' +
				", country='" + country + '\'' +
				", description='" + description + '\'' +
				", type=" + type +
				", dateOfRelease=" + dateOfRelease +
				", price=" + price +
				", status=" + status +
				'}';
	}
}
