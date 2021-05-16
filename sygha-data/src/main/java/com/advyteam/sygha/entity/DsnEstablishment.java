package com.advyteam.sygha.entity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "DSN_ESTABLISHMENT")
public class DsnEstablishment {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("nic")
    private String nic;
    @Field("siren")
    private String siren;
    @Field("fraction")
    private String fraction;
    @Field("apet_code")
    private String apetCode;
    @Field("address_1")
    private String address1;
    @Field("address_2")
    private String address2;
    @Field("address_3")
    private String address3;
    @Field("address_4")
    private String address4;
    @Field("staff_count")
    private String staffCount;
    @Field("remuneration_type")
    private String remunerationType;
    @Field("ecart")
    private BigDecimal ecart;
    @Field("horodatage")
    private LocalDateTime horodatage;
    @Field("establishment")
    private Establishment establishment;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getApetCode() {
        return apetCode;
    }

    public void setApetCode(String apetCode) {
        this.apetCode = apetCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(String staffCount) {
        this.staffCount = staffCount;
    }

    public String getRemunerationType() {
        return remunerationType;
    }

    public void setRemunerationType(String remunerationType) {
        this.remunerationType = remunerationType;
    }

    public BigDecimal getEcart() {
        return ecart;
    }

    public void setEcart(BigDecimal ecart) {
        this.ecart = ecart;
    }

    public LocalDateTime getHorodatage() {
        return horodatage;
    }

    public void setHorodatage(LocalDateTime horodatage) {
        this.horodatage = horodatage;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    @Override
    public String toString() {
        return "DsnEstablishment{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", nic='" + nic + '\'' +
                ", siren='" + siren + '\'' +
                ", fraction='" + fraction + '\'' +
                ", apetCode='" + apetCode + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", address4='" + address4 + '\'' +
                ", staffCount='" + staffCount + '\'' +
                ", remunerationType='" + remunerationType + '\'' +
                ", ecart=" + ecart +
                ", horodatage=" + horodatage +
                ", establishment=" + establishment +
                '}';
    }
}
