/*

 This software is the confidential information and copyrighted work of
 NetCracker Technology Corp. ("NetCracker") and/or its suppliers and
 is only distributed under the terms of a separate license agreement
 with NetCracker.
 Use of the software is governed by the terms of the license agreement.
 Any use of this software not in accordance with the license agreement
 is expressly prohibited by law, and may result in severe civil
 and criminal penalties. 
 
 Copyright (c) 1995-2017 NetCracker Technology Corp.
 
 All Rights Reserved.
 
*/
/*
 * Copyright 1995-2017 by NetCracker Technology Corp.,
 * University Office Park III
 * 95 Sawyer Road
 * Waltham, MA 02453
 * United States of America
 * All rights reserved.
 */
package com.netcracker.solutions.etisalat.rvcpe.portal.hook.login.server.model;

/**
 * @author lehr0416
 *         Date: 06-Jan-17
 *         Time: 16:18
 */
public class User {
    private String extUserFullName;
    private String extUserName;
    private String extUserPassword;
    private String accountNumber;
    private String timeZone;
    private String cwpSubscriberId;
    private String publicIP;
    private String svlanTag;
    private String cvlanTag;
    private String oltId;
    private String packageID;
    private String customerType;
    private String customerSegmentation;

    public String getExtUserFullName() {
        return extUserFullName;
    }

    public void setExtUserFullName(String extUserFullName) {
        this.extUserFullName = extUserFullName;
    }

    public String getExtUserName() {
        return extUserName;
    }

    public void setExtUserName(String extUserName) {
        this.extUserName = extUserName;
    }

    public String getExtUserPassword() {
        return extUserPassword;
    }

    public void setExtUserPassword(String extUserPassword) {
        this.extUserPassword = extUserPassword;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCwpSubscriberId() {
        return cwpSubscriberId;
    }

    public void setCwpSubscriberId(String cwpSubscriberId) {
        this.cwpSubscriberId = cwpSubscriberId;
    }

    public String getPublicIP() {
        return publicIP;
    }

    public void setPublicIP(String publicIP) {
        this.publicIP = publicIP;
    }

    public String getSvlanTag() {
        return svlanTag;
    }

    public void setSvlanTag(String svlanTag) {
        this.svlanTag = svlanTag;
    }

    public String getCvlanTag() {
        return cvlanTag;
    }

    public void setCvlanTag(String cvlanTag) {
        this.cvlanTag = cvlanTag;
    }

    public String getOltId() {
        return oltId;
    }

    public void setOltId(String oltId) {
        this.oltId = oltId;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerSegmentation() {
        return customerSegmentation;
    }

    public void setCustomerSegmentation(String customerSegmentation) {
        this.customerSegmentation = customerSegmentation;
    }
}
/*
 WITHOUT LIMITING THE FOREGOING, COPYING, REPRODUCTION, REDISTRIBUTION,
 REVERSE ENGINEERING, DISASSEMBLY, DECOMPILATION OR MODIFICATION
 OF THE SOFTWARE IS EXPRESSLY PROHIBITED, UNLESS SUCH COPYING,
 REPRODUCTION, REDISTRIBUTION, REVERSE ENGINEERING, DISASSEMBLY,
 DECOMPILATION OR MODIFICATION IS EXPRESSLY PERMITTED BY THE LICENSE
 AGREEMENT WITH NETCRACKER. 
 
 THIS SOFTWARE IS WARRANTED, IF AT ALL, ONLY AS EXPRESSLY PROVIDED IN
 THE TERMS OF THE LICENSE AGREEMENT, EXCEPT AS WARRANTED IN THE
 LICENSE AGREEMENT, NETCRACKER HEREBY DISCLAIMS ALL WARRANTIES AND
 CONDITIONS WITH REGARD TO THE SOFTWARE, WHETHER EXPRESS, IMPLIED
 OR STATUTORY, INCLUDING WITHOUT LIMITATION ALL WARRANTIES AND
 CONDITIONS OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 TITLE AND NON-INFRINGEMENT.
 
 Copyright (c) 1995-2017 NetCracker Technology Corp.
 
 All Rights Reserved.
*/