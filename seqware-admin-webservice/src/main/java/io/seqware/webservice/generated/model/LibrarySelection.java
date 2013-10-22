/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.seqware.webservice.generated.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author
 * boconnor
 */
@Entity
@Table(name = "library_selection")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "LibrarySelection.findAll", query = "SELECT l FROM LibrarySelection l"),
  @NamedQuery(name = "LibrarySelection.findByLibrarySelectionId", query = "SELECT l FROM LibrarySelection l WHERE l.librarySelectionId = :librarySelectionId"),
  @NamedQuery(name = "LibrarySelection.findByName", query = "SELECT l FROM LibrarySelection l WHERE l.name = :name"),
  @NamedQuery(name = "LibrarySelection.findByDescription", query = "SELECT l FROM LibrarySelection l WHERE l.description = :description")})
public class LibrarySelection implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "library_selection_id")
  private Integer librarySelectionId;
  @Size(max = 2147483647)
  @Column(name = "name")
  private String name;
  @Size(max = 2147483647)
  @Column(name = "description")
  private String description;
  @OneToMany(mappedBy = "librarySelection")
  private Collection<Lane> laneCollection;
  @OneToMany(mappedBy = "selection")
  private Collection<ExperimentLibraryDesign> experimentLibraryDesignCollection;

  public LibrarySelection() {
  }

  public LibrarySelection(Integer librarySelectionId) {
    this.librarySelectionId = librarySelectionId;
  }

  public Integer getLibrarySelectionId() {
    return librarySelectionId;
  }

  public void setLibrarySelectionId(Integer librarySelectionId) {
    this.librarySelectionId = librarySelectionId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @XmlTransient
  public Collection<Lane> getLaneCollection() {
    return laneCollection;
  }

  public void setLaneCollection(Collection<Lane> laneCollection) {
    this.laneCollection = laneCollection;
  }

  @XmlTransient
  public Collection<ExperimentLibraryDesign> getExperimentLibraryDesignCollection() {
    return experimentLibraryDesignCollection;
  }

  public void setExperimentLibraryDesignCollection(Collection<ExperimentLibraryDesign> experimentLibraryDesignCollection) {
    this.experimentLibraryDesignCollection = experimentLibraryDesignCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (librarySelectionId != null ? librarySelectionId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof LibrarySelection)) {
      return false;
    }
    LibrarySelection other = (LibrarySelection) object;
    if ((this.librarySelectionId == null && other.librarySelectionId != null) || (this.librarySelectionId != null && !this.librarySelectionId.equals(other.librarySelectionId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "io.seqware.webservice.model.LibrarySelection[ librarySelectionId=" + librarySelectionId + " ]";
  }
  
}
