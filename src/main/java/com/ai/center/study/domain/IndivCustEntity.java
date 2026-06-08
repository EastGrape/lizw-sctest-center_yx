package com.ai.center.study.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class IndivCustEntity {
    /** indiv_cust_id numeric(14) NOT NULL */
    private BigDecimal indivCustId;

    /** base_cust_id numeric(14) NULL */
    private BigDecimal baseCustId;

    /** cust_name varchar2 NOT NULL */
    private String custName;

    /** cust_address varchar2 NULL */
    private String custAddress;

    /** cust_cert_type numeric(2) NULL */
    private Integer custCertType;

    /** cust_cert_code varchar2 NULL */
    private String custCertCode;

    /** cust_cert_address varchar2 NULL */
    private String custCertAddress;

    /** state varchar2 NULL */
    private String state;

    /** done_code numeric(12) NULL */
    private BigDecimal doneCode;

    /** done_date date NULL */
    private Date doneDate;

    /** effective_date date NULL */
    private Date effectiveDate;

    /** expire_date date NULL */
    private Date expireDate;

    /** region_id varchar2 NULL */
    private String regionId;

    /** county_id varchar2 NULL */
    private String countyId;

    /** phone_number varchar2(30) NULL */
    private String phoneNumber;
}
