package com.ntq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "bus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b"),
    @NamedQuery(name = "Bus.findByBusID", query = "SELECT b FROM Bus b WHERE b.busID = :busID"),
    @NamedQuery(name = "Bus.findByPlateNumber", query = "SELECT b FROM Bus b WHERE b.plateNumber = :plateNumber"),
    @NamedQuery(name = "Bus.findByCapacity", query = "SELECT b FROM Bus b WHERE b.capacity = :capacity"),
    @NamedQuery(name = "Bus.findByAvatar", query = "SELECT b FROM Bus b WHERE b.avatar = :avatar"),
    @NamedQuery(name = "Bus.findByCreatedAt", query = "SELECT b FROM Bus b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Bus.findByUpdatedAt", query = "SELECT b FROM Bus b WHERE b.updatedAt = :updatedAt")})
public class Bus implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Capacity")
    private int capacity;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BusID")
    private Integer busID;
    @Size(max = 20)
    @Column(name = "name")
    private String name;
    @Column(name = "PlateNumber")
    private String plateNumber;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    @ManyToOne
    private Category categoryID;
    @JoinColumn(name = "CompanyID", referencedColumnName = "CompanyID")
    @ManyToOne
    private Company companyID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busId")
    @JsonIgnore
    private Set<Booking> bookingSet;
    @OneToMany(mappedBy = "busID")
    @JsonIgnore
    private Set<Trip> tripSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busID")
    @JsonIgnore
    private Set<Comment> commentSet;
    @Transient
    private MultipartFile file;

    public Bus() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Bus(Integer busID) {
        this.busID = busID;
    }

    public Bus(Integer busID, int capacity) {
        this.busID = busID;
        this.capacity = capacity;
    }

    public Integer getBusID() {
        return busID;
    }

    public void setBusID(Integer busID) {
        this.busID = busID;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    @XmlTransient
    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    @XmlTransient
    public Set<Trip> getTripSet() {
        return tripSet;
    }

    public void setTripSet(Set<Trip> tripSet) {
        this.tripSet = tripSet;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (busID != null ? busID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.busID == null && other.busID != null) || (this.busID != null && !this.busID.equals(other.busID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Bus[ busID=" + busID + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

//    public void setCreatedAt(LocalDateTime currentTime) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public void setUpdatedAt(LocalDateTime currentTime) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
