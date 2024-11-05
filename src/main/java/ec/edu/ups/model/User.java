package ec.edu.ups.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Usuario", schema = "algoritmosjabank")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "usuario_name", nullable = false, unique = true, length = 30)
    private String name;

    @Column(name = "usuario_clave", nullable = false, length = 30)
    private String clave;

    @Column(name = "usuario_activo", length = 1)
    private String activo;

    @Column(name = "usuario_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", clave=" + clave + ", activo=" + activo + ", fechaRegistro="
				+ fechaRegistro + "]";
	}
    
}
