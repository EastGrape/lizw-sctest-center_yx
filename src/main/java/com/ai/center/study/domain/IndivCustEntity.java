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

    /** cust_status numeric(2) NOT NULL */
    private Integer custStatus;

    /** cust_zipcode varchar2 NULL */
    private String custZipcode;

    /** cust_address varchar2 NULL */
    private String custAddress;

    /** cust_level numeric(2) NULL */
    private Integer custLevel;

    /** cust_cert_type numeric(2) NULL */
    private Integer custCertType;

    /** cust_cert_code varchar2 NULL */
    private String custCertCode;

    /** cust_cert_address varchar2 NULL */
    private String custCertAddress;

    /** cust_cert_expire date NULL */
    private Date custCertExpire;

    /** gender numeric(2) NULL */
    private Integer gender;

    /** lunar numeric(2) NULL */
    private Integer lunar;

    /** birthday date NULL */
    private Date birthday;

    /** occupation numeric(2) NULL */
    private Integer occupation;

    /** real_name_flag numeric(2) NOT NULL */
    private Integer realNameFlag;

    /** contract_no varchar2 NULL */
    private String contractNo;

    /** character_desc varchar2 NULL */
    private String characterDesc;

    /** marry_status numeric(2) NULL */
    private Integer marryStatus;

    /** vip_no varchar2 NULL */
    private String vipNo;

    /** allow_agent numeric(2) NULL */
    private Integer allowAgent;

    /** admin_family_id numeric(12) NULL */
    private BigDecimal adminFamilyId;

    /** entry_chnl numeric(2) NULL */
    private Integer entryChnl;

    /** state varchar2 NULL */
    private String state;

    /** create_op_id numeric(12) NULL */
    private BigDecimal createOpId;

    /** create_org_id numeric(12) NULL */
    private BigDecimal createOrgId;

    /** op_id numeric(12) NULL */
    private BigDecimal opId;

    /** org_id numeric(12) NULL */
    private BigDecimal orgId;

    /** done_code numeric(12) NULL */
    private BigDecimal doneCode;

    /** create_date date NULL */
    private Date createDate;

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

    /** notes varchar2 NULL */
    private String notes;

    /** old_cust_type numeric(2) NULL */
    private Integer oldCustType;

    /** cust_modify_date date NULL */
    private Date custModifyDate;

    /** uni_cust_id varchar2 NULL */
    private String uniCustId;

    /** uni_party_id varchar2 NULL */
    private String uniPartyId;
}
