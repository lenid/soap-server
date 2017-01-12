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
package com.netcracker.solutions.etisalat.rvcpe.portal.hook.login.server;

import com.netcracker.solutions.etisalat.rvcpe.portal.hook.login.generatedmodel.*;
import com.netcracker.solutions.etisalat.rvcpe.portal.hook.login.server.model.SoParams;
import com.netcracker.solutions.etisalat.rvcpe.portal.hook.login.server.model.User;
import com.netcracker.solutions.etisalat.rvcpe.portal.hook.login.server.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author lehr0416
 *         Date: 06-Jan-17
 *         Time: 14:06
 */
@Endpoint
public class TokenEndpoint {
    private static final String NAMESPACE_URI = "http://tokenvalidation.sso.onlineservices.etisalat.ae/";

    @Value("${etisalat.sso.soap.user.login}")
    private String login;
    @Value("${etisalat.sso.soap.user.password}")
    private String password;
    @Value("${etisalat.sso.soap.source.channel}")
    private String channel;
    @Value("${etisalat.sso.soap.request.flag}")
    private Integer flag;

    private UserRepository userRepository;

    @Autowired
    public TokenEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateToken")
    @ResponsePayload
    public ValidateTokenResponse getToken(@RequestPayload ValidateToken request) {
        ValidateTokenRequest validateTokenRequest = request.getArg0();
        SoParams params = null;
        try {
            params = TokenUtil.getSoParams(validateTokenRequest.getToken());
        } catch (IllegalStateException ex) {
            return getBadResponse(400, "Bad Request", "Invalid token format");
        }

        User user = null;

        try {
            user = userRepository.getUser(params);
        } catch (HttpClientErrorException ex) {
            return getBadResponse(400, "Bad Request", "No such user");
        } catch (ResourceAccessException ex) {
            Throwable cause = ex.getCause();

            if (cause instanceof UnknownHostException) {
                return getBadResponse(400, "Bad Request", "No such host");
            } else if (cause instanceof ConnectException) {
                return getBadResponse(400, "Bad Request", "Connection refused, check port value");
            }

            return getBadResponse(400, "Bad Request", "Something went wrong!");
        }

        if (!(login.equals(validateTokenRequest.getServiceAccessUsername()) &&
                password.equals(validateTokenRequest.getServiceAccessPassword()))) {
            return getBadResponse(401, "Access Denied", "Password or userName don't match");
        }

        if (!channel.equals(validateTokenRequest.getSourcChannel())) {
            return getBadResponse(401, "Access Denied", "Channel don't match");
        }

        if (!flag.equals(validateTokenRequest.getRequestFlag())) {
            return getBadResponse(401, "Access Denied", "Flag don't match");
        }

        return getValidResponse(user);
    }

    private ValidateTokenResponse getValidResponse(User user) {
        ValidateTokenResponse response = new ValidateTokenResponse();

        response.setReturn(getValidateSSOTokenResponse(user));

        return response;
    }

    private ValidateSSOTokenResponse getValidateSSOTokenResponse(User user) {
        ValidateSSOTokenResponse ssoToken = new ValidateSSOTokenResponse();

        ssoToken.setAccountNo(user.getAccountNumber());
        ssoToken.setStatus("200");
        ssoToken.setUsername(user.getExtUserName());
        ssoToken.setErrorCode("00");
        ssoToken.setErrorMsg("Query Is Valid");
        ssoToken.setAdditionalInfoList(getAdditionalInfoListExtended(user));

        return ssoToken;
    }

    private AdditionalInfoListExtended getAdditionalInfoListExtended(User user) {
        AdditionalInfoListExtended infoList = new AdditionalInfoListExtended();

        infoList.addAttribute("name", user.getExtUserFullName());
        infoList.addAttribute("internetUsername", user.getExtUserName());
        infoList.addAttribute("email", "");

        return infoList;
    }

    private ValidateTokenResponse getBadResponse(Integer status, String errorCode, String errorMsg) {
        ValidateTokenResponse response = new ValidateTokenResponse();
        ValidateSSOTokenResponse ssoToken = new ValidateSSOTokenResponse();

        ssoToken.setStatus(status.toString());
        ssoToken.setErrorCode(errorCode);
        ssoToken.setErrorMsg(errorMsg);

        response.setReturn(ssoToken);

        return response;
    }

    static class AdditionalInfoListExtended extends ValidateSSOTokenResponse.AdditionalInfoList {

        public void addAttribute(String name, String value) {
            AttributeDto attributeDto = new AttributeDto();
            attributeDto.setName(name);
            attributeDto.setValue(value);

            if (additionalInfo == null) {
                additionalInfo = new ArrayList<>();
            }

            additionalInfo.add(attributeDto);

        }

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