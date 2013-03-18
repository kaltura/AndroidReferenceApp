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
public enum KalturaDropFolderFileErrorCode implements KalturaEnumAsString {
    ERROR_UPDATE_ENTRY ("1"),
    ERROR_ADD_ENTRY ("2"),
    FLAVOR_NOT_FOUND ("3"),
    FLAVOR_MISSING_IN_FILE_NAME ("4"),
    SLUG_REGEX_NO_MATCH ("5"),
    ERROR_READING_FILE ("6"),
    ERROR_DOWNLOADING_FILE ("7"),
    LOCAL_FILE_WRONG_SIZE ("dropFolderXmlBulkUpload.LOCAL_FILE_WRONG_SIZE"),
    LOCAL_FILE_WRONG_CHECKSUM ("dropFolderXmlBulkUpload.LOCAL_FILE_WRONG_CHECKSUM"),
    ERROR_WRITING_TEMP_FILE ("dropFolderXmlBulkUpload.ERROR_WRITING_TEMP_FILE"),
    ERROR_ADDING_BULK_UPLOAD ("dropFolderXmlBulkUpload.ERROR_ADDING_BULK_UPLOAD");

    public String hashCode;

    KalturaDropFolderFileErrorCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaDropFolderFileErrorCode get(String hashCode) {
        if (hashCode.equals("1"))
        {
           return ERROR_UPDATE_ENTRY;
        }
        else 
        if (hashCode.equals("2"))
        {
           return ERROR_ADD_ENTRY;
        }
        else 
        if (hashCode.equals("3"))
        {
           return FLAVOR_NOT_FOUND;
        }
        else 
        if (hashCode.equals("4"))
        {
           return FLAVOR_MISSING_IN_FILE_NAME;
        }
        else 
        if (hashCode.equals("5"))
        {
           return SLUG_REGEX_NO_MATCH;
        }
        else 
        if (hashCode.equals("6"))
        {
           return ERROR_READING_FILE;
        }
        else 
        if (hashCode.equals("7"))
        {
           return ERROR_DOWNLOADING_FILE;
        }
        else 
        if (hashCode.equals("dropFolderXmlBulkUpload.LOCAL_FILE_WRONG_SIZE"))
        {
           return LOCAL_FILE_WRONG_SIZE;
        }
        else 
        if (hashCode.equals("dropFolderXmlBulkUpload.LOCAL_FILE_WRONG_CHECKSUM"))
        {
           return LOCAL_FILE_WRONG_CHECKSUM;
        }
        else 
        if (hashCode.equals("dropFolderXmlBulkUpload.ERROR_WRITING_TEMP_FILE"))
        {
           return ERROR_WRITING_TEMP_FILE;
        }
        else 
        if (hashCode.equals("dropFolderXmlBulkUpload.ERROR_ADDING_BULK_UPLOAD"))
        {
           return ERROR_ADDING_BULK_UPLOAD;
        }
        else 
        {
           return ERROR_UPDATE_ENTRY;
        }
    }
}
