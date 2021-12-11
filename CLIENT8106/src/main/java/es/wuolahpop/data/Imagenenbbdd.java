package es.wuolahpop.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the IMAGENENBBDD database table.
 * 
 */
@Entity
@Table(name="IMAGENENBBDD")
// Destacar que el LIKE necesita que el parametro de consulta tenga un % a ambos lados (buscar en google)
@NamedQueries({
	@NamedQuery(name="Imagenenbbdd.findAll", query="SELECT i FROM Imagenenbbdd i"),
	@NamedQuery(name="Imagenenbbdd.findBySimilarTitle", query="SELECT i FROM Imagenenbbdd i where i.titulo LIKE :titulo") })
public class Imagenenbbdd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_imagen")
	private int idImagen;

	@Lob
	private byte[] imagen;

	// Así se pone un campo default
	private String titulo ="sin titulo";

	public Imagenenbbdd() {
	}

	public int getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}