/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica01.domain;

/**
 *
 * @author home
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "suculenta")
public class Suculenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suculenta")
    private Integer idSuculenta;

    @NotBlank(message = "El nombre común no puede estar vacío.")
    @Size(max = 100, message = "El nombre común no puede tener más de 100 caracteres.")
    @Column(name = "nombre_comun", nullable = false, length = 100)
    private String nombreComun;

    @NotBlank(message = "El nombre científico no puede estar vacío.")
    @Size(max = 150, message = "El nombre científico no puede tener más de 150 caracteres.")
    @Column(name = "nombre_cientifico", nullable = false, length = 150)
    private String nombreCientifico;

    @NotBlank(message = "La familia no puede estar vacía.")
    @Size(max = 80, message = "La familia no puede tener más de 80 caracteres.")
    @Column(nullable = false, length = 80)
    private String familia;

    @NotBlank(message = "El color principal no puede estar vacío.")
    @Size(max = 50, message = "El color principal no puede tener más de 50 caracteres.")
    @Column(name = "color_principal", nullable = false, length = 50)
    private String colorPrincipal;

    @NotNull(message = "La altura es obligatoria.")
    @DecimalMin(value = "0.01", message = "La altura debe ser mayor que cero.")
    @Digits(integer = 4, fraction = 2, message = "La altura admite hasta 4 enteros y 2 decimales.")
    @Column(name = "altura_cm", nullable = false, precision = 6, scale = 2)
    private BigDecimal alturaCm;

    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0.00", message = "El precio no puede ser negativo.")
    @Digits(integer = 8, fraction = 2, message = "El precio admite hasta 8 enteros y 2 decimales.")
    @Column(name = "precio_estimado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioEstimado;

    @NotBlank(message = "El nivel de riego es obligatorio.")
    @Pattern(regexp = "Bajo|Medio|Alto", message = "El nivel de riego debe ser Bajo, Medio o Alto.")
    @Column(name = "nivel_riego", nullable = false)
    private String nivelRiego;

    @NotBlank(message = "La exposición solar no puede estar vacía.")
    @Size(max = 50, message = "La exposición solar no puede tener más de 50 caracteres.")
    @Column(name = "exposicion_solar", nullable = false, length = 50)
    private String exposicionSolar;

    @NotBlank(message = "La ruta o URL de la imagen no puede estar vacía.")
    @Size(max = 255, message = "La ruta de la imagen no puede tener más de 255 caracteres.")
    @Column(name = "ruta_imagen", nullable = false, length = 255)
    private String rutaImagen;
}
