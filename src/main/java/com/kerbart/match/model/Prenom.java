package com.kerbart.match.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by damien on 01/03/2017.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Prenom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;

    @Column
    String prenom;

    @Column
    String prenomSansAccent;

    @JsonIgnore
    @OneToMany(mappedBy = "prenom")
    List<PrenomOccurence> occurences;

    @Column
    Long totalOccurences;

    @Column
    Long totalOccurencesMasculin;

    @Column
    Long totalOccurencesFeminin;

    @Column
    Boolean masculin;

    @Column
    Boolean feminin;

    @Column
    Boolean metaDataExists;

    public Prenom() {
        this.token = UUID.randomUUID().toString();
    }

    public Prenom(String prenom) {
        this();
        this.prenom = prenom;
    }

    @Column
    String token;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenomSansAccent() {
        return prenomSansAccent;
    }

    public void setPrenomSansAccent(String prenomSansAccent) {
        this.prenomSansAccent = prenomSansAccent;
    }

    public List<PrenomOccurence> getOccurences() {
        return occurences;
    }

    public void setOccurences(List<PrenomOccurence> occurences) {
        this.occurences = occurences;
    }

    public Long getTotalOccurences() {
        return totalOccurences;
    }

    public void setTotalOccurences(Long totalOccurences) {
        this.totalOccurences = totalOccurences;
    }

    public Boolean getMasculin() {
        return masculin;
    }

    public void setMasculin(Boolean masculin) {
        this.masculin = masculin;
    }

    public Boolean getFeminin() {
        return feminin;
    }

    public void setFeminin(Boolean feminin) {
        this.feminin = feminin;
    }

    public Long getTotalOccurencesMasculin() {
        return totalOccurencesMasculin;
    }

    public void setTotalOccurencesMasculin(Long totalOccurencesMasculin) {
        this.totalOccurencesMasculin = totalOccurencesMasculin;
    }

    public Long getTotalOccurencesFeminin() {
        return totalOccurencesFeminin;
    }

    public void setTotalOccurencesFeminin(Long totalOccurencesFeminin) {
        this.totalOccurencesFeminin = totalOccurencesFeminin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Boolean getMetaDataExists() {
        return metaDataExists;
    }

    public void setMetaDataExists(Boolean metaDataExists) {
        this.metaDataExists = metaDataExists;
    }

    @Override
    public String toString() {
        return "Prenom{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", prenomSansAccent='" + prenomSansAccent + '\'' +
                ", occurences=" + occurences +
                ", totalOccurences=" + totalOccurences +
                ", masculin=" + masculin +
                ", feminin=" + feminin +
                '}';
    }
}
