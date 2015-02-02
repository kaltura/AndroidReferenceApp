// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2011  Kaltura Inc.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// @ignore
// ===================================================================================================
package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Fri, 17 Aug 12 06:33:26 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaSystemPartnerLimitType implements KalturaEnumAsString {
    ENTRIES ("ENTRIES"),
    MONTHLY_STREAM_ENTRIES ("MONTHLY_STREAM_ENTRIES"),
    MONTHLY_BANDWIDTH ("MONTHLY_BANDWIDTH"),
    PUBLISHERS ("PUBLISHERS"),
    ADMIN_LOGIN_USERS ("ADMIN_LOGIN_USERS"),
    LOGIN_USERS ("LOGIN_USERS"),
    USER_LOGIN_ATTEMPTS ("USER_LOGIN_ATTEMPTS"),
    BULK_SIZE ("BULK_SIZE"),
    MONTHLY_STORAGE ("MONTHLY_STORAGE"),
    MONTHLY_STORAGE_AND_BANDWIDTH ("MONTHLY_STORAGE_AND_BANDWIDTH"),
    END_USERS ("END_USERS"),
    ACCESS_CONTROLS ("ACCESS_CONTROLS");

    public String hashCode;

    KalturaSystemPartnerLimitType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaSystemPartnerLimitType get(String hashCode) {
        if (hashCode.equals("ENTRIES"))
        {
           return ENTRIES;
        }
        else 
        if (hashCode.equals("MONTHLY_STREAM_ENTRIES"))
        {
           return MONTHLY_STREAM_ENTRIES;
        }
        else 
        if (hashCode.equals("MONTHLY_BANDWIDTH"))
        {
           return MONTHLY_BANDWIDTH;
        }
        else 
        if (hashCode.equals("PUBLISHERS"))
        {
           return PUBLISHERS;
        }
        else 
        if (hashCode.equals("ADMIN_LOGIN_USERS"))
        {
           return ADMIN_LOGIN_USERS;
        }
        else 
        if (hashCode.equals("LOGIN_USERS"))
        {
           return LOGIN_USERS;
        }
        else 
        if (hashCode.equals("USER_LOGIN_ATTEMPTS"))
        {
           return USER_LOGIN_ATTEMPTS;
        }
        else 
        if (hashCode.equals("BULK_SIZE"))
        {
           return BULK_SIZE;
        }
        else 
        if (hashCode.equals("MONTHLY_STORAGE"))
        {
           return MONTHLY_STORAGE;
        }
        else 
        if (hashCode.equals("MONTHLY_STORAGE_AND_BANDWIDTH"))
        {
           return MONTHLY_STORAGE_AND_BANDWIDTH;
        }
        else 
        if (hashCode.equals("END_USERS"))
        {
           return END_USERS;
        }
        else 
        if (hashCode.equals("ACCESS_CONTROLS"))
        {
           return ACCESS_CONTROLS;
        }
        else 
        {
           return ENTRIES;
        }
    }
}
