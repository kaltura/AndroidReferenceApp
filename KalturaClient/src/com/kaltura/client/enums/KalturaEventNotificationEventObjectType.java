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
 * @date Tue, 09 Apr 13 06:52:58 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaEventNotificationEventObjectType implements KalturaEnumAsString {
    ENTRY ("1"),
    CATEGORY ("2"),
    ASSET ("3"),
    FLAVORASSET ("4"),
    THUMBASSET ("5"),
    KUSER ("8"),
    ACCESSCONTROL ("9"),
    BATCHJOB ("10"),
    BULKUPLOADRESULT ("11"),
    CATEGORYKUSER ("12"),
    CONVERSIONPROFILE2 ("14"),
    FLAVORPARAMS ("15"),
    FLAVORPARAMSCONVERSIONPROFILE ("16"),
    FLAVORPARAMSOUTPUT ("17"),
    GENERICSYNDICATIONFEED ("18"),
    KUSERTOUSERROLE ("19"),
    PARTNER ("20"),
    PERMISSION ("21"),
    PERMISSIONITEM ("22"),
    PERMISSIONTOPERMISSIONITEM ("23"),
    SCHEDULER ("24"),
    SCHEDULERCONFIG ("25"),
    SCHEDULERSTATUS ("26"),
    SCHEDULERWORKER ("27"),
    STORAGEPROFILE ("28"),
    SYNDICATIONFEED ("29"),
    THUMBPARAMS ("31"),
    THUMBPARAMSOUTPUT ("32"),
    UPLOADTOKEN ("33"),
    USERLOGINDATA ("34"),
    USERROLE ("35"),
    WIDGET ("36"),
    CATEGORYENTRY ("37");

    public String hashCode;

    KalturaEventNotificationEventObjectType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaEventNotificationEventObjectType get(String hashCode) {
        if (hashCode.equals("1"))
        {
           return ENTRY;
        }
        else 
        if (hashCode.equals("2"))
        {
           return CATEGORY;
        }
        else 
        if (hashCode.equals("3"))
        {
           return ASSET;
        }
        else 
        if (hashCode.equals("4"))
        {
           return FLAVORASSET;
        }
        else 
        if (hashCode.equals("5"))
        {
           return THUMBASSET;
        }
        else 
        if (hashCode.equals("8"))
        {
           return KUSER;
        }
        else 
        if (hashCode.equals("9"))
        {
           return ACCESSCONTROL;
        }
        else 
        if (hashCode.equals("10"))
        {
           return BATCHJOB;
        }
        else 
        if (hashCode.equals("11"))
        {
           return BULKUPLOADRESULT;
        }
        else 
        if (hashCode.equals("12"))
        {
           return CATEGORYKUSER;
        }
        else 
        if (hashCode.equals("14"))
        {
           return CONVERSIONPROFILE2;
        }
        else 
        if (hashCode.equals("15"))
        {
           return FLAVORPARAMS;
        }
        else 
        if (hashCode.equals("16"))
        {
           return FLAVORPARAMSCONVERSIONPROFILE;
        }
        else 
        if (hashCode.equals("17"))
        {
           return FLAVORPARAMSOUTPUT;
        }
        else 
        if (hashCode.equals("18"))
        {
           return GENERICSYNDICATIONFEED;
        }
        else 
        if (hashCode.equals("19"))
        {
           return KUSERTOUSERROLE;
        }
        else 
        if (hashCode.equals("20"))
        {
           return PARTNER;
        }
        else 
        if (hashCode.equals("21"))
        {
           return PERMISSION;
        }
        else 
        if (hashCode.equals("22"))
        {
           return PERMISSIONITEM;
        }
        else 
        if (hashCode.equals("23"))
        {
           return PERMISSIONTOPERMISSIONITEM;
        }
        else 
        if (hashCode.equals("24"))
        {
           return SCHEDULER;
        }
        else 
        if (hashCode.equals("25"))
        {
           return SCHEDULERCONFIG;
        }
        else 
        if (hashCode.equals("26"))
        {
           return SCHEDULERSTATUS;
        }
        else 
        if (hashCode.equals("27"))
        {
           return SCHEDULERWORKER;
        }
        else 
        if (hashCode.equals("28"))
        {
           return STORAGEPROFILE;
        }
        else 
        if (hashCode.equals("29"))
        {
           return SYNDICATIONFEED;
        }
        else 
        if (hashCode.equals("31"))
        {
           return THUMBPARAMS;
        }
        else 
        if (hashCode.equals("32"))
        {
           return THUMBPARAMSOUTPUT;
        }
        else 
        if (hashCode.equals("33"))
        {
           return UPLOADTOKEN;
        }
        else 
        if (hashCode.equals("34"))
        {
           return USERLOGINDATA;
        }
        else 
        if (hashCode.equals("35"))
        {
           return USERROLE;
        }
        else 
        if (hashCode.equals("36"))
        {
           return WIDGET;
        }
        else 
        if (hashCode.equals("37"))
        {
           return CATEGORYENTRY;
        }
        else 
        {
           return ENTRY;
        }
    }
}
