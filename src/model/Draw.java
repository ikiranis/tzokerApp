package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "DRAW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Draw.findAll", query = "SELECT d FROM Draw d")
    , @NamedQuery(name = "Draw.findById", query = "SELECT d FROM Draw d WHERE d.id = :id")
    , @NamedQuery(name = "Draw.findByDrawDate", query = "SELECT d FROM Draw d WHERE d.drawDate = :drawDate")
    , @NamedQuery(name = "Draw.findByDrawDateRange", query = "SELECT d FROM Draw d WHERE (d.drawDate BETWEEN :drawFromDate AND :drawToDate) AND d.gameId=:gameId")
    , @NamedQuery(name = "Draw.findByColumns", query = "SELECT d FROM Draw d WHERE d.columns = :columns")
})
public class Draw implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DRAW_DATE")
    @Temporal(TemporalType.DATE)
    private Date drawDate;
    @Basic(optional = false)
    @Column(name = "COLUMNS")
    private int columns;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drawId")
    private Collection<WinNumber> winNumberCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drawId")
    private Collection<Prize> prizeCollection;
    @JoinColumn(name = "GAME_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Game gameId;

    public Draw() {
    }

    public Draw(Integer id) {
        this.id = id;
    }

    public Draw(Integer id, Date drawDate, int columns) {
        this.id = id;
        this.drawDate = drawDate;
        this.columns = columns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    // Επιστρέφει την ημερομηνία σε... ανθρώπινη μορφή
    public String getFormatedDrawDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(drawDate);
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    @XmlTransient
    public Collection<WinNumber> getWinNumberCollection() {
        return winNumberCollection;
    }

    public void setWinNumberCollection(Collection<WinNumber> winNumberCollection) {
        this.winNumberCollection = winNumberCollection;
    }

    @XmlTransient
    public Collection<Prize> getPrizeCollection() {
        return prizeCollection;
    }

    public void setPrizeCollection(Collection<Prize> prizeCollection) {
        this.prizeCollection = prizeCollection;
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
        if (!(object instanceof Draw)) {
            return false;
        }
        Draw other = (Draw) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Draw[ id=" + id + " drawDate=" + drawDate + " columns="
                + columns + " winNumbers=" + winNumberCollection + " "
                + " prizeCategories=" + prizeCollection + "]";
    }

    // Επιστρέφει τους αριθμούς σε ενιαίο string
    public String getNumbersInString() {
        String numbers = "";

        for (WinNumber number : winNumberCollection) {
            if (number.getType() == '1') {
                numbers = numbers + number.getNumber() + " ";
            } else {
                numbers = numbers + "(" + number.getNumber() + ")";
            }

        }

        return numbers;
    }

}
