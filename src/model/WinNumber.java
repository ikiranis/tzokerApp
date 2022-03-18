package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "WIN_NUMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WinNumber.findAll", query = "SELECT w FROM WinNumber w")
    , @NamedQuery(name = "WinNumber.findById", query = "SELECT w FROM WinNumber w WHERE w.id = :id")
    , @NamedQuery(name = "WinNumber.findByNumber", query = "SELECT w FROM WinNumber w WHERE w.number = :number")
    , @NamedQuery(name = "WinNumber.findByType", query = "SELECT w FROM WinNumber w WHERE w.type = :type")})
public class WinNumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NUMBER")
    private String number;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private Character type;
    @JoinColumn(name = "DRAW_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Draw drawId;

    public WinNumber() {
    }

    public WinNumber(Integer id) {
        this.id = id;
    }

    public WinNumber(Integer id, String number, Character type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public Draw getDrawId() {
        return drawId;
    }

    public void setDrawId(Draw drawId) {
        this.drawId = drawId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WinNumber)) {
            return false;
        }
        WinNumber other = (WinNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WinNumber[ id=" + id + " number=" + number + " type=" + type + "]";
    }
    
}
