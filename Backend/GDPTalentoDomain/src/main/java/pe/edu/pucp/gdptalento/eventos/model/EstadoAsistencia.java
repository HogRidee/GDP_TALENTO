package pe.edu.pucp.gdptalento.eventos.model;


import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "estadoAsistencia")
@XmlEnum
public enum EstadoAsistencia {
    @XmlEnumValue("ASISTIO")
    ASISTIO, 
    @XmlEnumValue("FALTO")
    FALTO;
}
