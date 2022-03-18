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
@Table(name = "PRIZE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prize.findAll", query = "SELECT p FROM Prize p")
    , @NamedQuery(name = "Prize.findById", query = "SELECT p FROM Prize p WHERE p.id = :id")
    , @NamedQuery(name = "Prize.findByCategoryNumber", query = "SELECT p FROM Prize p WHERE p.categoryNumber = :categoryNumber")
    , @NamedQuery(name = "Prize.findByDivident", query = "SELECT p FROM Prize p WHERE p.divident = :divident")})
public class Prize implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CATEGORY_NUMBER")
    private int categoryNumber;
    @Basic(optional = false)
    @Column(name = "DIVIDENT")
    private int divident;
    @Basic(optional = false)
    @Column(name = "WINNERS")
    private int winners;
    @Basic(optional = false)
    @Column(name = "PROFIT")
    private int profit;
    @JoinColumn(name = "DRAW_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Draw drawId;

    public Prize() {
    }

    public Prize(Integer id) {
        this.id = id;
    }

    public Prize(Integer id, int categoryNumber, int divident) {
        this.id = id;
        this.categoryNumber = categoryNumber;
        this.divident = divident;
    }

    public Integer getId() {
        return id;
    }
    
    public int getWinners() {
        return winners;
    }

    public int getProfit() {
        return profit;
    }

    public void setWinners(int winners) {
        this.winners = winners;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public int getDivident() {
        return divident;
    }

    public void setDivident(int divident) {
        this.divident = divident;
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
        if (!(object instanceof Prize)) {
            return false;
        }
        Prize other = (Prize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Prize[ id=" + id + " categoryNumber=" + categoryNumber + " "
                + " divident=" + divident + "]";
    }
    
}
